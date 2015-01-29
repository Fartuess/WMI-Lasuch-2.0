package pl.edu.amu.lasuch.solrConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.amu.lasuch.solrConnector.service.SolrUpdaterService;

@ComponentScan
@EnableAutoConfiguration
@RestController
public class SolrConnector {
    public static final String EXIT = "exit";
    
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
        SolrUpdaterService solrUpdaterService = (SolrUpdaterService) context.getBean("solrUpdaterService");
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String in = "";
        
        while (!in.equalsIgnoreCase(EXIT)) {
           in = bufferedReader.readLine();
        }
    }
}
