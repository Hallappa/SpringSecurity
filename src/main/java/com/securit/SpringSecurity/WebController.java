package com.securit.SpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class WebController {
    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String homePage(Model model) {

       model.addAttribute("Result","Welcome");
        return "Login"; // Assuming "Login" is the logical view name for your login page
    }

    @PostMapping("/user")
    public String logIn1(@RequestParam String username, @RequestParam String password, Model model) {
        boolean isValid = userService.checkPassword(username, password);
        if (isValid) {
            model.addAttribute("Result", "Login successful");
            return "profile"; // Redirect to profile page
        } else {
            model.addAttribute("Result", "Login failed Enter Valid User Name And Password");
            return "login"; // Stay on the login page
        }

    }
    @PostMapping("create")
public String CreateUser(@ModelAttribute User user,Model model)
{
    userService.createUser(user);
    model.addAttribute("Result","User Account Created SuccessFully");
    return "profile";
}

}

