package main.java.com.def.max.Models;


import com.google.gson.annotations.SerializedName;
import main.java.com.def.max.Utils.Constants;

public class MovieDetailsGenres
{
    @SerializedName(Constants.id)
    private Integer id;

    @SerializedName(Constants.name)
    private String name;

    public MovieDetailsGenres()
    {

    }

    public MovieDetailsGenres(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
