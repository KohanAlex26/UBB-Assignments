package config;

import domain.validators.TrainerValidator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import repository.TrainerRepository;
import service.TrainerServiceInterface;
import service.TrainerServiceServer;

@Configuration
public class TrainerServerConfig implements ApplicationContextAware {
    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    @Bean
    RmiServiceExporter rmiTrainerServiceExporter() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("TrainerService");
        rmiServiceExporter.setServiceInterface(TrainerServiceInterface.class);
        rmiServiceExporter.setService(trainerService());
        return rmiServiceExporter;
    }

    @Bean
    TrainerServiceInterface trainerService() {
//        TrainerRepository trainerRepository  = (TrainerRepository) context.getBean("trainerRepository");
//        return new TrainerServiceServer(new TrainerValidator(), trainerRepository);
        return new TrainerServiceServer();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
