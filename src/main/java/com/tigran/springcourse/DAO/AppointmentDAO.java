package com.tigran.springcourse.DAO;

import com.tigran.springcourse.models.Appointment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppointmentDAO{

    private JdbcTemplate jdbcTemplate;

    public AppointmentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addAppointment(Appointment appointment){
        String sql = "INSERT INTO appointments (barberId,fullName,serviceType,phoneNumber,appointmentTime,emailAddress) VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                appointment.getBarberId(),
                appointment.getFullName(),
                appointment.getServiceType(),
                appointment.getPhoneNumber(),
                appointment.getAppointmentDateTime(),
                appointment.getEmailAddress());
    }
    public void updateAppointment(Appointment appointment){
        String sql = "UPDATE appointments SET barberId = ?,fullName = ?,serviceType = ?,phoneNumber = ?,appointmentTime = ?,emailAddress = ?";
        jdbcTemplate.update(sql,
                appointment.getBarberId(),
                appointment.getFullName(),
                appointment.getServiceType(),
                appointment.getPhoneNumber(),
                appointment.getAppointmentDateTime(),
                appointment.getEmailAddress());
    }
    public Appointment getAppointmentById(int id){
        String sql = "SELECT * FROM appointments WHERE id = ?";
        return (Appointment) jdbcTemplate.queryForObject(sql,new AppointmentRowMapper(),id);
    }
    public List<Appointment> getAllAppointments(){
        String sql = "SELECT * FROM appointments";
        return jdbcTemplate.query(sql,new AppointmentRowMapper());
    }
    public void deleteAppointment(int id){
        String sql = "DELETE FROM appointments WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }
}
