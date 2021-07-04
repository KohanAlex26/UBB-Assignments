package catalog.service;

import domain.entities.Sportive;
import domain.entities.Trainer;
import domain.exceptions.ValidatorException;
import domain.validators.SportiveTrainerValidator;
import domain.validators.SportiveValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.InMemoryRepository;
import service.SportiveService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SportiveServiceTest {
    private SportiveService sportiveService;

    @Before
    public void setUp() throws Exception{
        sportiveService = new SportiveService(new InMemoryRepository<>(new SportiveValidator()),new InMemoryRepository<>(new SportiveTrainerValidator()));
        Sportive sportive1 = new Sportive("a", "a", 1,1);
        Sportive sportive2 = new Sportive("b", "b", 2,2);
        Sportive sportive3 = new Sportive("c", "c", 3,3);
        sportive1.setId(1L);
        sportive2.setId(2L);
        sportive3.setId(3L);
        sportiveService.addSportive(sportive1);
        sportiveService.addSportive(sportive2);
        sportiveService.addSportive(sportive3);
    }

    @After
    public void tearDown(){
        sportiveService = null;
    }


    @Test
    public void testAddSportive() throws Exception {
        Sportive sportive4 = new Sportive("d","d",4,4);
        sportive4.setId(4L);
        sportiveService.addSportive(sportive4);
        assertEquals("The sportive should be saved", sportiveService.getAll().size(), 4);
    }

    @Test(expected = domain.exceptions.ValidatorException.class)
    public void testAddTrainerException() throws ValidatorException {
        Sportive sportive4 = new Sportive("d", "d", -1,4);
        sportive4.setId(4L);
        sportiveService.addSportive(sportive4);
    }


    @Test
    public void testGetAllSportives(){
        assertEquals(sportiveService.getAll().size(), 3);
    }

    @Test
    public void testFilterByAge(){
        assertEquals(sportiveService.filterByAge(2).size(), 1);
    }

    @Test
    public void testFilterByFirstName(){
        assertEquals(sportiveService.filterByFirstName("a").size(), 1);
    }

    @Test
    public void testFilterByTeamId(){
        assertEquals(sportiveService.filterByTeamId(1).size(), 1);
    }

    @Test
    public void testDeleteSportive() throws Exception{
        sportiveService.deleteSportive(3L);
        assertEquals("The sportive should be deleted", sportiveService.getAll().size(), 2);
    }

    @Test(expected = domain.exceptions.ValidatorException.class)
    public void testDeleteSportiveException() throws ValidatorException{
        sportiveService.deleteSportive(-2L);
    }

    @Test
    public void testSportiveTrainer() throws Exception{
        Sportive sportive5 = new Sportive("e","e",25,25);
        sportive5.setId(2L);
        sportiveService.updateSportive(sportive5);
        assertTrue(sportiveService.getAll().contains(sportive5));
    }

    @Test(expected = domain.exceptions.ValidatorException.class)
    public void testUpdateSportiveException() throws ValidatorException{
        Sportive sportive5 = new Sportive("e","e",25,25);
        sportive5.setId(-2L);
        sportiveService.updateSportive(sportive5);
    }
}
