package ui;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Team;
import domain.entities.Trainer;
import domain.enums.TrainingType;
import domain.exceptions.MyException;
import domain.exceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import service.SportiveServiceInterface;
import service.SportiveTrainerServiceInterface;
import service.TeamServiceInterface;
import service.TrainerServiceInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * The user interface.
 * It deals with input and display.
 */
public class Console {

    @Autowired
    private SportiveServiceInterface sportiveService;

    @Autowired
    private TrainerServiceInterface trainerService;

    @Autowired
    private SportiveTrainerServiceInterface sportiveTrainerService;

    @Autowired
    private TeamServiceInterface teamService;

    private Map<String, Runnable> commands;

    private ExecutorService executorService;

    public Console(SportiveServiceInterface sportiveService, TrainerServiceInterface trainerService, SportiveTrainerServiceInterface sportiveTrainerService, TeamServiceInterface teamService) {
        this.sportiveService = sportiveService;
        this.trainerService = trainerService;
        this.sportiveTrainerService = sportiveTrainerService;
        this.teamService = teamService;
        initCommands();
    }

    public Console() {
        initCommands();
    }

    public void initCommands(){
        commands = new HashMap<>();
        commands.put("exit", () -> System.exit(0));
        commands.put("add sportive", this::addSportive);
        commands.put("delete sportive", this::deleteSportive);
        commands.put("update sportive", this::updateSportive);
        commands.put("print all sportives", this::printAllSportives);
        commands.put("filter sportives by first name", this::filterSportivesByFirstName);
        commands.put("filter sportives by age", this::filterSportivesByAge);
        commands.put("filter sportives by team id", this::filterSportivesByTeamId);
////        commands.put("find one sportive", this::findOneSportive);
        commands.put("add trainer", this::addTrainer);
        commands.put("delete trainer", this::deleteTrainer);
        commands.put("update trainer", this::updateTrainer);
        commands.put("print all trainers", this::printAllTrainers);
        commands.put("filter trainers by name", this::filterTrainersByName);
        commands.put("associate sportive-trainer", this::associateSportiveTrainer);
        commands.put("dissociate sportive-trainer", this::dissociateSportiveTrainer);
        commands.put("trainers of one sportive", this::trainersOfOneSportive);
        commands.put("sportives of one trainer", this::sportivesOfOneTrainer);
        commands.put("training types of one sportive", this::trainingTypesOfOneSportive);
        commands.put("most expensive training", this::mostExpensiveTraining);
        commands.put("all sportives-trainers", this::allSportivesTrainers);
        commands.put("add team", this::addTeam);
        commands.put("delete team", this::deleteTeam);
        commands.put("update team", this::updateTeam);
        commands.put("print all teams", this::printAllTeams);
        commands.put("filter teams by name", this::filterTeamsByName);
        commands.put("menu", this::printMenu);
    }



