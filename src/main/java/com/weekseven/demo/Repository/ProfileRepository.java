package com.weekseven.demo.Repository;

import com.weekseven.demo.Model.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile,Long> {

}
