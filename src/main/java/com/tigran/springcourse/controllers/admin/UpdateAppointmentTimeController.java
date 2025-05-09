package com.tigran.springcourse.controllers.admin;

import com.tigran.springcourse.dao.AppointmentDAO;
import com.tigran.springcourse.models.Appointment;
import com.tigran.springcourse.service.UpdateAppointmentTimeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin")
public class UpdateAppointmentTimeController {
    private UpdateAppointmentTimeService appointmentTimeService;
    private AppointmentDAO appointmentDAO;
    @Autowired
    public UpdateAppointmentTimeController(AppointmentDAO appointmentDAO, UpdateAppointmentTimeService appointmentTimeService) {
        this.appointmentTimeService = appointmentTimeService;
        this.appointmentDAO = appointmentDAO;
    }
    @GetMapping("/update_time/{id}")
    public String updateAppointmentPage(@PathVariable("id") int id, Model model, HttpSession httpSession){
        String session = (String)httpSession.getAttribute("admin");
        if (session != null) {
            model.addAttribute("appointment_id", id);
            Appointment appointment = appointmentDAO.getAppointmentById(id);
            model.addAttribute("barber_id",appointment.getBarberId());
            model.addAttribute("time", appointment.getAppointmentDateTime());
            return "updateAppointmentTimePage";
        }
        return "loginPage";
    }

    @PostMapping("/update_time")
    public String updateAppointmentTime(@RequestParam("appointment_id") int appointment_id,
                                        @RequestParam("barber_id") int barber_id,
                                        @RequestParam("time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime localDateTime,
                                        HttpSession session,
                                        Model model){
        String sessionString = (String)session.getAttribute("admin");
        if (sessionString != null){
            boolean hasError = false;
            if (!appointmentDAO.isTimeAvailable(localDateTime,barber_id)){
                hasError = true;
                model.addAttribute("error","this time is not available");
            }else if (localDateTime.isBefore(LocalDateTime.now().minusMinutes(1))) {
                hasError = true;
                model.addAttribute("error", "Time can't be in the past");
            }
            if (hasError){
                model.addAttribute("hasError",hasError);
                return "updateAppointmentTimePage";
            }
            Appointment appointment = appointmentDAO.getAppointmentById(appointment_id);
            appointment.setAppointmentDateTime(localDateTime);
            appointmentDAO.updateAppointment(appointment);
            return "redirect:/admin/appointments";
        }
        return "redirect:/admin/login_page";
    }
}
