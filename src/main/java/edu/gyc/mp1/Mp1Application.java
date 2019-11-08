package edu.gyc.mp1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("edu.gyc.mp1.dao")
@SpringBootApplication
public class Mp1Application {

    public static void main(String[] args) {
        SpringApplication.run(Mp1Application.class, args);
    }

}
