package com.lostandfound.demo.Repository;

import com.lostandfound.demo.Model.AppUser;
import com.lostandfound.demo.Model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ItemRepository extends CrudRepository<Item,Long> {
 Iterable<Item> findByAppUsers(AppUser appUsers);
 Iterable<Item> findAllByTitleContainingIgnoreCase(String title);
 Iterable<Item> findAllByStatus(String status);


}
