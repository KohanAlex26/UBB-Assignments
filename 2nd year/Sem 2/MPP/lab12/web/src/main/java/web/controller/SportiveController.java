package web.controller;

import core.domain.Sportive;
import core.service.SportiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.SportiveConverter;
import web.dto.SportiveDTO;
import web.dto.SportivesDTO;

import java.util.List;
import java.util.Set;

@RestController
public class SportiveController {
    public static final Logger logger = LoggerFactory.getLogger(SportiveController.class);

    @Autowired
    private SportiveService sportiveService;

    @Autowired
    private SportiveConverter sportiveConverter;

    @RequestMapping(value = "/sportives", method = RequestMethod.GET)
    List<SportiveDTO> getSportives() {
        logger.trace("begin get sportives");
        List<SportiveDTO> cpy = new SportivesDTO(sportiveConverter
                .convertModelsToDTOs(sportiveService.getAllSportives())).getSportives();
        logger.trace("end get sportives={}",cpy);
        return cpy;
    }
    @RequestMapping(value="/sportives/get-page/pageno={pageNo},size={size}",method=RequestMethod.GET)
    List<SportiveDTO> getSportivesOnPage(@PathVariable Integer pageNo, @PathVariable Integer size){
        return sportiveConverter.convertModelsToDTOs(sportiveService.getSportivesOnPage(pageNo,size));
    }

//    @RequestMapping(value = "/sportives/{id}")
//    SportivesDTO getSportivesFromRepositoryByCatId(@PathVariable Long id) {
//        List<Sportive> sportives = sportiveService.getSportivesOfCat(id);
//        return new SportivesDTO(sportiveConverter.convertModelsToDTOs(sportives));
//    }

    @RequestMapping(value = "/sportives", method = RequestMethod.POST)
    void addSportive(@RequestBody SportiveDTO sportiveDTO) {
        logger.trace(sportiveDTO.toString());
        var sportive = sportiveConverter.convertDtoToModel(sportiveDTO);
        sportiveService.addSportive(
                sportive.getFirstName(),
                sportive.getLastName(),
                sportive.getAge(),
                sportive.getTeam()
        );
    }

    @RequestMapping(value = "/sportives/{id}", method = RequestMethod.PUT)
    void updateSportive(@PathVariable Long id, @RequestBody SportiveDTO sportiveDTO) {
        var sportive = sportiveConverter.convertDtoToModel(sportiveDTO);
        sportiveService.updateSportive(
                id,
                sportive.getFirstName(),
                sportive.getLastName(),
                sportive.getAge(),
                sportive.getTeam()
        );
    }

    @RequestMapping(value = "/sportives/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteSportive(@PathVariable Long id) {
        sportiveService.deleteSportive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/sportives/filterAge/{age}", method = RequestMethod.GET)
    List<SportiveDTO> getSportivesByAge(@PathVariable int age) {
        logger.trace("begin filter sportives name={}",age);
        List<SportiveDTO> cpy= new SportivesDTO(sportiveConverter
                .convertModelsToDTOs(sportiveService.filterSportivesByAge(age))).getSportives();
        logger.trace("end filter sportives={}",cpy);
        return cpy;
    }

    @RequestMapping(value = "/sportives/filterFirstName/{firstName}", method = RequestMethod.GET)
    List<SportiveDTO> getSportivesByFirstName(@PathVariable String firstName) {
        logger.trace("begin filter sportives name={}",firstName);
        List<SportiveDTO> cpy = new SportivesDTO(sportiveConverter
                .convertModelsToDTOs(sportiveService.filterSportivesByFirstName(firstName))).getSportives();
        logger.trace("end filter sportives={}",cpy);
        return cpy;
    }

    @RequestMapping(value = "/sportives/filterTeamId/{teamId}", method = RequestMethod.GET)
    List<SportiveDTO> getSportivesByTeamId(@PathVariable int teamId) {
        logger.trace("begin filter sportives name={}",teamId);
        List<SportiveDTO> cpy = new SportivesDTO(sportiveConverter
                .convertModelsToDTOs(sportiveService.filterSportivesByTeamId(teamId))).getSportives();
        logger.trace("end filter sportives={}",cpy);
        return cpy;
    }

    @RequestMapping(value = "/sportives/sort", method = RequestMethod.GET)
    List<SportiveDTO> getSportivesSorted() {
        logger.trace("begin sort sportives");
        List<SportiveDTO> cpy = new SportivesDTO(sportiveConverter
                .convertModelsToDTOs(sportiveService.sortSportivesAscendingByFirstName())).getSportives();
        logger.trace("end sort sportives={}",cpy);
        return cpy;
    }


    @RequestMapping(value = "/sportives/{id}")
    SportivesDTO getSportivesFromRepositoryByTeamId(@PathVariable Long id) {
        List<Sportive> sportives = sportiveService.getSportivesOfTeam(id);
        return new SportivesDTO(sportiveConverter.convertModelsToDTOs(sportives));
    }

}
