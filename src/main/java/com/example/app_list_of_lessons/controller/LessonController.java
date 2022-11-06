package com.example.app_list_of_lessons.controller;

import com.example.app_list_of_lessons.entity.Lesson;
import com.example.app_list_of_lessons.repository.ActionsRepository;
import com.example.app_list_of_lessons.repository.LessonRepository;
import com.example.app_list_of_lessons.service.ActionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Controller
public class LessonController {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ActionsRepository actionsRepository;

    @Autowired
    private ActionsService actionsService;

    @GetMapping({"/list","/"})
    public ModelAndView getAlllessons(){
        log.info("/list -> connection");
        ModelAndView mav = new ModelAndView("list-lessons");
        mav.addObject("lessons", lessonRepository.findAll());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("Пользователем "+ auth.getName() +  "  Просмотрен список дисциплин ");
        actionsService.saveAction(LocalDateTime.now(), "Пользователем "+ auth.getName() +  " Просмотрен список дисциплин ");
        return mav;
    }
    @GetMapping("/addLessonForm")
    public ModelAndView addLessonForm(){
        ModelAndView mav = new ModelAndView("add-lesson-form");
        Lesson lesson = new Lesson();
        mav.addObject("lesson", lesson);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("Пользователем "+ auth.getName() + " Добавлена дисциплина " + lesson.getName());
        actionsService.saveAction(LocalDateTime.now(), "Пользователем "+ auth.getName() + " Добавлена дисциплина " + lesson.getName());
        return mav;
    }

    @PostMapping("/saveLesson")
    public String saveLesson(@ModelAttribute Lesson lesson){
        lessonRepository.save(lesson);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long lessonId){
        ModelAndView mav = new ModelAndView("add-lesson-form");
        Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
        Lesson lesson = new Lesson();
        if (optionalLesson.isPresent()){
            lesson = optionalLesson.get();
        }
        mav.addObject("lesson", lesson);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("Пользователем "+ auth.getName() + " Изменена дисциплина " + lesson.getName() + "  " + lesson.getSpeciality());
        actionsService.saveAction(LocalDateTime.now(), "Пользователем "+ auth.getName() + " Изменена дисциплина " + lesson.getName() + "  " + lesson.getSpeciality());
        return mav;
    }

    @GetMapping("/deleteLesson")
    public String deleteLesson(@RequestParam Long lessonId){
        lessonRepository.deleteById(lessonId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("Пользователем "+ auth.getName() + " Удалена дисциплина " + lessonRepository.findById(lessonId));
        actionsService.saveAction(LocalDateTime.now(), "Пользователем "+ auth.getName() + " Удалена дисциплина №" + lessonId);
        return "redirect:/list";
    }
}
