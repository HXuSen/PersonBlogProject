package com.hxsstu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: BlogApplication
 * Package: com.hxsstu
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/19-16:09
 */
@SpringBootApplication
@MapperScan("com.hxsstu.mapper")
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class,args);
    }
}
