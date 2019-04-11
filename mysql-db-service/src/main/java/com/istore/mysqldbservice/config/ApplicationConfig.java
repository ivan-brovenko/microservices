package com.istore.mysqldbservice.config;

import com.istore.mysqldbservice.repository.impl.UserRepositoryImpl;
import com.istore.mysqldbservice.utils.impl.ConsoleLogManager;
import com.istore.mysqldbservice.utils.impl.FileLogManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public UserRepositoryImpl userRepository(){
        ConsoleLogManager consoleLogManager = ConsoleLogManager.getLogManager();
        FileLogManager fileLogManager = FileLogManager.getLogManager();

        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        userRepository.add(consoleLogManager);
        userRepository.add(fileLogManager);
        return userRepository;
    }
}
