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


        AppUser user1 = new AppUser("rodas4@gmail.com","rod","password");
       user1.setRoles(Arrays.asList(userRole));
        Item item = new Item("cat", "http://www.catbreedslist.com/uploads/allimg/cat-pictures/Persian-Cat-1.jpg", "pet","white, persian breed","Lost");
        itemRepository.save(item);
        user1.additem(item);
        appUserRepository.save(user1);

        AppUser user2 = new AppUser("sam@gmail.com", "sam","pass");
        user2.setRoles(Arrays.asList(userRole));
        Item item2 = new Item("t-shirt", "https://uniqlo.scene7.com/is/image/UNIQLO/goods_08_180703?$prod$", "cloth","grey color","Lost");
        itemRepository.save(item2);
        user2.additem(item2);
        appUserRepository.save(user2);


        AppUser user3 = new AppUser( "ted@gmail.com", "ted","pass");
        user3.setRoles(Arrays.asList(userRole));
        Item item3 = new Item("watch", "http://www.cartier.com/content/dam/rcq/car/59/07/43/590743.png","other"," cartier,leather strap","Lost" );
        itemRepository.save(item3);
        user3.additem(item3);
        appUserRepository.save(user3);












    }

}