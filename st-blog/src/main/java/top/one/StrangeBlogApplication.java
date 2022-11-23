package top.one;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.one.dao")
public class StrangeBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(StrangeBlogApplication.class,args);
    }
}