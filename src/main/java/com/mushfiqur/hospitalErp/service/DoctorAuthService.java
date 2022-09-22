package com.mushfiqur.hospitalErp.service;

import com.mushfiqur.hospitalErp.domain.Doctor;
import com.mushfiqur.hospitalErp.dto.LoginDto;
import com.mushfiqur.hospitalErp.dto.doctor.DoctorHomeDto;
import com.mushfiqur.hospitalErp.dto.doctor.DoctorRegistrationDto;
import com.mushfiqur.hospitalErp.repository.DoctorRepository;
import com.mushfiqur.hospitalErp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorAuthService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorAuthService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public DoctorHomeDto getDoctorHomeDto(Doctor doctor) {
        DoctorHomeDto doctorHomeDto = new DoctorHomeDto();
        doctorHomeDto.setName(doctor.getName());
        doctorHomeDto.setDegree(doctor.getDegree());
        doctorHomeDto.setSpeciality(doctor.getSpeciality());

        doctorHomeDto.getActions().put("View Appointments", "/appt/show");

        return doctorHomeDto;
    }

    public Doctor getDoctorFromDto(DoctorRegistrationDto doctorRegistrationDto) {
        Doctor doctor = new Doctor();

        doctor.setName(doctorRegistrationDto.getName());
        doctor.setPhone(doctorRegistrationDto.getPhone());
        doctor.setUsername(doctorRegistrationDto.getUsername());
        doctor.setDegree(doctorRegistrationDto.getDegree());
        doctor.setSpeciality(doctorRegistrationDto.getSpeciality());

        String hashedPass = Utils.getHash(doctorRegistrationDto.getPassword());
        doctor.setPassword(hashedPass);

        return doctor;
    }

    public boolean isValidLogin(LoginDto loginDto) {
        String passHash = Utils.getHash(loginDto.getPassword());
        return doctorRepository.existsDoctorByUsernameAndPassword(loginDto.getUsername(), passHash);
    }
}
