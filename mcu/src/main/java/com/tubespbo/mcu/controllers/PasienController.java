package com.tubespbo.mcu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tubespbo.mcu.beans.LaporanDaftarMCU;
import com.tubespbo.mcu.beans.Pasien;
import com.tubespbo.mcu.services.PaketMCUService;
import com.tubespbo.mcu.services.PasienService;

@Controller
@RequestMapping("/pasien")
public class PasienController {

    @Autowired
    private PasienService pasienService;

    @Autowired
    private PaketMCUService paketMCUService;
    
    @GetMapping("/dashboard")
    public String dashboardPasien(@RequestParam("idPasien") int idPasien, Model model){
        Pasien pasien = pasienService.cariPasienById(idPasien);

        if (pasien == null) {
           return "redirect:/auth/loginPasien";
        }

        model.addAttribute("pasien", pasien);
        model.addAttribute("listPaket", paketMCUService.semuaPaket());
        return "pasien/dashboard";
    }

    @GetMapping("/pilih-paket")
    public String formPilihPaket(@RequestParam("idPasien") int idPasien, Model model){
        Pasien pasien = pasienService.cariPasienById(idPasien);

        if (pasien == null) {
            return "redirect:/auth/loginPasien";
        }

        model.addAttribute("pasien", pasien);
        model.addAttribute("listPaket", paketMCUService.semuaPaket());
        return "pasien/pilih-paket";
    }

    @PostMapping("/daftar-paket")
    public String daftarPaketMCU(@RequestParam("idPasien") int idPasien, @RequestParam("idPaket") int idPaket, Model model){
        Pasien pasien = pasienService.cariPasienById(idPasien);

        if (pasien == null) {
            return "redirect:/auth/loginPasien";
        }

        try {
            LaporanDaftarMCU laporan = pasienService.daftarMCU(idPasien, idPaket);
            model.addAttribute("success", "Berhasil mendaftar ke paket MCU: " + laporan.getPaket().getNamaPaket());
        } catch (Exception e) {
            model.addAttribute("error", "Gagal mendaftar:" + e.getMessage());
        }

        return "redirect:/pasien/dashboard?idPasien=" + idPasien;
    }

    @GetMapping("/logout")
    public String logoutPasien(){
        return "redirect:/auth/loginPasien";
    }

    
}
