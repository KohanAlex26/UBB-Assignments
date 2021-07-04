package catalog.domain;

import domain.entities.Sportive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author radu.
 */
public class SportiveTest {
    private String firstName;
    private String lastName;
    private int age;
    private int teamId;

    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private static final String FIRST_NAME = "a";
    private static final String NEW_FIRST_NAME = "b";
    private static final String LAST_NAME = "c";
    private static final String NEW_LAST_NAME = "d";
    private static final int AGE = 10;
    private static final int NEW_AGE = 20;
    private static final int TEAMID = 1;
    private static final int NEW_TEAMID = 2;


    private Sportive sportive;

    @Before
    public void setUp() throws Exception {
        sportive = new Sportive(FIRST_NAME,LAST_NAME,AGE,TEAMID);
        sportive.setId(ID);
    }

    @After
    public void tearDown() throws Exception {
        sportive=null;
    }

    @Test
    public void testGetFirstName() throws Exception {
        assertEquals("First names should be equal", FIRST_NAME, sportive.getFirstName());
    }

    @Test
    public void testSetFirstName() throws Exception {
        sportive.setFirstName(NEW_FIRST_NAME);
        assertEquals("First names should be equal", NEW_FIRST_NAME, sportive.getFirstName());
    }

    @Test
    public void testGetLastName() throws Exception {
        assertEquals("Last names should be equal", LAST_NAME, sportive.getLastName());
    }

    @Test
    public void testSetLastName() throws Exception {
        sportive.setLastName(NEW_LAST_NAME);
        assertEquals("Last names should be equal", NEW_LAST_NAME, sportive.getLastName());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("Ids should be equal", ID, sportive.getId());
    }

    @Test
    public void testSetId() throws Exception {
        sportive.setId(NEW_ID);
        assertEquals("Ids should be equal", NEW_ID, sportive.getId());
    }

    @Test
    public void testGetAge() throws Exception {
        assertEquals("Ages should be equal", AGE, sportive.getAge());
    }

    @Test
    public void testSetAge() throws Exception {
        sportive.setAge(NEW_AGE);
        assertEquals("Ages should be equal", NEW_AGE, sportive.getAge());

    }

    @Test
    public void testGetTeamId() throws Exception {
        assertEquals("Team ids should be equal", TEAMID, sportive.getTeamId());
    }

    @Test
    public void testSetRezistance() throws Exception {
        sportive.setTeamId(NEW_TEAMID);
        assertEquals("Team ids should be equal", NEW_TEAMID, sportive.getTeamId());

    }
}