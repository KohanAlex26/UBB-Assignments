package lab8.web.controller;

import lab8.core.model.Sportive;
import lab8.core.model.Trainer;
import lab8.core.service.SportiveTrainerService;
import lab8.web.converter.SportiveConverter;
import lab8.web.converter.SportiveTrainerConverter;
import lab8.web.converter.TeamConverter;
import lab8.web.converter.TrainerConverter;
import lab8.web.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class SportiveTrainerController {
    public static final Logger log = LoggerFactory.getLogger(SportiveTrainerController.class);

    @Autowired
    private SportiveTrainerService sportiveTrainerService;

    @Autowired
    private SportiveTrainerConverter sportiveTrainerConverter;

    @Autowired
    private TrainerConverter trainerConverter;

    @Autowired
    private SportiveConverter sportiveConverter;

    @RequestMapping(value = "/sportives-trainers", method = RequestMethod.POST)
    SportiveTrainerDto saveSportiveTrainer(@RequestBody SportiveTrainerDto stDto) {
        log.trace("SportiveTrainerController - saveSportiveTrainer - enter method");
        return sportiveTrainerConverter.convertModelToDto(sportiveTrainerService.saveSportiveTrainer(
                sportiveTrainerConverter.convertDtoToModel(stDto)
        ));
    }

    @RequestMapping(value = "/sportives-trainers/{id}", method = RequestMethod.PUT)
    SportiveTrainerDto updateSportiveTrainer(@PathVariable Long id,
                         @RequestBody SportiveTrainerDto stDto) {
        log.trace("SportiveTrainerController - updateSportiveTrainer - enter method");
        return sportiveTrainerConverter.convertModelToDto( sportiveTrainerService.updateSportiveTrainer(id,
                sportiveTrainerConverter.convertDtoToModel(stDto)));
    }

    @RequestMapping(value = "/sportives-trainers/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteTeam(@PathVariable Long id){
        log.trace("SportiveTrainerController - deleteSportiveTrainer - enter method");

        sportiveTrainerService.deleteSportiveTrainer(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/sportives-trainers", method = RequestMethod.GET)
    SportivesTrainersDto getSportivesTrainers() {
        log.trace("SportiveTrainerController - getTeams - enter method");
        return new SportivesTrainersDto(sportiveTrainerConverter
                .convertModelsToDtos(sportiveTrainerService.getAllSportivesTrainers()));
    }

    @RequestMapping(value = "/sportives-trainers/filterTrainers/{s}", method = RequestMethod.GET)
    TrainersDto allTrainersOfOneSportive(@PathVariable Sportive s) {
        log.trace("begin filter sportives-trainers name={}",s);
        TrainersDto cpy = new TrainersDto(trainerConverter
                .convertModelsToDtos(sportiveTrainerService.allTrainersOfOneSportive(s)));
        log.trace("end filter sportives-trainers={}",cpy);
        return cpy;
    }

    @RequestMapping(value = "/sportives-trainers/filterSportives/{t}", method = RequestMethod.GET)
    SportivesDto allSportivesOfOneTrainer(@PathVariable Trainer t) {
        log.trace("begin filter sportives-trainers name={}",t);
        SportivesDto cpy = new SportivesDto(sportiveConverter
                .convertModelsToDtos(sportiveTrainerService.allSportivesOfOneTrainer(t)));
        log.trace("end filter sportives-trainers={}",cpy);
        return cpy;
    }

//    @RequestMapping(value = "/sportives-trainers/filterTrainings/{s}", method = RequestMethod.GET)
//    String allTrainingTypesOfOneSportive(@PathVariable Sportive s) {
//        log.trace("begin filter sportives-trainers name={}",s);
//        Set<String> cpy = (sportiveConverter
//                .convertModelsToDtos(sportiveTrainerService.allTrainingTypesOfOneSportive(s))) {
//        };
//        log.trace("end filter sportives-trainers={}",cpy);
//        return cpy;
//    }
//
//    @RequestMapping(value = "/sportives-trainers/filterMostExpensive/{}", method = RequestMethod.GET)
//    SportiveTrainerDto mostExpensiveTraining() {
//        log.trace("begin filter sportives-trainers name={}", "");
//        SportiveTrainerDto cpy = new SportiveTrainerDto(sportiveTrainerConverter
//                .convertModelsToDtos(sportiveTrainerService.mostExpensiveTraining()));
//        log.trace("end filter sportives-trainers={}",cpy);
//        return cpy;
//    }

}
