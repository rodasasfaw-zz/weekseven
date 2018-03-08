package com.weekseven.demo.DataLoader;


import com.weekseven.demo.Model.AppUser;
import com.weekseven.demo.Model.NewsCategory;
import com.weekseven.demo.Model.Role;
import com.weekseven.demo.Repository.AppUserRepository;
import com.weekseven.demo.Repository.NewsCategoryRepository;
import com.weekseven.demo.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    AppUserRepository appUserRepository;


    @Autowired
    RoleRepository roleRepository;
    @Autowired
    NewsCategoryRepository newsCategoryRepository;


    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data...");

        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

       Role adminRole = roleRepository.findByRoleName("ADMIN");
       Role userRole = roleRepository.findByRoleName("USER");


        AppUser user1 = new AppUser("rodas4@gmail.com","rod","pass");
        user1.setRoles(Arrays.asList(userRole));
        NewsCategory newsCategory=new NewsCategory("sport");
        newsCategoryRepository.save(newsCategory);
        user1.addnewsCategory(newsCategory);
        appUserRepository.save(user1);

        AppUser user2 = new AppUser("sam@gmail.com", "sam","pass");
        user2.setRoles(Arrays.asList(userRole));
        NewsCategory newsCategory2=new NewsCategory("World news");
        newsCategoryRepository.save(newsCategory2);
        user2.addnewsCategory(newsCategory2);
        appUserRepository.save(user2);


        AppUser user3 = new AppUser( "ted@gmail.com", "ted","pass");
        user3.setRoles(Arrays.asList(userRole));
        NewsCategory newsCategory3=new NewsCategory("politics");
        newsCategoryRepository.save(newsCategory3);
        user3.addnewsCategory(newsCategory3);
        appUserRepository.save(user3);














    }

}