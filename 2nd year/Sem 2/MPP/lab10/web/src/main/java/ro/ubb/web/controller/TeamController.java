package ro.ubb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.core.service.ITeamService;
import ro.ubb.web.converter.TeamConverter;
import ro.ubb.web.dto.TeamDto;
import ro.ubb.web.dto.TeamsDto;


import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
public class TeamController {
    public static final Logger log = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private ITeamService teamService;

    @Autowired
    private TeamConverter teamConverter;

    @RequestMapping(value="/teams/get-page/pageno={pageNo},size={size}",method= RequestMethod.GET)
    List<TeamDto> getTeamsOnPage(@PathVariable Integer pageNo, @PathVariable Integer size){
        log.trace("begin get page={}",pageNo);
        return teamConverter.convertModelsToDtos(teamService.getTeamsOnPage(pageNo,size));
    }

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    List<TeamDto> getTeams() {
        log.trace("begin get teams");
        List<TeamDto> cpy = new TeamsDto(teamConverter
                .convertModelsToDtos(teamService.getAllTeams())).getTeams();
        log.trace("end get teams={}",cpy);
        return cpy;
    }

    @CrossOrigin
    @RequestMapping(value = "/teams", method = RequestMethod.POST)
    ResponseEntity<?> saveTeam(@RequestBody TeamDto TeamDto) {
        log.trace("begin add team={}", TeamDto);
        try {
            teamService.addTeam(
                    teamConverter.convertDtoToModel(TeamDto)
            );
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/teams/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updateTeam(@RequestBody TeamDto teamDto) {
        log.trace(teamDto.getTeamName(),"\n\n\n");
        log.trace("begin update team={}", teamDto);
        try {
            teamService.updateTeam(teamConverter.convertDtoToModel(teamDto));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/teams/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteTeam(@PathVariable Long id){
        log.trace("begin delete team with id={}", id);
        try {
            teamService.deleteTeam(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/teams/sort", method = RequestMethod.GET)
    List<TeamDto> getTeamsSorted() {
        log.trace("begin sort teams");
        List<TeamDto> cpy = new TeamsDto(teamConverter
                .convertModelsToDtos(teamService.sortTeamsAscendingByName())).getTeams();
        log.trace("end sort teams={}",cpy);
        return cpy;
    }

    @RequestMapping(value = "/teams/filterByName/{teamName}", method = RequestMethod.GET)
    List<TeamDto> getTeamsByFirstName(@PathVariable String teamName) {
        log.trace("begin filter teams name={}",teamName);
        List<TeamDto> cpy = new TeamsDto(teamConverter
                .convertModelsToDtos(teamService.filterTeamsByTeamName(teamName))).getTeams();
        log.trace("end filter teams={}",cpy);
        return cpy;
    }
}
