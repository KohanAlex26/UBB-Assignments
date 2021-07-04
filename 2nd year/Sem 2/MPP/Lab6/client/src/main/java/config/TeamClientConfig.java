package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import service.TeamServiceClient;
import service.TeamServiceInterface;

@Configuration
public class TeamClientConfig {

    @Bean
    RmiProxyFactoryBean rmiTeamProxyFactoryBean(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(TeamServiceInterface.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/TeamService");
        return rmiProxyFactoryBean;

    }

//    @Bean
//    TeamServiceInterface teamServiceInterface() {
//        return new TeamServiceClient();
//    }


}
