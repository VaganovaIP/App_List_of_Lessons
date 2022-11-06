package com.example.app_list_of_lessons.repository;

import com.example.app_list_of_lessons.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

