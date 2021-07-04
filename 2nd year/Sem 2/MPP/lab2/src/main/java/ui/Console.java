package ui;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Team;
import domain.entities.Trainer;
import domain.enums.TrainingType;
import domain.exceptions.MyException;
import domain.exceptions.ValidatorException;
import domain.validators.Validator;
import service.SportiveService;
import service.SportiveTrainerService;
import service.TeamService;
import service.TrainerService;

import java.io.BufferedReader;
import java.util.*;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The user interface.
 * It deals with input and display.
 */
public class Console {
    private SportiveService sportiveService;
    private TrainerService trainerService;
    private SportiveTrainerService sportiveTrainerService;
    private TeamService teamService;

    public Console(SportiveService sportiveService, TrainerService trainerService, SportiveTrainerService sportiveTrainerService, TeamService teamService) {
        this.sportiveService=sportiveService;
        this.trainerService = trainerService;
        this.sportiveTrainerService = sportiveTrainerService;
        this.teamService = teamService;
    }

    /**
     * Shorten the calls to the System.out.println() method.
     * @param object
     *          The object to display. It's toString() method will be called.
     */
    private void print(Object object){
        System.out.println(object.toString());
    }

    /**
     * Displays the help screen
     */
    public void displayHelp(){
        String output = "\tHelp menu - You can input the command name or it's number. Available commands:\n\n" +
                "\t0/quit/exit - exists the application\n" +
                "\t1/help - displays this screen\n" +
                "\t2/addTrainer - adds a new trainer\n" +
                "\t3/deleteTrainer - deletes a trainer\n" +
                "\t4/updateTrainer - updates a trainer\n" +
                "\t5/listTrainers - list all trainers\n" +
                "\t6/addSportive - adds a new sportive\n" +
                "\t7/listSportives - print all sportives\n" +
                "\t8/sportivesByAge - filter the sportives by age\n" +
                "\t9/printByFirstName - filter the sportives by their first name\n" +
                "\t10/printByTeamId - filter the sportives by team id\n" +
                "\t11/filterTrainers - filter trainers by name\n" +
                "\t12/associateSportiveTrainer - associate a sportive with a trainer\n" +
                "\t13/allTrainersOfOneSportive - all trainers of one sportive\n" +
                "\t14/allSportivesOfOneTrainer - all sportives of one trainer\n" +
                "\t15/allTrainingTypesOfOneSportive - all training types of one sportive\n" +
                "\t16/dissociateSportiveTrainer - dissociate a sportive and a trainer\n" +
                "\t17/mostExpensiveTraining - the most expensive training\n" +
                "\t18/updateSportive - updates a sportive\n" +
                "\t19/deleteSportive - deletes a sportive\n" +
                "\t20/addTeam - adds a new team\n" +
                "\t21/listTeams - print all teams\n" +
                "\t22/deleteTeam - delete a team\n" +
                "\t23/updateTeam - updates a team\n" +
                "\t24/filterByTeamName - print all teams containing a given substring\n" +
                "\t25/printSportiveTrainer - print relations between trainer and sportive\n" ;
        print(output);
    }

    private void addTeam(){
        try{
            Team team = readTeam();
            teamService.addTeam(team);
            System.out.println("Team was added successfully.");
        }
        catch (IOException | MyException exception){
            System.out.println(exception.getMessage());
        }
    }

