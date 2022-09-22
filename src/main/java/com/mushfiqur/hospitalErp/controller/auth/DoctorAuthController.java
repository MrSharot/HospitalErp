package com.mushfiqur.hospitalErp.controller.auth;

import com.mushfiqur.hospitalErp.domain.Doctor;
import com.mushfiqur.hospitalErp.dto.LoginDto;
import com.mushfiqur.hospitalErp.dto.doctor.DoctorHomeDto;
import com.mushfiqur.hospitalErp.dto.doctor.DoctorRegistrationDto;
import com.mushfiqur.hospitalErp.repository.DoctorRepository;
import com.mushfiqur.hospitalErp.service.DoctorAuthService;
import com.mushfiqur.hospitalErp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.mushfiqur.hospitalErp.utils.Utils.getMessageResponse;

@RestController
@RequestMapping("/auth/doctor")
public class DoctorAuthController {

    private final DoctorRepository doctorRepository;

    private final DoctorAuthService doctorAuthService;

    @Autowired
    public DoctorAuthController(DoctorRepository doctorRepository, DoctorAuthService doctorAuthService) {
        this.doctorRepository = doctorRepository;
        this.doctorAuthService = doctorAuthService;
    }

    @GetMapping
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Utils.getErrorMessages(bindingResult);
        }

        if (!doctorAuthService.isValidLogin(loginDto)) {
            return getMessageResponse("Invalid doctor credentials.", HttpStatus.UNAUTHORIZED);
        }

        Doctor doctor = doctorRepository.findDoctorByUsername(loginDto.getUsername()).get();
        DoctorHomeDto doctorHomeDto = doctorAuthService.getDoctorHomeDto(doctor);

        return new ResponseEntity<>(doctorHomeDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity register(@RequestBody @Valid DoctorRegistrationDto doctorRegistrationDto,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Utils.getErrorMessages(bindingResult);
        }

        if (doctorRepository.existsDoctorByUsername(doctorRegistrationDto.getUsername())) {
            return getMessageResponse("Doctor with the same username already exists, pick a different one.",
                    HttpStatus.BAD_REQUEST);
        }

        Doctor doctor = doctorAuthService.getDoctorFromDto(doctorRegistrationDto);
        doctorRepository.save(doctor);

        return new ResponseEntity<>(doctorRegistrationDto, HttpStatus.OK);
    }
}
