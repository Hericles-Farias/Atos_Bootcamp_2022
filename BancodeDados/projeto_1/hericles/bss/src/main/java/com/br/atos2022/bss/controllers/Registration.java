package com.br.atos2022.bss.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class Registration {
    

    // @Autowired
    // private userService userServ;

    @GetMapping("/create")
    public String showRegistrationForm(){
        
        return "create2";

    }

    // @GetMapping("/a")
    // public String showRegistrationForm2(){
        
    //     return "registration2.html";

    // }


    // @GetMapping("/home")
    // public String showhome(){
    //     return "registration";
    // }

    // @ModelAttribute("user")
    // public user userRegistration(){
    //     user user = new user();
    //     //user.setCurrentPlan(null);
    //     return user;
    // }

    // @GetMapping(path="/register")
    // public @ResponseBody ModelAndView showRegistration(){
    //     ModelAndView modelAndView = new ModelAndView();
    //     modelAndView.setViewName("templates/registration.html");
    //     return modelAndView;    
    // }

    // @GetMapping(path="/register")
    // public String showRegistration(){
    //     return "registration";    
    // }


    // @PostMapping("/register")
    // public String registerUserAccount(@ModelAttribute("user") user user) {
    //     userServ.save(user);
    //     return "redirect:/registration?success";
    // }
    

}
