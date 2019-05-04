package com.istore.mysqldbservice.utils.impl;

import com.istore.mysqldbservice.utils.LogManager;

import java.util.Objects;

public class FileLogManager implements LogManager {
    private static FileLogManager logManager;

    private FileLogManager() {
    }

    public synchronized static FileLogManager getLogManager() {
        if (Objects.isNull(logManager)) {
            logManager = new FileLogManager();
        }
        return logManager;
    }

    @Override
    public void log(String message) {
        System.out.println("To file: " + message);
    }
}
