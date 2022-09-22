package com.mushfiqur.hospitalErp.controller.appt;

import com.mushfiqur.hospitalErp.domain.Doctor;
import com.mushfiqur.hospitalErp.domain.Slot;
import com.mushfiqur.hospitalErp.dto.LoginDto;
import com.mushfiqur.hospitalErp.dto.appt.ApptInfoDto;
import com.mushfiqur.hospitalErp.dto.appt.ApptListDto;
import com.mushfiqur.hospitalErp.dto.appt.DoctorInfoDto;
import com.mushfiqur.hospitalErp.repository.DoctorRepository;
import com.mushfiqur.hospitalErp.service.AppointmentService;
import com.mushfiqur.hospitalErp.service.DoctorAuthService;
import com.mushfiqur.hospitalErp.service.PatientAuthService;
import com.mushfiqur.hospitalErp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.mushfiqur.hospitalErp.utils.Utils.getMessageResponse;

@RestController
@RequestMapping("/appt")
public class AppointmentController {

    private final DoctorRepository doctorRepository;

    private final AppointmentService appointmentService;

    private final DoctorAuthService doctorAuthService;

    private final PatientAuthService patientAuthService;

    @Autowired
    public AppointmentController(DoctorRepository doctorRepository,
                                 AppointmentService appointmentService,
                                 DoctorAuthService doctorAuthService,
                                 PatientAuthService patientAuthService) {

        this.doctorRepository = doctorRepository;
        this.appointmentService = appointmentService;
        this.doctorAuthService = doctorAuthService;
        this.patientAuthService = patientAuthService;
    }

    @GetMapping("/doctor-list")
    public ResponseEntity showDoctorsList(@RequestBody @Valid LoginDto loginDto,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Utils.getErrorMessages(bindingResult);
        }

        if (!patientAuthService.isValidLogin(loginDto)) {
            return getMessageResponse("Invalid patient credentials.", HttpStatus.UNAUTHORIZED);
        }

        List<Doctor> doctorsList = doctorRepository.findAll();
        List<DoctorInfoDto> doctorInfoDtoList = appointmentService.getDocInfo(doctorsList);

        return new ResponseEntity<>(doctorInfoDtoList, HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity bookAppointment(@RequestBody @Valid ApptInfoDto apptInfoDto,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Utils.getErrorMessages(bindingResult);
        }

        LoginDto loginDto = apptInfoDto.getLogin();

        // Patient Login Check
        if (!patientAuthService.isValidLogin(loginDto)) {
            return getMessageResponse("Invalid patient credentials.", HttpStatus.UNAUTHORIZED);
        }

        // Doctor Existence Check
        Optional<Doctor> optionalDoctor = doctorRepository.findDoctorByUsername(apptInfoDto.getDoctorUsername());

        if (!optionalDoctor.isPresent()) {
            return getMessageResponse("Invalid doctor credentials.", HttpStatus.BAD_REQUEST);
        }

        Doctor doctor = optionalDoctor.get();
        Slot slot = appointmentService.getSlotFromString(apptInfoDto.getSlot());
        LocalDate date = apptInfoDto.getDate();

        boolean appointmentExists = appointmentService.doesAppointmentExist(doctor, slot, date);

        if (appointmentExists) {
            return getMessageResponse("An Appointment already exists with the given Doctor, " +
                    "Slot and Date combination. Please change.", HttpStatus.BAD_REQUEST);
        }

        appointmentService.saveAppointment(loginDto.getUsername(), doctor, slot, date);

        return new ResponseEntity(apptInfoDto, HttpStatus.OK);
    }


    @GetMapping("/show")
    public ResponseEntity showAppointments(@RequestBody @Valid LoginDto loginDto,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Utils.getErrorMessages(bindingResult);
        }

        if (!doctorAuthService.isValidLogin(loginDto)) {
            return getMessageResponse("Invalid doctor credentials.", HttpStatus.UNAUTHORIZED);
        }

        Doctor doctor = doctorRepository.findDoctorByUsername(loginDto.getUsername()).get();
        List<ApptListDto> appointmentListDto = appointmentService.getAppointmentListDto(doctor.getAppointments());

        return new ResponseEntity(appointmentListDto, HttpStatus.OK);
    }
}
