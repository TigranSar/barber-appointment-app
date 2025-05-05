package com.tigran.springcourse.dao;

import com.tigran.springcourse.models.Barber;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BarberRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Barber barber = new Barber();
        barber.setId(rs.getInt("id"));
        barber.setFullName(rs.getString("fullName"));
        barber.setShavingPrice(rs.getInt("shavingPrice"));
        barber.setHairCutPrice(rs.getInt("hairCutPrice"));
        barber.setPhotopath(rs.getString("photopath"));
        return barber;
    }
}
