import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.SportiveServiceServer;
import service.SportiveTrainerServiceServer;
import service.TeamServiceServer;
import service.TrainerServiceServer;


public class Server {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("config");

        System.out.println("\nServer started\n");

    }
}
