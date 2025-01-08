package com.tubespbo.mcu.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tubespbo.mcu.beans.LaporanDaftarMCU;
import com.tubespbo.mcu.repositories.LaporanDaftarMCURepository;

@Service
public class LaporanDaftarMCUService {
    @Autowired
    private LaporanDaftarMCURepository laporanDaftarMCURepository;

    public List<LaporanDaftarMCU> laporanByPeriode(Date tanggalAwal, Date tanggalAkhir){
        return laporanDaftarMCURepository.findByTanggalDaftarBetween(tanggalAwal, tanggalAkhir);
    }

    public List<LaporanDaftarMCU> laporanByNamaPaket(String namaPaket){
        return laporanDaftarMCURepository.findByPaket_NamaPaket(namaPaket);
    }

    public List<LaporanDaftarMCU> semuaLaporan(){
        return laporanDaftarMCURepository.findAll();
    }

    public double hitungTotalPendapatanByPeriode(Date tanggalAwal, Date tanggalAkhir){
        List<LaporanDaftarMCU> laporan = laporanByPeriode(tanggalAwal, tanggalAkhir);
        double total = 0;
        for(LaporanDaftarMCU laporanDaftarMCU : laporan){
            total += laporanDaftarMCU.getPaket().getHargaPaket();
        }
        return total;
    }
}
