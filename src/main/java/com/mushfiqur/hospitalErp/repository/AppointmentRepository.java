package com.mushfiqur.hospitalErp.repository;

import com.mushfiqur.hospitalErp.domain.Appointment;
import com.mushfiqur.hospitalErp.domain.Doctor;
import com.mushfiqur.hospitalErp.domain.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(" SELECT COUNT(appt)" +
            " FROM Appointment appt" +
            " WHERE appt.doctor = :doctor" +
            "   AND appt.slot = :slot " +
            "   AND appt.appointmentDate = :date")
    Long retrieveApptCount(@Param("doctor") Doctor doctor,
                           @Param("slot") Slot slot,
                           @Param("date") LocalDate date);
}
