package com.securit.SpringSecurity.Controller;

import com.securit.SpringSecurity.doa.User;
import com.securit.SpringSecurity.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.AuthenticationException;
@Controller

public class WebController {
    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @GetMapping("/home")
    public String homePage(Model model) {

       model.addAttribute("user",new User());
        return "Login";
    }
@PostMapping("/user")
public String logIn1(@RequestParam String username, @RequestParam String password, Model model) {
    try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        model.addAttribute("Result", "Login Success full.");

        return "profile";
    } catch (AuthenticationException e) {
        model.addAttribute("Result", "Login failed. Enter valid username and password.");
        return "login"; // Stay on the login page with error message
    }
}
    @PostMapping("create")
public String CreateUser(@ModelAttribute User user,Model model)
{
    userService.createUser(user);
    model.addAttribute("Result","User Account Created SuccessFully");
    return "profile";
}
@GetMapping("/logout")
public String creating(Model model)
{
    model.addAttribute("Result","Success Fully Logout");
   return "profile";
}
}

