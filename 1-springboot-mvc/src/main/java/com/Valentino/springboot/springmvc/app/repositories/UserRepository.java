package com.Valentino.springboot.springmvc.app.repositories;

import com.Valentino.springboot.springmvc.app.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
