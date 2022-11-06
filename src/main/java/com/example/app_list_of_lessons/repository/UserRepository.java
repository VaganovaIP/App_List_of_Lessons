package com.example.app_list_of_lessons.repository;

import com.example.app_list_of_lessons.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
