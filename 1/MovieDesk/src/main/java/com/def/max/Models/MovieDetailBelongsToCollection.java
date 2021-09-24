package main.java.com.def.max.Models;

import com.google.gson.annotations.SerializedName;
import main.java.com.def.max.Utils.Constants;

public class MovieDetailBelongsToCollection
{
    @SerializedName(Constants.id)
    private Integer id;

    @SerializedName(Constants.name)
    private String name;

    @SerializedName(Constants.poster_path)
    private String poster_path;

    @SerializedName(Constants.backdrop_path)
    private String backdrop_path;

    public MovieDetailBelongsToCollection()
    {
    }

    public MovieDetailBelongsToCollection(Integer id, String name, String poster_path, String backdrop_path) {
        this.id = id;
        this.name = name;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
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

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}
