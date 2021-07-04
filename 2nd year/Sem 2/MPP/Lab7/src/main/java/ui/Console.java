package ui;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Team;
import domain.entities.Trainer;
import domain.enums.TrainingType;
import domain.exceptions.MyException;
import domain.exceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.SportiveService;
import service.SportiveTrainerService;
import service.TeamService;
import service.TrainerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by radu.
 */
@Component
public class Console {
    @Autowired
    private SportiveService sportiveService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private SportiveTrainerService sportiveTrainerService;

    private Map<String, Runnable> commands;

    public void runConsole() {
        initCommands();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        this.printMenu();
        while(true){
            try {
                System.out.println(">>>");
                String command = br.readLine();
                if(!commands.containsKey(command))
                    throw new MyException("Invalid command");
                commands.get(command).run();
                //this.printMenu();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void initCommands(){
        commands = new HashMap<>();
        commands.put("exit", () -> System.exit(0));
        commands.put("1", this::addSportive);
        commands.put("add sportive", this::addSportive);
        commands.put("2", this::deleteSportive);
        commands.put("delete sportive", this::deleteSportive);
        commands.put("3", this::updateSportive);
        commands.put("update sportive", this::updateSportive);
        commands.put("4", this::printAllSportives);
        commands.put("print all sportives", this::printAllSportives);
        commands.put("5", this::filterSportivesByFirstName);
        commands.put("filter sportives by first name", this::filterSportivesByFirstName);
        commands.put("6", this::filterSportivesByAge);
        commands.put("filter sportives by age", this::filterSportivesByAge);
        commands.put("7", this::filterSportivesByTeamId);
        commands.put("filter sportives by team id", this::filterSportivesByTeamId);
        commands.put("8", this::addTrainer);
        commands.put("add trainer", this::addTrainer);
        commands.put("9", this::deleteTrainer);
        commands.put("delete trainer", this::deleteTrainer);
        commands.put("10", this::updateTrainer);
        commands.put("update trainer", this::updateTrainer);
        commands.put("11", this::printAllTrainers);
        commands.put("print all trainers", this::printAllTrainers);
        commands.put("12", this::filterTrainersByName);
        commands.put("filter trainers by name", this::filterTrainersByName);
        commands.put("13", this::associateSportiveTrainer);
        commands.put("associate sportive-trainer", this::associateSportiveTrainer);
        commands.put("14", this::dissociateSportiveTrainer);
        commands.put("dissociate sportive-trainer", this::dissociateSportiveTrainer);
        commands.put("15", this::trainersOfOneSportive);
        commands.put("trainers of one sportive", this::trainersOfOneSportive);
        commands.put("16", this::sportivesOfOneTrainer);
        commands.put("sportives of one trainer", this::sportivesOfOneTrainer);
        commands.put("17", this::trainingTypesOfOneSportive);
        commands.put("training types of one sportive", this::trainingTypesOfOneSportive);
        commands.put("18", this::mostExpensiveTraining);
        commands.put("most expensive training", this::mostExpensiveTraining);
        commands.put("19", this::allSportivesTrainers);
        commands.put("all sportives-trainers", this::allSportivesTrainers);
        commands.put("20", this::addTeam);
        commands.put("add team", this::addTeam);
        commands.put("21", this::deleteTeam);
        commands.put("delete team", this::deleteTeam);
        commands.put("22", this::updateTeam);
        commands.put("update team", this::updateTeam);
        commands.put("23", this::printAllTeams);
        commands.put("print all teams", this::printAllTeams);
        commands.put("24", this::filterTeamsByName);
        commands.put("filter teams by name", this::filterTeamsByName);
        commands.put("menu", this::printMenu);
    }

    public void printMenu(){
        System.out.println("\nSportive");
        System.out.println("----------");
        System.out.println("1. add sportive");
        System.out.println("2. delete sportive");
        System.out.println("3. update sportive");
        System.out.println("4. print all sportives");
        System.out.println("5. filter sportives by first name");
        System.out.println("6. filter sportives by age");
        System.out.println("7. filter sportives by team id");
        System.out.println("----------\n");
        System.out.println("Trainer");
        System.out.println("----------");
        System.out.println("8. add trainer");
        System.out.println("9. delete trainer");
        System.out.println("10. update trainer");
        System.out.println("11. print all trainers");
        System.out.println("12. filter trainers by name");
        System.out.println("----------\n");
        System.out.println("SportiveTrainer");
        System.out.println("----------");
        System.out.println("13. associate sportive-trainer");
        System.out.println("14. dissociate sportive-trainer");
        System.out.println("15. trainers of one sportive");
        System.out.println("16. sportives of one trainer");
        System.out.println("17. training types of one sportive");
        System.out.println("18. most expensive training");
        System.out.println("19. all sportives-trainers");
        System.out.println("----------\n");
        System.out.println("Team");
        System.out.println("----------");
        System.out.println("20. add team");
        System.out.println("21. delete team");
        System.out.println("22. update team");
        System.out.println("23. print all teams");
        System.out.println("24. filter teams by name");
        System.out.println("----------\n");
        System.out.println(" 0. exit");
    }




    private Trainer readTrainer() {
        System.out.println("Read trainer {firstName, lastName, age}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("id(integer greater than 0): ");
            Long id = Long.valueOf(bufferRead.readLine());// ...
            System.out.println("firstName(characters): ");
            String firstName = bufferRead.readLine();
            System.out.println("lastName(characters): ");
            String lastName = bufferRead.readLine();
            System.out.println("Age(integer greater than 0): ");
            int age = Integer.parseInt(bufferRead.readLine());// ...

            Trainer trainer = new Trainer(firstName,lastName,age);
            //trainer.setId(1L);

            return trainer;
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch (NumberFormatException ex) {
            System.out.println("error");
        }
        return null;
    }

    public void addTrainer(){
        Trainer trainer = readTrainer();

        try {
            trainerService.addTrainer(trainer);
            System.out.println("Trainer added successfully");
        } catch (MyException e) {
            System.out.println("Trainer couldn't be added");
            System.out.println(e.getMessage());
        }
    }

    public void deleteTrainer(){
        printAllTrainers();
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Give an id: ");
            Long id = Long.parseLong(bufferRead.readLine());
            Trainer trainer = new Trainer("-","-",1);
            trainer.setId(id);
            trainerService.deleteTrainer(trainer);
            System.out.println("Trainer was deleted successfully");

        } catch (MyException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateTrainer(){
        try {
            printAllTrainers();
            Trainer trainer = readTrainer();
            trainerService.updateTrainer(trainer);
            System.out.println("Trainer was updated successfully.");
        } catch (MyException  | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printAllTrainers() {
        List<Trainer> trainers = trainerService.getAllTrainers();
        if (trainers.isEmpty()) {
            System.out.println("There are no trainers!");
        } else {
            trainers.forEach(System.out::println);
        }
    }

    public void filterTrainersByName(){
        System.out.println("Enter name: ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            String name = bufferRead.readLine();
            List<Trainer> trainers = trainerService.filterTrainersByName(name);
            if (trainers.isEmpty()) {
                System.out.println("There are no trainers with name " + name);
            } else {
                trainers.forEach(System.out::println);
                System.out.println("Trainers filtered by name ! ");
            }
        } catch (MyException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private Sportive readSportive() {
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
                teamId = Integer.parseInt(arguments.get(4));
            } catch (NumberFormatException nfe) {
                throw new MyException("Argument for teamId is not an integer");
            }
            Sportive s = new Sportive(firstname,lastName,age,teamId);
            //s.setId(id);
            return s;
        } catch (MyException | IOException e) {
            System.out.println(e.getMessage());
            return readSportive();
        }
    }

    public void addSportive(){
        Sportive sportive = readSportive();

        try {
            sportiveService.addSportive(sportive);
            System.out.println("Sportive added successfully");
        } catch (MyException e) {
            System.out.println("Sportive couldn't be added");
            System.out.println(e.getMessage());
        }
    }

    public void deleteSportive(){
        printAllSportives();
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Give an id: ");
            Long id = Long.parseLong(bufferRead.readLine());
            Sportive sportive = new Sportive("-","-",1,1);
            sportive.setId(id);
            sportiveService.deleteSportive(sportive);
            System.out.println("Sportive was deleted successfully");

        } catch (MyException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateSportive(){
        try {
            printAllSportives();
            Sportive sportive = readSportive();
            sportiveService.updateSportive(sportive);
            System.out.println("Sportive was updated successfully.");
        } catch (MyException  | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printAllSportives(){
        List<Sportive> sportives = sportiveService.getAllSportives();
        if(sportives.isEmpty())
            System.out.println("There is no sportive ");
        else {
            sportives.forEach(System.out::println);
        }
    }

    public void filterSportivesByAge(){
        //to do
        System.out.println("Read age: ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            int age = Integer.parseInt(bufferRead.readLine());
            List<Sportive> sportives= sportiveService.filterSportivesByAge(age);
            if(sportives.isEmpty())
                System.out.println("There is no sportive with age less than "+ age);
            else
            {
                sportives.forEach(System.out::println);
                System.out.println("Sportvies filtered by age ! ");
            }
        } catch (ValidatorException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void filterSportivesByFirstName(){
        //to do
        System.out.println("Read first name: ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            String firstName = bufferRead.readLine();
            List<Sportive> sportives= sportiveService.filterSportivesByFirstName(firstName);
            if(sportives.isEmpty())
                System.out.println("There is no sportive with first name containing "+ firstName);
            else {
                sportives.forEach(System.out::println);
                System.out.println("Sportvies filtered by first name ! ");
            }

        } catch (ValidatorException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void filterSportivesByTeamId(){
        //to do
        System.out.println("Read team id: ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            int teamId = Integer.parseInt(bufferRead.readLine());
            List<Sportive> sportives= sportiveService.filterSportivesByTeamId(teamId);
            if(sportives.isEmpty())
                System.out.println("There is no sportive with team id  "+ teamId);
            else {
                sportives.forEach(System.out::println);
                System.out.println("Sportvies filtered by team id ! ");
            }

        } catch (ValidatorException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void associateSportiveTrainer() {
        System.out.println("Associate sportives with trainers: ");
        try {
            Scanner userInput = new Scanner(System.in);
            Sportive s = chooseAsportive();
            Trainer t = chooseAtrainer();
            List<TrainingType> types = Arrays.asList(TrainingType.values());
            types.forEach(System.out::println);
            System.out.println("Choose a training type: ");
            String trainingType = userInput.nextLine();
            System.out.println("Choose a cost: ");
            String cost = userInput.nextLine();
            SportiveTrainer st = new SportiveTrainer(s.getId(), t.getId(), trainingType, Integer.parseInt(cost));
            sportiveTrainerService.addSportiveTrainer(st);
            System.out.println("Association between a sportive and a trainer has been made successfully!");
        }catch(CancellationException ex){
            ex.printStackTrace();
        }
    }

    private void allSportivesTrainers() {
        List<SportiveTrainer> sts = sportiveTrainerService.getAllSportivesTrainers();
        if(sts.isEmpty()){
            System.out.println("There are no SportivesTrainers entities!");
        }
        else{
            sts.forEach(System.out::println);
        }
    }

    private void dissociateSportiveTrainer() {
        System.out.println("Dissociate sportives and trainers: ");
        Scanner userInput = new Scanner(System.in);
        allSportivesTrainers();
        System.out.println("Choose an association: ");
        String input = userInput.nextLine();
        Long id = Long.parseLong(input);
        SportiveTrainer st = new SportiveTrainer(id, 1L, 1L, "none", 1);
        st.setId(id);
        sportiveTrainerService.deleteSportiveTrainer(st);
        System.out.println("Dissociation of a sportive and a trainer has been made successfully!");
    }

    private void trainersOfOneSportive() {
        Scanner userInput = new Scanner(System.in);
        List<Sportive> sportives = sportiveService.getAllSportives();
        printAllSportives();
        System.out.println("Choose a sportive: ");
        String input = userInput.nextLine();
        Long id = Long.parseLong(input);
        Sportive s = sportives.stream().filter(sp -> sp.getId().equals(id)).findAny().orElseThrow();
        List<Trainer> trainers = sportiveTrainerService.allTrainersOfOneSportive(s);
        if(trainers.isEmpty()){
            System.out.println("There are no trainers associated with the sportive: " + s.toString());
        }
        else{
            System.out.println("All the trainers of " + s + ": ");
            trainers.forEach(System.out::println);
        }
    }

    private void sportivesOfOneTrainer() {
        Scanner userInput = new Scanner(System.in);
        List<Trainer> trainers = trainerService.getAllTrainers();
        printAllTrainers();
        System.out.println("Choose a trainer: ");
        String input = userInput.nextLine();
        Long id = Long.parseLong(input);
        Trainer t = trainers.stream().filter(sp -> sp.getId().equals(id)).findAny().orElseThrow();
        List<Sportive> sportives = sportiveTrainerService.allSportivesOfOneTrainer(t);
        if(sportives.isEmpty()){
            System.out.println("There are no sportives associated with the trainer: " + t.toString());
        }
        else{
            System.out.println("All the sportives of " + t + ": ");
            sportives.forEach(System.out::println);
        }
    }

    private void trainingTypesOfOneSportive() {
        Scanner userInput = new Scanner(System.in);
        List<Sportive> sportives = sportiveService.getAllSportives();
        printAllSportives();
        System.out.println("Choose a sportive: ");
        String input = userInput.nextLine();
        Long id = Long.parseLong(input);
        Sportive s = sportives.stream().filter(sp -> sp.getId().equals(id)).findAny().orElseThrow();
        List<String> trainingTypes = sportiveTrainerService.allTrainingTypesOfOneSportive(s);
        System.out.println("All training types of " + s + ": ");
        trainingTypes.forEach(System.out::println);
    }

    private void mostExpensiveTraining() {
        System.out.println("The most expensive training is : \n" + sportiveTrainerService.mostExpensiveTraining());
    }

    private Sportive chooseAsportive() {
            String input;
            List<Sportive> sportives = sportiveService.getAllSportives();
            Scanner userInput = new Scanner(System.in);
            printAllSportives();
            System.out.println("Choose a sportive: ");
            input = userInput.nextLine();
            Long id = Long.parseLong(input);
            Sportive s = sportives.stream().filter(sp -> sp.getId().equals(id)).findAny().orElseThrow();
            System.out.println("You have chosen the sportive: " + s.toString());
            return s;
        }

        private Trainer chooseAtrainer(){
            String input;
            List<Trainer> trainers = trainerService.getAllTrainers();
            Scanner userInput = new Scanner(System.in);
            printAllTrainers();
            System.out.println("Choose a trainer: ");
            input = userInput.nextLine();
            Long id = Long.parseLong(input);
            return trainers.stream().filter(tr -> tr.getId().equals(id)).findAny().orElseThrow();
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////
    //Team
    //////////////////////////////////////////////////////////////////////////////////////////////////
    private void addTeam(){
            try {
                Team team = readTeam();
                teamService.saveTeam(team);
                System.out.println("Team added successfully");
            } catch (MyException e) {
                System.out.println("Team couldn't be added");
                System.out.println(e.getMessage());
            }
            catch (IOException e){
                System.out.println("Invalid prameters");
            }
    }
    public void deleteTeam(){
        printAllTeams();
        System.out.println("Enter the id for the team: ");
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            Long id = Long.valueOf(bufferedReader.readLine());
            teamService.deleteById(id);
            System.out.println("Team deleted succesfully");
        }
        catch (ValidatorException | IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void updateTeam(){
        try{
            printAllTeams();
            Team team = readTeam();
            teamService.updateTeam(team);
            System.out.println("Team updated succesfully");
        }
        catch (ValidatorException | IOException | NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }
    public void printAllTeams(){
        List<Team> teams = teamService.getAllTeams();
        if(teams.isEmpty()){
            System.out.println("There are no teams!");
        }
        else{
            teams.forEach(System.out::println);
        }
    }

    public void filterTeamsByName(){
        System.out.println("Enter name: ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            String token = bufferRead.readLine();
            List<Team> teams = teamService.filterTeamsByTeamName(token);
            if(teams.isEmpty()){
                System.out.println("There is no team with the name " + token);
            }
            else{
                teams.forEach(System.out::println);
                System.out.println("Teams filtered by name ! ");
            }

        } catch (ValidatorException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private Team readTeam() throws IOException, MyException{
        System.out.println("Read team {teamId, teamName}");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try{

            System.out.println("teamName (at least a character)");
            String teamName = bufferedReader.readLine();

            Team team = new Team(teamName);
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

}
