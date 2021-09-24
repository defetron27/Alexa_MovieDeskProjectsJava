package main.java.com.def.max.Models;

import com.google.gson.annotations.SerializedName;
import main.java.com.def.max.Utils.Constants;

import java.util.List;

public class MovieDetails
{
    @SerializedName(Constants.adult)
    private boolean adult;

    @SerializedName(Constants.backdrop_path)
    private String backdrop_path;

    @SerializedName(Constants.belongs_to_collection)
    private MovieDetailBelongsToCollection belongs_to_collection;

    @SerializedName(Constants.budget)
    private Long budget;

    @SerializedName(Constants.genres)
    private List<MovieDetailsGenres> genres;

    @SerializedName(Constants.homepage)
    private String homepage;

    @SerializedName(Constants.id)
    private Integer id;

    @SerializedName(Constants.imdb_id)
    private String imdb_id;

    @SerializedName(Constants.original_language)
    private String original_language;

    @SerializedName(Constants.original_title)
    private String original_title;

    @SerializedName(Constants.overview)
    private String overview;

    @SerializedName(Constants.popularity)
    private Double popularity;

    @SerializedName(Constants.poster_path)
    private String poster_path;

    @SerializedName(Constants.production_companies)
    private List<MovieDetailProductionCompanies> production_companies;

    @SerializedName(Constants.production_countries)
    private List<MovieDetailProductionCountries> production_countries;

    @SerializedName(Constants.release_date)
    private String release_date;

    @SerializedName(Constants.revenue)
    private Long revenue;

    @SerializedName(Constants.runtime)
    private Long runtime;

    @SerializedName(Constants.spoken_languages)
    private List<MovieDetailSpokenLanguages> spoken_languages;

    @SerializedName(Constants.status)
    private String status;

    @SerializedName(Constants.tagline)
    private String tagline;

    @SerializedName(Constants.title)
    private String title;

    @SerializedName(Constants.video)
    private Boolean video;

    @SerializedName(Constants.vote_average)
    private Double vote_average;

    @SerializedName(Constants.vote_count)
    private Integer vote_count;

    public MovieDetails() {
    }

    public MovieDetails(boolean adult, String backdrop_path, MovieDetailBelongsToCollection belongs_to_collection, Long budget, List<MovieDetailsGenres> genres, String homepage, Integer id, String imdb_id, String original_language, String original_title, String overview, Double popularity, String poster_path, List<MovieDetailProductionCompanies> production_companies, List<MovieDetailProductionCountries> production_countries, String release_date, Long revenue, Long runtime, List<MovieDetailSpokenLanguages> spoken_languages, String status, String tagline, String title, Boolean video, Double vote_average, Integer vote_count) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.belongs_to_collection = belongs_to_collection;
        this.budget = budget;
        this.genres = genres;
        this.homepage = homepage;
        this.id = id;
        this.imdb_id = imdb_id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.production_companies = production_companies;
        this.production_countries = production_countries;
        this.release_date = release_date;
        this.revenue = revenue;
        this.runtime = runtime;
        this.spoken_languages = spoken_languages;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path()
    {
        String baseImageUrl = "https://image.tmdb.org/t/p/w500";
        return baseImageUrl + backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public MovieDetailBelongsToCollection getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public void setBelongs_to_collection(MovieDetailBelongsToCollection belongs_to_collection) {
        this.belongs_to_collection = belongs_to_collection;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public List<MovieDetailsGenres> getGenres() {
        return genres;
    }

    public void setGenres(List<MovieDetailsGenres> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        String baseImageUrl = "https://image.tmdb.org/t/p/w500";
        return baseImageUrl + poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public List<MovieDetailProductionCompanies> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<MovieDetailProductionCompanies> production_companies) {
        this.production_companies = production_companies;
    }

    public List<MovieDetailProductionCountries> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<MovieDetailProductionCountries> production_countries) {
        this.production_countries = production_countries;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Long getRevenue() {
        return revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }

    public Long getRuntime() {
        return runtime;
    }

    public void setRuntime(Long runtime) {
        this.runtime = runtime;
    }

    public List<MovieDetailSpokenLanguages> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(List<MovieDetailSpokenLanguages> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }
}
