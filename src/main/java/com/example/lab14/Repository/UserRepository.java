package com.example.lab14.Repository;

import com.example.lab14.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends CrudRepository<User ,Integer> {
    User findByUsername(String username);
}
