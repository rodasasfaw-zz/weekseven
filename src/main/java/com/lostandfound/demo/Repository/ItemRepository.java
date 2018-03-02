package com.lostandfound.demo.Repository;

import com.lostandfound.demo.Model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item,Long> {
//    Iterable<Item> findByTitle(Iterable<Item> items);
Item findByTitle(String title);
}
