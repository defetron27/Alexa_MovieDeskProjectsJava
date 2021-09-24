package main.java.com.def.max;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.*;
import com.google.common.base.Joiner;
import com.google.gson.Gson;
import main.java.com.def.max.Models.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetMovieDetailsSpeechLet implements SpeechletV2
{

    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope)
    {

    }

    @Override
    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope)
    {
        return getWelcomeResponse();
    }

    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope)
    {
        Intent intent = requestEnvelope.getRequest().getIntent();

        String intentName = (intent != null) ? intent.getName() : "";

        if (intentName != null && intentName.equals("GetMovieName"))
        {
            String movieName = intent.getSlot("movie").getValue();

            String apiKey = "0581b7383bf18be60b37e4c87d8511f1";

            try
            {
                String movieUrl = movieName.replaceAll(" ", "+");

                URL url = new URL("https://api.themoviedb.org/3/search/movie?api_key=" + apiKey +"&query=" + movieUrl);

                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

                con.setDoOutput(true);

                con.setRequestMethod("GET");

                con.setRequestProperty("Content-Type", "application/json");

                BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

                StringBuilder stringBuilder = new StringBuilder();

                String output;

                while ((output = br.readLine()) != null)
                {
                    stringBuilder.append(output);
                }

                String names = stringBuilder.toString();

                MovieResponse movieResponse = new Gson().fromJson(names, MovieResponse.class);

                if (movieResponse != null)
                {
                    List<MovieResults> movieResultsList = movieResponse.getResults();

                    if (movieResultsList != null && movieResultsList.size() > 0)
                    {
                        Map<Integer,String> getNames = new HashMap<>();

                        for (MovieResults movieResults : movieResultsList)
                        {
                            String language = movieResults.getOriginal_language();

                            if (language.equals("en"))
                            {
                                getNames.put(movieResults.getId(),movieResults.getOriginal_title());
                            }
                        }

                        if (getNames.size() > 1)
                        {
                            StringBuilder builder = new StringBuilder();

                            ArrayList<String> movieTitles = new ArrayList<>(getNames.values());

                            for (int i=0; i<movieTitles.size(); i++)
                            {
                                if (i == movieTitles.size() - 1)
                                {
                                    builder.append(movieTitles.get(i)).append(" \n");
                                }
                                else
                                {
                                    builder.append(movieTitles.get(i)).append(", \n");
                                }
                            }

                            String movieNames = builder.toString();

                            String cardTitle = "Results using " + "\'" + movieName + "\'" + " keyword : ";

                            String speechText = "I have found " + String.valueOf(getNames.size()) + " movie results. \nPlease choose exact movie title from below ones. \n" + movieNames;

                            String rePrompt = "Please choose exact movie title from below ones. \n" + movieNames;

                            return getSpeechLetResponse(cardTitle,speechText,rePrompt,true);
                        }
                        else
                        {
                            Map.Entry<Integer,String> entry = getNames.entrySet().iterator().next();

                            Integer id = entry.getKey();

                            try
                            {
                                URL urlDetail = new URL("https://api.themoviedb.org/3/movie/" + id + "?api_key=" + apiKey);

                                HttpsURLConnection connection = (HttpsURLConnection) urlDetail.openConnection();

                                connection.setDoOutput(true);

                                connection.setRequestMethod("GET");

                                connection.setRequestProperty("Content-Type", "application/json");

                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));

                                StringBuilder resultBuilder = new StringBuilder();

                                String jsonOutput;

                                while ((jsonOutput = bufferedReader.readLine()) != null)
                                {
                                    resultBuilder.append(jsonOutput);
                                }

                                String details = resultBuilder.toString();

                                MovieDetails movieDetails = new Gson().fromJson(details,MovieDetails.class);

                                if (movieDetails != null)
                                {
                                    StringBuilder readyDetails = new StringBuilder();

                                    String movieTitle = movieDetails.getTitle();
                                    String originalTitle = movieDetails.getOriginal_title();
                                    String originalLanguage = movieDetails.getOriginal_language();

                                    boolean adult = movieDetails.isAdult();

                                    String status = movieDetails.getStatus();
                                    String releaseDate = movieDetails.getRelease_date();

                                    Long runtime = movieDetails.getRuntime();
                                    Long budget = movieDetails.getBudget();
                                    Long revenue = movieDetails.getRevenue();

                                    List<MovieDetailsGenres> movieDetailsGenresList = movieDetails.getGenres();
                                    List<MovieDetailProductionCompanies> movieDetailProductionCompaniesList = movieDetails.getProduction_companies();
                                    List<MovieDetailProductionCountries> movieDetailProductionCountriesList = movieDetails.getProduction_countries();

                                    String overview = movieDetails.getOverview();
                                    final String homepage = movieDetails.getHomepage();

                                    String posterPath = movieDetails.getPoster_path();
                                    String backDropPoster = movieDetails.getBackdrop_path();

                                    if (movieTitle != null)
                                    {
                                        if (movieTitle.length() > 0)
                                        {
                                            readyDetails.append("Movie Title : ").append(movieTitle).append(".  \n");
                                        }
                                    }

                                    if (originalTitle != null)
                                    {
                                        if (originalTitle.length() > 0)
                                        {
                                            readyDetails.append("Original Title : ").append(originalTitle).append(".  \n");
                                        }
                                    }

                                    if (originalLanguage != null)
                                    {
                                        if (originalLanguage.length() > 0)
                                        {
                                            readyDetails.append("Original Language : ").append("English").append(".  \n");
                                        }
                                    }

                                    if (movieDetails.getVote_average() != null)
                                    {
                                        Double ratingAverage = movieDetails.getVote_average();

                                        readyDetails.append("Rating Average : ").append(String.valueOf(ratingAverage)).append(" / 10").append(". \n");

                                    }

                                    if (adult)
                                    {
                                        readyDetails.append("Adult : ").append("Yes").append(". \n");
                                    }
                                    else
                                    {
                                        readyDetails.append("Adult : ").append("No").append(". \n");
                                    }

                                    if (status != null)
                                    {
                                        if (status.length() > 0)
                                        {
                                            readyDetails.append("Status : ").append(status).append(". \n");
                                        }
                                    }

                                    if (releaseDate != null)
                                    {
                                        if (releaseDate.length() > 0)
                                        {
                                            readyDetails.append("Release Date : ").append(releaseDate).append(". \n");
                                        }
                                    }

                                    if (runtime != null)
                                    {
                                        readyDetails.append("Runtime : ").append(String.valueOf(runtime)).append(" min").append(". \n");
                                    }

                                    if (budget != null)
                                    {
                                        readyDetails.append("Budget : ").append(String.valueOf(budget)).append(". \n");
                                    }

                                    if (revenue != null)
                                    {
                                        readyDetails.append("Revenue : ").append(String.valueOf(revenue)).append(". \n");
                                    }

                                    if (movieDetailsGenresList != null && movieDetailsGenresList.size() > 0)
                                    {
                                        StringBuilder genres = new StringBuilder();

                                        for (int i=0; i<movieDetailsGenresList.size(); i++)
                                        {
                                            if (i == movieDetailsGenresList.size()-1)
                                            {
                                                genres.append(movieDetailsGenresList.get(i).getName());
                                            }
                                            else
                                            {
                                                genres.append(movieDetailsGenresList.get(i).getName()).append(",");
                                            }
                                        }

                                        readyDetails.append("Genres : ").append(genres.toString()).append(". \n");
                                    }

                                    if (movieDetailProductionCountriesList != null && movieDetailProductionCountriesList.size() > 0)
                                    {
                                        StringBuilder country = new StringBuilder();

                                        for (int i=0; i<movieDetailProductionCountriesList.size(); i++)
                                        {
                                            if (i == movieDetailProductionCountriesList.size()-1)
                                            {
                                                country.append(movieDetailProductionCountriesList.get(i).getName());
                                            }
                                            else
                                            {
                                                country.append(movieDetailProductionCountriesList.get(i).getName()).append(",");
                                            }
                                        }

                                        readyDetails.append("Production Countries : ").append(country.toString()).append(". \n");
                                    }

                                    if (movieDetailProductionCompaniesList != null && movieDetailProductionCompaniesList.size() > 0)
                                    {
                                        StringBuilder companies = new StringBuilder();

                                        for (int i=0; i<movieDetailProductionCompaniesList.size(); i++)
                                        {
                                            if (i == movieDetailProductionCompaniesList.size()-1)
                                            {
                                                companies.append(movieDetailProductionCompaniesList.get(i).getName());
                                            }
                                            else
                                            {
                                                companies.append(movieDetailProductionCompaniesList.get(i).getName()).append(",");
                                            }
                                        }

                                        readyDetails.append("Production Companies : ").append(companies.toString()).append(". \n");
                                    }

                                    if (overview != null)
                                    {
                                        if (overview.length() > 0)
                                        {
                                            readyDetails.append("Overview : ").append(overview).append(" \n");
                                        }
                                    }

                                    if (homepage != null)
                                    {
                                        if (homepage.length() > 0)
                                        {
                                            readyDetails.append("HomePage : ").append(homepage).append(". \n");
                                        }
                                    }

                                    Image image = new Image();
                                    image.setLargeImageUrl(posterPath);
                                    image.setSmallImageUrl(backDropPoster);

                                    StandardCard standardCard = new StandardCard();
                                    standardCard.setTitle(movieName + " details");
                                    standardCard.setImage(image);
                                    standardCard.setText(readyDetails.toString());

                                    PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
                                    speech.setText(readyDetails.toString() + " \n If you would like to get another movie detail. Simply say Alexa, ask movie desk to say 'some movie name' movie details.");

                                    String rePromptText = "If you would like to get another movie detail. Simply say Alexa, ask movie desk to say 'some movie name' movie details.";

                                    Reprompt reprompt = new Reprompt();

                                    PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
                                    outputSpeech.setText(rePromptText);

                                    reprompt.setOutputSpeech(outputSpeech);

                                    return SpeechletResponse.newAskResponse(speech,reprompt,standardCard);
                                }
                                else
                                {
                                    String cardTitle = movieName + " Results";

                                    String speechText = "I can't find any movie title results using the keyword \"" + movieName + "\". \nPlease, say the correct movie title or try another title.";

                                    String rePrompt = "Please, say the correct movie title or try another title.";

                                    return getSpeechLetResponse(cardTitle,speechText,rePrompt,true);
                                }
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();

                                String cardTitle = movieName + " Results";

                                String speechText = "I can't find any movie title results using the keyword \"" + movieName + "\". \nPlease, say the correct movie title or try another title.";

                                String rePrompt = "Please, say the correct movie title or try another title.";

                                return getSpeechLetResponse(cardTitle,speechText,rePrompt,true);
                            }
                        }
                    }
                    else
                    {
                        String cardTitle = movieName + " Results";

                        String speechText = "I can't find any movie title results using the keyword \"" + movieName + "\". \nPlease, say the correct movie title or try another title.";

                        String rePrompt = "Please, say the correct movie title or try another title.";

                        return getSpeechLetResponse(cardTitle,speechText,rePrompt,true);
                    }
                }
                else
                {
                    String cardTitle = movieName + " Results";

                    String speechText = "I can't find any movie title results using the keyword \"" + movieName + "\". \nPlease, say the correct movie title or try another title.";

                    String rePrompt = "Please, say the correct movie title or try another title.";

                    return getSpeechLetResponse(cardTitle,speechText,rePrompt,true);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();

                String cardTitle = movieName + " Results";

                String speechText = "I can't find any movie title results using the keyword \"" + movieName + "\". \nPlease, say the correct movie title or try another title.";

                String rePrompt = "Please, say the correct movie title or try another title.";

                return getSpeechLetResponse(cardTitle,speechText,rePrompt,true);
            }
        }
        else if (intentName != null && intentName.equals("GetMovieCast"))
        {
            String movieName = intent.getSlot("movieCast").getValue();

            String apiKey = "0581b7383bf18be60b37e4c87d8511f1";

            try
            {
                String movieUrl = movieName.replaceAll(" ", "+");

                URL url = new URL("https://api.themoviedb.org/3/search/movie?api_key=" + apiKey +"&query=" + movieUrl);

                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

                con.setDoOutput(true);

                con.setRequestMethod("GET");

                con.setRequestProperty("Content-Type", "application/json");

                BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

                StringBuilder stringBuilder = new StringBuilder();

                String output;

                while ((output = br.readLine()) != null)
                {
                    stringBuilder.append(output);
                }

                String names = stringBuilder.toString();

                MovieResponse movieResponse = new Gson().fromJson(names,MovieResponse.class);

                if (movieResponse != null)
                {
                    List<MovieResults> movieResultsList = movieResponse.getResults();

                    if (movieResultsList != null && movieResultsList.size() > 0)
                    {
                        Map<Integer, String> getNames = new HashMap<>();

                        for (MovieResults movieResults : movieResultsList)
                        {
                            String language = movieResults.getOriginal_language();

                            if (language.equals("en"))
                            {
                                getNames.put(movieResults.getId(),movieResults.getOriginal_title());
                            }
                        }

                        if (getNames.size() > 1)
                        {
                            StringBuilder builder = new StringBuilder();

                            ArrayList<String> movieTitles = new ArrayList<>(getNames.values());

                            for (int i=0; i<movieTitles.size(); i++)
                            {
                                if (i == movieTitles.size() - 1)
                                {
                                    builder.append(movieTitles.get(i)).append(" \n");
                                }
                                else
                                {
                                    builder.append(movieTitles.get(i)).append(", \n");
                                }
                            }

                            String movieNames = builder.toString();

                            String cardTitle = "Results using " + "\'" + movieName + "\'" + " keyword : ";

                            String speechText = "I have found " + String.valueOf(getNames.size()) + " movie results. Please choose exact movie title from below ones. \n" + movieNames;

                            String rePromptText = " Please choose exact movie title from below ones. \n " + movieNames;

                            return getSpeechLetResponse(cardTitle,speechText,rePromptText,true);
                        }
                        else
                        {
                            Map.Entry<Integer, String> entry = getNames.entrySet().iterator().next();

                            Integer id = entry.getKey();

                            try
                            {
                                URL urlDetail = new URL("https://api.themoviedb.org/3/movie/" + id + "/credits?api_key=" + apiKey);

                                HttpsURLConnection connection = (HttpsURLConnection) urlDetail.openConnection();

                                connection.setDoOutput(true);

                                connection.setRequestMethod("GET");

                                connection.setRequestProperty("Content-Type", "application/json");

                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));

                                StringBuilder resultBuilder = new StringBuilder();

                                String jsonOutput;

                                while ((jsonOutput = bufferedReader.readLine()) != null)
                                {
                                    resultBuilder.append(jsonOutput);
                                }

                                String details = resultBuilder.toString();

                                MovieCastAndCrew movieCastAndCrew = new Gson().fromJson(details,MovieCastAndCrew.class);

                                if (movieCastAndCrew != null)
                                {
                                    List<MovieCast> movieCastList = movieCastAndCrew.getCast();

                                    if (movieCastList != null && movieCastList.size() > 0)
                                    {
                                        StringBuilder castAndCrewBuilder = new StringBuilder();

                                        String movieOriginalName = entry.getValue();

                                        castAndCrewBuilder.append("Movie Title : ").append(movieOriginalName).append(", \n");

                                        int castLimit;

                                        if (movieCastList.size() > 70)
                                        {
                                            castLimit = 70;
                                        }
                                        else
                                        {
                                            castLimit = movieCastList.size();
                                        }

                                        castAndCrewBuilder.append("Cast Count : ").append(String.valueOf(castLimit)).append(", \n");

                                        castAndCrewBuilder.append("Cast Details : ").append("\n");

                                        for (int i=0; i<castLimit; i++)
                                        {
                                            if (i == castLimit - 1)
                                            {
                                                String gender = "" ;

                                                if (movieCastList.get(i).getGender() == 1)
                                                {
                                                    gender = "Female";
                                                }
                                                else if (movieCastList.get(i).getGender() == 2)
                                                {
                                                    gender = "Male";
                                                }

                                                castAndCrewBuilder.append(String.valueOf(castLimit)).append(" . ")
                                                        .append("Name : ").append(movieCastList.get(i).getName()).append(", \n")
                                                        .append("Gender : ").append(gender).append(", \n")
                                                        .append("Character : ").append(movieCastList.get(i).getCharacter()).append(". \n");
                                            }
                                            else
                                            {
                                                String gender = "" ;

                                                if (movieCastList.get(i).getGender() == 1)
                                                {
                                                    gender = "Female";
                                                }
                                                else if (movieCastList.get(i).getGender() == 2)
                                                {
                                                    gender = "Male";
                                                }

                                                castAndCrewBuilder.append(String.valueOf(i + 1)).append(" . ")
                                                        .append("Name : ").append(movieCastList.get(i).getName()).append(", \n")
                                                        .append("Gender : ").append(gender).append(", \n")
                                                        .append("Character : ").append(movieCastList.get(i).getCharacter()).append(". \n");
                                            }
                                        }

                                        String movieCastsAndCrews = castAndCrewBuilder.toString();

                                        String cardTitle = entry.getValue() + " Cast Details : ";

                                        String speechText = movieCastsAndCrews + " \nIf you would like to get another cast details. Simply say Alexa, ask movie desk to say 'some movie name' cast details.";

                                        String rePrompt = "If you would like to get another cast details. Simply say Alexa, ask movie desk to say 'some movie name' cast details.";

                                        return getSpeechLetResponse(cardTitle,speechText,rePrompt,true);
                                    }
                                    else
                                    {
                                        return sendMovieFailedResponse(movieName);
                                    }
                                }
                                else
                                {
                                    return sendMovieFailedResponse(movieName);
                                }
                            }
                            catch (IOException e)
                            {
                                return sendMovieFailedResponse(movieName);
                            }
                        }
                    }
                    else
                    {
                        return sendMovieFailedResponse(movieName);
                    }
                }
                else
                {
                    return sendMovieFailedResponse(movieName);
                }
            }
            catch (IOException e)
            {
                return sendMovieFailedResponse(movieName);
            }
        }
        else if (intentName != null && intentName.equals("GetMovieCrew"))
        {
            String movieName = intent.getSlot("movieCrew").getValue();
            String apiKey = "0581b7383bf18be60b37e4c87d8511f1";

            try
            {
                String movieUrl = movieName.replaceAll(" ", "+");

                URL url = new URL("https://api.themoviedb.org/3/search/movie?api_key=" + apiKey +"&query=" + movieUrl);

                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

                con.setDoOutput(true);

                con.setRequestMethod("GET");

                con.setRequestProperty("Content-Type", "application/json");

                BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

                StringBuilder stringBuilder = new StringBuilder();

                String output;

                while ((output = br.readLine()) != null)
                {
                    stringBuilder.append(output);
                }

                String names = stringBuilder.toString();

                MovieResponse movieResponse = new Gson().fromJson(names,MovieResponse.class);

                if (movieResponse != null)
                {
                    List<MovieResults> movieResultsList = movieResponse.getResults();

                    if (movieResultsList != null && movieResultsList.size() > 0)
                    {
                        Map<Integer, String> getNames = new HashMap<>();

                        for (MovieResults movieResults : movieResultsList)
                        {
                            String language = movieResults.getOriginal_language();

                            if (language.equals("en"))
                            {
                                getNames.put(movieResults.getId(),movieResults.getOriginal_title());
                            }
                        }

                        if (getNames.size() > 1)
                        {
                            StringBuilder builder = new StringBuilder();

                            ArrayList<String> movieTitles = new ArrayList<>(getNames.values());

                            for (int i=0; i<movieTitles.size(); i++)
                            {
                                if (i == movieTitles.size() - 1)
                                {
                                    builder.append(movieTitles.get(i)).append(" \n");
                                }
                                else
                                {
                                    builder.append(movieTitles.get(i)).append(", \n");
                                }
                            }

                            String movieNames = builder.toString();

                            String cardTitle = "Results using " + "\'" + movieName + "\'" + " keyword : ";

                            String speechText = "I have found " + String.valueOf(getNames.size()) + " movie results. \nPlease choose exact movie title from below ones. \n" + movieNames;

                            String rePrompt = "Please choose exact movie title from below ones. \n " + movieNames;

                            return getSpeechLetResponse(cardTitle,speechText,rePrompt,true);
                        }
                        else
                        {
                            Map.Entry<Integer, String> entry = getNames.entrySet().iterator().next();

                            Integer id = entry.getKey();

                            try
                            {
                                URL urlDetail = new URL("https://api.themoviedb.org/3/movie/" + id + "/credits?api_key=" + apiKey);

                                HttpsURLConnection connection = (HttpsURLConnection) urlDetail.openConnection();

                                connection.setDoOutput(true);

                                connection.setRequestMethod("GET");

                                connection.setRequestProperty("Content-Type", "application/json");

                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));

                                StringBuilder resultBuilder = new StringBuilder();

                                String jsonOutput;

                                while ((jsonOutput = bufferedReader.readLine()) != null)
                                {
                                    resultBuilder.append(jsonOutput);
                                }

                                String details = resultBuilder.toString();

                                MovieCastAndCrew movieCastAndCrew = new Gson().fromJson(details,MovieCastAndCrew.class);

                                if (movieCastAndCrew != null)
                                {
                                    List<MovieCrew> movieCrewList = movieCastAndCrew.getCrew();

                                    if (movieCrewList != null && movieCrewList.size() > 0)
                                    {
                                        StringBuilder castAndCrewBuilder = new StringBuilder();

                                        String movieOriginalName = entry.getValue();

                                        castAndCrewBuilder.append("Movie Title : ").append(movieOriginalName).append(", \n");

                                        int crewLimit;

                                        if (movieCrewList.size() > 70)
                                        {
                                            crewLimit = 70;
                                        }
                                        else
                                        {
                                            crewLimit = movieCrewList.size();
                                        }

                                        castAndCrewBuilder.append("Crew Count : ").append(String.valueOf(crewLimit)).append(", \n");

                                        castAndCrewBuilder.append("Crew Details : ").append("\n");

                                        for (int i=0; i<crewLimit; i++)
                                        {
                                            if (i == crewLimit - 1)
                                            {
                                                String gender = "" ;

                                                if (movieCrewList.get(i).getGender() == 1)
                                                {
                                                    gender = "Female";
                                                }
                                                else if (movieCrewList.get(i).getGender() == 2)
                                                {
                                                    gender = "Male";
                                                }

                                                castAndCrewBuilder.append(String.valueOf(crewLimit)).append(" . ")
                                                        .append("Name : ").append(movieCrewList.get(i).getName()).append(", \n")
                                                        .append("Gender : ").append(gender).append(", \n")
                                                        .append("Department : ").append(movieCrewList.get(i).getDepartment()).append(", \n")
                                                        .append("Job : ").append(movieCrewList.get(i).getJob()).append(". \n");
                                            }
                                            else
                                            {
                                                String gender = "" ;

                                                if (movieCrewList.get(i).getGender() == 1)
                                                {
                                                    gender = "Female";
                                                }
                                                else if (movieCrewList.get(i).getGender() == 2)
                                                {
                                                    gender = "Male";
                                                }

                                                castAndCrewBuilder.append(String.valueOf(i + 1)).append(" . ")
                                                        .append("Name : ").append(movieCrewList.get(i).getName()).append(", \n")
                                                        .append("Gender : ").append(gender).append(", \n")
                                                        .append("Department : ").append(movieCrewList.get(i).getDepartment()).append(", \n")
                                                        .append("Job : ").append(movieCrewList.get(i).getJob()).append(". \n");
                                            }
                                        }

                                        String movieCastsAndCrews = castAndCrewBuilder.toString();

                                        String cardTitle = entry.getValue() + " Crew Details : ";

                                        String speechText = movieCastsAndCrews + " \nIf you would like to get another crew details. Simply say Alexa, ask movie desk to say 'some movie name' crew details.";

                                        String rePrompt = "If you would like to get another crew details. Simply say Alexa, ask movie desk to say 'some movie name' crew details.";

                                        return getSpeechLetResponse(cardTitle,speechText,rePrompt,true);
                                    }
                                    else
                                    {
                                        return sendMovieFailedResponse(movieName);
                                    }
                                }
                                else
                                {
                                    return sendMovieFailedResponse(movieName);
                                }
                            }
                            catch (IOException e)
                            {
                                return sendMovieFailedResponse(movieName);
                            }
                        }
                    }
                    else
                    {
                        return sendMovieFailedResponse(movieName);
                    }
                }
                else
                {
                    return sendMovieFailedResponse(movieName);
                }
            }
            catch (IOException e)
            {
                return sendMovieFailedResponse(movieName);
            }
        }
        else if (intentName != null && intentName.equals("GetCastDetail"))
        {
            String personName = intent.getSlot("name").getValue();

            String apiKey = "0581b7383bf18be60b37e4c87d8511f1";

            try
            {
                String movieUrl = personName.replaceAll(" ", "+");

                URL url = new URL("https://api.themoviedb.org/3/search/person?api_key=" + apiKey +"&query=" + movieUrl);

                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

                con.setDoOutput(true);

                con.setRequestMethod("GET");

                con.setRequestProperty("Content-Type", "application/json");

                BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

                StringBuilder stringBuilder = new StringBuilder();

                String output;

                while ((output = br.readLine()) != null)
                {
                    stringBuilder.append(output);
                }

                String names = stringBuilder.toString();

                CastResponse castResponse = new Gson().fromJson(names,CastResponse.class);

                if (castResponse != null)
                {
                    List<CastResults> castResultsList = castResponse.getResults();

                    if (castResultsList != null && castResultsList.size() > 0)
                    {
                        Map<Integer,String> getNames = new HashMap<>();

                        for (CastResults castResults : castResultsList)
                        {
                            getNames.put(castResults.getId(),castResults.getName());
                        }

                        if (getNames.size() > 1)
                        {
                            StringBuilder builder = new StringBuilder();

                            ArrayList<String> personOriginalNames = new ArrayList<>(getNames.values());

                            for (int i=0; i<personOriginalNames.size(); i++)
                            {
                                if (i == personOriginalNames.size() - 1)
                                {
                                    builder.append(personOriginalNames.get(i)).append(" \n");
                                }
                                else
                                {
                                    builder.append(personOriginalNames.get(i)).append(", \n");
                                }
                            }

                            String personNames = builder.toString();

                            String cardTitle = "Results using " + "\'" + personName + "\'" + " keyword : ";

                            String speechText = "I have found " + String.valueOf(getNames.size()) + " person results. \nPlease choose exact person name from below ones. \n" + personNames;

                            String rePromptText = "Please choose exact person name from below ones. \n" + personNames;

                            return getSpeechLetResponse(cardTitle,speechText,rePromptText,true);
                        }
                        else
                        {
                            Map.Entry<Integer,String> entry = getNames.entrySet().iterator().next();

                            Integer id = entry.getKey();

                            try
                            {
                                URL urlDetail = new URL("https://api.themoviedb.org/3/person/" + id + "?api_key=" + apiKey);

                                HttpsURLConnection connection = (HttpsURLConnection) urlDetail.openConnection();

                                connection.setDoOutput(true);

                                connection.setRequestMethod("GET");

                                connection.setRequestProperty("Content-Type", "application/json");

                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));

                                StringBuilder resultBuilder = new StringBuilder();

                                String jsonOutput;

                                while ((jsonOutput = bufferedReader.readLine()) != null)
                                {
                                    resultBuilder.append(jsonOutput);
                                }

                                String details = resultBuilder.toString();

                                CastDetails castDetails = new Gson().fromJson(details,CastDetails.class);

                                if (castDetails != null)
                                {
                                    StringBuilder readyDetails = new StringBuilder();

                                    String name = castDetails.getName();

                                    List<String> alsoKnownAs = castDetails.getAlso_known_as();

                                    String otherName = "-";

                                    if (alsoKnownAs != null && alsoKnownAs.size() > 0)
                                    {
                                        otherName = Joiner.on(", \n").join(alsoKnownAs);
                                    }

                                    String gender = "-";

                                    if (castDetails.getGender() > 0)
                                    {
                                        if (castDetails.getGender() == 1)
                                        {
                                            gender = "Female";
                                        }
                                        else if (castDetails.getGender() == 2)
                                        {
                                            gender = "Male";
                                        }
                                    }

                                    String knowForDepartment = castDetails.getKnow_for_department();
                                    String birthday = castDetails.getBirthday();
                                    String placeOfBirth = castDetails.getPlace_of_birth();

                                    String homepage = castDetails.getHomepage();

                                    String biography = castDetails.getBiography();

                                    String profilePath = castDetails.getProfile_path();


                                    if (name != null)
                                    {
                                        if (name.length() > 0)
                                        {
                                            readyDetails.append("Name : ").append(name).append(".  \n");
                                        }
                                    }

                                    if (otherName.length() > 0)
                                    {
                                        readyDetails.append("Also Known As : ").append(otherName).append(".  \n");
                                    }

                                    readyDetails.append("Gender : ").append(gender).append(".  \n");

                                    if (knowForDepartment != null)
                                    {
                                        if (knowForDepartment.length() > 0)
                                        {
                                            readyDetails.append("Know For Department : ").append(knowForDepartment).append(". \n");
                                        }
                                    }

                                    if (birthday != null)
                                    {
                                        if (birthday.length() > 0)
                                        {
                                            readyDetails.append("Birthday : ").append(birthday).append(". \n");
                                        }
                                    }

                                    if (placeOfBirth != null)
                                    {
                                        readyDetails.append("Place Of Birth : ").append(String.valueOf(placeOfBirth)).append(" min").append(". \n");
                                    }

                                    if (homepage != null)
                                    {
                                        if (homepage.length() > 0)
                                        {
                                            readyDetails.append("Official Site : ").append(homepage).append(". \n");
                                        }
                                    }

                                    if (biography != null)
                                    {
                                        if (biography.length() > 0)
                                        {
                                            readyDetails.append("Biography : ").append(biography).append(" \n");
                                        }
                                    }

                                    Image image = new Image();
                                    image.setLargeImageUrl(profilePath);

                                    StandardCard standardCard = new StandardCard();
                                    standardCard.setTitle(name + " details");
                                    standardCard.setImage(image);
                                    standardCard.setText(readyDetails.toString());

                                    PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
                                    speech.setText(readyDetails.toString() + " \n If you would like to get another cast detail. Simply say Alexa, ask movie desk to say 'some movie name' cast details.");

                                    String rePromptText = "If you would like to get another cast detail. Simply say Alexa, ask movie desk to say 'some movie name' cast details.";

                                    Reprompt reprompt = new Reprompt();

                                    PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
                                    outputSpeech.setText(rePromptText);

                                    reprompt.setOutputSpeech(outputSpeech);

                                    return SpeechletResponse.newAskResponse(speech,reprompt,standardCard);
                                }
                                else
                                {
                                    return sendCastFailedResponse(personName);
                                }
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();

                                return sendCastFailedResponse(personName);
                            }
                        }
                    }
                    else
                    {
                        return sendCastFailedResponse(personName);
                    }
                }
                else
                {
                    return sendCastFailedResponse(personName);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();

                return sendCastFailedResponse(personName);
            }
        }
        else if (intentName != null && intentName.equals("AMAZON.FallbackIntent"))
        {
            return getFallbackResponse();
        }
        else if (intentName != null && intentName.equals("AMAZON.HelpIntent"))
        {
            return getHelpResponse();
        }
        else if (intentName != null && intentName.equals("AMAZON.StopIntent"))
        {
            return getStopOrCancelResponse();
        }
        else if (intentName != null && intentName.equals("AMAZON.CancelIntent"))
        {
            return getStopOrCancelResponse();
        }
        else if (intentName != null && intentName.equals("AMAZON.YesIntent"))
        {
            return getYesResponse();
        }
        else if (intentName != null && intentName.equals("AMAZON.NoIntent"))
        {
            return getNoResponse();
        }
        else
        {
            return getHelpResponse();
        }
    }

    private SpeechletResponse sendMovieFailedResponse(String movieName)
    {
        String cardTitle = movieName + " Results";

        String speechText = "I can't find any movie title results using the keyword \"" + movieName + "\". \nPlease, say the correct movie title or try another title.";

        String rePromptText = "Please, say the correct movie title or try another title.";

        return getSpeechLetResponse(cardTitle,speechText,rePromptText,true);
    }

    private SpeechletResponse sendCastFailedResponse(String personName)
    {
        String cardTitle = personName + " Results";

        String speechText = "I can't find any cast name results using the name \"" + personName + "\". \nPlease, say the correct movie title or try another title.";

        String rePromptText = "Please, say the correct movie title or try another title.";

        return getSpeechLetResponse(cardTitle,speechText,rePromptText,true);
    }

    @Override
    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope)
    {

    }

    private SpeechletResponse getWelcomeResponse()
    {
        String speechText = "Welcome to the Alexa MovieDesk. " +
                "It's a pleasure to talk to you. " +
                "If you want exact movie or cast & crews details please follow instructions below. " +
                "If you want movie details ask \"Alexa, ask movie desk to say 'some movie name' movie details.\" " +
                "Or If you want movie cast or crew details ask \"Alexa, ask movie desk to say 'some movie name' cast or crew details.\". " +
                "I can give the details of that movie or cast & crew details. " +
                "Ok, Now you start to say the movie titles. Also, another thing please, give exact movie title for exact details. \n" +
                "If you want more instructions or help, ask \" Alexa ask movie desk to say help.\"";

        String cardTitle = "Welcome";

        String rePromptText = "If you want more instructions or help, ask \" Alexa ask movie desk to say help.\"";

        return getSpeechLetResponse(cardTitle,speechText,rePromptText,true);
    }

    private SpeechletResponse getFallbackResponse()
    {
        String speechText = "Oops..There was some internal connection or server problem, don't worry. \nYou say that the name again please.";

        String cardTitle = "Server Problem";

        String rePromptText = "You say that the name again please.";

        return getSpeechLetResponse(cardTitle,speechText,rePromptText,true);
    }

    private SpeechletResponse getHelpResponse()
    {
        String speechText = "Hey friend, it pleasure to help you. " +
                "If you have any doubts or you don't know how to ask to 'Alexa', don't worry. \n" +
                "I clarify your doubts. " +
                "I give you some examples to how to ask to 'Alexa' . \n" +
                "Ok, now let's start, you say, \n" +
                "\"Alexa, ask movie desk to say 'some movie name' movie details. \" \n" +
                "\"Alexa, ask movie desk to say 'some movie name' crew details. \" \n" +
                "\"Alexa, ask movie desk to say 'some movie name' cast details. \" \n" +
                "\"Alexa, ask movie desk to say 'some cast name' details. \" \n" +
                "I believe that the above examples are helpful to you. \n" +
                "Ok, now start to say some movie title. ";

        String cardTitle = "Help";

        String rePromptText = "Ok, now start to say some movie title.";

        return getSpeechLetResponse(cardTitle,speechText,rePromptText,true);
    }

    private SpeechletResponse getStopOrCancelResponse()
    {
        String speechText = "Would you like to cancel or stop all the conversations?";

        String cardTitle = "Stop or Cancel";

        return getSpeechLetResponse(cardTitle,speechText,speechText,true);
    }

    private SpeechletResponse getYesResponse()
    {
        String speechText = "Ok, i stopped the all conversations. If you like to speak to me again. Simply you can ask or say \"Alexa, ask movie desk to say \"some movie name\" movie details.";

        return getSpeechLetResponse(speechText,"GoodBye!",speechText,false);
    }

    private SpeechletResponse getNoResponse()
    {
        String speechText = "Ok, don't worry. We can continue the conversation. \nPlease, tell me some movie title to get the details.";

        String cardTitle = "Continue Conversation";

        String rePrompt = "Please, tell me some movie title to get the details.";

        return getSpeechLetResponse(cardTitle,speechText,rePrompt,true);
    }

    private SpeechletResponse getSpeechLetResponse(String cardTitle,String speechText, String repromptText, boolean isAskResponse)
    {
        SimpleCard card = new SimpleCard();
        card.setTitle(cardTitle);
        card.setContent(speechText);

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        if (isAskResponse)
        {
            PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
            repromptSpeech.setText(repromptText);
            Reprompt reprompt = new Reprompt();
            reprompt.setOutputSpeech(repromptSpeech);

            return SpeechletResponse.newAskResponse(speech, reprompt, card);

        }
        else
        {
            return SpeechletResponse.newTellResponse(speech, card);
        }
    }
}