package com.tubespbo.mcu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.tubespbo.mcu.beans.Admin;
import com.tubespbo.mcu.beans.Pasien;
import com.tubespbo.mcu.services.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping({"","/"})
    public String loginPage(){
        return "login";
    }

    @GetMapping("/loginPasien")
    public String loginPasienForm() {
        return "auth/loginPasien";
    }

    @PostMapping("/loginPasien")
    public String loginPasien(@RequestParam String nama, @RequestParam String password, Model model) {
       try {
            Pasien pasien = authService.loginPasien(nama, password);
            model.addAttribute("pasien", pasien);
            return "redirect:/pasien/dashboard";
       } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "auth/loginPasien";
       }
    }

    @GetMapping("/loginAdmin")
    public String loginAdminForm() {
        return "auth/loginAdmin";
    }

    @PostMapping("/loginAdmin")
    public String loginAdmin(@RequestParam String nama, @RequestParam String password, Model model) {
        try {
            Admin admin = authService.loginAdmin(nama, password);
            model.addAttribute("admin", admin);
            return "redirect:/admin/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "auth/loginAdmin";
        }
    }

    @GetMapping("/registerPasien")
    public String registerPasienForm(Model model){
        model.addAttribute("pasien", new Pasien());
        return "auth/registerPasien";
    }

    @PostMapping("/registerPasien")
    public String registerPasien(@ModelAttribute Pasien pasien, Model model){
        try {
            authService.registerPasien(pasien);
            return "redirect:/auth/loginPasien?sucess";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "auth/registerPasien";
        }
    }
}
