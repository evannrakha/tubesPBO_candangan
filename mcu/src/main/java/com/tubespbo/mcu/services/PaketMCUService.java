package com.tubespbo.mcu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tubespbo.mcu.beans.PaketMCU;
import com.tubespbo.mcu.repositories.LaporanDaftarMCURepository;
import com.tubespbo.mcu.repositories.PaketMCURepository;

@Service
public class PaketMCUService {
    @Autowired
    private PaketMCURepository paketMCURepository;

    @Autowired
    private LaporanDaftarMCURepository laporanDaftarMCURepository;

    public PaketMCU tambahPaketMCU(PaketMCU paketMCU) {
        return paketMCURepository.save(paketMCU);
    }

    public PaketMCU editPaketMCU(int idPaket, PaketMCU paketBaru){
        PaketMCU paketLama = paketMCURepository.findById(idPaket).orElseThrow(() -> new RuntimeException("Paket MCU tidak ditemukan"));
        paketLama.setNamaPaket(paketBaru.getNamaPaket());
        paketLama.setHargaPaket(paketBaru.getHargaPaket());
        paketLama.setDeskripsi(paketBaru.getDeskripsi());
        return paketMCURepository.save(paketLama);
    }

    public void hapusPaketMCU(int idPaket){
        if(!laporanDaftarMCURepository.findByPaket_NamaPaket(cariPaketById(idPaket).getNamaPaket()).isEmpty()){
            throw new RuntimeException("Paket MCU tidak bisa dihapus karena sudah ada pasien yang mendaftar");
        }
        paketMCURepository.deleteById(idPaket);
    }

   
    public List<PaketMCU> semuaPaket(){
        return paketMCURepository.findAll();
    }

    public PaketMCU cariPaketById(int id){
        return paketMCURepository.findById(id).orElseThrow(() -> new RuntimeException("Paket MCU tidak ditemukan"));
    }
}
