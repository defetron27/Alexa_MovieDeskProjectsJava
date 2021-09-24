package main.java.com.def.max.Models;

import com.google.gson.annotations.SerializedName;
import main.java.com.def.max.Utils.Constants;

import java.util.List;

public class CastResponse
{
    @SerializedName(Constants.page)
    private int page;

    @SerializedName(Constants.total_results)
    private int total_results;

    @SerializedName(Constants.total_pages)
    private int total_pages;

    @SerializedName(Constants.results)
    private List<CastResults> results;

    public CastResponse() {
    }

    public CastResponse(int page, int total_results, int total_pages, List<CastResults> results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<CastResults> getResults() {
        return results;
    }

    public void setResults(List<CastResults> results) {
        this.results = results;
    }
}
