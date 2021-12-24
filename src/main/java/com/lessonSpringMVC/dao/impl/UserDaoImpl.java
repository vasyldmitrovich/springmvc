package com.lessonSpringMVC.dao.impl;

import com.lessonSpringMVC.dao.Dao;
import com.lessonSpringMVC.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserDaoImpl implements Dao<User> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /* That code need if we want to use JDBC API, not JDBC Template
    private static final String URL = "jdbc:postgresql://localhost:5432/springmvc";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "512891";
    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    If use UserMapper, need rewrite
    new BeanPropertyRowMapper<>(User.class)
    to new UserMapper
    */

    @Override
    public User get(String id) {

        /*User user = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from users where id=?");
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getString("id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;*/

        return jdbcTemplate.query("select * from users where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);/*null rewrite new Error(....)...*/
    }

    @Override
    public List<User> getAll() {

        /*List<User> userList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from users";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;*/

        return jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User user) {

        /*String sql = "insert into users values ('"+
                UUID.randomUUID()+"','"+user.getEmail()+"','"+user.getPassword()+"')";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        jdbcTemplate.update("insert into users values (?,?,?)",
                UUID.randomUUID(), user.getEmail(), user.getPassword());
    }

    @Override
    public void update(User user, String id) {

        /*try {
            String sql = "update users set email='"+user.getEmail()+
                    "', password='"+user.getPassword()+"' where id='"+id+"'";
            System.out.println(sql);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        jdbcTemplate.update("update users set email=?, password=? where id=?",
                user.getEmail(), user.getPassword(), id);
    }

    @Override
    public void delete(String id) {

        /*try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("delete from users where id=?");
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        jdbcTemplate.update("delete from users where id=?", id);
    }
}
