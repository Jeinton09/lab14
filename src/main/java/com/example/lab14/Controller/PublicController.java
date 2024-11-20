package com.example.lab14.Controller;

import com.example.lab14.Models.UserRegisterDTO;
import com.example.lab14.Service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String showUserRegisterForm(Model model) {
        model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        return "register_user";
    }

    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute  UserRegisterDTO userRegisterDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "register_user";
        }

        try {
            userService.registerUserAsUser(userRegisterDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Usuario registrado exitosamente.");
            return "redirect:/public/auth/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register_user";
        }
    }

    // Registro para administradores
    @GetMapping("/register-admin")
    public String showAdminRegisterForm(Model model) {
        model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        return "register_admin";
    }

    @PostMapping("/register-admin")
    public String registerAdmin(
            @ModelAttribute UserRegisterDTO userRegisterDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "register_admin";
        }

        try {
            userService.registerUserAsAdmin(userRegisterDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Administrador registrado exitosamente.");
            return "redirect:/public/auth/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register_admin";
        }
    }
}
