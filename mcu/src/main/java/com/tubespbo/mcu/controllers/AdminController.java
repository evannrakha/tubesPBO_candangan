package com.tubespbo.mcu.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tubespbo.mcu.beans.PaketMCU;
import com.tubespbo.mcu.services.LaporanDaftarMCUService;
import com.tubespbo.mcu.services.PaketMCUService;
import com.tubespbo.mcu.services.PasienService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PaketMCUService paketMCUService;

    @Autowired
    private LaporanDaftarMCUService laporanDaftarMCUService;

    @Autowired
    private PasienService pasienService;

    @GetMapping("/dashboard")
    public String adminDashboard(){
        return "admin/dashboard";
    }

    @GetMapping("/paket")
    public String listPaketMCU(Model model){
        model.addAttribute("listPaket", paketMCUService.semuaPaket());
        return "admin/paket";
    }

    @GetMapping("/paket/add")
    public String showAddPaketForm(Model model){
        model.addAttribute("paket", new PaketMCU());
        return "admin/paket_form";
    }

    @PostMapping("/paket/add")
    public String savePaketMCU(@ModelAttribute PaketMCU paket, Model model){
        try{
            paketMCUService.tambahPaketMCU(paket);
            return "redirect:/admin/paket";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "admin/paket_form";
        }

    }

    @GetMapping("/paket/edit/{id}")
    public String showEditPaketForm(@PathVariable int id, Model model){
        PaketMCU paket = paketMCUService.cariPaketById(id);
        model.addAttribute("paket", paket);
        return "admin/editPaket_form";
    }

    @PostMapping("/paket/edit/{id}")
    public String editPaketMCU(@PathVariable int id, @ModelAttribute PaketMCU paket, Model model){
        try{
            paketMCUService.editPaketMCU(id, paket);
            return "redirect:/admin/paket";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "admin/editPaket_form";
        }
    }

    @GetMapping("/paket/delete/{id}")
    public String deletePaketMCU(@PathVariable int id){
        paketMCUService.hapusPaketMCU(id);
        return "redirect:/admin/paket";
    }

    @GetMapping("/laporan")
    public String viewLaporan(@RequestParam(value = "StartDate", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
    @RequestParam(value = "EndDate", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, 
    @RequestParam(value = "Paket", required = false) String paket, Model model){
        if(startDate != null && endDate != null){
            model.addAttribute("listLaporan", laporanDaftarMCUService.laporanByPeriode(startDate, endDate));
        } else if(paket != null){
            model.addAttribute("listLaporan", laporanDaftarMCUService.laporanByNamaPaket(paket));
        } else {
            model.addAttribute("listLaporan", laporanDaftarMCUService.semuaLaporan());
        }
        return "admin/laporan";
    }

    @GetMapping("/laporan/pendapatan")
    public String viewPendapatan(@RequestParam(value = "StartDate", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
    @RequestParam(value = "EndDate", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, Model model){
        if(startDate != null && endDate != null){
            model.addAttribute("pendapatan", laporanDaftarMCUService.hitungTotalPendapatanByPeriode(startDate, endDate));
        } else {
            model.addAttribute("pendapatan", 0);
        }
        return "admin/pendapatan";
    }

    @GetMapping("/cari/namaPasien")
    public String cariPasienByNama(@RequestParam(value ="nama", required = false) String nama, Model model){
        if (nama != null && !nama.isEmpty()){
            System.out.println("mencari pasien dengan nama"+nama);
            model.addAttribute("pasien", pasienService.cariPasienByNama(nama));
        }else{
            model.addAttribute("listPasien", pasienService.semuaPasien());
            model.addAttribute("message", "Silahkan masukkan nama pasien untuk mencari.");
        }
       
        return "admin/pasienNama";
    }
    
    @GetMapping("/cari/periodePasien")
    public String cariPasienByPeriode(@RequestParam(value ="startDate", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
    @RequestParam(value = "endDate", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, Model model){
        if (startDate != null && endDate != null){
            model.addAttribute("listPasien", pasienService.cariPasienByPeriode(startDate, endDate));
        }else{
            model.addAttribute("listPasien", pasienService.semuaPasien());
            model.addAttribute("message", "Silahkan masukkan periode tanggal untuk mencari.");
        }
        return "admin/pasienPeriode";
    }

    @GetMapping("/logout")
    public String logoutAdmin(){
        return "redirect:/auth/loginAdmin";
    }

}
