package catalog.domain;

import domain.entities.Trainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author oprea.
 */
public class TrainerTest {
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private static final String FIRST_NAME = "a";
    private static final String NEW_FIRST_NAME = "b";
    private static final String LAST_NAME = "c";
    private static final String NEW_LAST_NAME = "d";
    private static final int AGE = 10;
    private static final int NEW_AGE = 20;

    private Trainer trainer;

    @Before
    public void setUp() throws Exception{
        trainer = new Trainer(FIRST_NAME, LAST_NAME, AGE);
        trainer.setId(ID);
    }

    @After
    public void tearDown() throws Exception{
        trainer = null;
    }

    @Test
    public void testGetFirstName() throws Exception {
        assertEquals("First names should be equal", FIRST_NAME, trainer.getFirstName());
    }

    @Test
    public void testSetFirstName() throws Exception {
        trainer.setFirstName(NEW_FIRST_NAME);
        assertEquals("First names should be equal", NEW_FIRST_NAME, trainer.getFirstName());
    }

    @Test
    public void testGetLastName() throws Exception {
        assertEquals("Last names should be equal", LAST_NAME, trainer.getLastName());
    }

    @Test
    public void testSetLastName() throws Exception {
        trainer.setLastName(NEW_LAST_NAME);
        assertEquals("Last names should be equal", NEW_LAST_NAME, trainer.getLastName());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("Ids should be equal", ID, trainer.getId());
    }

    @Test
    public void testSetId() throws Exception {
        trainer.setId(NEW_ID);
        assertEquals("Ids should be equal", NEW_ID, trainer.getId());
    }

    @Test
    public void testGetAge() throws Exception {
        assertEquals("Ages should be equal", AGE, trainer.getAge());
    }

    @Test
    public void testSetAge() throws Exception {
        trainer.setAge(NEW_AGE);
        assertEquals("Ages should be equal", NEW_AGE, trainer.getAge());
    }
}
