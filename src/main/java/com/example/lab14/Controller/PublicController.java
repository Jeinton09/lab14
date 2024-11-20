package com.example.lab14.Controller;

import com.example.lab14.Models.UserRegisterDTO;
import com.example.lab14.Service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    private final UserDetailService userService;

    @GetMapping("/home")
    public String home(){
        return "public home";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        return "register";
    }

    // Maneja el envío del formulario de registro
    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserRegisterDTO userRegisterDTO, Model model) {
        try {
            userService.registerUser(userRegisterDTO);
            model.addAttribute("successMessage", "Usuario registrado exitosamente");
            return "redirect:/public/auth/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }
}
