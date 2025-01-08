package com.tubespbo.mcu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tubespbo.mcu.beans.LaporanDaftarMCU;
import java.util.*;

public interface LaporanDaftarMCURepository extends JpaRepository<LaporanDaftarMCU, Integer> {
    public List<LaporanDaftarMCU> findByPasien_Nama(String nama_pasien);
    public List<LaporanDaftarMCU> findByPaket_NamaPaket(String namaPaket);
    public List<LaporanDaftarMCU> findByTanggalDaftarBetween(Date startDate, Date endDate);
}
