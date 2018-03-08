package com.weekseven.demo.Repository;

import com.weekseven.demo.Model.NewsCategory;
import org.springframework.data.repository.CrudRepository;

public interface NewsCategoryRepository extends CrudRepository<NewsCategory,Long> {

}
