package com.data.session09;

import com.data.session09.model.LoggingExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class Session09Application implements CommandLineRunner {
    @Autowired
    private LoggingExample loggingExample;

    public static void main(String[] args) {
        SpringApplication.run(Session09Application.class, args);
    }

    @Override
    public void run(String... args) {
        loggingExample.testLogging();
    }
}
