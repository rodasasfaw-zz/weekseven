package com.weekseven.demo.Repository;

import com.weekseven.demo.Model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findAppUserById(long id);
    AppUser findAppUserByUsername(String name);
    AppUser findByRoles(AppUser appUsers);





}
