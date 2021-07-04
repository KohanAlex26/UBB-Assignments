package catalog.service;

import domain.entities.Team;
import domain.exceptions.ValidatorException;
import domain.validators.TeamValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.InMemoryRepository;
import service.TeamService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TeamServiceTest {
    private TeamService teamService;

    @Before
    public void setUp() throws Exception{
        teamService = new TeamService(new InMemoryRepository<>(new TeamValidator()));
        Team team1 = new Team("a");
        Team team2 = new Team("b");
        Team team3 = new Team("c");
        team1.setId(1L);
        team2.setId(2L);
        team3.setId(3L);
        teamService.addTeam(team1);
        teamService.addTeam(team2);
        teamService.addTeam(team3);
    }

    @After
    public void tearDown(){
        teamService = null;
    }


    @Test
    public void testAddTeam() throws Exception {
        Team team4 = new Team("d");
        team4.setId(4L);
        teamService.addTeam(team4);
        assertEquals("The team should be saved", teamService.getAll().size(), 4);
    }

    @Test(expected = domain.exceptions.ValidatorException.class)
    public void testAddTeamException() throws ValidatorException {
        Team team4 = new Team("");
        team4.setId(4L);
        teamService.addTeam(team4);
    }

    @Test
    public void testDeleteTeam() throws Exception{
        teamService.deleteTeam(3L);
        assertEquals("The team should be deleted", teamService.getAll().size(), 2);
    }

    @Test(expected = domain.exceptions.ValidatorException.class)
    public void testDeleteTeamException() throws ValidatorException{
        teamService.deleteTeam(-2L);
    }

    @Test
    public void testUpdateTeam() throws Exception{
        Team team5 = new Team("e");
        team5.setId(2L);
        teamService.updateTeam(team5);
        assertTrue(teamService.getAll().contains(team5));
    }

    @Test(expected = domain.exceptions.ValidatorException.class)
    public void testUpdateTeamException() throws ValidatorException{
        Team team5 = new Team("e");
        team5.setId(-2L);
        teamService.updateTeam(team5);
    }

    @Test
    public void testGetAllTeams(){
        assertEquals(teamService.getAll().size(), 3);
    }

    @Test
    public void testFilterByName(){
        String name = "a";
        assertEquals(teamService.filterByTeamName(name).size(), 1);
    }
}
