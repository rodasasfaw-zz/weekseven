package com.lostandfound.demo.Model;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String image;
    private String category;
    private String description;
    private String status = "Lost";
    @ManyToMany(mappedBy = "items")
    private Set<AppUser> appUsers;

    public Item() {
   this.appUsers = new HashSet<>();
    }

    public Item(String title, String image, String category, String description, String status) {
        this.title = title;
        this.image = image;
        this.category = category;
        this.description = description;
        this.status = status;
        this.appUsers = new HashSet<>();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Set<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(Set<AppUser> appUsers) {
        this.appUsers = appUsers;
    }

//    public void addappusertoItem(AppUser a){
//
//        this.appUsers.add(a);
//    }

}

