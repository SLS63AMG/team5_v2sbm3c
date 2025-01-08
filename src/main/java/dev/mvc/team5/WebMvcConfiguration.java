package dev.mvc.team5;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dev.mvc.survey.Survey;
import dev.mvc.tool.Contents;

import dev.mvc.tool.Tool;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Windows: path = "C:/kd/deploy/resort/review/storage";
        // ▶ file:///C:/kd/deploy/resort/review/storage
      
        // Ubuntu: path = "/home/ubuntu/deploy/resort/review/storage";
        // ▶ file:////home/ubuntu/deploy/resort/review/storage
      
        // JSP 인식되는 경로: http://localhost:9091/review/storage;
<<<<<<< HEAD
        registry.addResourceHandler("/notice/images/**").addResourceLocations("file:///" + Contents.getUploadDir_notice());
        registry.addResourceHandler("/menu/storage/**").addResourceLocations("file:///" + Contents.getUploadDir_menu());
=======
        registry.addResourceHandler("/notice/storage/**").addResourceLocations("file:///" + Contents.getUploadDir_notice());
        
        registry.addResourceHandler("/inquiry/storage/**").addResourceLocations("file:///" + Contents.getUploadDir_inquiry());
>>>>>>> 6fec2892767a54be11a6649956893d6db78d63a0
        
        // Survey 관련 ResourceHandler
        // Windows: path = "C:/kd/deploy/resort/contents/storage";
        // ▶ file:///C:/kd/deploy/resort/contents/storage
      
        // Ubuntu: path = "/home/ubuntu/deploy/resort/contents/storage";
        // ▶ file:////home/ubuntu/deploy/resort/contents/storage
      
        // JSP 인식되는 경로: http://localhost:9091/survey/storage;
        registry.addResourceHandler("/survey/storage/**").addResourceLocations("file:///" + Survey.getUploadDir());
    }
}
