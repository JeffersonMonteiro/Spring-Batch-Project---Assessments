package com.example.demo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRowMapper implements RowMapper<Users> {

    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException{
        Users user = new Users();
        rs.getLong("user_id");
        rs.getString("user_name");
        rs.getString("dept");
        rs.getBigDecimal("account");

        return user;
    }
}