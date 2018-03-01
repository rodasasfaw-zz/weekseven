package com.lostandfound.demo.Repository;

import com.lostandfound.demo.Model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository  extends CrudRepository<Role, Long>{
    Role findByRoleName(String roleName);
}
