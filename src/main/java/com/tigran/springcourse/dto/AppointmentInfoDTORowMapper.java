package com.tigran.springcourse.dto;

import com.tigran.springcourse.dto.AppointmentInfoDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentInfoDTORowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        AppointmentInfoDTO appointmentInfoDTO = new AppointmentInfoDTO();
        appointmentInfoDTO.setAppointmentId(rs.getInt("appointment_id"));
        appointmentInfoDTO.setBarberFullName(rs.getString("barber_fullname"));
        appointmentInfoDTO.setBarberPhotoPath(rs.getString("barber_photopath"));
        appointmentInfoDTO.setClientFullName(rs.getString("client_fullname"));
        appointmentInfoDTO.setShaving(rs.getBoolean("shaving"));
        appointmentInfoDTO.setHariCut(rs.getBoolean("haircut"));
        appointmentInfoDTO.setClientPhoneNumber(rs.getString("client_phone_number"));
        appointmentInfoDTO.setAppointmentTime(rs.getTimestamp("appointment_time").toLocalDateTime());
        return appointmentInfoDTO;
    }
}
