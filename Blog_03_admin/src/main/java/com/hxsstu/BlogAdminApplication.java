package com.hxsstu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: BlogAdminApplication
 * Package: com.hxsstu
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/21-15:51
 */
@SpringBootApplication
@MapperScan("com.hxsstu.mapper")
public class BlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class,args);
    }
}
