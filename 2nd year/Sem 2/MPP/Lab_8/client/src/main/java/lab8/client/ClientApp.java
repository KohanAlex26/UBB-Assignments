package lab8.client;

import lab8.core.enums.TrainingType;
import lab8.core.exceptions.MyException;
import lab8.core.model.Sportive;
import lab8.core.model.SportiveTrainer;
import lab8.core.model.Team;
import lab8.core.model.Trainer;
import lab8.web.dto.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class ClientApp {
    public static final String TEAMS_URL = "http://localhost:8080/api/teams";
    public static final String TRAINERS_URL = "http://localhost:8080/api/trainers";
    public static final String SPORTIVES_URL = "http://localhost:8080/api/sportives";
    public static final String SPORTIVESTRAINERS_URL = "http://localhost:8080/api/sportives-trainers";
    private static void printMenu() {
        System.out.println("1. Teams");
        System.out.println("2. Sportives");
        System.out.println("3. Trainers");
        System.out.println("4. SportivesTrainers");
        System.out.println("0. Exit");
    }

    public static void runConsole(RestTemplate restTemplate){
        try{
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            printMenu();
            int cmd = Integer.parseInt(bufferRead.readLine());
            while(cmd != 0){
                switch(cmd){
                    case 1:
                        runTeamConsole(restTemplate);
                        break;
                    case 2:
                        runSportiveConsole(restTemplate);
                        break;
                    case 3:
                        runTrainerConsole(restTemplate);
                        break;
                    case 4:
                        runSportiveTrainerConsole(restTemplate);
                        break;
                }
                printMenu();
                cmd = Integer.parseInt(bufferRead.readLine());
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public static void printTeamMenu(){
        System.out.println("1. Add team");
        System.out.println("2. Delete Team");
        System.out.println("3. Update Team");
        System.out.println("4. Print all teams");
        System.out.println("5. Filter teams by name");
        System.out.println("0. Exit");
    }
    public static void runTeamConsole(RestTemplate restTemplate){
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            printTeamMenu();
            int cmd = Integer.parseInt(bufferRead.readLine());
            Long id;
            while(cmd != 0){
                switch(cmd){
                    case 1:
                        System.out.println("1. Add team");
                        TeamDto team = readTeam();
                        TeamDto saved = restTemplate.postForObject(
                                TEAMS_URL,
                                team,
                                TeamDto.class);
                        System.out.println("Team saved successfully.");
                        break;
                    case 2:
                        System.out.println("2. Delete Team");
                        printAllTeams(restTemplate);
                        id = Long.valueOf(readId());
                        restTemplate.delete(TEAMS_URL + "/{id}", id);
                        System.out.println("Team deleted successfully.");
                        break;
                    case 3:
                        System.out.println("3. Update Team");
                        TeamDto tt = readTeam();
                        id = Long.valueOf(readId());
                        TeamsDto allTeams = restTemplate.getForObject(TEAMS_URL, TeamsDto.class);
                        Long finalId = id;
                        TeamDto t = allTeams.getTeams()
                                .stream()
                                .filter(x -> x.getId().equals(finalId))
                                .findFirst().orElse(null);
                        t.setTeamName(tt.getTeamName());
                        restTemplate.put(TEAMS_URL + "/{id}", t, t.getId());
                        System.out.println("Team updated successfully.");
                        break;
                    case 4:
                        System.out.println("4. Print all teams");
                        printAllTeams(restTemplate);
                        break;
                    case 5:
                        System.out.println("5. Filter teams by name");
                        String name = readName();
                        System.out.println("You've entered: " + name);

                        TeamsDto allTeams2 = restTemplate.getForObject(TEAMS_URL, TeamsDto.class);
                        allTeams2.getTeams()
                                .stream()
                                .filter(ts -> ts.getTeamName().contains(name))
                                .forEach(System.out::println);
                        break;
                }
                printTeamMenu();
                cmd = Integer.parseInt(bufferRead.readLine());
            }
        }catch(IOException | NullPointerException ex){
            ex.printStackTrace();
        }
    }
    public static void printAllTeams(RestTemplate restTemplate){
        TeamsDto allTeams =restTemplate.getForObject(TEAMS_URL, TeamsDto.class);
        System.out.println(allTeams);
    }

    public static void printTrainerMenu(){
        System.out.println("1. Add trainer");
        System.out.println("2. Delete trainer");
        System.out.println("3. Update trainer");
        System.out.println("4. Print all trainers");
        System.out.println("5. Filter trainers by name");
        System.out.println("0. Exit");
    }
    public static void runTrainerConsole(RestTemplate restTemplate){
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            printTrainerMenu();
         int cmd = Integer.parseInt(bufferRead.readLine());
            while(cmd != 0){
                switch(cmd){
                    case 1:
                        System.out.println("1. Add trainer");
                        TrainerDto trainer = readTrainer();
                        TrainerDto saved = restTemplate.postForObject(
                                TRAINERS_URL,
                                trainer,
                                TrainerDto.class);
                        System.out.println("Trainer saved successfully.");
                        break;
                    case 2:
                        System.out.println("2. Delete trainer");
                        printAllTrainers(restTemplate);
                        Long id = Long.valueOf(readId());
                        restTemplate.delete(TRAINERS_URL + "/{id}", id);
                        System.out.println("Trainer deleted successfully.");
                        break;
                    case 3:
                        System.out.println("3. Update trainer");
                        TrainerDto tt = readTrainer();
                        id = Long.valueOf(readId());
                        TrainersDto allTrainers = restTemplate.getForObject(TRAINERS_URL, TrainersDto.class);
                        Long finalId = id;
                        TrainerDto t = allTrainers.getTrainers()
                                .stream()
                                .filter(x -> x.getId().equals(finalId))
                                .findFirst().orElse(null);
                        t.setFirstName(tt.getFirstName());
                        t.setLastName(tt.getLastName());
                        t.setAge(tt.getAge());
                        restTemplate.put(TRAINERS_URL + "/{id}", t, t.getId());
                        System.out.println("Trainer updated successfully.");
                        break;
                    case 4:
                        System.out.println("4. Print all trainers");
                        printAllTrainers(restTemplate);
                        break;
                    case 5:
                        System.out.println("5. Filter trainers by name");
                        String name = readName();
                        System.out.println("You've entered: " + name);

                        TrainersDto allTrainers2 = restTemplate.getForObject(TRAINERS_URL, TrainersDto.class);
                        allTrainers2.getTrainers()
                                .stream()
                                .filter(tr -> tr.getFirstName().contains(name))
                                .forEach(System.out::println);
                        break;
                    case 0:
                        break;
                }
                printTrainerMenu();
                cmd = Integer.parseInt(bufferRead.readLine());
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public static void printAllTrainers(RestTemplate restTemplate){
        TrainersDto allTrainers =restTemplate.getForObject(TRAINERS_URL, TrainersDto.class);
        System.out.println(allTrainers);
    }

    public static void printSportiveTrainerMenu(){
        System.out.println("1. Associate sportive-trainer");
        System.out.println("2. Dissociate sportive-trainer");
        System.out.println("3. All trainers of one sportive");
        System.out.println("4. All sportives of one trainer");
        System.out.println("5. All training types of one sportive");
        System.out.println("6. Most expensive training");
        System.out.println("7. All sportives-trainers");
        System.out.println("0. Exit");
    }
    public static void runSportiveTrainerConsole(RestTemplate restTemplate){
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            printSportiveTrainerMenu();
            int cmd = Integer.parseInt(bufferRead.readLine());
            while(cmd != 0){
                switch(cmd){
                    case 1:
                        System.out.println("1. Associate sportive-trainer");
                        SportiveTrainerDto st = readSportiveTrainer(restTemplate);
                        SportiveTrainerDto saved = restTemplate.postForObject(
                                SPORTIVESTRAINERS_URL,
                                st,
                                SportiveTrainerDto.class);
                        System.out.println("Association made successfully!");
                        break;
                    case 2:
                        System.out.println("2. Dissociate sportive-trainer");
                        printAllSportivesTrainers(restTemplate);
                        Long id = Long.valueOf(readId());
                        restTemplate.delete(SPORTIVESTRAINERS_URL + "/{id}", id);
                        System.out.println("Dissociation made succesfully!");
                        break;
                    case 3:
                        System.out.println("3. All trainers of one sportive");
                        printAllSportives(restTemplate);
                        Long sportiveID = Long.valueOf(readId());
                        SportivesTrainersDto all = restTemplate.getForObject(SPORTIVESTRAINERS_URL, SportivesTrainersDto.class);
                        List<SportiveTrainerDto> stt = all.getSportivesTrainers().stream()
                                .filter(sp -> sp.getSportiveID().equals(sportiveID))
                                .collect(Collectors.toList());
                        stt.stream()
                                .map(SportiveTrainerDto::getTrainerID)
                                .collect(Collectors.toList())
                                .forEach(System.out::println);
                        break;
                    case 4:
                        System.out.println("4. All sportives of one trainer");
                        printAllTrainers(restTemplate);
                        Long trainerID = Long.valueOf(readId());
                        SportivesTrainersDto all2 = restTemplate.getForObject(SPORTIVESTRAINERS_URL, SportivesTrainersDto.class);
                        List<SportiveTrainerDto> sttt = all2.getSportivesTrainers().stream()
                                .filter(sp -> sp.getTrainerID().equals(trainerID))
                                .collect(Collectors.toList());
                        sttt.stream()
                                .map(SportiveTrainerDto::getSportiveID)
                                .collect(Collectors.toList())
                                .forEach(System.out::println);
                        break;
                    case 5:
                        System.out.println("5. All training types of one sportive");
                        printAllSportives(restTemplate);
                        Long sportiveID2 = Long.valueOf(readId());
                        SportivesTrainersDto all3 = restTemplate.getForObject(SPORTIVESTRAINERS_URL, SportivesTrainersDto.class);
                        List<SportiveTrainerDto> st1 = all3.getSportivesTrainers().stream()
                                .filter(sp -> sp.getSportiveID().equals(sportiveID2))
                                .collect(Collectors.toList());
                        st1.stream()
                                .map(SportiveTrainerDto::getTrainingType)
                                .collect(Collectors.toList())
                                .forEach(System.out::println);
                        break;
                    case 6:
                        System.out.println("6. Most expensive training");
                        SportivesTrainersDto all4 = restTemplate.getForObject(SPORTIVESTRAINERS_URL, SportivesTrainersDto.class);
                        Set<Integer> costs = all4.getSportivesTrainers().stream()
                                .map(SportiveTrainerDto::getCost)
                                .collect(Collectors.toSet());
                        int maxCost = Collections.max(costs);
                        System.out.println(all4.getSportivesTrainers().stream()
                                .filter(x -> x.getCost() == maxCost).findAny().orElseThrow());
                        break;
                    case 7:
                        System.out.println("7. All sportives-trainers");
                        printAllSportivesTrainers(restTemplate);
                        break;
                }
                printSportiveTrainerMenu();
                cmd = Integer.parseInt(bufferRead.readLine());
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void printAllSportivesTrainers(RestTemplate restTemplate){
        SportivesTrainersDto all =restTemplate.getForObject(SPORTIVESTRAINERS_URL, SportivesTrainersDto.class);
        System.out.println(all);
    }


    public static SportiveTrainerDto readSportiveTrainer(RestTemplate restTemplate){
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Choose a sportive: ");
            printAllSportives(restTemplate);
            Long sportiveID = Long.valueOf(bufferRead.readLine());
            System.out.println("Choose a trainer: ");
            printAllTrainers(restTemplate);
            Long trainerID = Long.valueOf(bufferRead.readLine());
            List<TrainingType> types = Arrays.asList(TrainingType.values());
            types.forEach(System.out::println);
            System.out.println("Choose a training type: ");
            String trainingType = bufferRead.readLine();
            System.out.println("Choose a cost: ");
            int cost = Integer.parseInt(bufferRead.readLine());
            return new SportiveTrainerDto(sportiveID, trainerID, cost, trainingType);
        }catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
    }

    private static TrainerDto readTrainer() {
        System.out.println("Read trainer {firstName, lastName, age}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("firstName(characters): ");
            String firstName = bufferRead.readLine();
            System.out.println("lastName(characters): ");
            String lastName = bufferRead.readLine();
            System.out.println("Age(integer greater than 0): ");
            int age = Integer.parseInt(bufferRead.readLine());// ...

            TrainerDto trainer = new TrainerDto(firstName,lastName,age);
            //trainer.setId(1L);

            return trainer;
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch (NumberFormatException ex) {
            System.out.println("error");
        }
        return null;
    }
    private static TeamDto readTeam() throws IOException, MyException{
        System.out.println("Read team {teamId, teamName}");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try{

            System.out.println("teamName (at least a character)");
            String teamName = bufferedReader.readLine();

            TeamDto team = new TeamDto(teamName);
            //team.setId(id);

            return team;
        }
        catch (IOException e){
            throw new IOException("*Invalid team attributes");
        }
        catch (MyException e){
            throw new MyException("*Invalid team attributes");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

   public static void printSportiveMenu(){
       System.out.println("1. Add sportive");
       System.out.println("2. Delete sportive");
       System.out.println("3. Update sportive");
       System.out.println("4. Print all sportives");
       System.out.println("5. Filter sportives by age");
       System.out.println("6. Filter sportives by first name");
       System.out.println("7. Filter sportives by team id");
       System.out.println("0. Exit");
   }
    public static void runSportiveConsole(RestTemplate restTemplate){
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            printSportiveMenu();
            int cmd = Integer.parseInt(bufferRead.readLine());
            while(cmd != 0){
                switch(cmd){
                    case 1:
                        System.out.println("1. Add sportive");
                        addSportive(restTemplate);
                        break;
                    case 2:
                        System.out.println("2. Delete sportive");
                        deleteSportive(restTemplate);
                        break;
                    case 3:
                        System.out.println("3. Update sportive");
                        updateSportive(restTemplate);
                        break;
                    case 4:
                        System.out.println("4. Print all sportives");
                        printAllSportives(restTemplate);
                        break;
                    case 5:
                        System.out.println("5. Filter sportives by age");
                        int age = Integer.parseInt(readAge());
                        System.out.println("You've entered: " + age);

                        SportivesDto allSportives = restTemplate.getForObject(SPORTIVES_URL, SportivesDto.class);
                        List<SportiveDto> list=allSportives.getSportives()
                                .stream()
                                .filter(sportive -> sportive.getAge()<=age)
                                .collect(Collectors.toList());
                        if(list.isEmpty())
                            System.out.println("There is no sportive with age less than "+ age);
                        else
                            list.forEach(System.out::println);
                        break;
                    case 6:
                        System.out.println("6. Filter sportives by first name");
                        String firstName = readName();
                        System.out.println("You've entered: " + firstName);

                        SportivesDto allSportives2 = restTemplate.getForObject(SPORTIVES_URL, SportivesDto.class);
                        List<SportiveDto> list2=allSportives2.getSportives()
                                .stream()
                                .filter(sportive -> sportive.getFirstName().contains(firstName))
                                .collect(Collectors.toList());
                        if(list2.isEmpty())
                            System.out.println("There is no sportive with first name "+ firstName);
                        else
                            list2.forEach(System.out::println);
                        break;
                    case 7:
                        System.out.println("7. Filter sportives by team id");
                        int teamId = Integer.parseInt(readTeamId());
                        System.out.println("You've entered: " + teamId);

                        SportivesDto allSportives3 = restTemplate.getForObject(SPORTIVES_URL, SportivesDto.class);
                        List<SportiveDto> list3=allSportives3.getSportives()
                                .stream()
                                .filter(sportive -> sportive.getTid()==teamId)
                                .collect(Collectors.toList());

                        if(list3.isEmpty())
                            System.out.println("There is no sportive with team id "+ teamId);
                        else
                            list3.forEach(System.out::println);
                        break;
                }
                printSportiveMenu();
                cmd = Integer.parseInt(bufferRead.readLine());
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void printAllSportives(RestTemplate restTemplate){
        SportivesDto allSportives =restTemplate.getForObject(SPORTIVES_URL, SportivesDto.class);
        System.out.println(allSportives);
    }

    public static void addSportive(RestTemplate restTemplate){
        SportiveDto sportive = readSportive();
        try {
            SportiveDto savedSportive = restTemplate.postForObject(SPORTIVES_URL,
                    sportive,
                    SportiveDto.class);
            System.out.println("saved sportive:");
            System.out.println(savedSportive);
        }
        catch(HttpServerErrorException e)
        {
            System.out.println("Team id invalid");
        }

        /*Sportive sportive = readSportive();

        try {
            sportiveService.addSportive(sportive);
            System.out.println("Sportive added successfully");
        } catch (MyException e) {
            System.out.println("Sportive couldn't be added");
            System.out.println(e.getMessage());
        }*/
    }

    public static void deleteSportive(RestTemplate restTemplate){
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            Long id = Long.parseLong(bufferRead.readLine());
            restTemplate.delete(SPORTIVES_URL + "/{id}", id);
            System.out.println("Sportive deleted successfully.");
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

    }

    public static void updateSportive(RestTemplate restTemplate){
        //Read the data that user want to be updated, together with the id
        SportiveDto sportive = readSportive();
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        Long id = Long.valueOf(readId());

        //Find the sportive with the given id
        SportivesDto allSportives = restTemplate.getForObject(SPORTIVES_URL, SportivesDto.class);
        SportiveDto sp = allSportives.getSportives()
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst().orElse(null);

        //Set the given values for the sportive
        sp.setFirstName(sportive.getFirstName());
        sp.setLastName(sportive.getLastName());
        sp.setAge(sportive.getAge());
        sp.setTid(sportive.getTid());

        //Update with restTemplate
        restTemplate.put(SPORTIVES_URL + "/{id}", sp, sp.getId());
        System.out.println("Sportive updated successfully.");
    }
    private static SportiveDto readSportive() {
        System.out.println("Read sportive {firstName, lastName, age, teamId}");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = br.readLine();
            if (line.equals("done")) {
                return null;
            }
            List<String> arguments = Arrays.stream(line.split(" "))
                    .filter(word -> !word.equals(""))
                    .collect(Collectors.toList());
            if (arguments.size() != 4)
                throw new MyException("Wrong number of arguments for reading student: (" + arguments.size() + " instead of 4");
            String firstname = arguments.get(0);
            String lastName = arguments.get(1);
            int age;
            try {
                age = Integer.parseInt(arguments.get(2));
            } catch (NumberFormatException nfe) {
                throw new MyException("Argument for age is not an integer");
            }
            int teamId;
            try {
                teamId = Integer.parseInt(arguments.get(3));
            } catch (NumberFormatException nfe) {
                throw new MyException("Argument for teamId is not an integer");
            }
            Sportive s = new Sportive(firstname,lastName,age,teamId);
            //s.setId(id);
            return new SportiveDto(s.getFirstName(),s.getLastName(),s.getAge(),s.getTid());
        } catch (MyException | IOException e) {
            System.out.println(e.getMessage());
            return readSportive();
        }
    }
    private static String readId() {
        System.out.println("Enter the id: ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            return String.valueOf(id);

        } catch (IOException | RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
        return String.valueOf(-1);
    }
    private static String readAge() {
        System.out.println("Enter the age ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long age = Long.valueOf(bufferRead.readLine());
            return String.valueOf(age);

        } catch (IOException | RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
        return String.valueOf(-1);
    }
    private static String readName() {
        System.out.println("Enter the name ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            String firstName = bufferRead.readLine();
            return firstName;

        } catch (IOException | RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
        return "not good";
    }

    private static String readTeamId() {
        System.out.println("Enter team id ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Integer teamId = Integer.valueOf(bufferRead.readLine());
            return String.valueOf(teamId);

        } catch (IOException | RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
        return String.valueOf(-1);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "lab8.client.config"
                );

        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        System.out.println("Hello client!");

        runConsole(restTemplate);
        System.out.println("Goodbye!");
    }
}
