package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import service.SportiveTrainerServiceInterface;

@Configuration
public class SportiveTrainerClientConfig {
    @Bean
    RmiProxyFactoryBean rmiSportiveTrainerProxyFactoryBean(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(SportiveTrainerServiceInterface.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/SportiveTrainerService");
        return rmiProxyFactoryBean;
    }

}
