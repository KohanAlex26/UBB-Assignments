package ro.ubb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.core.service.ITrainerService;
import ro.ubb.web.converter.TrainerConverter;
import ro.ubb.web.dto.TrainerDto;
import ro.ubb.web.dto.TrainersDto;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
public class TrainerController {
    public static final Logger log = LoggerFactory.getLogger(TrainerController.class);

    @Autowired
    private ITrainerService trainerService;

    @Autowired
    private TrainerConverter trainerConverter;

    @RequestMapping(value="/trainers/get-page/pageno={pageNo},size={size}",method= RequestMethod.GET)
    List<TrainerDto> getTrainersOnPage(@PathVariable Integer pageNo, @PathVariable Integer size){
        log.trace("begin get page={}",pageNo);
        return trainerConverter.convertModelsToDtos(trainerService.getTrainersOnPage(pageNo,size));
    }

    @RequestMapping(value = "/trainers", method = RequestMethod.GET)
    List<TrainerDto> getTrainers() {
        log.trace("begin get trainers");
        List<TrainerDto> cpy = new TrainersDto(trainerConverter
                .convertModelsToDtos(trainerService.getAllTrainers())).getTrainers();
        log.trace("end get trainers={}",cpy);
        return cpy;
    }

    @CrossOrigin
    @RequestMapping(value = "/trainers", method = RequestMethod.POST)
    ResponseEntity<?> saveTrainer(@RequestBody TrainerDto TrainerDto) {
        log.trace("begin add trainer={}", TrainerDto);
        try {
            trainerService.addTrainer(
                    trainerConverter.convertDtoToModel(TrainerDto)
            );
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/trainers/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updateTrainer(@RequestBody TrainerDto trainerDto) {
        log.trace(trainerDto.getFirstName(),trainerDto.getLastName(),trainerDto.getAge(),"\n\n\n");
        log.trace("begin update trainer={}", trainerDto);
        try {
            trainerService.updateTrainer(trainerConverter.convertDtoToModel(trainerDto));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/trainers/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteTrainer(@PathVariable Long id){
        log.trace("begin delete trainer with id={}", id);
        try {
            trainerService.deleteTrainer(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/trainers/sort", method = RequestMethod.GET)
    List<TrainerDto> getTrainersSorted() {
        log.trace("begin sort trainers");
        List<TrainerDto> cpy = new TrainersDto(trainerConverter
                .convertModelsToDtos(trainerService.sortTrainersAscendingByFirstName())).getTrainers();
        log.trace("end sort trainers={}",cpy);
        return cpy;
    }

    @RequestMapping(value = "/trainers/filterFirstName/{firstName}", method = RequestMethod.GET)
    List<TrainerDto> getTrainersByFirstName(@PathVariable String firstName) {
        log.trace("begin filter trainers name={}",firstName);
        List<TrainerDto> cpy = new TrainersDto(trainerConverter
                .convertModelsToDtos(trainerService.filterTrainersByName(firstName))).getTrainers();
        log.trace("end filter trainers={}",cpy);
        return cpy;
    }
}
