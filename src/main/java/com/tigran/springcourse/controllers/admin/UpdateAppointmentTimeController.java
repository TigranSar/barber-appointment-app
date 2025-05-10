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
                                        @RequestParam("time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime appointmentTime,
                                        HttpSession session,
                                        Model model){
        String sessionString = (String)session.getAttribute("admin");
        if (sessionString == null){
            return "redirect:/admin/login_page";
        }
        String error = appointmentTimeService.UpdateAppointment(appointment_id,barber_id,appointmentTime);
        if (error != null){
            model.addAttribute("error",error);
            model.addAttribute("hasError", true);
            model.addAttribute("time",appointmentTime);
            model.addAttribute("appointment_id",appointment_id);
            model.addAttribute("barber_id",barber_id);
            return "updateAppointmentTimePage";
        }
        return "redirect:/admin/appointments";
    }
}
