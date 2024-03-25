package org.example.usermanager.dao;

import org.example.usermanager.model.User;
import org.example.usermanager.servlet.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private static final String INSERT_USERS_SQL = "insert into users (name, email, country) value(?, ?, ?)";
    private static final String SELECT_USERS_BY_ID = "select * from users where id=?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USER_SQL = "delete from users where id=?";
    private static final String UPDATE_USER_SQL = "update users set name=?, email=?, country=? where id=?";




    @Override
    public User selectUser(int id) {
        User user = new User();
        try {
            Connection connection = DB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_BY_ID);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(name, email, country);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public List<User> selectAllUser() {
        List<User> userList = new ArrayList<>();
        try {
            Connection connection = DB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                userList.add(new User(id, name, email, country));
            }
            connection.close();
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public void deleteUser(int id) {
        try {
            Connection connection = DB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    public void create(User user) {
        try {
            Connection connection = DB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void edit(int id) {
        try {
            Connection connection = DB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL);
            preparedStatement.setString(1, "name");
            preparedStatement.setString(2, "email");
            preparedStatement.setString(3, "country");
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
