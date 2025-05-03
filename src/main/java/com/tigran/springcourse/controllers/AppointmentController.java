package com.tigran.springcourse.controllers;

import com.tigran.springcourse.DAO.BarberDAO;
import com.tigran.springcourse.models.Appointment;
import com.tigran.springcourse.validator.AppointmentValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("client")
public class AppointmentController {
    private BarberDAO barberDAO;
    private AppointmentValidator appointmentValidator;

    @Autowired
    public AppointmentController(BarberDAO barberDAO, AppointmentValidator appointmentValidator) {
        this.barberDAO = barberDAO;
        this.appointmentValidator = appointmentValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
    }

    @GetMapping("/appointment/{id}")
    public String getForm(@PathVariable("id") int id, Model model){
        Appointment appointment = new Appointment();
        appointment.setBarberId(id);
        model.addAttribute("barber", barberDAO.getBarberById(id));
        model.addAttribute("appointment",appointment);
        return "appointmentForm";
    }
    @PostMapping("/sendAppointment")
    public String sendAppointment(@ModelAttribute("appointment") @Valid Appointment appointment,
                                  BindingResult bindingResult, Model model){
        appointmentValidator.validate(appointment,bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("appointment",appointment);
            model.addAttribute("barber",barberDAO.getBarberById(appointment.getBarberId()));
            return "appointmentForm";
        }
        return "redirect:/client/choose_barber";
    }
}
