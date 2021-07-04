package web.controller;


import core.domain.Sportive;
import core.domain.SportiveTrainer;
//import core.service.SportiveTrainerService;
import core.service.SportiveService;
import core.service.SportiveTrainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.converter.SportiveTrainerConverter;

import web.dto.SportiveTrainerDTO;
import web.dto.SportiveTrainerPrimaryKeyDTO;

import java.util.HashSet;
import java.util.Set;

@RestController
public class SportiveTrainerController {
    public static final Logger logger = LoggerFactory.getLogger(SportiveTrainerController.class);

    @Autowired
    private SportiveTrainerService sportiveTrainerService;


    @Autowired
    private SportiveService sportiveService;

    @Autowired
    private SportiveTrainerConverter sportiveTrainerConverter;
//    @Autowired
//    private SportiveTrainerService sportiveTrainerService;

    @RequestMapping(value = "/sportiveTrainer", method = RequestMethod.POST)
    void addSportiveTrainer(@RequestBody SportiveTrainerPrimaryKeyDTO sportiveTrainerPrimaryKeyDTO) {
        logger.trace("addSportiveTrainer - method entered - sportiveTrainerPrimaryKeyDTO: " + sportiveTrainerPrimaryKeyDTO);
        sportiveTrainerService.addSportiveTrainer(
                sportiveTrainerPrimaryKeyDTO.getSportiveId(),
                sportiveTrainerPrimaryKeyDTO.getTrainerId(),
                sportiveTrainerPrimaryKeyDTO.getCost(),
                sportiveTrainerPrimaryKeyDTO.getTrainingType()
        );
        logger.trace("addPurchase - purchase added");
    }




//    @RequestMapping(value = "/sportiveTrainer/{sportiveId}", method = RequestMethod.GET)
//    public Set<SportiveTrainerDTO> getSportiveTrainers(
//            @PathVariable final Long sportiveId) {
//        logger.trace("getSportiveTrainers: sportiveId={}", sportiveId);
//
//        Sportive sportive = sportiveService.getSportiveById(sportiveId);
//        Set<SportiveTrainerDTO> result = new HashSet<>();
//
//        Set<SportiveTrainer> sportiveTrainers = sportive.getSportiveTrainer();
//        result.addAll(sportiveTrainerConverter
//                .convertModelsToDtos(sportiveTrainers));
//
//        logger.trace("getSportiveTrainers: result={}", result);
//
//        return result;
//    }
//
//    @RequestMapping(value = "/sportivesTrainers", method = RequestMethod.GET)
//    List<SportiveTrainerDTO> getSportivesTrainers() {
//        logger.trace("begin get sportivesTrainers");
//        List<SportiveTrainerDTO> cpy = new SportiveTrainersDTO(sportiveTrainerConverter
//                .convertModelsToDTOs(sportiveTrainerService.getAllSportivesTrainers())).getSportiveTrainers();
//        logger.trace("end get sportivesTrainers={}",cpy);
//        return cpy;
//    }

//    @RequestMapping(value = "/sportiveTrainers", method = RequestMethod.POST)
//    void addSportiveTrainer(@RequestBody SportiveTrainerDTO customerSportiveTrainerPrimaryKeyDTO){
//        logger.trace("addSportiveTrainer - method entered - customerSportiveTrainerPrimaryKeyDTO: " + customerSportiveTrainerPrimaryKeyDTO);
//        sportiveTrainerService.addSportiveTrainer(
//                customerSportiveTrainerPrimaryKeyDTO.getSportive().getId(),
//                customerSportiveTrainerPrimaryKeyDTO.getTrainer().getId(),
//                customerSportiveTrainerPrimaryKeyDTO.getCost(),
//                customerSportiveTrainerPrimaryKeyDTO.getTrainingType()
//        );
//        logger.trace("addSportiveTrainer - sportiveTrainer added");
//    }

//    @RequestMapping(value = "/sportiveTrainers", method = RequestMethod.POST)
//    void addSportiveTrainer(@RequestBody CustomerSportiveTrainerPrimaryKeyDTO customerSportiveTrainerPrimaryKeyDTO){
//        logger.trace("addSportiveTrainer - method entered - customerSportiveTrainerPrimaryKeyDTO: " + customerSportiveTrainerPrimaryKeyDTO);
//        sportiveTrainerService.addSportiveTrainer(
//                customerSportiveTrainerPrimaryKeyDTO.getSportiveId(),
//                customerSportiveTrainerPrimaryKeyDTO.getTrainerId(),
//                customerSportiveTrainerPrimaryKeyDTO.getCost(),
//                customerSportiveTrainerPrimaryKeyDTO.getTrainingType()
//                );
//        logger.trace("addSportiveTrainer - sportiveTrainer added");
//    }

}
