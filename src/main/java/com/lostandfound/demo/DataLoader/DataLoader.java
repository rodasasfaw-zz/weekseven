package com.lostandfound.demo.DataLoader;


import com.lostandfound.demo.Model.AppUser;
import com.lostandfound.demo.Model.Item;
import com.lostandfound.demo.Model.Role;
import com.lostandfound.demo.Repository.AppUserRepository;
import com.lostandfound.demo.Repository.ItemRepository;
import com.lostandfound.demo.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    RoleRepository roleRepository;


    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data...");

        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRoleName("ADMIN");
        Role userRole = roleRepository.findByRoleName("USER");


        AppUser user1 = new AppUser("rodas","asfaw", "rodas4@gmail.com", "rod","password");
        user1.setRoles(Arrays.asList(userRole));
        appUserRepository.save(user1);

        AppUser user2 = new AppUser("samuel","jack", "sam@gmail.com", "sam","pass");
        user2.setRoles(Arrays.asList(userRole));
        appUserRepository.save(user2);


        AppUser user3 = new AppUser("ted","mosby", "ted@gmail.com", "ted","pass");
        user3.setRoles(Arrays.asList(adminRole));
        appUserRepository.save(user3);

        Item item = new Item("t-shirt", "https://wordans-mxelda46illwc0hq.netdna-ssl.com/files/model_specifications/2016/1/6/189993/189993_big.jpg?1452127037", "cloth","grey color");
        itemRepository.save(item);
        Item item2 = new Item("cat", "http://cdn1-www.cattime.com/assets/uploads/gallery/persian-cats-and-kittens/persian-cats-and-kittens-8.jpg", "pet","white, persian breed");
        itemRepository.save(item2);
        Item item3 = new Item("watch", "https://store.storeimages.cdn-apple.com/4974/as-images.apple.com/is/image/AppleInc/aos/published/images/4/2/42/hermes/42-hermes-fauve-singletour-s3-grid?wid=270&hei=275&fmt=jpeg&qlt=95&op_sharpen=0&resMode=bicub&op_usm=0.5,0.5,0,0&iccEmbed=0&layer=comp&.v=1504647832173","other"," leather strap" );
        itemRepository.save(item3);






    }

}