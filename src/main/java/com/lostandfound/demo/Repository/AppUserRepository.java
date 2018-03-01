package com.lostandfound.demo.Repository;

import com.lostandfound.demo.Model.AppUser;
import com.lostandfound.demo.Model.Item;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findAppUserByUsername(String username);

    Iterable<AppUser> findByitemIn(Iterable<Item> items);


}
