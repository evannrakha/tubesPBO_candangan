package com.tubespbo.mcu.beans;

import jakarta.persistence.*;


@Entity
public class PaketMCU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_paket;

    private String namaPaket;
    private double hargaPaket;
    private String jenis_pemeriksaan;
    private String deskripsi;

    public PaketMCU() {
    
    }
    
    public PaketMCU(int id_paket, String namaPaket, double hargaPaket, String jenis_pemeriksaan, String deskripsi) {
        this.id_paket = id_paket;
        this.namaPaket = namaPaket;
        this.hargaPaket = hargaPaket;
        this.jenis_pemeriksaan = jenis_pemeriksaan;
        this.deskripsi = deskripsi;
    }

    public int getId_paket() {
        return id_paket;
    }
    public void setId_paket(int id_paket) {
        this.id_paket = id_paket;
    }
    public String getNamaPaket() {
        return namaPaket;
    }
    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }
    public double getHargaPaket() {
        return hargaPaket;
    }
    public void setHargaPaket(double hargaPaket) {
        this.hargaPaket = hargaPaket;
    }
    public String getJenis_pemeriksaan() {
        return jenis_pemeriksaan;
    }
    public void setJenis_pemeriksaan(String jenis_pemeriksaan) {
        this.jenis_pemeriksaan = jenis_pemeriksaan;
    }
    public String getDeskripsi() {
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    

    
}
