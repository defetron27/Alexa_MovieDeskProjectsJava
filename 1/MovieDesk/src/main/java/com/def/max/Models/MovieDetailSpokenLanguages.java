package main.java.com.def.max.Models;

import com.google.gson.annotations.SerializedName;
import main.java.com.def.max.Utils.Constants;

public class MovieDetailSpokenLanguages
{
    @SerializedName(Constants.iso_639_1)
    private String iso_639_1;

    @SerializedName(Constants.name)
    private String name;

    public MovieDetailSpokenLanguages()
    {
    }

    public MovieDetailSpokenLanguages(String iso_639_1, String name) {
        this.iso_639_1 = iso_639_1;
        this.name = name;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
