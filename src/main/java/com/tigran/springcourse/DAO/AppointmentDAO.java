package com.tigran.springcourse.dao;

import com.tigran.springcourse.dto.AppointmentInfoDTO;
import com.tigran.springcourse.dto.AppointmentInfoDTORowMapper;
import com.tigran.springcourse.models.Appointment;
import com.tigran.springcourse.service.ServiceTypeService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AppointmentDAO{

    private JdbcTemplate jdbcTemplate;

    public AppointmentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addAppointment(Appointment appointment){
        String sql = "INSERT INTO appointments (barberId,fullName,phoneNumber,appointmentTime,haircut,shaving) VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                appointment.getBarberId(),
                appointment.getFullName(),
                appointment.getPhoneNumber(),
                appointment.getAppointmentDateTime(),
                appointment.isHairCut(),
                appointment.isShaving()
                );
    }
    public void updateAppointment(Appointment appointment){
        String sql = "UPDATE appointments SET barberId = ?,fullName = ?,phoneNumber = ?,appointmentTime = ?,haircut = ?,shaving = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                appointment.getBarberId(),
                appointment.getFullName(),
                appointment.getPhoneNumber(),
                appointment.getAppointmentDateTime(),
                appointment.isHairCut(),
                appointment.isShaving(),
                appointment.getId()
                );
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
    public boolean isTimeAvailable(LocalDateTime dateTime, int barberid) {
        LocalDateTime start = dateTime.minusMinutes(30);
        LocalDateTime end = dateTime.plusMinutes(30);
        String sql = "SELECT COUNT(*) FROM appointments WHERE appointmenttime BETWEEN ? AND ? AND barberid = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, start, end,barberid);
        return count == 0;
    }
    public List<AppointmentInfoDTO> getAllAppointmentInfo(){
        String sql = "select a.id as appointment_id," +
                "b.fullname as barber_fullname," +
                "b.photopath as barber_photopath," +
                "a.fullname as client_fullname," +
                "a.appointmenttime as appointment_time," +
                "a.haircut,a.shaving, " +
                "a.phonenumber as client_phone_number " +
                "from appointments a " +
                "inner join barbers b " +
                "on a.barberid = b.id" +
                "ORDER BY ABS(EXTRACT(EPOCH FROM (appointmenttime - now())))";
        return jdbcTemplate.query(sql,new AppointmentInfoDTORowMapper());
    }
}
