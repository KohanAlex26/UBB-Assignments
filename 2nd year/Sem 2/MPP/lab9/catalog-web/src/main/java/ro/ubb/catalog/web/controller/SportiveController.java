package ro.ubb.catalog.web.controller;

/*import lab8.core.service.SportiveService;
import lab8.web.converter.SportiveConverter;
import lab8.web.dto.SportiveDto;
import lab8.web.dto.SportivesDto;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.service.SportiveService;
import ro.ubb.catalog.web.converter.SportiveConverter;
import ro.ubb.catalog.web.dto.SportiveDto;
import ro.ubb.catalog.web.dto.SportivesDto;

import java.util.Set;

@RestController
public class SportiveController {
    public static final Logger log = LoggerFactory.getLogger(SportiveController.class);

    @Autowired
    private SportiveService sportiveService;

    @Autowired
    private SportiveConverter sportiveConverter;

    @RequestMapping(value = "/sportives")
    Set<SportiveDto> getAllSportives() {
        log.trace("SportiveController - getSportives");
        return sportiveConverter.convertModelsToDtos(
                sportiveService.getAllSportives());
    }

    @RequestMapping(value = "/sportives", method = RequestMethod.POST)
    SportiveDto saveSportive(@RequestBody SportiveDto sportiveDto) {
        log.trace("SportiveController - saveSportive - enter method");
        return sportiveConverter.convertModelToDto(sportiveService.saveSportive(
                sportiveConverter.convertDtoToModel(sportiveDto)
        ));
    }

}
