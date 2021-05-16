package ro.ubb.web.controller;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.ValidPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ro.ubb.core.service.ISportiveService;
import ro.ubb.core.validators.ValidatorException;
import ro.ubb.web.converter.SportiveConverter;
import ro.ubb.web.dto.SportiveDto;
import ro.ubb.web.dto.SportivesDto;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
public class SportiveController {
    public static final Logger log = LoggerFactory.getLogger(SportiveController.class);

    @Autowired
    private ISportiveService sportiveService;

    @Autowired
    private SportiveConverter sportiveConverter;
    
    @RequestMapping(value="/sportives/get-page/pageno={pageNo},size={size}",method=RequestMethod.GET)
    List<SportiveDto> getSportivesOnPage(@PathVariable Integer pageNo, @PathVariable Integer size){
        log.trace("begin get page={}",pageNo);
        return sportiveConverter.convertModelsToDtos(sportiveService.getSportivesOnPage(pageNo,size));
    }
    
    @RequestMapping(value = "/sportives", method = RequestMethod.GET)
    List<SportiveDto> getSportives() {
        log.trace("begin get sportives");
        List<SportiveDto> cpy = new SportivesDto(sportiveConverter
                .convertModelsToDtos(sportiveService.getAllSportives())).getSportives();
        log.trace("end get sportives={}",cpy);
        return cpy;
    }

    @CrossOrigin
    @RequestMapping(value = "/sportives", method = RequestMethod.POST)
    ResponseEntity<?> saveSportive(@RequestBody SportiveDto SportiveDto) {
        log.trace("begin add sportive={}", SportiveDto);
        try {
            sportiveService.addSportive(
                    sportiveConverter.convertDtoToModel(SportiveDto)
            );
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/sportives/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updateSportive(@RequestBody SportiveDto sportiveDto) {
        log.trace(sportiveDto.getFirstName(),sportiveDto.getLastName(),sportiveDto.getAge(),sportiveDto.getTid(),"\n\n\n");
        log.trace("begin update sportive={}", sportiveDto);
        try {
            sportiveService.updateSportive(sportiveConverter.convertDtoToModel(sportiveDto));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/sportives/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteSportive(@PathVariable Long id){
        log.trace("begin delete sportive with id={}", id);
        try {
            sportiveService.deleteSportive(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/sportives/sort", method = RequestMethod.GET)
    List<SportiveDto> getSportivesSorted() {
        log.trace("begin sort sportives");
        List<SportiveDto> cpy = new SportivesDto(sportiveConverter
                .convertModelsToDtos(sportiveService.sortSportivesAscendingByFirstName())).getSportives();
        log.trace("end sort sportives={}",cpy);
        return cpy;
    }
    
    @RequestMapping(value = "/sportives/filterAge/{age}", method = RequestMethod.GET)
    List<SportiveDto> getSportivesByAge(@PathVariable int age) {
        log.trace("begin filter sportives name={}",age);
        List<SportiveDto> cpy= new SportivesDto(sportiveConverter
                .convertModelsToDtos(sportiveService.filterSportivesByAge(age))).getSportives();
        log.trace("end filter sportives={}",cpy);
        return cpy;
    }

    @RequestMapping(value = "/sportives/filterFirstName/{firstName}", method = RequestMethod.GET)
    List<SportiveDto> getSportivesByFirstName(@PathVariable String firstName) {
        log.trace("begin filter sportives name={}",firstName);
        List<SportiveDto> cpy = new SportivesDto(sportiveConverter
                .convertModelsToDtos(sportiveService.filterSportivesByFirstName(firstName))).getSportives();
        log.trace("end filter sportives={}",cpy);
        return cpy;
    }

    @RequestMapping(value = "/sportives/filterTeamId/{teamId}", method = RequestMethod.GET)
    List<SportiveDto> getSportivesByTeamId(@PathVariable int teamId) {
        log.trace("begin filter sportives name={}",teamId);
        List<SportiveDto> cpy = new SportivesDto(sportiveConverter
                .convertModelsToDtos(sportiveService.filterSportivesByTeamId(teamId))).getSportives();
        log.trace("end filter sportives={}",cpy);
        return cpy;
    }

}
