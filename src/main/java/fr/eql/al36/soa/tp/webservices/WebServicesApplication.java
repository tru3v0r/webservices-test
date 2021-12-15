package fr.eql.al36.soa.tp.webservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebServicesApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WebServicesApplication.class);
        app.setAdditionalProfiles("initDataSet");
        ConfigurableApplicationContext context = app.run(args);

        System.out.println("http://localhost:8484/webservices/");
    }

}
