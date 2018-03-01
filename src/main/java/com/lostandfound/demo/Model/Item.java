package com.lostandfound.demo.Model;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String image;
    private String category;
    private String description;
    @ManyToMany
    private Collection<AppUser> appUsers;

    public Item() {
    }

    public Item(String title, String image, String category, String description) {
        this.title = title;
        this.image = image;
        this.category = category;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(Collection<AppUser> appUsers) {
        this.appUsers = appUsers;
    }
    public void addAppUser(AppUser a){
        appUsers.add(a);
    }
}
