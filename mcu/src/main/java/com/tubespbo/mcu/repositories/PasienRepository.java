package com.tubespbo.mcu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tubespbo.mcu.beans.Pasien;
import java.util.List;
import java.util.Date;

public interface PasienRepository extends JpaRepository<Pasien, Integer> {
    public Pasien findByNama(String nama);
    public List<Pasien> findByDaftar_Paket_NamaPaket(String namaPaket);
    public List<Pasien> findByDaftar_TanggalDaftarBetween(Date startDate, Date endDate);
}
