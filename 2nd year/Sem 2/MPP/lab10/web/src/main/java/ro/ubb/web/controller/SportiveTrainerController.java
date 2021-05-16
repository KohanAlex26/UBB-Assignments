package ro.ubb.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.core.model.Sportive;
import ro.ubb.core.model.SportiveTrainer;
import ro.ubb.core.service.ISportiveService;
import ro.ubb.core.service.ISportiveTrainerService;
import ro.ubb.web.converter.SportiveConverter;
import ro.ubb.web.converter.SportiveTrainerConverter;
import ro.ubb.web.converter.TrainerConverter;
import ro.ubb.web.dto.*;

import java.util.List;

@RestController
public class SportiveTrainerController {
    public static final Logger log = LoggerFactory.getLogger(SportiveTrainerController.class);

    @Autowired
    private ISportiveTrainerService sportiveTrainerService;

    @Autowired
    private SportiveTrainerConverter sportiveTrainerConverter;

    @Autowired
    private TrainerConverter trainerConverter;

   /* @Autowired
    private TrainerConverter trainerConverter;

    @Autowired
    private SportiveConverter sportiveConverter;*/

    @RequestMapping(value="/sportivesTrainers/get-page/pageno={pageNo},size={size}",method= RequestMethod.GET)
    List<SportiveTrainerDto> getSportivesTrainersOnPage(@PathVariable Integer pageNo, @PathVariable Integer size){
        log.trace("begin get page={}",pageNo);
        return sportiveTrainerConverter.convertModelsToDtos(sportiveTrainerService.getSportivesTrainersOnPage(pageNo,size));
    }

    @RequestMapping(value = "/sportivesTrainers", method = RequestMethod.GET)
    List<SportiveTrainerDto> getSportivesTrainers() {
        log.trace("begin get sportivesTrainers");
        List<SportiveTrainerDto> cpy = new SportivesTrainersDto(sportiveTrainerConverter
                .convertModelsToDtos(sportiveTrainerService.getAllSportivesTrainers())).getSportivesTrainers();
        log.trace("end get sportivesTrainers={}",cpy);
        return cpy;
    }


    @RequestMapping(value = "/sportivesTrainers/sort", method = RequestMethod.GET)
    List<SportiveTrainerDto> getSportivesTrainersSorted() {
        log.trace("begin sort sportivesTrainers");
        List<SportiveTrainerDto> cpy = new SportivesTrainersDto(sportiveTrainerConverter
                .convertModelsToDtos(sportiveTrainerService.sortSportivesTrainersAscendingByCost())).getSportivesTrainers();
        log.trace("end sort sportivesTrainers={}",cpy);
        return cpy;
    }

    @RequestMapping(value = "/sportivesTrainers/allTrainersOfOneSportive/{id}", method = RequestMethod.GET)
    List<TrainerDto> getTrainersOfOneSportive(@PathVariable Long id) {
        log.trace("begin filter sportivesTrainer id={}",id);
        List<TrainerDto> cpy= new TrainersDto(trainerConverter
                .convertModelsToDtos(sportiveTrainerService.allTrainersOfOneSportive(id))).getTrainers();
        log.trace("end filter sportivesTrainers={}",cpy);
        return cpy;
    }
}
