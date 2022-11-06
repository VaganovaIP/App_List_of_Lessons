package com.example.app_list_of_lessons.controller;

import com.example.app_list_of_lessons.dto.UserDto;
import com.example.app_list_of_lessons.entity.User;
import com.example.app_list_of_lessons.repository.ActionsRepository;
import com.example.app_list_of_lessons.repository.UserRepository;
import com.example.app_list_of_lessons.service.ActionsService;
import com.example.app_list_of_lessons.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
public class SecurityController {
    @Autowired
    private ActionsRepository actionsRepository;
    @Autowired
    private UserRepository userRepository;
    private UserService userService;
    @Autowired
    private ActionsService actionsService;

    public SecurityController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/index")
    public String home(){
        log.info("Просмотр главной страницы приложения");
        actionsService.saveAction(LocalDateTime.now(), "Просмотр главной страницы приложения");
        return "index";}

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result, Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.reject("email", null, "Этот почтовый адрес уже зарегистрирован");
        }
        if (result.hasErrors()){
            model.addAttribute("user",userDto);
            return "/register";
        }
        userService.saveUser(userDto);
        log.info("Пользователь " + userDto.getEmail() + " зарегистрировался на сайте");
        actionsService.saveAction(LocalDateTime.now(), "Пользователь " + userDto.getEmail() + " зарегистрировался на сайте");
        return "redirect:/register?success";
    }

    @GetMapping("users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users",users);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("Пользователь "+ auth.getName() + " вошел в систему");
        actionsService.saveAction(LocalDateTime.now(), "Пользователь "+ auth.getName() + " вошел в систему");
        log.info("Пользователем "+ auth.getName() + " Просмотрен список пользователей");
        actionsService.saveAction(LocalDateTime.now(), "Пользователем "+ auth.getName() + " Просмотрен список пользователей");
        return "users";
    }
}
