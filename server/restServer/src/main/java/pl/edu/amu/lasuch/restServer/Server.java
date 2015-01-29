package pl.edu.amu.lasuch.restServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.h2.server.web.WebServlet;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.boot.SpringApplication;
import javax.servlet.Filter;
import org.springframework.jms.core.JmsTemplate;

@ComponentScan
@EnableAutoConfiguration
@RestController
public class Server {
    public static final String EXIT = "exit";
    public static ApplicationContext context;
    
    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }
    
    @Bean
    public Filter shallowETagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }
    
    public static void main(String[] args) throws IOException {
        context = new ClassPathXmlApplicationContext("/context.xml");
        SpringApplication.run(Server.class, args);
    }
}
