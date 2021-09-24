package main.java.com.def.max.Models;

import com.google.gson.annotations.SerializedName;
import main.java.com.def.max.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class CastKnowFor
{
    @SerializedName(Constants.vote_average)
    private Double vote_average;

    @SerializedName(Constants.vote_count)
    private Integer vote_count;

    @SerializedName(Constants.id)
    private Integer id;

    @SerializedName(Constants.video)
    private boolean video;

    @SerializedName(Constants.media_type)
    private String media_type;

    @SerializedName(Constants.title)
    private String title;

    @SerializedName(Constants.popularity)
    private Double popularity;

    @SerializedName(Constants.poster_path)
    private String poster_path;

    @SerializedName(Constants.original_language)
    private String original_language;

    @SerializedName(Constants.original_title)
    private String original_title;

    @SerializedName(Constants.genre_ids)
    private List<Integer> genre_ids = new ArrayList<>();

    @SerializedName(Constants.backdrop_path)
    private String backdrop_path;

    @SerializedName(Constants.adult)
    private boolean adult;

    @SerializedName(Constants.overview)
    private String overview;

    @SerializedName(Constants.release_date)
    private String release_date;

    public CastKnowFor() {

    }

    public CastKnowFor(Double vote_average, Integer vote_count, Integer id, boolean video, String media_type, String title, Double popularity, String poster_path, String original_language, String original_title, List<Integer> genre_ids, String backdrop_path, boolean adult, String overview, String release_date) {
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.id = id;
        this.video = video;
        this.media_type = media_type;
        this.title = title;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.original_language = original_language;
        this.original_title = original_title;
        this.genre_ids = genre_ids;
        this.backdrop_path = backdrop_path;
        this.adult = adult;
        this.overview = overview;
        this.release_date = release_date;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path()
    {
        String baseImageUrl = "https://image.tmdb.org/t/p/w500";
        return baseImageUrl + poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
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

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
