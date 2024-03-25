package org.example.usermanager.dao;

import org.example.usermanager.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {


     User selectUser(int id);

     List<User> selectAllUser();

     void deleteUser(int id) throws SQLException;




     void create(User user);

     void edit(int id);
}
