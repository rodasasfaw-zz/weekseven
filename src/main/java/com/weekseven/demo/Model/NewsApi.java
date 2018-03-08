package com.weekseven.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsApi {
    private String Status;
    private String totalResults;
    private Article [] articles;

    public NewsApi(String status, String totalResults, Article[] articles) {
        Status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public NewsApi() {
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public Article[] getArticles() {
        return articles;
    }

    public void setArticles(Article[] articles) {
        this.articles = articles;
    }
}
