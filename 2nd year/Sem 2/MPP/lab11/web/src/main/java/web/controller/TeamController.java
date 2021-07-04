package web.controller;


import core.domain.Sportive;
import core.domain.Team;
import core.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.TeamConverter;
import web.dto.SportivesDTO;
import web.dto.TeamDTO;
import web.dto.TeamsDTO;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Set;

@RestController
public class TeamController {
    public static final Logger logger = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamConverter teamConverter;

    @RequestMapping(value="/teams/get-page/pageno={pageNo},size={size}",method= RequestMethod.GET)
    List<TeamDTO> getTeamsOnPage(@PathVariable Integer pageNo, @PathVariable Integer size){
        return teamConverter.convertModelsToDTOs(teamService.getTeamsOnPage(pageNo,size));
    }

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    List<TeamDTO> getTeams() {
        logger.trace("begin get teams");
        List<TeamDTO> cpy = new TeamsDTO(teamConverter
                .convertModelsToDTOs(teamService.getAllTeams())).getTeams();
        logger.trace("end get teams={}",cpy);
        return cpy;
    }



//    @RequestMapping(value = "/teams/{breed}")
//    TeamsDTO getTeamsByBreed(@PathVariable String breed) {
//        logger.trace("getTeamsByBreed - method entered - breed: " + breed);
//        List<Team> teams = teamService.getTeamsByBreed(breed);
//        TeamsDTO teamsDTO = new TeamsDTO(teamConverter.convertModelsToDTOs(teams));
//        logger.trace("getAllTeams: " + teams);
//        return teamsDTO;
//    }

    @RequestMapping(value = "/teams", method = RequestMethod.POST)
    void addTeam(@RequestBody TeamDTO teamDTO) throws ValidationException {
        logger.trace("addTeam - method entered - teamDTO: " + teamDTO);
        var team = teamConverter.convertDtoToModel(teamDTO);
        teamService.addTeam(
                team.getTeamName()
        );
        logger.trace("addTeam - team added");
    }

    @RequestMapping(value = "/teams/{id}", method = RequestMethod.PUT)
    void updateTeam(@PathVariable Long id, @RequestBody TeamDTO teamDTO) throws ValidationException {
        logger.trace("updateTeam - method entered - teamDTO: " + teamDTO);
        var team = teamConverter.convertDtoToModel(teamDTO);
        teamService.updateTeam(
                id,
                team.getTeamName()
        );
        logger.trace("updateTeam - team updated");
    }

    @RequestMapping(value = "/teams/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteTeam(@PathVariable Long id) throws ValidationException {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/teams/sort", method = RequestMethod.GET)
    List<TeamDTO> getTeamsSorted() {
        List<TeamDTO> cpy = new TeamsDTO(teamConverter
                .convertModelsToDTOs(teamService.sortTeamsAscendingByName())).getTeams();
        return cpy;
    }

    @RequestMapping(value = "/teams/filterByName/{teamName}", method = RequestMethod.GET)
    List<TeamDTO> getTeamsByFirstName(@PathVariable String teamName) {
        List<TeamDTO> cpy = new TeamsDTO(teamConverter
                .convertModelsToDTOs(teamService.filterTeamsByTeamName(teamName))).getTeams();
        return cpy;
    }

}
