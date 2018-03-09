package com.weekseven.demo.Model;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String newsName;
   @ManyToMany(mappedBy = "profiles")
    private Collection<AppUser> appusers;
   private String category;
   private String topic;

    public Profile(){
        this.appusers=new HashSet<>();
    }

    public Profile(String newsName, String category, String topic) {
        this.newsName = newsName;
        this.category = category;
        this.topic = topic;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Collection<AppUser> getAppusers() {
        return appusers;
    }

    public void setAppusers(Collection<AppUser> appusers) {
        this.appusers = appusers;
    }

}
