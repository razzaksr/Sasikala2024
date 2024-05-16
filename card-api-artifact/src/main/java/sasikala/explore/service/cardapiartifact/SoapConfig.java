package sasikala.explore.service.cardapiartifact;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class SoapConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean servletRegistration(ApplicationContext applicationContext){
        MessageDispatcherServlet messageDispatcherServlet=new MessageDispatcherServlet();
        messageDispatcherServlet.setTransformWsdlLocations(true);
        messageDispatcherServlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean<>(messageDispatcherServlet,"/cardrepo/*");
    }

    @Bean(name = "cards")
    public DefaultWsdl11Definition conversion(XsdSchema cardSchema){
        DefaultWsdl11Definition defaultWsdl11Definition=new DefaultWsdl11Definition();
        defaultWsdl11Definition.setTargetNamespace("http://card.soap");
        defaultWsdl11Definition.setPortTypeName("CardPort");
        defaultWsdl11Definition.setLocationUri("/cardrepo");
        defaultWsdl11Definition.setSchema(cardSchema);
        return defaultWsdl11Definition;
    }
    @Bean
    public XsdSchema cardSchema(){
        return new SimpleXsdSchema(new ClassPathResource("creditcard.xsd"));
    }
}
