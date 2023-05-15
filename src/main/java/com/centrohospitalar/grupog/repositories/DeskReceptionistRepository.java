package com.centrohospitalar.grupog.repositories;

import com.centrohospitalar.grupog.entities.DeskReceptionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeskReceptionistRepository extends JpaRepository<DeskReceptionist, Long> {

    DeskReceptionist getByEmail(String email);
    DeskReceptionist getById(Long id);
    DeskReceptionist getDeskReceptionistById(Long id);
}