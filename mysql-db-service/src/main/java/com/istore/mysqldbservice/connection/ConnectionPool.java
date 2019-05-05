package com.istore.mysqldbservice.connection;


import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ConnectionPool {

    private static ConnectionPool instance = null;
    private static final int POOL_SIZE = 10;
    private static final int MAX_TIME = 2;
    private static Lock lock = new ReentrantLock(true);
    private BlockingQueue<Connection> connections;

    private ConnectionPool() {
        initConnection();
    }

    public static ConnectionPool getInstance() {
        lock.lock();

        if (null == instance) {
            instance = new ConnectionPool();
        }
        lock.unlock();

        return instance;
    }

    private void initConnection() {
        connections = new LinkedBlockingDeque<>(POOL_SIZE);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/istore_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&user=root&password=rootroot");
                connections.add(connection);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public final Connection getConnection() {
        try {
            Connection connection = connections.poll(MAX_TIME, TimeUnit.SECONDS);
            if (connection != null) {
                log.info("Connection " + connection + " took from connection pool");
            } else {
                log.error("Couldn't retrieve a connection from pool");
            }
            return connection;
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public void release(Connection connection) {
        if (connection != null) {
            try {
                connections.put(connection);
                log.info("Connection " + connection + "  released");
                log.info("There are(is) " + (connections.size() - connections.remainingCapacity()) + " connection(s) in the pool.");
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }

}