    private void updateTeam(){
        try{
            Team team = readTeam();
            teamService.updateTeam(team);
            System.out.println("Team updated succesfully.");
        }
        catch (ValidatorException | IOException | NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }

    private void filterTeamsByTeamName(){
        System.out.println("Enter the token to search teams: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String token = bufferedReader.readLine();
            Set<Team> teamSet = teamService.filterByTeamName(token);
            teamSet.stream().forEach(System.out::println);
        }
        catch (MyException | IOException e){
            System.out.println(e.getMessage());
        }
    }


    private void addSportive(){
        try {
            Sportive sportive = readSportive();
            sportiveService.addSportive(sportive);
            System.out.println("Sportive was added successfully.");
        } catch (ValidatorException  | NumberFormatException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void addTrainer(){
        try {
            Trainer trainer = readTrainer();
            trainerService.addTrainer(trainer);
            System.out.println("Trainer was added successfully.");
        } catch (ValidatorException | IOException | NumberFormatException exception) {
            System.out.println(exception.getMessage());
        }
    }
    private void associateSportiveTrainer(){
        System.out.println("Associate sportives with trainers: ");
        Set<Sportive> sportives = sportiveService.getAll();
        Set<Trainer> trainers = trainerService.getAll();
        Scanner userInput = new Scanner(System.in);
        try {
            System.out.println("Give an ID: ");
            String input = userInput.nextLine();
            Long id = Long.parseLong(input);
            listSportives();
            System.out.println("Choose a sportive: ");
            input = userInput.nextLine();
            Long id1 = Long.parseLong(input);
            Sportive s = sportives.stream().filter(sp -> sp.getId().equals(id1)).findAny().orElseThrow();
            System.out.println("You have chosen the sportive: " + s.toString());
            printAllTrainers();
            System.out.println("Choose a trainer: ");
            input = userInput.nextLine();
            Long id2 = Long.parseLong(input);
            Trainer t = trainers.stream().filter(tr -> tr.getId().equals(id2)).findAny().orElseThrow();
            List<TrainingType> types = Arrays.asList(TrainingType.values());
            types.forEach(System.out::println);
            System.out.println("Choose a training type: ");
            String trainingType = userInput.nextLine();
            System.out.println("Choose a cost: ");
            String cost = userInput.nextLine();
            sportiveTrainerService.associateSportiveTrainer(id, s, t, trainingType, Integer.parseInt(cost));
            System.out.println("Association between a sportive and a trainer has been made successfully!");
        }catch(ValidatorException | NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void dissociateSportiveTrainer(){
        System.out.println("Dissociate sportives and trainers: ");
        Set<String> associations = sportiveTrainerService.getAssociations();
        Scanner userInput = new Scanner(System.in);
        try{
            associations.forEach(System.out::println);
            System.out.println("Choose an association: ");
            String input = userInput.nextLine();
            Long id = Long.parseLong(input);
            sportiveTrainerService.dissociationSportiveTrainer(id);
            System.out.println("Dissociation of a sportive and a trainer has been made successfully!");
        }catch(ValidatorException | NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void mostExpensiveTraining(){
        System.out.println("The most expensive training is : \n" + sportiveTrainerService.mostExpensiveTraining());
    }

    private void printAllTrainers(){
        Set<Trainer> trainers = trainerService.getAll();
        trainers.forEach(System.out::println);
    }

    private void deleteTrainer() {
        System.out.println("Enter the id for the trainer: ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            trainerService.deleteTrainer(id);
            System.out.println("Trainer was deleted successfully");

        } catch (ValidatorException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void updateTrainer() {
        try {
            Trainer trainer = readTrainer();
            trainerService.updateTrainer(trainer);
            System.out.println("Trainer was updated successfully.");
        } catch (ValidatorException | IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private void filterTrainersByName(){
        System.out.println("Enter the name to search the trainer: ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            String name = bufferRead.readLine();
            Set<Trainer> filteredTrainers = trainerService.filterTrainersByName(name);
            filteredTrainers.forEach(System.out::println);
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void listSportives(){
        Set<Sportive> sportives = sportiveService.getAll();
        sportives.stream().forEach(System.out::println);
    }


    public void printAllTeams(){
        Set<Team> teams = teamService.getAll();
        teams.stream().forEach(System.out::println);
    }

    public void printSportiveTrainer(){
        Set<SportiveTrainer> stService = sportiveTrainerService.getAll();
        stService.stream().forEach(System.out::println);
    }

    public void deleteTeam(){
        System.out.println("Enter the id for the team: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            Long id = Long.valueOf(bufferedReader.readLine());
            teamService.deleteTeam(id);
            System.out.println("Team deleted succesfully");
        }
        catch (ValidatorException | IOException e){
            System.out.println(e.getMessage());
        }
    }


    public void printByFirstName(){
        System.out.println("Read a part of a first name(characters): ");
        String st=new Scanner(System.in).nextLine();
        Set<Sportive> sportives = sportiveService.filterByFirstName(st);
        sportives.stream().forEach(System.out::println);
    }

    public void printByTeamId(){
        try
        {
            System.out.println("Read team id(integer greater than 0): ");
            int id = new Scanner(System.in).nextInt();
            Set<Sportive> sportives = sportiveService.filterByTeamId(id);
            sportives.stream().forEach(System.out::println);
        }
        catch (InputMismatchException e)
        {
            System.out.println("Read numbers!");
        }
    }

    public void allTrainersOfOneSportive(){
        Set<Sportive> sportives = sportiveService.getAll();
        Scanner userInput = new Scanner(System.in);
        try{
            listSportives();
            System.out.println("Choose a sportive: ");
            String input = userInput.nextLine();
            Long id = Long.parseLong(input);
            Sportive s = sportives.stream().filter(sp -> sp.getId().equals(id)).findAny().orElseThrow();
            Set<Trainer> trainers = sportiveTrainerService.allTrainersOfOneSportive(s);
            System.out.println("All the trainers of " + s + ": ");
            trainers.forEach(System.out::println);
        }catch(ValidatorException | NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void allSportivesOfOneTrainer(){
        Set<Trainer> trainers = trainerService.getAll();
        Scanner userInput = new Scanner(System.in);
        try{
            printAllTrainers();
            System.out.println("Choose a trainer: ");
            String input = userInput.nextLine();
            Long id = Long.parseLong(input);
            Trainer t = trainers.stream().filter(sp -> sp.getId().equals(id)).findAny().orElseThrow();
            Set<Sportive> sportives = sportiveTrainerService.allSportivesOfOneTrainer(t);
            System.out.println("All the sportives of " + t + ": ");
            sportives.forEach(System.out::println);
        }catch(ValidatorException | NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void allTrainingTypesOfOneSportive(){
        Set<Sportive> sportives = sportiveService.getAll();
        Scanner userInput = new Scanner(System.in);
        try{
            listSportives();
            System.out.println("Choose a sportive: ");
            String input = userInput.nextLine();
            Long id = Long.parseLong(input);
            Sportive s = sportives.stream().filter(sp -> sp.getId().equals(id)).findAny().orElseThrow();
            Set<String> trainingTypes = sportiveTrainerService.allTrainingTypesOfOneSportive(s);
            System.out.println("All training types of " + s + ": ");
            trainingTypes.forEach(System.out::println);
        }catch(ValidatorException | NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
    }

    private Sportive readSportive() {
        System.out.println("Read sportive {id,firstName,lastName,age,teamId}");

        Set<Team> teams = teamService.getAll();
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

            printAllTeams();
            System.out.println("Team id(integer greater than 0): ");
            int teamId = Integer.parseInt(bufferRead.readLine());// ...
            Team t = teams.stream().filter(team -> team.getId()==teamId).findAny().orElseThrow(()-> new MyException("Need an existent team id"));

            Sportive sportive = new Sportive(firstName,lastName,age,teamId);
            sportive.setId(id);

            return sportive;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (NoSuchElementException e)
        {
            System.out.println("error");
        }
        return null;
    }

    private Trainer readTrainer() throws IOException, NumberFormatException {
        System.out.println("Read trainer {id, firstName, lastName, age}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());// ...
            String firstName = bufferRead.readLine();
            String lastName = bufferRead.readLine();
            int age = Integer.parseInt(bufferRead.readLine());// ...

            Trainer trainer = new Trainer(firstName,lastName,age);
            trainer.setId(id);

            return trainer;
        } catch (IOException ex) {
            throw new IOException (ex.getMessage());
        }catch (NumberFormatException ex) {
            throw new NumberFormatException(ex.getMessage() + " the value must be integer!");
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

    public void printSportivesWithAge(){
        try {
            System.out.println("Read age(integer greater than 0): ");
            int age = new Scanner(System.in).nextInt();
            Set<Sportive> sportives = sportiveService.filterByAge(age);
            sportives.stream().forEach(System.out::println);
        }
        catch (InputMismatchException e)
        {
            System.out.println("NumberFormatException.. Read an integer!");
        }

    }

    private void deleteSportive() {
        System.out.println("Enter the id for the sportive: ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            sportiveService.deleteSportive(id);
            System.out.println("Sportive was deleted successfully");

        } catch (ValidatorException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void updateSportive() {
        try {
            Sportive sportive = readSportive();
            sportiveService.updateSportive(sportive);
            System.out.println("Sportive was updated successfully.");
        } catch (ValidatorException  | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The method dealing with console input and output.
     */
    public void runConsole()
    {
            //initialize the scanner
            Scanner userInput = new Scanner(System.in);

            print("Application started\n");

        label:
        while(true) {
            try {
                System.out.println("For help press 1 or help");
                //token is the whole sequence typed by the user
                String token = userInput.nextLine();

                //converting the token to a list of words
                List<String> wordList = Arrays.asList(token.split(" "));

                if (wordList.size() > 0) {
                    String methodName = wordList.get(0);
                    switch (methodName) {
                        case "quit":
                        case "exit":
                        case "0":
                            break label;
                        case "help":
                        case "1":
                            displayHelp();
                            break;
                        case "addTrainer":
                        case "2":
                            this.addTrainer();
                            break;
                        case "deleteTrainer":
                        case "3":
                            this.deleteTrainer();
                            break;
                        case "updateTrainer":
                        case "4":
                            this.updateTrainer();
                            break;
                        case "listTrainers":
                        case "5":
                            this.printAllTrainers();
                            break;
                        case "addSportive":
                        case "6":
                            this.addSportive();
                            break;
                        case "listSportives":
                        case "7":
                            this.listSportives();
                            break;
                        case "sportivesByAge":
                        case "8":
                            this.printSportivesWithAge();
                            break;
                        case "printByFirstName":
                        case "9":
                            this.printByFirstName();
                            break;
                        case "printByTeamId":
                        case "10":
                            this.printByTeamId();
                            break;
                        case "filterTrainers":
                        case "11":
                            this.filterTrainersByName();
                            break;
                        case "associateSportiveTrainer":
                        case "12":
                            this.associateSportiveTrainer();
                            break;
                        case "allTrainersOfOneSportive":
                        case "13":
                            this.allTrainersOfOneSportive();
                            break;
                        case "allSportivesOfOneTrainer":
                        case "14":
                            this.allSportivesOfOneTrainer();
                            break;
                        case "allTrainingTypesOfOneSportive":
                        case "15":
                            this.allTrainingTypesOfOneSportive();
                            break;
                        case "dissociateSportiveTrainer":
                        case "16":
                            this.dissociateSportiveTrainer();
                            break;
                        case "mostExpensiveTraining":
                        case "17":
                            this.mostExpensiveTraining();
                            break;
                        case "updateSportive":
                        case "18":
                            this.updateSportive();
                            break;
                        case "deleteSportive":
                        case "19":
                            this.deleteSportive();
                            break;
                        case "addTeam":
                        case "20":
                            this.addTeam();
                            break;
                        case "listTeams":
                        case "21":
                            this.printAllTeams();
                            break;
                        case "deleteTeam":
                        case "22":
                            this.deleteTeam();
                            break;
                        case "updateTeam":
                        case "23":
                            this.updateTeam();
                            break;
                        case "filterByTeamName":
                        case "24":
                            this.filterTeamsByTeamName();
                            break;
                        case "printSportiveTrainer":
                        case "25":
                            this.printSportiveTrainer();
                            break;
                        default:
                            print("*Unknown command");
                            break;
                    }
                }
            }
            catch (MyException e){
                            print(e.getMessage());
                        }
            catch (Exception e){
                            e.printStackTrace();
                        }
        }

        print("Application closed\n");
    }
}
