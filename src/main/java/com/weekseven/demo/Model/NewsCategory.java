package com.weekseven.demo.Model;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class NewsCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String newsName;
   @ManyToMany(mappedBy = "newsCategories")
    private Collection<AppUser> appusers;

    public NewsCategory(){
        this.appusers=new HashSet<>();
    }

    public NewsCategory(String newsName) {
        this.newsName = newsName;
        this.appusers=new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public Collection<AppUser> getAppusers() {
        return appusers;
    }

    public void setAppusers(Collection<AppUser> appusers) {
        this.appusers = appusers;
    }
}
