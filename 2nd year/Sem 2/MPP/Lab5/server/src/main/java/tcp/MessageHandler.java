package tcp;

import domain.entities.*;

import service.*;
import utils.Factory;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class MessageHandler {

    SportiveServiceInterface sportiveService;
    TrainerServiceInterface trainerService;
    SportiveTrainerServiceInterface sportiveTrainerService;
    TeamServiceInterface teamServiceInterface;

    public MessageHandler(SportiveServiceInterface sportiveService, TrainerServiceInterface trainerService,SportiveTrainerServiceInterface sportiveTrainerService, TeamServiceInterface teamServiceInterface) {
        this.sportiveService = sportiveService;
        this.trainerService = trainerService;
        this.sportiveTrainerService = sportiveTrainerService;
        this.teamServiceInterface = teamServiceInterface;
    }

    public Message addSportive(Message request){
        Sportive parameter = Factory.messageToSportive(request.getBody());
        try {
            if(sportiveService.addSportive(parameter).get())
                return new Message("ok", "ok");
            else
                return new Message("error", "Sportive already exists");
        }
        catch (InterruptedException | ExecutionException | RuntimeException  e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message addTrainer(Message request){
        Trainer parameter = Factory.messageToTrainer(request.getBody());
        try {
            if(trainerService.addTrainer(parameter).get())
                return new Message("ok", "ok");
            else
                return new Message("error", "Trainer already exists");
        }
        catch (InterruptedException | ExecutionException | RuntimeException  e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message deleteSportive(Message request){
        Long parameter = Factory.messageToSportiveId(request.getBody());
        try {
            if(sportiveService.deleteSportive(parameter).get())
                return new Message("ok", "ok");
            else
                return new Message("error", "Sportive does not exist");
        }
        catch (InterruptedException | ExecutionException  e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message deleteTrainer(Message request){
        Long parameter = Factory.messageToSportiveId(request.getBody());
        try {
            if(trainerService.deleteTrainer(parameter).get())
                return new Message("ok", "ok");
            else
                return new Message("error", "Trainer does not exist");
        }
        catch (InterruptedException | ExecutionException  e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message updateSportive(Message request){
        Sportive parameter = Factory.messageToSportive(request.getBody());
        try {
            if(sportiveService.updateSportive(parameter).get())
                return new Message("ok", "ok");
            else
                return new Message("error", "Sportive does not exist");
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message updateTrainer(Message request){
        Trainer parameter = Factory.messageToTrainer(request.getBody());
        try {
            if(trainerService.updateTrainer(parameter).get())
                return new Message("ok", "ok");
            else
                return new Message("error", "Trainer does not exist");
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message printAllSportives(Message request){
        try {
            Set<Sportive> result =  sportiveService.getAllSportives().get();
            String messageBody = result.stream()
                    .map(Factory::sportiveToString)
                    .reduce("", (s,e) -> s + e + "\n");
            return new Message("ok", messageBody);
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message printAllTrainers(Message request){
        try {
            Set<Trainer> result =  trainerService.getAllTrainers().get();
            String messageBody = result.stream()
                    .map(Factory::trainerToString)
                    .reduce("", (s,e) -> s + e + "\n");
            return new Message("ok", messageBody);
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message filterByAge(Message request){
        int toFilterBy = Integer.parseInt(request.getBody());
        try {
            Set<Sportive> result =  sportiveService.filterByAge(toFilterBy).get();
            String messageBody = result.stream()
                    .map(Factory::sportiveToString)
                    .reduce("", (s,e) -> s + e + "\n");
            return new Message("ok", messageBody);
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message filterByFirstName(Message request){
        String toFilterBy = request.getBody();
        try {
            Set<Sportive> result =  sportiveService.filterByFirstName(toFilterBy).get();
            String messageBody = result.stream()
                    .map(Factory::sportiveToString)
                    .reduce("", (s,e) -> s + e + "\n");
            return new Message("ok", messageBody);
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message filterTrainersByName(Message request){
        String toFilterBy = request.getBody();
        try {
            Set<Trainer> result =  trainerService.filterTrainersByName(toFilterBy).get();
            String messageBody = result.stream()
                    .map(Factory::trainerToString)
                    .reduce("", (s,e) -> s + e + "\n");
            return new Message("ok", messageBody);
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message filterByTeamId(Message request){
        int toFilterBy = Integer.parseInt(request.getBody());
        try {
            Set<Sportive> result =  sportiveService.filterByTeamId(toFilterBy).get();
            String messageBody = result.stream()
                    .map(Factory::sportiveToString)
                    .reduce("", (s,e) -> s + e + "\n");
            return new Message("ok", messageBody);
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }
    public Message associateSportiveTrainer(Message request){
        SportiveTrainer parameter = Factory.messageToSportiveTrainer(request.getBody());
        try {
            if(sportiveTrainerService.associateSportiveTrainer(parameter).get())
                return new Message("ok", "ok");
            else
                return new Message("error", "SportiveTrainer already exists");
        }
        catch (InterruptedException | ExecutionException | RuntimeException  e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }
    public Message dissociateSportiveTrainer(Message request){
        Long parameter = Factory.messageToSportiveId(request.getBody());
        try {
            if(sportiveTrainerService.dissociateSportiveTrainer(parameter).get())
                return new Message("ok", "ok");
            else
                return new Message("error", "SportiveTrainer does not exists");
        }
        catch (InterruptedException | ExecutionException | RuntimeException  e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }
    public Message allTrainersOfOneSportive(Message request){
        Sportive sp = Factory.messageToSportive(request.getBody());
        try {
            Set<Trainer> result =  sportiveTrainerService.allTrainersOfOneSportive(sp).get();
            String messageBody = result.stream()
                    .map(Factory::trainerToString)
                    .reduce("", (s,e) -> s + e + "\n");
            return new Message("ok", messageBody);
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }
    public Message allSportivesOfOneTrainer(Message request){
        Trainer tr = Factory.messageToTrainer(request.getBody());
        try {
            Set<Sportive> result =  sportiveTrainerService.allSportivesOfOneTrainer(tr).get();
            String messageBody = result.stream()
                    .map(Factory::sportiveToString)
                    .reduce("", (s,e) -> s + e + "\n");
            return new Message("ok", messageBody);
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }
    public Message allTrainingTypesOfOneSportive(Message request){
        Sportive sp = Factory.messageToSportive(request.getBody());
        try {
            Set<String> result =  sportiveTrainerService.allTrainingTypesOfOneSportive(sp).get();
            String messageBody = result.stream()
                                        .reduce("", (s,e) -> s + e + "\n");
            return new Message("ok", messageBody);
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }
    public Message mostExpensiveTraining(Message request){
        try {
            SportiveTrainer result = sportiveTrainerService.mostExpensiveTraining().get();
            String messageBody = Factory.sportiveTrainerToString(result);
            return new Message("ok", messageBody);
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }
    public Message printAllSportivesTrainers(Message request){
        try {
            Set<SportiveTrainer> result =  sportiveTrainerService.getAllSportivesTrainers().get();
            String messageBody = result.stream()
                    .map(Factory::sportiveTrainerToString)
                    .reduce("", (s,e) -> s + e + "\n");
            return new Message("ok", messageBody);
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }
//    public Message findOneSportive(Message request){
//        Long id = Long.parseLong(request.getBody());
//        try {
//            Sportive result =  sportiveService.findOneSportive(id).get();
////            String messageBody = result.stream()
////                    .map(Factory::sportiveToString)
////                    .reduce("", (s,e) -> s + e + "\n");
//            return new Message("ok", "ok");
//        }
//        catch (InterruptedException | ExecutionException e) {
//            System.out.println(e.getCause().getMessage());
//            return new Message("error", e.getCause().getMessage());
//        }
//    }

    public Message addTeam(Message request){
        Team parameter = Factory.messageToTeam(request.getBody());
        try {
            if(teamServiceInterface.addTeam(parameter).get())
                return new Message("ok", "ok");
            else
                return new Message("error", "Team already exists");
        }
        catch (InterruptedException | ExecutionException | RuntimeException  e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }
    public Message updateTeam(Message request){
        Team parameter = Factory.messageToTeam(request.getBody());
        try {
            if(teamServiceInterface.updateTeam(parameter).get())
                return new Message("ok", "ok");
            else
                return new Message("error", "Team does not exist");
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }
    public Message deleteTeam(Message request){
        Long parameter = Factory.messageToTeamId(request.getBody());
        try {
            if(teamServiceInterface.deleteTeam(parameter).get())
                return new Message("ok", "ok");
            else
                return new Message("error", "Team does not exist");
        }
        catch (InterruptedException | ExecutionException  e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }
    public Message printAllTeams(Message request){
        try {
            Set<Team> result =  teamServiceInterface.getAllTeams().get();
            String messageBody = result.stream()
                    .map(Factory::teamToString)
                    .reduce("", (s,e) -> s + e + "\n");
            return new Message("ok", messageBody);
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }
    public Message filterTeamsByName(Message request){
        String toFilterBy = request.getBody();
        try {
            Set<Team> result =  teamServiceInterface.filterTeamsByName(toFilterBy).get();
            String messageBody = result.stream()
                    .map(Factory::teamToString)
                    .reduce("", (s,e) -> s + e + "\n");
            return new Message("ok", messageBody);
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }
}
