package com.centrohospitalar.grupog.repositories;


import com.centrohospitalar.grupog.entities.DateScalePeriod;
import com.centrohospitalar.grupog.entities.Doctor;
import com.centrohospitalar.grupog.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DateScalePeriodRepository extends JpaRepository<DateScalePeriod, Long> {

}
