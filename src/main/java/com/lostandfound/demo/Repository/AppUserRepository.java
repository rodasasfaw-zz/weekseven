package com.lostandfound.demo.Repository;

import com.lostandfound.demo.Model.AppUser;
import com.lostandfound.demo.Model.Item;
import com.lostandfound.demo.Model.Role;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findAppUserById(long id);
    AppUser findAppUserByUsername(String name);
   // AppUser findFirstByItemsAndRolesIs(Item lostitem,Role role);
    AppUser findByRoles(AppUser appUsers);





}
