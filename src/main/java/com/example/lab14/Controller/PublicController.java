package com.example.lab14.Controller;

import com.example.lab14.Models.UserRegisterDTO;
import com.example.lab14.Service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    private final UserDetailService userService;

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        return "register";
    }

    // Maneja el envío del formulario de registro
    @PostMapping("/register")
    public String registerUser( @ModelAttribute UserRegisterDTO userRegisterDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Corrige los errores en el formulario");
            return "register";
        }

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
