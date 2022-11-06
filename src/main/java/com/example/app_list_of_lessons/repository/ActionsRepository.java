package com.example.app_list_of_lessons.repository;

import com.example.app_list_of_lessons.entity.Actions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionsRepository extends JpaRepository<Actions, Long> {

}
