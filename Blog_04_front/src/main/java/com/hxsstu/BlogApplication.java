package com.hxsstu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
@EnableScheduling
@EnableSwagger2
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class,args);
    }
}
