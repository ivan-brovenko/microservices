package com.istore.mysqldbservice.repository.impl.jdbc;

import com.istore.mysqldbservice.connection.ConnectionPool;
import com.istore.mysqldbservice.exception.ApplicationException;
import com.istore.mysqldbservice.memento.Storage;
import com.istore.mysqldbservice.memento.UserSnapshot;
import com.istore.mysqldbservice.model.Role;
import com.istore.mysqldbservice.model.User;
import com.istore.mysqldbservice.repository.UserRepository;
import com.istore.mysqldbservice.utils.LogManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserRepositoryJDBCImpl implements UserRepository {
    private List<LogManager> logManagers = new ArrayList<>();

    private ApplicationContext applicationContext;

    public void add(LogManager logManager) {
        logManagers.add(logManager);
    }

    public void remove(LogManager logManager) {
        logManagers.remove(logManager);
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            return getUser(email, password, connection);
        } catch (SQLException e) {
            throw new ApplicationException("Can't find user ", e);
        } finally {
            ConnectionPool.getInstance().release(connection);
        }
    }

    @Override
    public User save(User user) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            return saveUser(user, connection);
        } catch (SQLException e) {
            throw new ApplicationException("Can't save user", e);
        } finally {
            ConnectionPool.getInstance().release(connection);
        }
    }

    @Override
    public List<User> findAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            return getUsers(connection);
        } catch (SQLException e) {
            throw new ApplicationException("Can't find all users", e);
        } finally {
            ConnectionPool.getInstance().release(connection);
        }
    }

    @Override
    public void rollback(String userEmail) {
        Storage storage = applicationContext.getBean(Storage.class);
        UserSnapshot userSnapshot = storage.getLastSnapshotByUserEmail(userEmail);
        System.out.println(storage);
        System.out.println(userSnapshot);
        System.out.println(userEmail);
        User user = new User();
        user.setEmail(userEmail);
        user.load(userSnapshot);
        executeUpdateUser(user);
    }

    @Override
    public User updateUser(User user) {
        Storage storage = applicationContext.getBean(Storage.class);
        System.out.println(user);
        System.out.println(user.save());
        User formerUser = findUserByEmail(user.getEmail());
        System.out.println("formerUser" + formerUser);
        storage.addSnapshot(formerUser.save(), user.getEmail());
        System.out.println(storage);
        return executeUpdateUser(user);
    }

    private User findUserByEmail(String email) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * from user where email=?");
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return createUser(resultSet);
            }
        } catch (SQLException e) {
            throw new ApplicationException("Can't find user by email", e);
        }
        return null;
    }


    private User executeUpdateUser(User user) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE user SET username =?," +
                    "password=?,phone=? where email=?");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getEmail());
            ps.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new ApplicationException("Can't update user", e);
        } finally {
            ConnectionPool.getInstance().release(connection);
        }
    }

    private List<User> getUsers(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * from user");
        ResultSet resultSet = ps.executeQuery();
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(createUser(resultSet));
        }
        return users;
    }

    private User getUser(String email, String password, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * from user where email=? and password = ?");
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            return createUser(resultSet);
        }
        return null;
    }

    private User saveUser(User user, Connection connection) throws SQLException {
        logManagers.forEach(logManager -> logManager.log("User:" + user.getEmail() + " added"));

        PreparedStatement ps = connection.prepareStatement("INSERT INTO user values (?,?,?,?,?)");
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getUsername());
        ps.setString(3, user.getPassword());
        ps.setInt(4, user.getRole().getId());
        ps.setString(5, user.getPhone());
        ps.executeUpdate();
        return user;
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

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
