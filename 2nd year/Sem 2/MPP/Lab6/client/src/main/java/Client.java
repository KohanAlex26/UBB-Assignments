import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.SportiveServiceInterface;

import service.SportiveTrainerServiceInterface;
import service.TeamServiceInterface;
import service.TrainerServiceInterface;
import ui.Console;

import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "config"
                );
        Arrays.asList(context.getBeanDefinitionNames()).forEach(e -> System.out.println(e));
        System.out.println("\n\n");

        SportiveServiceInterface sportiveService = context.getBean(SportiveServiceInterface.class);
        TrainerServiceInterface trainerService = context.getBean(TrainerServiceInterface.class);
        SportiveTrainerServiceInterface sportiveTrainerService = context.getBean(SportiveTrainerServiceInterface.class);
        TeamServiceInterface teamService = context.getBean(TeamServiceInterface.class);

        Console console = new Console(sportiveService, trainerService, sportiveTrainerService, teamService);
        //Console console = new Console();

        console.run();
    }
}
