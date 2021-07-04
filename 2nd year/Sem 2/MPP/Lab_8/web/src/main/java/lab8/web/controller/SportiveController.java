package lab8.web.controller;

import lab8.core.service.SportiveService;
import lab8.web.converter.SportiveConverter;
import lab8.web.dto.SportiveDto;
import lab8.web.dto.SportivesDto;
import lab8.web.dto.TrainersDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SportiveController {
    public static final Logger log = LoggerFactory.getLogger(SportiveController.class);

    @Autowired
    private SportiveService sportiveService;

    @Autowired
    private SportiveConverter sportiveConverter;


    @RequestMapping(value = "/sportives", method = RequestMethod.GET)
    SportivesDto getSportives() {
        log.trace("SportiveController - getSportive - enter method");
        return new SportivesDto(sportiveConverter
                .convertModelsToDtos(sportiveService.getAllSportives()));
    }

    @RequestMapping(value = "/sportives", method = RequestMethod.POST)
    SportiveDto saveSportive(@RequestBody SportiveDto sportiveDto) {
        log.trace("SportiveController - saveSportive - enter method");
        return sportiveConverter.convertModelToDto(sportiveService.saveSportive(
                sportiveConverter.convertDtoToModel(sportiveDto)
        ));
    }

    @RequestMapping(value = "/sportives/{id}", method = RequestMethod.PUT)
    SportiveDto updateSportive(@PathVariable Long id,
                         @RequestBody SportiveDto sportiveDto) {
        log.trace("SportiveController - updateSportive - enter method");
        return sportiveConverter.convertModelToDto( sportiveService.updateSportive(id,
                sportiveConverter.convertDtoToModel(sportiveDto)));
    }

    @RequestMapping(value = "/sportives/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteSportive(@PathVariable Long id){
        log.trace("SportiveController - deleteSportive - enter method");

        sportiveService.deleteSportive(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/sportives/filterAge/{age}", method = RequestMethod.GET)
    SportivesDto getSportivesByAge(@PathVariable int age) {
        log.trace("begin filter sportives name={}",age);
        SportivesDto cpy = new SportivesDto(sportiveConverter
                .convertModelsToDtos(sportiveService.filterSportivesByAge(age)));
        log.trace("end filter sportives={}",cpy);
        return cpy;
    }

    @RequestMapping(value = "/sportives/filterFirstName/{firstName}", method = RequestMethod.GET)
    SportivesDto getSportivesByFirstName(@PathVariable String firstName) {
        log.trace("begin filter sportives name={}",firstName);
        SportivesDto cpy = new SportivesDto(sportiveConverter
                .convertModelsToDtos(sportiveService.filterSportivesByFirstName(firstName)));
        log.trace("end filter sportives={}",cpy);
        return cpy;
    }

    @RequestMapping(value = "/sportives/filterTeamId/{teamId}", method = RequestMethod.GET)
    SportivesDto getSportivesByTeamId(@PathVariable int teamId) {
        log.trace("begin filter sportives name={}",teamId);
        SportivesDto cpy = new SportivesDto(sportiveConverter
                .convertModelsToDtos(sportiveService.filterSportivesByTeamId(teamId)));
        log.trace("end filter sportives={}",cpy);
        return cpy;
    }

}