    public void printMenu(){
        System.out.println("Menu:");
        System.out.println(
                commands.keySet()
                        .stream()
                        .reduce("", (s, k) -> s += k + "\n")
        );
    }
    public void run(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        this.printMenu();
        while(true){
            try {
                System.out.println(">>>");
                String command = br.readLine();
                if(!commands.containsKey(command))
                    throw new MyException("Invalid command");
                commands.get(command).run();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Method to handle adding students.
     */
    private void addSportive() {
    Sportive sportive = readSportive();
//    if (sportive == null) {
//    }
    CompletableFuture.supplyAsync(
            ()-> {
                try {
                    sportiveService.addSportive(sportive);
                    return "Sportive added successfully";
                } catch (MyException e) {
                    e.printStackTrace();
                    return e.getMessage();
                }

            }
    )
            .thenAcceptAsync(System.out::println);
    }

    /**
     * Method to handle deleting students.
     */
    private void deleteSportive() {
//        Sportive sportive = readSportive();
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            System.out.println("Read id: ");
            Long id = Long.valueOf(bufferRead.readLine());

        CompletableFuture.supplyAsync(
                ()-> {
                    try {
                        sportiveService.deleteSportive(id);
                        return "Sportive deleted successfully";
                    } catch (MyException e) {
                        e.printStackTrace();
                        return e.getMessage();
                    }


                }
            )
                .thenAcceptAsync(System.out::println);
        }
        catch(IOException e)
        {
            System.out.println();
        }
    }

    /**
     * Method to handle updating students.
     */
    private void updateSportive() {
    Sportive sportive = readSportive();
//    if (sportive == null) {
//        break;
//    }
    /*try {
        studentController.updateStudent(student);
        System.out.println("Student updated successfully");
    } catch (MyException e) {
        System.out.println(e.getMessage());
    }*/
    CompletableFuture.supplyAsync(
            ()-> {
                try {
                    sportiveService.updateSportive(sportive);
                    return "Sportive updated successfully";
                } catch (MyException e) {
                    e.printStackTrace();
                    return e.getMessage();
                }

            }
    )
            .thenAcceptAsync(System.out::println);
    }
    /**
     * Helper method to handle reading a student from keyboard.
     *
     * @return read student
     */
    private Sportive readSportive() {
        System.out.println("Read sportive {id, firstName,lastName, age, teamId}");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = br.readLine();
            if (line.equals("done")) {
                return null;
            }
            List<String> arguments = Arrays.stream(line.split(" "))
                    .filter(word -> !word.equals(""))
                    .collect(Collectors.toList());
            if (arguments.size() != 5)
                throw new MyException("Wrong number of arguments for reading student: (" + arguments.size() + " instead of 4");
            long id;
            try {
                id = Long.parseLong(arguments.get(0));
            } catch (NumberFormatException nfe) {
                throw new MyException("Argument for id is not a number");
            }
            String firstname = arguments.get(1);
            String lastName = arguments.get(2);
            int age;
            try {
                age = Integer.parseInt(arguments.get(3));
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
            s.setId(id);
            return s;
        } catch (MyException | IOException e) {
            System.out.println(e.getMessage());
            return readSportive();
        }
    }

    public void printAllSportives(){
        CompletableFuture.supplyAsync(()->sportiveService.getAllSportives())
                .thenAcceptAsync(sportives -> sportives.forEach(System.out::println));;
    }

    private void filterSportivesByAge() {
        System.out.println("filtered sportives (age < 5):");

        CompletableFuture.supplyAsync(
                ()-> sportiveService.filterSportivesByAge(5)
        )
                .thenAcceptAsync(sportives -> sportives.forEach(System.out::println));
    }

    private void filterSportivesByFirstName() {
        System.out.println("filtered sportives (name containing 'a'):");

        CompletableFuture.supplyAsync(
                ()-> sportiveService.filterSportivesByFirstName("a")
        )
                .thenAcceptAsync(sportives -> sportives.forEach(System.out::println));
    }

    private void filterSportivesByTeamId() {
        System.out.println("filtered sportives (teamid equal to 1):");

        CompletableFuture.supplyAsync(
                ()-> sportiveService.filterSportivesByTeamId(1)
        )
                .thenAcceptAsync(sportives -> sportives.forEach(System.out::println));
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
            System.out.println("Association between a sportive and a trainer has been made successfully!");

            CompletableFuture.supplyAsync(
                    ()-> {
                        try {
                            sportiveTrainerService.associateSportiveTrainer(st);
                            return "Association made successfully!";
                        } catch (MyException e) {
                            e.printStackTrace();
                            return e.getMessage();
                        }

                    }
            )
                    .thenAcceptAsync(System.out::println);
        }catch(CancellationException ex){
            ex.printStackTrace();
        }
    }

    private void dissociateSportiveTrainer(){
        System.out.println("Dissociate sportives and trainers: ");
        Scanner userInput = new Scanner(System.in);
        try{
            allSportivesTrainers();
            System.out.println("Choose an association: ");
            String input = userInput.nextLine();
            Long id = Long.parseLong(input);
            System.out.println("Dissociation of a sportive and a trainer has been made successfully!");

            CompletableFuture.supplyAsync(
                    ()-> {
                        try {
                            sportiveTrainerService.dissociateSportiveTrainer(id);
                            return "Dissociation made successfully!";
                        } catch (MyException e) {
                            e.printStackTrace();
                            return e.getMessage();
                        }

                    }
            )
                    .thenAcceptAsync(System.out::println);
        }catch(ValidatorException | NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void trainersOfOneSportive() {
        Scanner userInput = new Scanner(System.in);
        try {
            Set<Sportive> sportives = sportiveService.getAllSportives();
            printAllSportives();
            System.out.println("Choose a sportive: ");
            String input = userInput.nextLine();
            Long id = Long.parseLong(input);
            Sportive s = sportives.stream().filter(sp -> sp.getId().equals(id)).findAny().orElseThrow();
            CompletableFuture.supplyAsync(
                    () -> sportiveTrainerService.allTrainersOfOneSportive(s)
            )
                    .thenAcceptAsync(trainers -> trainers.forEach(System.out::println));
        } catch (ValidatorException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void sportivesOfOneTrainer() {
        Scanner userInput = new Scanner(System.in);
        try {
            Set<Trainer> trainers = trainerService.getAllTrainers();
            printAllTrainers();
            System.out.println("Choose a trainer: ");
            String input = userInput.nextLine();
            Long id = Long.parseLong(input);
            Trainer t = trainers.stream().filter(sp -> sp.getId().equals(id)).findAny().orElseThrow();
            CompletableFuture.supplyAsync(
                    () -> sportiveTrainerService.allSportivesOfOneTrainer(t)
            )
                    .thenAcceptAsync(sportives -> sportives.forEach(System.out::println));
        } catch (ValidatorException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void trainingTypesOfOneSportive() {
        Scanner userInput = new Scanner(System.in);
        try {
            Set<Sportive> sportives = sportiveService.getAllSportives();
            printAllSportives();
            System.out.println("Choose a sportive: ");
            String input = userInput.nextLine();
            Long id = Long.parseLong(input);
            Sportive s = sportives.stream().filter(sp -> sp.getId().equals(id)).findAny().orElseThrow();
            CompletableFuture.supplyAsync(
                    () -> sportiveTrainerService.allTrainingTypesOfOneSportive(s)
            )
                    .thenAcceptAsync(trainings -> trainings.forEach(System.out::println));
        } catch (ValidatorException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void mostExpensiveTraining() {
        try {
            System.out.println("The most expensive training is :");
            CompletableFuture.supplyAsync(
                    () -> sportiveTrainerService.mostExpensiveTraining()
            )
                    .thenAcceptAsync(System.out::println);
        } catch (ValidatorException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void allSportivesTrainers(){
        CompletableFuture.supplyAsync(()->sportiveTrainerService.getAllSportivesTrainers())
                .thenAcceptAsync(sportivesTrainers -> sportivesTrainers.forEach(System.out::println));
    }

    private Sportive chooseAsportive() {
        try {
            String input;
            Set<Sportive> sportives = sportiveService.getAllSportives();
            Scanner userInput = new Scanner(System.in);
            printAllSportives();
            System.out.println("Choose a sportive: ");
            input = userInput.nextLine();
            Long id = Long.parseLong(input);
            Sportive s = sportives.stream().filter(sp -> sp.getId().equals(id)).findAny().orElseThrow();
            System.out.println("You have chosen the sportive: " + s.toString());
            return s;
        } catch (CancellationException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    private Trainer chooseAtrainer(){
        try {
            String input;
            Set<Trainer> trainers = trainerService.getAllTrainers();
            Scanner userInput = new Scanner(System.in);
            printAllTrainers();
            System.out.println("Choose a trainer: ");
            input = userInput.nextLine();
            Long id = Long.parseLong(input);
            Trainer t = trainers.stream().filter(tr -> tr.getId().equals(id)).findAny().orElseThrow();
            return t;
        } catch (CancellationException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    //    public void findOneSportive(){
//        System.out.println("Read id: ");
//        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            Long id = Long.parseLong(bufferRead.readLine());
//            sportiveService.findOneSportive(id);
//            System.out.println("Sportvies finded by id ! ");
//        } catch (ValidatorException | IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//    public void filterSportivesByFirstName(){
//        //to do
//    }
//    public void filterSportivesByAge(){
//        //to do
//        System.out.println("Read age: ");
//        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            int age = Integer.parseInt(bufferRead.readLine());
//            sportiveService.filterByAge(age).thenAcceptAsync(sportives -> sportives.forEach(System.out::println));;
//            System.out.println("Sportvies filtered by age ! ");
//
//        } catch (ValidatorException | IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//    public void filterSportivesByTeamId(){
//        //to do
//        System.out.println("Read team id: ");
//        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            int teamId = Integer.parseInt(bufferRead.readLine());
//            sportiveService.filterByTeamId(teamId).thenAcceptAsync(sportives -> sportives.forEach(System.out::println));;
//            System.out.println("Sportvies filtered by team id ! ");
//
//        } catch (ValidatorException | IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    public void filterByFirstName(){
//        //to do
//        System.out.println("Read first name: ");
//        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            String firstName = bufferRead.readLine();
//            sportiveService.filterByFirstName(firstName).thenAcceptAsync(sportives -> sportives.forEach(System.out::println));;
//            System.out.println("Sportvies filtered by first name ! ");
//
//        } catch (ValidatorException | IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
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
        //BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        Trainer trainer = readTrainer();
        try {
            // Long id = Long.valueOf(bufferRead.readLine());
            trainerService.deleteTrainer(trainer);
            System.out.println("Trainer was deleted successfully");

        } catch (ValidatorException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateTrainer(){
        try {
            Trainer trainer = readTrainer();
            trainerService.updateTrainer(trainer);
            System.out.println("Trainer was updated successfully.");
        } catch (ValidatorException  | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printAllTrainers(){
        CompletableFuture.supplyAsync(()->trainerService.getAllTrainers())
            .thenAcceptAsync(trainers -> trainers.forEach(System.out::println));;
    }

    public void filterTrainersByName(){
        System.out.println("Enter name: ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            String name = bufferRead.readLine();
            CompletableFuture.supplyAsync(()-> trainerService.filterTrainersByName(name))
                    .thenAcceptAsync(trainers -> trainers.forEach(System.out::println));
            System.out.println("Trainers filtered by name ! ");
        } catch (ValidatorException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
//
//    public void associateSportiveTrainer(){
//        System.out.println("Associate sportives with trainers: ");
//        try {
//            Scanner userInput = new Scanner(System.in);
//            System.out.println("Give an ID: ");
//            String input = userInput.nextLine();
//            Long id = Long.parseLong(input);
//            Sportive s = chooseAsportive();
//            Trainer t = chooseAtrainer();
//            List<TrainingType> types = Arrays.asList(TrainingType.values());
//            types.forEach(System.out::println);
//            System.out.println("Choose a training type: ");
//            String trainingType = userInput.nextLine();
//            System.out.println("Choose a cost: ");
//            String cost = userInput.nextLine();
//            SportiveTrainer st = new SportiveTrainer(id, s.getId(), t.getId(), trainingType, Integer.parseInt(cost));
//            sportiveTrainerService.associateSportiveTrainer(st);
//            System.out.println("Association between a sportive and a trainer has been made successfully!");
//        }catch(CancellationException ex){
//            ex.printStackTrace();
//        }
//    }
//
//    public void dissociateSportiveTrainer(){
//        System.out.println("Dissociate sportives and trainers: ");
//        Scanner userInput = new Scanner(System.in);
//        try{
//            allSportivesTrainers();
//            System.out.println("Choose an association: ");
//            String input = userInput.nextLine();
//            Long id = Long.parseLong(input);
//            sportiveTrainerService.dissociateSportiveTrainer(id);
//            System.out.println("Dissociation of a sportive and a trainer has been made successfully!");
//        }catch(ValidatorException | NumberFormatException ex){
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    public void trainersOfOneSportive(){
//
//        Scanner userInput = new Scanner(System.in);
//        try{
//            Set<Sportive> sportives = sportiveService.getAllSportives().get();
//            printAllSportives();
//            System.out.println("Choose a sportive: ");
//            String input = userInput.nextLine();
//            Long id = Long.parseLong(input);
//            Sportive s = sportives.stream().filter(sp -> sp.getId().equals(id)).findAny().orElseThrow();
//            Set<Trainer> trainers = sportiveTrainerService.allTrainersOfOneSportive(s).get();
//            System.out.println("All the trainers of " + s + ": ");
//            trainers.forEach(System.out::println);
//        }catch(ValidatorException | NumberFormatException ex){
//            System.out.println(ex.getMessage());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
//    public void sportivesOfOneTrainer(){
//
//        Scanner userInput = new Scanner(System.in);
//        try{
//            Set<Trainer> trainers = trainerService.getAllTrainers().get();
//            printAllTrainers();
//            System.out.println("Choose a trainer: ");
//            String input = userInput.nextLine();
//            Long id = Long.parseLong(input);
//            Trainer t = trainers.stream().filter(sp -> sp.getId().equals(id)).findAny().orElseThrow();
//            Set<Sportive> sportives = sportiveTrainerService.allSportivesOfOneTrainer(t).get();
//            System.out.println("All the sportives of " + t + ": ");
//            sportives.forEach(System.out::println);
//        }catch(ValidatorException | NumberFormatException ex){
//            System.out.println(ex.getMessage());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
//    public void trainingTypesOfOneSportive(){
//
//        Scanner userInput = new Scanner(System.in);
//        try{
//            Set<Sportive> sportives = sportiveService.getAllSportives().get();
//            printAllSportives();
//            System.out.println("Choose a sportive: ");
//            String input = userInput.nextLine();
//            Long id = Long.parseLong(input);
//            Sportive s = sportives.stream().filter(sp -> sp.getId().equals(id)).findAny().orElseThrow();
//            Set<String> trainingTypes = sportiveTrainerService.allTrainingTypesOfOneSportive(s).get();
//            System.out.println("All training types of " + s + ": ");
//            trainingTypes.forEach(System.out::println);
//        }catch(ValidatorException | NumberFormatException ex){
//            System.out.println(ex.getMessage());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void mostExpensiveTraining(){
//        try
//        {
//            System.out.println("The most expensive training is : \n" + sportiveTrainerService.mostExpensiveTraining().get());
//
//        }
//        catch(InterruptedException | ExecutionException e)
//        {
//            System.out.println(e);
//        }
//    }
//
//    public void allSportivesTrainers(){
//        try {
//            Set<SportiveTrainer> stService = sportiveTrainerService.getAllSportivesTrainers().get();
//            stService.stream().forEach(System.out::println);
//        }catch(InterruptedException | ExecutionException e){
//            e.printStackTrace();
//        }
//    }
//
//
    private void addTeam(){
        try{
            Team team = readTeam();
            CompletableFuture.supplyAsync(
                    () -> {
                        try{
                            teamService.addTeam(team);
                            return "Team added successfully";
                        } catch (MyException e) {
                            e.printStackTrace();
                            return e.getMessage();
                        } catch (Exception e) {
                            e.printStackTrace();
                            return e.getMessage();
                        }
                    }
            ).thenAcceptAsync(System.out::println);
        }
        catch (IOException | MyException exception){
            System.out.println(exception.getMessage());
        }
    }
    public void deleteTeam(){
        System.out.println("Enter the id for the team: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            Long id = Long.valueOf(bufferedReader.readLine());
            CompletableFuture.supplyAsync(
                    () -> {
                        try{
                            teamService.deleteTeam(id);
                            return "Team deleted succesfully";
                        } catch (MyException e) {
                            e.printStackTrace();
                            return e.getMessage();
                        }
                    }
            ).thenAcceptAsync(System.out::println);
        }
        catch (ValidatorException | IOException e){
            System.out.println(e.getMessage());
        }
    }
    private void updateTeam(){
        try{
            Team team = readTeam();
            CompletableFuture.supplyAsync(
                    () -> {
                        try{
                            teamService.updateTeam(team);
                            return "Team updated succesfully";
                        } catch (MyException e) {
                            e.printStackTrace();
                            return e.getMessage();
                        }
                    }
            ).thenAcceptAsync(System.out::println);
        }
        catch (ValidatorException | IOException | NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }
    public void printAllTeams(){
        CompletableFuture.supplyAsync(()->teamService.getAllTeams())
                .thenAcceptAsync( (teams) -> {teams.forEach(System.out::println);});
    }

    public void filterTeamsByName(){
        System.out.println("Enter name: ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            String token = bufferRead.readLine();
            CompletableFuture.supplyAsync(()-> teamService.filterTeamsByTeamName(token))
                    .thenAcceptAsync(teams -> teams.forEach(System.out::println));
            System.out.println("Teams filtered by name ! ");
        } catch (ValidatorException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private Team readTeam() throws IOException, MyException{
        System.out.println("Read team {teamId, teamName}");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("id (should be posititve): ");
            Long id = Long.valueOf(bufferedReader.readLine());

            System.out.println("teamName (at least a character)");
            String teamName = bufferedReader.readLine();

            Team team = new Team(teamName);
            team.setId(id);

            return team;
        }
        catch (IOException e){
            throw new IOException("*Invalid team attributes");
        }
        catch (MyException e){
            throw new MyException("*Invalid team attributes");
        }

    }

//    private Sportive readSportive() {
//        System.out.println("Read sportive {id,firstName,lastName,age,teamId}");
//
////        Set<Team> teams = teamService.getAll();
//        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            System.out.println("id(integer greater than 0): ");
//            Long id = Long.valueOf(bufferRead.readLine());// ...
//            System.out.println("firstName(characters): ");
//            String firstName = bufferRead.readLine();
//            System.out.println("lastName(characters): ");
//            String lastName = bufferRead.readLine();
//            System.out.println("Age(integer greater than 0): ");
//            int age = Integer.parseInt(bufferRead.readLine());// ...
//
//            printAllTeams();
//            System.out.println("Team id(integer greater than 0): ");
//            int teamId = Integer.parseInt(bufferRead.readLine());// ...
////            Team t = teams.stream().filter(team -> team.getId()==teamId).findAny().orElseThrow(()-> new MyException("Need an existent team id"));
//
//            Sportive sportive = new Sportive(firstName,lastName,age,teamId);
//            sportive.setId(id);
//
//            return sportive;
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        catch (NoSuchElementException e)
//        {
//            System.out.println("error");
//        }
//        return null;
//    }

    private Trainer readTrainer() {
        System.out.println("Read trainer {id, firstName, lastName, age}");
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
            trainer.setId(id);

            return trainer;
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch (NumberFormatException ex) {
            System.out.println("error");
        }
        return null;
    }
//
//    private Sportive chooseAsportive() {
//        try {
//            String input;
//            Set<Sportive> sportives = sportiveService.getAllSportives().get();
//            Scanner userInput = new Scanner(System.in);
//            printAllSportives();
//            System.out.println("Choose a sportive: ");
//            input = userInput.nextLine();
//            Long id = Long.parseLong(input);
//            Sportive s = sportives.stream().filter(sp -> sp.getId().equals(id)).findAny().orElseThrow();
//            System.out.println("You have chosen the sportive: " + s.toString());
//            return s;
//        } catch (CancellationException | ExecutionException | InterruptedException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//    private Trainer chooseAtrainer(){
//        try {
//            String input;
//            Set<Trainer> trainers = trainerService.getAllTrainers().get();
//            Scanner userInput = new Scanner(System.in);
//            printAllTrainers();
//            System.out.println("Choose a trainer: ");
//            input = userInput.nextLine();
//            Long id = Long.parseLong(input);
//            Trainer t = trainers.stream().filter(tr -> tr.getId().equals(id)).findAny().orElseThrow();
//            return t;
//        } catch (CancellationException | ExecutionException | InterruptedException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
}