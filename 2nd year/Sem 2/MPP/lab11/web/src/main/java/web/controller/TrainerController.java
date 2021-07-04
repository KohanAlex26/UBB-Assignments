package web.controller;

import core.domain.Trainer;
import core.service.TrainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.TrainerConverter;
import web.dto.TrainerDTO;
import web.dto.TrainersDTO;

import java.util.List;
import java.util.Set;

@RestController
public class TrainerController {
    public static final Logger logger = LoggerFactory.getLogger(TrainerController.class);

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private TrainerConverter trainerConverter;

    @RequestMapping(value = "/trainers", method = RequestMethod.GET)
    List<TrainerDTO> getTrainers() {
        logger.trace("begin get trainers");
        List<TrainerDTO> cpy = new TrainersDTO(trainerConverter
                .convertModelsToDTOs(trainerService.getAllTrainers())).getTrainers();
        logger.trace("end get trainers={}",cpy);
        return cpy;
    }

    @RequestMapping(value="/trainers/get-page/pageno={pageNo},size={size}",method= RequestMethod.GET)
    List<TrainerDTO> getTrainersOnPage(@PathVariable Integer pageNo, @PathVariable Integer size){
        return trainerConverter.convertModelsToDTOs(trainerService.getTrainersOnPage(pageNo,size));
    }


    @RequestMapping(value = "/trainers", method = RequestMethod.POST)
    void addTrainer(@RequestBody TrainerDTO trainerDTO){
        logger.trace("addTrainer - method entered - trainerDTO: " + trainerDTO);
        var trainer = trainerConverter.convertDtoToModel(trainerDTO);
        trainerService.addTrainer(
                trainer.getFirstName(),
                trainer.getLastName(),
                trainer.getAge(),
                trainer.getAward()
        );
        logger.trace("addTrainer - trainer added");
    }

    @RequestMapping(value = "/trainers/{id}", method = RequestMethod.PUT)
    void updateTrainer(@PathVariable Long id, @RequestBody TrainerDTO trainerDTO) {
        logger.trace("updateTrainer - method entered - trainerDTO: " + trainerDTO);
        var trainer = trainerConverter.convertDtoToModel(trainerDTO);
        trainerService.updateTrainer(
                id,
                trainer.getFirstName(),
                trainer.getLastName(),
                trainer.getAge(),
                trainer.getAward()
        );
        logger.trace("updateTrainer - trainer updated");
    }

    @RequestMapping(value = "/trainers/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/trainers/sort", method = RequestMethod.GET)
    List<TrainerDTO> getTrainersSorted() {
        logger.trace("begin sort trainers");
        List<TrainerDTO> cpy = new TrainersDTO(trainerConverter
                .convertModelsToDTOs(trainerService.sortTrainersAscendingByFirstName())).getTrainers();
        logger.trace("end sort trainers={}",cpy);
        return cpy;
    }

    @RequestMapping(value = "/trainers/filterFirstName/{firstName}", method = RequestMethod.GET)
    List<TrainerDTO> getTrainersByFirstName(@PathVariable String firstName) {
        logger.trace("begin filter trainers name={}",firstName);
        List<TrainerDTO> cpy = new TrainersDTO(trainerConverter
                .convertModelsToDTOs(trainerService.filterTrainersByName(firstName))).getTrainers();
        logger.trace("end filter trainers={}",cpy);
        return cpy;
    }
}
