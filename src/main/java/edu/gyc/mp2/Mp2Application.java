package edu.gyc.mp2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.gyc.mp2.dao")
public class Mp2Application {

    public static void main(String[] args) {
        SpringApplication.run(Mp2Application.class, args);
    }

}
