package main.java.com.def.max.Models;

import com.google.gson.annotations.SerializedName;
import main.java.com.def.max.Utils.Constants;

public class MovieCrew
{
    @SerializedName(Constants.credit_id)
    private String credit_id;

    @SerializedName(Constants.department)
    private String department;

    @SerializedName(Constants.gender)
    private int gender;

    @SerializedName(Constants.id)
    private Integer id;

    @SerializedName(Constants.job)
    private String job;

    @SerializedName(Constants.name)
    private String name;

    @SerializedName(Constants.profile_path)
    private String profile_path;

    public MovieCrew() {
    }

    public MovieCrew(String credit_id, String department, int gender, Integer id, String job, String name, String profile_path) {
        this.credit_id = credit_id;
        this.department = department;
        this.gender = gender;
        this.id = id;
        this.job = job;
        this.name = name;
        this.profile_path = profile_path;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_path()
    {
        String baseImageUrl = "https://image.tmdb.org/t/p/w500";
        return baseImageUrl + profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }
}
