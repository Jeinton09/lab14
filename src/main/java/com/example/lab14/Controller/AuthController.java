package com.example.lab14.Controller;

import com.example.lab14.Models.UserRegisterDTO;
import com.example.lab14.Service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserDetailService userService;

    // Renderiza la página de registro

}
