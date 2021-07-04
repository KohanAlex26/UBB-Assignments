package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import service.SportiveServiceInterface;

@Configuration
public class SportiveClientConfig {
    @Bean
    RmiProxyFactoryBean rmiSportiveProxyFactoryBean(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(SportiveServiceInterface.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/SportiveService");
        return rmiProxyFactoryBean;
    }


}
