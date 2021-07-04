package config;

import domain.entities.Team;
import domain.validators.TeamValidator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import repository.RepositoryInterface;
import repository.TeamRepository;
import service.TeamServiceInterface;
import service.TeamServiceServer;

@Configuration
public class TeamServerConfig implements ApplicationContextAware {
    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    @Bean
    RmiServiceExporter rmiTeamServiceExporter() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("TeamService");
        rmiServiceExporter.setServiceInterface(TeamServiceInterface.class);
        rmiServiceExporter.setService(teamService());
        return rmiServiceExporter;
    }

    @Bean
    TeamServiceServer teamService() {
        //TeamRepository teamRepository  = (TeamRepository) context.getBean("teamRepository");
        //return new TeamServiceServer(new TeamValidator(), teamRepository);

        return new TeamServiceServer();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
