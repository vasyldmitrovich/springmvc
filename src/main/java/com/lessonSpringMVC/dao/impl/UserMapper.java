package com.lessonSpringMVC.dao.impl;

import com.lessonSpringMVC.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
* How parsing from ResultSet object */
public class UserMapper /*implements RowMapper<User>*/ {
    /*@Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        return user;
    }*/
}
