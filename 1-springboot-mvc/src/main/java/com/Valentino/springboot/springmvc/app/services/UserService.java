package com.Valentino.springboot.springmvc.app.services;

import com.Valentino.springboot.springmvc.app.entities.User;
import com.Valentino.springboot.springmvc.app.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public interface UserService  {
     List<User> findAll();
     Optional<User> findById(Long id);
     User save(User user);
     void remove (Long id);
}
