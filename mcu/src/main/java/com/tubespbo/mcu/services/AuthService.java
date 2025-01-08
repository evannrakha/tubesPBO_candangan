package com.tubespbo.mcu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tubespbo.mcu.beans.Admin;
import com.tubespbo.mcu.beans.Pasien;
import com.tubespbo.mcu.repositories.AdminRepository;
import com.tubespbo.mcu.repositories.PasienRepository;

@Service
public class AuthService {
    @Autowired
    private PasienRepository pasienRepository;

    @Autowired
    private AdminRepository adminRepository;

    public Pasien loginPasien(String nama, String password) {
        Pasien pasien = pasienRepository.findByNama(nama);
        if (pasien != null && pasien.getPassword().equals(password)) {
            return pasien;
        }
        throw new IllegalArgumentException("Nama atau password salah");
    }

    public Admin loginAdmin(String nama, String password) {
        Admin admin = adminRepository.findByNama(nama);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        throw new IllegalArgumentException("Nama atau password salah");
    }

    public Pasien registerPasien(Pasien pasien) {
        if (pasienRepository.findByNama(pasien.getNama()) != null) {
            throw new IllegalArgumentException("Nama sudah terdaftar");
        }

        pasien.setPassword(pasien.getPassword());
        return pasienRepository.save(pasien);
    }
}
