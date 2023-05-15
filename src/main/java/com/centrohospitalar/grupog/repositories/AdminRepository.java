package com.centrohospitalar.grupog.repositories;

import com.centrohospitalar.grupog.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin getAdminByEmail(String email);
    Admin getAdminById(Long id);


}
