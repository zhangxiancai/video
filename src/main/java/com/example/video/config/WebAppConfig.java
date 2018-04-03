package com.example.video.config;


import com.example.video.global.GlobalConstant;
import com.example.video.global.GlobalMy;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        GlobalMy.LOCATION=System.getenv(GlobalConstant.VIDEOS_LOCATION);
        if( GlobalMy.LOCATION==null){
            GlobalMy.LOCATION="/videos/";
        }
        registry.addResourceHandler("/userVideos/**").addResourceLocations("file:"+GlobalMy.LOCATION);
        super.addResourceHandlers(registry);
    }
}
