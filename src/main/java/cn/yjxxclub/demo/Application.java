package cn.yjxxclub.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Author: Starry.Teng
 * Email: tengxing7452@163.com
 * Date: 17-9-12
 * Time: 下午12:59
 * Describe: Application
 */
@SpringBootApplication
//@EnableScheduling//通过@EnableScheduling注解开启对计划任务的支持
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}