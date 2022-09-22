package com.mushfiqur.hospitalErp.service;

import com.mushfiqur.hospitalErp.domain.Patient;
import com.mushfiqur.hospitalErp.dto.LoginDto;
import com.mushfiqur.hospitalErp.dto.patient.PatientHomeDto;
import com.mushfiqur.hospitalErp.dto.patient.PatientRegistrationDto;
import com.mushfiqur.hospitalErp.repository.PatientRepository;
import com.mushfiqur.hospitalErp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientAuthService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientAuthService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientHomeDto getPatientHomeDto(Patient patient) {
        PatientHomeDto patientHomeDto = new PatientHomeDto();
        patientHomeDto.setName(patient.getName());
        patientHomeDto.setPhone(patient.getPhone());

        patientHomeDto.getActions().put("Doctors List", "/appt/doctor-list");
        patientHomeDto.getActions().put("Book Appointments", "/appt/book");

        return patientHomeDto;
    }

    public Patient getPatientFromDto(PatientRegistrationDto patientRegistrationDto) {
        Patient patient = new Patient();
        patient.setName(patientRegistrationDto.getName());
        patient.setPhone(patientRegistrationDto.getPhone());
        patient.setUsername(patientRegistrationDto.getUsername());

        String hashedPass = Utils.getHash(patientRegistrationDto.getPassword());
        patient.setPassword(hashedPass);

        return patient;
    }

    public boolean isValidLogin(LoginDto loginDto) {
        String passHash = Utils.getHash(loginDto.getPassword());
        return patientRepository.existsPatientByUsernameAndPassword(loginDto.getUsername(), passHash);
    }
}
