package config;

import domain.validators.SportiveValidator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import repository.SportiveRepository;
import service.SportiveServiceServer;
import service.SportiveServiceInterface;


@Configuration
public class SportiveServerConfig implements ApplicationContextAware {

    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    @Bean
    RmiServiceExporter rmiStudentServiceExporter() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("SportiveService");
        rmiServiceExporter.setServiceInterface(SportiveServiceInterface.class);
        rmiServiceExporter.setService(sportiveService());
        return rmiServiceExporter;
    }

    @Bean
    SportiveServiceInterface sportiveService() {
//        SportiveRepository sportiveRepository  = (SportiveRepository) context.getBean("sportiveRepository");
//        return new SportiveServiceServer(new SportiveValidator(), sportiveRepository);
        return new SportiveServiceServer();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
