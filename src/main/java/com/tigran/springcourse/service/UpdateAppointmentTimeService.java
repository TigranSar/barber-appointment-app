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

    public String UpdateAppointment(int appointment_id, LocalDateTime appointmentTime, Model model){
        if (!appointmentDAO.isTimeAvailable(appointmentTime,appointment_id)){
            model.addAttribute("error","this time is not available");
            return "appointmentsListPage";
        }else if (appointmentTime.isBefore(LocalDateTime.now().minusMinutes(1))){
            model.addAttribute("error","Time can't be in the past");
            return "appointmentsListPage";
        }else {
            Appointment appointment = appointmentDAO.getAppointmentById(appointment_id);
            appointment.setAppointmentDateTime(appointmentTime);
            appointmentDAO.updateAppointment(appointment);
            return "redirect:/admin/appointments";
        }
    }
}
