package com.example.demo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRowMapper implements RowMapper<Users> {

    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException{
        Users user = new Users();
        rs. getLong(1);
        rs.getString(2);
        rs.getString(3);
        rs.getBigDecimal(4);

        return user;
    }
}