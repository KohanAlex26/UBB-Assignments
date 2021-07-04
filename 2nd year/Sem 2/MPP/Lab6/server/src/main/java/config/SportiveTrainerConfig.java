package config;

import domain.validators.SportiveTrainerValidator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import repository.SportiveRepository;
import repository.SportiveTrainerRepository;
import repository.TrainerRepository;
import service.SportiveTrainerServiceServer;
import service.SportiveTrainerServiceInterface;

@Configuration
public class SportiveTrainerConfig implements ApplicationContextAware {

    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    @Bean
    RmiServiceExporter rmiSportiveTrainerServiceExporter() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("SportiveTrainerService");
        rmiServiceExporter.setServiceInterface(SportiveTrainerServiceInterface.class);
        rmiServiceExporter.setService(sportiveTrainerService());
        return rmiServiceExporter;
    }

    @Bean
    SportiveTrainerServiceInterface sportiveTrainerService() {
//        SportiveTrainerRepository sportiveTrainerRepository  = (SportiveTrainerRepository) context.getBean("sportiveTrainerRepository");
//        SportiveRepository sportiveRepository  = (SportiveRepository) context.getBean("sportiveRepository");
//        TrainerRepository trainerRepository  = (TrainerRepository) context.getBean("trainerRepository");
//        return new SportiveTrainerServiceServer(new SportiveTrainerValidator(), sportiveTrainerRepository, sportiveRepository, trainerRepository);
        return new SportiveTrainerServiceServer();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
