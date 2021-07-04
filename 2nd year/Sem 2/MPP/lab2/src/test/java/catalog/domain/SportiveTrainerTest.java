package catalog.domain;

import domain.entities.SportiveTrainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author dzen.
 */
public class SportiveTrainerTest {
    public static final Long ID = 1L;
    public static final Long NEW_ID = 2L;
    public static final Long SPORTIVE_ID = 1L;
    public static final Long NEW_SPORTIVE_ID = 2L;
    public static final Long TRAINER_ID = 1L;
    public static final Long NEW_TRAINER_ID = 2L;
    public static final String TRAINING_TYPE = "continuous";
    public static final String NEW_TRAINING_TYPE = "weight";
    public static final int COST = 10;
    public static final int NEW_COST = 20;

    private SportiveTrainer st;

    @Before
    public void setUp() throws Exception{
        st = new SportiveTrainer(SPORTIVE_ID, TRAINER_ID, TRAINING_TYPE, COST);
        st.setId(ID);
    }

    @After
    public void tearDown() throws Exception{
        st = null;
    }

    @Test
    public void testGetId() throws Exception{
        assertEquals("The IDs should be equal", ID, st.getId());
    }

    @Test
    public void testSetId() throws Exception{
        st.setId(NEW_ID);
        assertEquals("The IDs should be equal", NEW_ID, st.getId());
    }

    @Test
    public void testGetSportiveID() throws Exception{
        assertEquals("The sportive IDs should be equal", SPORTIVE_ID, st.getSportiveID());
    }

    @Test
    public void testSetSportiveID() throws Exception{
        st.setSportiveID(NEW_SPORTIVE_ID);
        assertEquals("The sportive IDs should be equal", NEW_SPORTIVE_ID, st.getSportiveID());
    }

    @Test
    public void testGetTrainerID() throws Exception{
        assertEquals("The trainer IDs should be equal", TRAINER_ID, st.getTrainerID());
    }

    @Test
    public void testSetTrainerID() throws Exception{
        st.setTrainerID(NEW_TRAINER_ID);
        assertEquals("The sportive IDs should be equal", NEW_TRAINER_ID, st.getTrainerID());
    }

    @Test
    public void testGetTrainingType() throws Exception{
        assertEquals("The training types should be equal", TRAINING_TYPE, st.getTrainingType());
    }

    @Test
    public void testSetTrainingType() throws Exception{
        st.setTrainingType(NEW_TRAINING_TYPE);
        assertEquals("The training types should be equal", NEW_TRAINING_TYPE, st.getTrainingType());
    }

    @Test
    public void testGetCost() throws Exception{
        assertEquals("The costs should be equal", COST, st.getCost());
    }

    @Test
    public void testSetCost() throws Exception{
        st.setCost(NEW_COST);
        assertEquals("The costs should be equal", NEW_COST, st.getCost());
    }
}
