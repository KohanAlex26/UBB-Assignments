package catalog.domain;

import domain.entities.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Sebi
 */
public class TeamTest {
    public static final Long newId = 6L;
    public static final Long setId = 7L;
    public static final Long deleteID = 3L;
    public static final Long updateId = 1L;

    public static final String updateTeamName = "RoboBlast";
    public static final String newTeamName = "LeiiDeAur";

    private Team team;

    @Before
    public void setUp() throws Exception{
        team = new Team(newTeamName);
        team.setId(newId);
    }

    @After
    public void tearDown() throws Exception{
        this.team = null;
    }


    //tests for getters
    @Test
    public void testGetId() throws Exception{
        assertEquals("The ID's should be equal", team.getId(), newId);
    }
    @Test
    public void testGetTeamName() throws Exception{
        assertEquals("The team names should be equal", team.getTeamName(), newTeamName);
    }


    //tests for setters
    @Test
    public void testSetId() throws Exception{
        this.team.setId(setId);
        assertEquals("The ID's should be equal", team.getId(), setId);
    }
    @Test
    public void testSetTeamName() throws Exception{
        this.team.setTeamName(updateTeamName);
        assertEquals("The team names should be equal", team.getTeamName(), updateTeamName);
    }




}
