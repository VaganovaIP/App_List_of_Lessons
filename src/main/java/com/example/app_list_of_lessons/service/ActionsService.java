package com.example.app_list_of_lessons.service;

import com.example.app_list_of_lessons.entity.Actions;

import java.time.LocalDateTime;

public interface ActionsService {
    Actions saveAction(LocalDateTime date_actions, String description);
}
