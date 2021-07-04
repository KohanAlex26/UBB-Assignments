import service.*;
import tcp.TcpClient;
import ui.Console;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String[] args) {
        System.out.println("Client started the process");

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        TcpClient tcpClient = new TcpClient();
        SportiveServiceInterface sportiveService = new SportiveService(executorService,tcpClient);
        TrainerServiceInterface trainerService = new TrainerService(executorService, tcpClient);
        SportiveTrainerServiceInterface sportiveTrainerService = new SportiveTrainerService(executorService, tcpClient);
        TeamServiceInterface teamService = new TeamService(executorService, tcpClient);

        ExecutorService executorServiceUi = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Console ui = new Console(executorServiceUi, sportiveService, trainerService, sportiveTrainerService, teamService);
        ui.run();

        executorService.shutdown();
        executorServiceUi.shutdown();
        System.out.println("Client ended the process.");
    }
}
