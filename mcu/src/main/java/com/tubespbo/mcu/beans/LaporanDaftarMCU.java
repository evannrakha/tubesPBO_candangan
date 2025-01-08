package com.tubespbo.mcu.beans;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class LaporanDaftarMCU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_laporan;

    @Temporal(TemporalType.DATE)
    private Date tanggalDaftar;

    @ManyToOne
    @JoinColumn(name = "paket_id", nullable = false)
    private PaketMCU paket;

    @ManyToOne
    @JoinColumn(name = "pasien_id", nullable = false)
    private Pasien pasien;

    public LaporanDaftarMCU() {
    }
    
    public LaporanDaftarMCU(int id_laporan, Date tanggalDaftar, PaketMCU paket, Pasien pasien) {
        this.id_laporan = id_laporan;
        this.tanggalDaftar = tanggalDaftar;
        this.paket = paket;
        this.pasien = pasien;
    }

    public int getId_laporan() {
        return id_laporan;
    }

    public void setId_laporan(int id_laporan) {
        this.id_laporan = id_laporan;
    }

    public Date getTanggal_daftar() {
        return tanggalDaftar;
    }

    public void setTanggal_daftar(Date tanggalDaftar) {
        this.tanggalDaftar = tanggalDaftar;
    }

    public PaketMCU getPaket() {
        return paket;
    }

    public void setPaket(PaketMCU paket) {
        this.paket = paket;
    }

    public Pasien getNama_pasien() {
        return pasien;
    }

    public void setNama_pasien(Pasien pasien) {
        this.pasien = pasien;
    }

}
