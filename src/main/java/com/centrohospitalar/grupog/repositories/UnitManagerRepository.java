package com.centrohospitalar.grupog.repositories;
import com.centrohospitalar.grupog.entities.Doctor;
import com.centrohospitalar.grupog.entities.UnitManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UnitManagerRepository extends JpaRepository<UnitManager, Long> {

    UnitManager getByEmail(String email);
    UnitManager getById(Long id);
    UnitManager getUnitManagerById(Long id);

}