package com.centrohospitalar.grupog.repositories;


import com.centrohospitalar.grupog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    public Role getByName(String name);

}
