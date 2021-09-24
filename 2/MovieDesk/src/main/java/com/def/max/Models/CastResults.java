package main.java.com.def.max.Models;

import com.google.gson.annotations.SerializedName;
import main.java.com.def.max.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class CastResults
{
    @SerializedName(Constants.popularity)
    private Double popularity;

    @SerializedName(Constants.id)
    private Integer id;

    @SerializedName(Constants.profile_path)
    private String profile_path;

    @SerializedName(Constants.name)
    private String name;

    @SerializedName(Constants.know_for)
    private List<CastKnowFor> know_for;

    @SerializedName(Constants.adult)
    private boolean adult;

    public CastResults() {
    }

    public CastResults(Double popularity, Integer id, String profile_path, String name, List<CastKnowFor> know_for, boolean adult) {
        this.popularity = popularity;
        this.id = id;
        this.profile_path = profile_path;
        this.name = name;
        this.know_for = know_for;
        this.adult = adult;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfile_path()
    {
        String baseImageUrl = "https://image.tmdb.org/t/p/w500";
        return baseImageUrl + profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CastKnowFor> getKnow_for() {
        return know_for;
    }

    public void setKnow_for(List<CastKnowFor> know_for) {
        this.know_for = know_for;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }
}
