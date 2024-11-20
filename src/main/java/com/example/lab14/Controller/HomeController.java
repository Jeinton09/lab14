package com.example.lab14.Controller;

import com.example.lab14.Service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final UserDetailService userService;


    @GetMapping("/user")
    public String index(){
        return "usuario";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "Admin";
    }

}
