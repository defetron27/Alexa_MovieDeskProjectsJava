package main.java.com.def.max.Models;


import com.google.gson.annotations.SerializedName;
import main.java.com.def.max.Utils.Constants;

public class MovieDetailProductionCompanies
{
    @SerializedName(Constants.id)
    private Integer id;

    @SerializedName(Constants.logo_path)
    private String logo_path;

    @SerializedName(Constants.name)
    private String name;

    @SerializedName(Constants.origin_country)
    private String origin_country;

    public MovieDetailProductionCompanies()
    {
    }

    public MovieDetailProductionCompanies(Integer id, String logo_path, String name, String origin_country) {
        this.id = id;
        this.logo_path = logo_path;
        this.name = name;
        this.origin_country = origin_country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogo_path()
    {
        String baseImageUrl = "https://image.tmdb.org/t/p/w500";
        return baseImageUrl + logo_path;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }
}
