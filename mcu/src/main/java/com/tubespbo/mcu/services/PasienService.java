package com.tubespbo.mcu.services;

import com.tubespbo.mcu.beans.LaporanDaftarMCU;
import com.tubespbo.mcu.beans.Pasien;
import com.tubespbo.mcu.repositories.LaporanDaftarMCURepository;
import com.tubespbo.mcu.repositories.PaketMCURepository;
import com.tubespbo.mcu.repositories.PasienRepository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasienService {
    @Autowired
    private PasienRepository pasienRepository;

    @Autowired
    private LaporanDaftarMCURepository laporanDaftarMCURepository;

    @Autowired
    private PaketMCURepository paketMCURepository;

    public Pasien tambahPasien(Pasien pasien) {
        return pasienRepository.save(pasien);
    }

    public Pasien cariPasienByNama(String nama) {
        return pasienRepository.findByNama(nama);
    }

    public LaporanDaftarMCU daftarMCU(int idPasien, int idPaket) {
        Pasien pasien = pasienRepository.findById(idPasien).get();
        LaporanDaftarMCU laporanDaftarMCU = new LaporanDaftarMCU();
        laporanDaftarMCU.setNama_pasien(pasien);
        laporanDaftarMCU.setPaket(paketMCURepository.findById(idPaket).get());
        return laporanDaftarMCURepository.save(laporanDaftarMCU);
    }

    public List<Pasien> cariPasienByPaket(String namaPaket) {
        return pasienRepository.findByDaftar_Paket_NamaPaket(namaPaket);
    }

    public List<Pasien> cariPasienByPeriode(Date startDate, Date endDate) {
        return pasienRepository.findByDaftar_TanggalDaftarBetween(startDate, endDate);
    }

    public Pasien cariPasienById(int id) {
        return pasienRepository.findById(id).orElseThrow(() -> new RuntimeException("Pasien tidak ditemukan"));
    }

    public List<Pasien> semuaPasien() {
        return pasienRepository.findAll();
    }
}
