package com.tubespbo.mcu.beans;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Pasien extends User {
    @OneToMany(mappedBy = "pasien", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LaporanDaftarMCU> daftar = new ArrayList<LaporanDaftarMCU>();

    public Pasien() {
        super();
    }

    public Pasien(int id, String nama, String gender, String password, int umur) {
        super(id, nama, gender, password, umur);
    }

    public List<LaporanDaftarMCU> getDaftar_pendaftaran() {
        return daftar;
    }

}
