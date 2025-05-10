package com.tigran.springcourse.service;

import com.tigran.springcourse.dao.AppointmentDAO;
import com.tigran.springcourse.models.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;

@Service
public class UpdateAppointmentTimeService {
    private AppointmentDAO appointmentDAO;
    @Autowired
    public UpdateAppointmentTimeService(AppointmentDAO appointmentDAO) {
        this.appointmentDAO = appointmentDAO;
    }

    public String UpdateAppointment(int appointment_id, int barber_id, LocalDateTime appointmentTime){
        if (!appointmentDAO.isTimeAvailable(appointmentTime,barber_id)){
            return "This time is not available";
        }else if (appointmentTime.isBefore(LocalDateTime.now().minusMinutes(1))) {
            return "Time can't be in the past";
        }
        Appointment appointment = appointmentDAO.getAppointmentById(appointment_id);
        appointment.setAppointmentDateTime(appointmentTime);
        appointmentDAO.updateAppointment(appointment);
        return null;
    }
}
