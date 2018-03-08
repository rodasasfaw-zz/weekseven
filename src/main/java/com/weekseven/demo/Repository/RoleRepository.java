package com.weekseven.demo.Repository;

import com.weekseven.demo.Model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository  extends CrudRepository<Role, Long>{
    Role findByRoleName(String roleName);
    //AppUser findByapAndAppusers(AppUser appUser);
}
