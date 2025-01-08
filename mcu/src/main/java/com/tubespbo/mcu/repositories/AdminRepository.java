package com.tubespbo.mcu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tubespbo.mcu.beans.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    public Admin findByNama(String nama);
}
