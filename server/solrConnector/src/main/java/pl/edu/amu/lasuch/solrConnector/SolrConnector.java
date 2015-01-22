package pl.edu.amu.lasuch.solrConnector;

import java.io.IOException;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan
@EnableAutoConfiguration
@RestController
public class SolrConnector {
    public static final String EXIT = "exit";
    
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
    }
}
