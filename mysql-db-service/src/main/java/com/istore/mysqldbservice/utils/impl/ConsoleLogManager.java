package com.istore.mysqldbservice.utils.impl;

import com.istore.mysqldbservice.utils.LogManager;

import java.util.Objects;

public class ConsoleLogManager implements LogManager {
    private static ConsoleLogManager consoleLogManager;

    private ConsoleLogManager() {
    }

    public static ConsoleLogManager getLogManager() {
        if (Objects.isNull(consoleLogManager)) {
            consoleLogManager = new ConsoleLogManager();
        }
        return consoleLogManager;
    }

    @Override
    public void log(String message) {
        System.out.println("To console: " + message);
    }
}
