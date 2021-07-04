package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import service.TrainerServiceInterface;

@Configuration
public class TrainerClientConfig {
    @Bean
    RmiProxyFactoryBean rmiTrainerProxyFactoryBean(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(TrainerServiceInterface.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/TrainerService");
        return rmiProxyFactoryBean;
    }
}
