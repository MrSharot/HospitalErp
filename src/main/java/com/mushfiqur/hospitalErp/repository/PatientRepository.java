package com.mushfiqur.hospitalErp.repository;

import com.mushfiqur.hospitalErp.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findPatientByUsername(String username);

    boolean existsPatientByUsername(String username);

    boolean existsPatientByUsernameAndPassword(String username, String password);
}
