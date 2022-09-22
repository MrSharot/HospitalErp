package com.mushfiqur.hospitalErp.repository;

import com.mushfiqur.hospitalErp.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findDoctorByUsername(String username);

    boolean existsDoctorByUsername(String username);

    boolean existsDoctorByUsernameAndPassword(String username, String password);
}
