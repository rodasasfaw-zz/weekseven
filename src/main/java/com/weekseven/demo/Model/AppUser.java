package com.weekseven.demo.Model;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;
    private String username;
    private String password;

    @ManyToMany(fetch =FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Collection<Role> roles;
    @ManyToMany
    private Collection<NewsCategory> newsCategories;




    public AppUser() {
       this.roles = new HashSet<>();
       this.newsCategories=new HashSet<>();

    }

    public AppUser( String email, String username, String password) {

        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = new HashSet<>();
        this.newsCategories=new HashSet<>();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }



    public void addRole(Role r) {

        roles.add(r);
    }
public void addnewsCategory(NewsCategory n){
        newsCategories.add(n);
}

}

