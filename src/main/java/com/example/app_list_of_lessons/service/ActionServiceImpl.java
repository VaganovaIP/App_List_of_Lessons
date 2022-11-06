package com.example.app_list_of_lessons.service;

import com.example.app_list_of_lessons.entity.Actions;
import com.example.app_list_of_lessons.repository.ActionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ActionServiceImpl implements ActionsService {
    private final ActionsRepository actionsRepository;

    @Autowired
    public ActionServiceImpl(ActionsRepository actionsRepository) {
        this.actionsRepository = actionsRepository;
    }

    @Override
    public Actions saveAction(LocalDateTime date_actions, String action) {

        Actions actions = new Actions(date_actions,  action);
        return  actionsRepository.save(actions);
    }
}
