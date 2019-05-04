package com.istore.mysqldbservice.repository.impl.jdbc;

import com.istore.mysqldbservice.model.Role;
import com.istore.mysqldbservice.model.User;
import com.istore.mysqldbservice.repository.UserRepository;
import com.istore.mysqldbservice.utils.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryJDBCImpl implements UserRepository {

//    @Autowired
//    private DataSource dataSource;

    private List<LogManager> logManagers = new ArrayList<>();

    public void add(LogManager logManager){
        logManagers.add(logManager);
    }

    public void remove(LogManager logManager) {
        logManagers.remove(logManager);
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/istore_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&user=root&password=rootroot");
            PreparedStatement ps = connection.prepareStatement("SELECT * from user where email=? and password = ?");
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return createUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User save(User user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/istore_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&user=root&password=rootroot");
            logManagers.forEach(logManager -> logManager.log("User:"+user.getEmail()+" added"));

            PreparedStatement ps = connection.prepareStatement("INSERT INTO user values (?,?,?,?)");
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3,user.getPassword());
            ps.setInt(4,user.getRole().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/istore_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&user=root&password=rootroot");
            PreparedStatement ps = connection.prepareStatement("SELECT * from user");
            ResultSet resultSet = ps.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setEmail(resultSet.getString("email"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        Role role = new Role();
        role.setId(resultSet.getInt("role_id"));
        user.setRole(role);
        return user;
    }
}
