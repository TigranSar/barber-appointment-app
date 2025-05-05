package com.tigran.springcourse.dao;

import com.tigran.springcourse.models.Barber;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BarberDAO{
    private final JdbcTemplate jdbcTemplate;

    public BarberDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void addBarber(Barber barber){
        jdbcTemplate.update("INSERT INTO barbers(fullName, hairCutPrice,shavingPrice,photopath) VALUES (?, ?,?,?)",
                barber.getFullName(),barber.getHairCutPrice(),barber.getShavingPrice(),barber.getPhotopath());
    }
    public Barber getBarberById(int id){
        String sql = "SELECT * FROM barbers WHERE id = ?";
        return (Barber)jdbcTemplate.queryForObject(sql, new BarberRowMapper(), id);
    }
    public void updateBarber(Barber barber){
        String sql = "UPDATE barbers SET fullName = ?, hairCutPrice = ?, shavingPrice = ?, photopath = ? WHERE id = ?";
        jdbcTemplate.update(sql,barber.getFullName(),barber.getHairCutPrice(),barber.getShavingPrice(),barber.getPhotopath(),barber.getId());
    }
    public List<Barber> getAllBarbers(){
        String sql = "SELECT * FROM barbers";
        return jdbcTemplate.query(sql,new BarberRowMapper());
    }
    public void deleteBarber(int id){
        String sql = "DELETE FROM barbers WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }
}
