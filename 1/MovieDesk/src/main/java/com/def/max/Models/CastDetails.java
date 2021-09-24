package main.java.com.def.max.Models;

import com.google.gson.annotations.SerializedName;
import main.java.com.def.max.Utils.Constants;

import java.util.List;

public class CastDetails
{
    @SerializedName(Constants.birthday)
    private String birthday;

    @SerializedName(Constants.know_for_department)
    private String know_for_department;

    @SerializedName(Constants.deathday)
    private String deathday;

    @SerializedName(Constants.id)
    private Integer id;

    @SerializedName(Constants.name)
    private String name;

    @SerializedName(Constants.also_known_as)
    private List<String> also_known_as;

    @SerializedName(Constants.gender)
    private int gender;

    @SerializedName(Constants.biography)
    private String biography;

    @SerializedName(Constants.popularity)
    private Double popularity;

    @SerializedName(Constants.place_of_birth)
    private String place_of_birth;

    @SerializedName(Constants.profile_path)
    private String profile_path;

    @SerializedName(Constants.adult)
    private boolean adult;

    @SerializedName(Constants.imdb_id)
    private String imdb_id;

    @SerializedName(Constants.homepage)
    private String homepage;

    public CastDetails() {
    }

    public CastDetails(String birthday, String know_for_department, String deathday, Integer id, String name, List<String> also_known_as, int gender, String biography, Double popularity, String place_of_birth, String profile_path, boolean adult, String imdb_id, String homepage) {
        this.birthday = birthday;
        this.know_for_department = know_for_department;
        this.deathday = deathday;
        this.id = id;
        this.name = name;
        this.also_known_as = also_known_as;
        this.gender = gender;
        this.biography = biography;
        this.popularity = popularity;
        this.place_of_birth = place_of_birth;
        this.profile_path = profile_path;
        this.adult = adult;
        this.imdb_id = imdb_id;
        this.homepage = homepage;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getKnow_for_department() {
        return know_for_department;
    }

    public void setKnow_for_department(String know_for_department) {
        this.know_for_department = know_for_department;
    }

    public String getDeathday() {
        return deathday;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAlso_known_as() {
        return also_known_as;
    }

    public void setAlso_known_as(List<String> also_known_as) {
        this.also_known_as = also_known_as;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public String getProfile_path()
    {
        String baseImageUrl = "https://image.tmdb.org/t/p/w500";
        return baseImageUrl + profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
}
