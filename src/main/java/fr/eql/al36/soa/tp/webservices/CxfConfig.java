package fr.eql.al36.soa.tp.webservices;
import fr.eql.al36.soa.tp.webservices.service.CalculTVA;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration
@EnableAutoConfiguration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class CxfConfig {

    @Autowired
    private ApplicationContext applicationContext;

    // Replaces the need for web.xml
    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext context) {
        return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
    }

    @Autowired
    private CalculTVA calculTVA; //service spring interne

    //@Autowired
    //private IS2 s2;

    // Replaces cxf-servlet.xml
    @Bean
    // <jaxws:endpoint id="conversionServiceEndpoint" implementor="#conversionImpl" address="/conversion"/>
    public EndpointImpl conversionServiceEndpoint() {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        EndpointImpl endpoint = new EndpointImpl(bus, calculTVA /* implementor */);
        endpoint.publish("/calculTVA");
        //URL soap complete:
        //http://localhost:8484/webservices/soap/calculTVA
        //http://localhost:8484/webservices/soap/calculTVA?wsdl
        return endpoint;
    }
}