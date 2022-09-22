package com.mushfiqur.hospitalErp.controller.auth;

import com.mushfiqur.hospitalErp.domain.Patient;
import com.mushfiqur.hospitalErp.dto.LoginDto;
import com.mushfiqur.hospitalErp.dto.patient.PatientHomeDto;
import com.mushfiqur.hospitalErp.dto.patient.PatientRegistrationDto;
import com.mushfiqur.hospitalErp.repository.PatientRepository;
import com.mushfiqur.hospitalErp.service.PatientAuthService;
import com.mushfiqur.hospitalErp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.mushfiqur.hospitalErp.utils.Utils.getMessageResponse;

@RestController
@RequestMapping("/auth/patient")
public class PatientAuthController {

    private final PatientRepository patientRepository;

    private final PatientAuthService patientAuthService;

    @Autowired
    public PatientAuthController(PatientRepository patientRepository, PatientAuthService patientAuthService) {
        this.patientRepository = patientRepository;
        this.patientAuthService = patientAuthService;
    }

    @GetMapping
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Utils.getErrorMessages(bindingResult);
        }

        if (!patientAuthService.isValidLogin(loginDto)) {
            return getMessageResponse("Invalid patient credentials.", HttpStatus.UNAUTHORIZED);
        }

        Patient patient = patientRepository.findPatientByUsername(loginDto.getUsername()).get();

        PatientHomeDto patientHomeDto = patientAuthService.getPatientHomeDto(patient);

        return new ResponseEntity<>(patientHomeDto, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity registerDoctor(@RequestBody @Valid PatientRegistrationDto patientRegistrationDto,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Utils.getErrorMessages(bindingResult);
        }

        if (patientRepository.existsPatientByUsername(patientRegistrationDto.getUsername())) {
            return getMessageResponse("Patient with the same username already exists, pick a different one.",
                    HttpStatus.BAD_REQUEST);
        }

        Patient patient = patientAuthService.getPatientFromDto(patientRegistrationDto);
        patientRepository.save(patient);

        return new ResponseEntity<>(patientRegistrationDto, HttpStatus.OK);
    }
}
