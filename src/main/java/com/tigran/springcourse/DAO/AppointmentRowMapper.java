package com.tigran.springcourse.dao;

import com.tigran.springcourse.models.Appointment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setId(rs.getInt("id"));
        appointment.setBarberId(rs.getInt("barberId"));
        appointment.setFullName(rs.getString("fullName"));
        appointment.setPhoneNumber(rs.getString("phoneNumber"));
        appointment.setAppointmentDateTime(rs.getTimestamp("appointmentTime").toLocalDateTime());
        appointment.setHairCut(rs.getBoolean("haircut"));
        appointment.setShaving(rs.getBoolean("shaving"));
        return appointment;
    }
}
