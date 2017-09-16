package cn.yjxxclub.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Author: Starry.Teng
 * Email: tengxing7452@163.com
 * Date: 17-9-12
 * Time: 下午3:32
 * Describe: WebMvcConfig
 */
//@Configuration
public class WebMvcConfig  extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/login");
        registry.addViewController("/chat").setViewName("/chat");
    }

}