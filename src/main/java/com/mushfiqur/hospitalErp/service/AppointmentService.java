package com.mushfiqur.hospitalErp.service;

import com.mushfiqur.hospitalErp.domain.Appointment;
import com.mushfiqur.hospitalErp.domain.Doctor;
import com.mushfiqur.hospitalErp.domain.Patient;
import com.mushfiqur.hospitalErp.domain.Slot;
import com.mushfiqur.hospitalErp.dto.appt.ApptListDto;
import com.mushfiqur.hospitalErp.dto.appt.DoctorInfoDto;
import com.mushfiqur.hospitalErp.repository.AppointmentRepository;
import com.mushfiqur.hospitalErp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final PatientRepository patientRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    public List<DoctorInfoDto> getDocInfo(List<Doctor> doctorList) {
        List<DoctorInfoDto> doctorInfoDtoList = new ArrayList<>();

        for (Doctor doctor : doctorList) {
            DoctorInfoDto doctorInfoDto = new DoctorInfoDto();

            doctorInfoDto.setName(doctor.getName());
            doctorInfoDto.setPhone(doctor.getPhone());
            doctorInfoDto.setSpeciality(doctor.getSpeciality());
            doctorInfoDto.setUsername(doctor.getUsername());
            doctorInfoDto.setDegree(doctor.getDegree());

            doctorInfoDtoList.add(doctorInfoDto);
        }

        return doctorInfoDtoList;
    }

    public Slot getSlotFromString(String slotName) {
        slotName = slotName.substring(0, 5);
        slotName = slotName.replace(" ", "");
        slotName = "SLOT_" + slotName;

        return Slot.valueOf(slotName);
    }

    public boolean doesAppointmentExist(Doctor doctor, Slot slot, LocalDate localDate) {
        return appointmentRepository.retrieveApptCount(doctor, slot, localDate) != 0;
    }

    public void saveAppointment(String patientUsername, Doctor doctor, Slot slot, LocalDate date) {
        Patient patient = patientRepository.findPatientByUsername(patientUsername).get();
        Appointment appointment = new Appointment(slot, date, patient, doctor);

        appointmentRepository.save(appointment);
    }

    public List<ApptListDto> getAppointmentListDto(Set<Appointment> appointmentList) {
        List<ApptListDto> apptListDtos = new ArrayList<>();

        for (Appointment appt : appointmentList) {
            Patient patient = appt.getPatient();
            ApptListDto apptListDto = new ApptListDto(appt.getSlot().getDisplayName(), appt.getAppointmentDate(),
                    patient.getName(), patient.getPhone(), patient.getUsername());

            apptListDtos.add(apptListDto);
        }

        return apptListDtos;
    }
}
