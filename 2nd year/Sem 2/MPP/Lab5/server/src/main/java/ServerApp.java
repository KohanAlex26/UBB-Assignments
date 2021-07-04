import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Team;
import domain.entities.Trainer;
import domain.validators.SportiveTrainerValidator;
import domain.validators.SportiveValidator;
import domain.validators.TeamValidator;
import domain.validators.TrainerValidator;

import repository.*;
import service.SportiveService;
import service.SportiveTrainerService;
import service.TeamService;
import service.TrainerService;
import tcp.MessageHandler;
import tcp.TcpServer;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApp {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Repository<Long, Sportive> sportiveRepository = new SportiveSqlRepository(new SportiveValidator(), "./files/credentials/alex.txt");
        Repository<Long, SportiveTrainer> sportiveTrainerRepository = new SportiveTrainerSqlRepository(new SportiveTrainerValidator(), "./files/credentials/alex.txt");
        Repository<Long, Trainer> trainerRepository = new TrainerSqlRepository(new TrainerValidator(), "./files/credentials/alex.txt");
        Repository<Long, Team> teamRepository = new TeamSqlRepository(new TeamValidator(), "./files/credentials/alex.txt");

        SportiveService sportiveService = new SportiveService(executorService, sportiveRepository);
        sportiveService.setSportiveService(sportiveService);

        SportiveTrainerService sportiveTrainerService = new SportiveTrainerService(executorService, sportiveTrainerRepository, sportiveRepository, trainerRepository);
        sportiveTrainerService.setSportiveTrainerService(sportiveTrainerService);

        TrainerService trainerService = new TrainerService(executorService, trainerRepository);
        trainerService.setTrainerService(trainerService);

        //to do services for team
        TeamService teamService = new TeamService(executorService, teamRepository);
        teamService.setTeamService(teamService);

        MessageHandler messageHandler = new MessageHandler(sportiveService, trainerService, sportiveTrainerService, teamService);
        TcpServer server = new TcpServer(executorService);

        server.addHandler("add sportive", messageHandler::addSportive);
        server.addHandler("delete sportive", messageHandler::deleteSportive);
        server.addHandler("update sportive", messageHandler::updateSportive);
        server.addHandler("filter sportives by age", messageHandler::filterByAge);
        server.addHandler("filter sportives by first name", messageHandler::filterByFirstName);
        server.addHandler("filter sportives by team id", messageHandler::filterByTeamId);
        server.addHandler("print all sportives", messageHandler::printAllSportives);
//        server.addHandler("find one sportive", messageHandler::findOneSportive);
//        server.addHandler("print all", messageHandler::);
        server.addHandler("associate sportive-trainer", messageHandler::associateSportiveTrainer);
        server.addHandler("dissociate sportive-trainer",  messageHandler::dissociateSportiveTrainer);
        server.addHandler("trainers of one sportive", messageHandler::allTrainersOfOneSportive);
        server.addHandler("sportives of one trainer", messageHandler::allSportivesOfOneTrainer);
        server.addHandler("training types of one sportive", messageHandler::allTrainingTypesOfOneSportive);
        server.addHandler("most expensive training", messageHandler::mostExpensiveTraining);
        server.addHandler("all sportives-trainers", messageHandler::printAllSportivesTrainers);
        // trainer
        server.addHandler("add trainer", messageHandler::addTrainer);
        server.addHandler("delete trainer", messageHandler::deleteTrainer);
        server.addHandler("update trainer", messageHandler::updateTrainer);
        server.addHandler("print all trainers", messageHandler::printAllTrainers);
        server.addHandler("filter trainers by name", messageHandler::filterTrainersByName);
        //team
        server.addHandler("add team", messageHandler::addTeam);
        server.addHandler("update team", messageHandler::updateTeam);
        server.addHandler("delete team", messageHandler::deleteTeam);
        server.addHandler("print all teams", messageHandler::printAllTeams);
        server.addHandler("filter teams by name", messageHandler::filterTeamsByName);



        System.out.println("Server started");
        server.startServer();
        executorService.shutdown();
        System.out.println("Server stopped");
    }
}
