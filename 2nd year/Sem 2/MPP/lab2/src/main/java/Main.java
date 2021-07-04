import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Team;
import domain.entities.Trainer;
import domain.validators.SportiveTrainerValidator;
import domain.validators.SportiveValidator;
import domain.validators.TeamValidator;
import domain.validators.TrainerValidator;
import repository.InMemoryRepository;
import repository.Repository;
import repository.database.SportiveTrainerSqlRepository;
import repository.database.SportiveSqlRepository;
import repository.database.TeamSqlRepository;
import repository.database.TrainerSqlRepository;
import repository.file.SportiveFileRepository;
import repository.file.SportiveTrainerFileRepository;
import repository.file.TeamFileRepository;
import repository.file.TrainerFileRepository;
import repository.xml.SportiveTrainerXmlRepository;
import repository.xml.SportiveXmlRepository;
import repository.xml.TeamXmlRepository;
import repository.xml.TrainerXmlRepository;
import service.SportiveService;
import service.SportiveTrainerService;
import service.TeamService;
import service.TrainerService;
import ui.Console;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        System.out.println("Ciorba de vacuta");

        try {
            // validators
            SportiveValidator sportiveValidator= new SportiveValidator();
            TrainerValidator trainerValidator = new TrainerValidator();
            SportiveTrainerValidator sportiveTrainerValidator = new SportiveTrainerValidator();
            TeamValidator teamValidator = new TeamValidator();

            Scanner userInput = new Scanner(System.in);
            System.out.println("1/inMemory\n" +
                    "2/File\n" +
                    "3/XML\n" +
                    "4/SQL\n");
            String token = userInput.nextLine();
            switch (token) {
                case "1":
                    Repository<Long, Sportive> sportiveRepository = new InMemoryRepository<>(sportiveValidator);
                    Repository<Long, Trainer> trainerRepository = new InMemoryRepository<>(trainerValidator);
                    Repository<Long, SportiveTrainer> sportiveTrainerRepository = new InMemoryRepository<>(sportiveTrainerValidator);
                    Repository<Long, Team> teamRepository = new InMemoryRepository<>(teamValidator);

                    SportiveService sportiveService = new SportiveService(sportiveRepository,sportiveTrainerRepository);
                    TrainerService trainerService = new TrainerService(trainerRepository,sportiveTrainerRepository);
                    SportiveTrainerService sportiveTrainerService = new SportiveTrainerService(sportiveTrainerRepository, sportiveRepository, trainerRepository);
                    TeamService teamService = new TeamService(teamRepository);

                    initInMemory(sportiveService,trainerService,sportiveTrainerService,teamService);
                    Console console = new Console(sportiveService, trainerService, sportiveTrainerService, teamService);
                    console.displayHelp();
                    console.runConsole();
                    break;
                case "2":
                    Repository<Long, SportiveTrainer> sportiveTrainerRepositoryFile = new SportiveTrainerFileRepository(sportiveTrainerValidator, "./data/sportivesTrainers.txt");
                    Repository<Long, Trainer> trainerRepositoryFile = new TrainerFileRepository(trainerValidator, "./data/trainers.txt");
                    Repository<Long, Sportive> sportiveRepositoryFile = new SportiveFileRepository(sportiveValidator, "./data/sportive.txt");
                    Repository<Long, Team> teamRepositoryFile = new TeamFileRepository(teamValidator, "./data/team.txt");

                    SportiveService sportiveServiceFile = new SportiveService(sportiveRepositoryFile,sportiveTrainerRepositoryFile);
                    TrainerService trainerServiceFile = new TrainerService(trainerRepositoryFile,sportiveTrainerRepositoryFile);
                    SportiveTrainerService sportiveTrainerServiceFile = new SportiveTrainerService(sportiveTrainerRepositoryFile, sportiveRepositoryFile, trainerRepositoryFile);
                    TeamService teamServiceFile = new TeamService(teamRepositoryFile);

                    Console consoleFile = new Console(sportiveServiceFile, trainerServiceFile, sportiveTrainerServiceFile, teamServiceFile);
                    consoleFile.displayHelp();
                    consoleFile.runConsole();
                    break;
                case "3":
                    Repository<Long, SportiveTrainer> sportiveTrainerXmlRepository = new SportiveTrainerXmlRepository(sportiveTrainerValidator);
                    Repository<Long, Sportive> sportiveXmlRepository = new SportiveXmlRepository(sportiveValidator);
                    Repository<Long, Trainer> trainerXmlRepository = new TrainerXmlRepository(trainerValidator);
                    Repository<Long, Team> teamRepositoryXml = new TeamXmlRepository(teamValidator);

                    SportiveService sportiveServiceXml = new SportiveService(sportiveXmlRepository, sportiveTrainerXmlRepository);
                    TrainerService trainerServiceXml = new TrainerService(trainerXmlRepository, sportiveTrainerXmlRepository);
                    SportiveTrainerService sportiveTrainerServiceXml = new SportiveTrainerService(sportiveTrainerXmlRepository, sportiveXmlRepository, trainerXmlRepository);
                    TeamService teamServiceXml = new TeamService(teamRepositoryXml);

                    Console consoleXml = new Console(sportiveServiceXml, trainerServiceXml, sportiveTrainerServiceXml, teamServiceXml);
                    consoleXml.displayHelp();
                    consoleXml.runConsole();
                    break;
                case "4":
                   String url = "jdbc:postgresql://localhost:5432/Proiect";
                    String user = "postgres";
                    String password = "password";

                    //credintials for Sebi
                   // String url = "jdbc:postgresql://localhost:5432/proiect";
                    //String user = "postgres";
                    //String password = "bjmrggqt";


                    Repository<Long, Sportive> sportiveRepositorySql = new SportiveSqlRepository(sportiveValidator,url, user, password);
                    Repository<Long, SportiveTrainer> sportiveTrainerRepositorySql = new SportiveTrainerSqlRepository(sportiveTrainerValidator, url, user, password);
                    Repository<Long, Trainer> trainerRepositorySql = new TrainerSqlRepository(trainerValidator, url, user, password);
                    Repository<Long, Team> teamRepositorySql = new TeamSqlRepository(teamValidator, url, user, password);

                    //temporary for adding sportive
                    Team t=new Team ("a");
                    t.setId(5L);
                    teamRepositorySql.save(t);

                    SportiveService sportiveServiceSql = new SportiveService(sportiveRepositorySql, sportiveTrainerRepositorySql);
                    TrainerService trainerServiceSql = new TrainerService(trainerRepositorySql, sportiveTrainerRepositorySql);
                    SportiveTrainerService sportiveTrainerServiceSql = new SportiveTrainerService(sportiveTrainerRepositorySql, sportiveRepositorySql, trainerRepositorySql);
                    TeamService teamServiceSql = new TeamService(teamRepositorySql);

                    Console consoleSql = new Console(sportiveServiceSql, trainerServiceSql, sportiveTrainerServiceSql, teamServiceSql);
                    consoleSql.displayHelp();
                    consoleSql.runConsole();
                    break;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void initInMemory(SportiveService sportiveService,TrainerService trainerService,SportiveTrainerService sportiveTrainerService,TeamService teamService)
    {
            Sportive s1 = new Sportive("a","a",1,1);
            s1.setId(1L);
            Sportive s2 = new Sportive("b","b",2,2);
            s2.setId(2L);
            Sportive s3 = new Sportive("c","c",22,2);
            s3.setId(3L);
            Sportive s4 = new Sportive("d","d",5,1);
            s4.setId(4L);
            Sportive s5 = new Sportive("j","k",40,3);
            s5.setId(5L);

            Trainer t1 = new Trainer("a", "b", 25);
            t1.setId(1L);
            Trainer t2 = new Trainer("b", "b", 35);
            t2.setId(2L);
            Trainer t3 = new Trainer("c", "c", 23);
            t3.setId(3L);
            Trainer t4 = new Trainer("d", "e", 25);
            t4.setId(4L);
            Trainer t5 = new Trainer("e", "h", 35);
            t5.setId(5L);

            SportiveTrainer st1 = new SportiveTrainer(1L, 1L, "continuous", 50);
            st1.setId(1L);
            SportiveTrainer st2 = new SportiveTrainer(1L, 2L, "fartlek", 45);
            st2.setId(2L);
            SportiveTrainer st3 = new SportiveTrainer(1L, 3L, "weight", 70);
            st3.setId(3L);
            SportiveTrainer st4 = new SportiveTrainer(2L, 1L, "plyometric", 100);
            st4.setId(4L);
            SportiveTrainer st5 = new SportiveTrainer(2L, 3L, "weight", 120);
            st5.setId(5L);

            Team team1 = new Team("Robo");
            team1.setId(1L);
            Team team2 = new Team("Panselutele");
            team2.setId(2L);
            Team team3 = new Team("PisicileSalbatice");
            team3.setId(3L);
            Team team4 = new Team("3pi");
            team4.setId(4L);
            Team team5 = new Team("Oaia lui Vasile");
            team5.setId(5L);

            sportiveService.addSportive(s1);
            sportiveService.addSportive(s2);
            sportiveService.addSportive(s3);
            sportiveService.addSportive(s4);
            sportiveService.addSportive(s5);


            trainerService.addTrainer(t1);
            trainerService.addTrainer(t2);
            trainerService.addTrainer(t3);
            trainerService.addTrainer(t4);
            trainerService.addTrainer(t5);

            sportiveTrainerService.associateSportiveTrainer(1L,s1, t1, "continuous", 50);
            sportiveTrainerService.associateSportiveTrainer(2L,s1, t2, "fartlek", 45);
            sportiveTrainerService.associateSportiveTrainer(3L,s1, t3, "weight", 70);
            sportiveTrainerService.associateSportiveTrainer(4L,s2, t1, "plyometric", 100);
            sportiveTrainerService.associateSportiveTrainer(5L,s2, t3, "weight", 120);

            teamService.addTeam(team1);
            teamService.addTeam(team2);
            teamService.addTeam(team3);
            teamService.addTeam(team4);
            teamService.addTeam(team5);

    }
}
