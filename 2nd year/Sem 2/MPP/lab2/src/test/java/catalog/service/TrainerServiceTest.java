package catalog.service;

import domain.entities.Trainer;
import domain.exceptions.ValidatorException;
import domain.validators.SportiveTrainerValidator;
import domain.validators.TrainerValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.InMemoryRepository;
import service.TrainerService;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TrainerServiceTest {
    private TrainerService trainerService;

    @Before
    public void setUp() throws Exception{
        trainerService = new TrainerService(new InMemoryRepository<>(new TrainerValidator()),new InMemoryRepository<>(new SportiveTrainerValidator()));
        Trainer trainer1 = new Trainer("a", "a", 21);
        Trainer trainer2 = new Trainer("b", "b", 22);
        Trainer trainer3 = new Trainer("c", "c", 23);
        trainer1.setId(1L);
        trainer2.setId(2L);
        trainer3.setId(3L);
        trainerService.addTrainer(trainer1);
        trainerService.addTrainer(trainer2);
        trainerService.addTrainer(trainer3);
    }

    @After
    public void tearDown(){
        trainerService = null;
    }


    @Test
    public void testAddTrainer() throws Exception {
        Trainer trainer4 = new Trainer("d", "d", 24);
        trainer4.setId(4L);
        trainerService.addTrainer(trainer4);
        assertEquals("The trainer should be saved", trainerService.getAll().size(), 4);
    }

    @Test(expected = domain.exceptions.ValidatorException.class)
    public void testAddTrainerException() throws ValidatorException{
        Trainer trainer4 = new Trainer("d", "d", -1);
        trainer4.setId(4L);
        trainerService.addTrainer(trainer4);
    }

    @Test
    public void testDeleteTrainer() throws Exception{
        trainerService.deleteTrainer(3L);
        assertEquals("The trainer should be deleted", trainerService.getAll().size(), 2);
    }

    @Test(expected = domain.exceptions.ValidatorException.class)
    public void testDeleteTrainerException() throws ValidatorException{
        trainerService.deleteTrainer(-2L);
    }

    @Test
    public void testUpdateTrainer() throws Exception{
        Trainer trainer5 = new Trainer("e","e",25);
        trainer5.setId(2L);
        trainerService.updateTrainer(trainer5);
        assertTrue(trainerService.getAll().contains(trainer5));
    }

    @Test(expected = domain.exceptions.ValidatorException.class)
    public void testUpdateTrainerException() throws ValidatorException{
        Trainer trainer5 = new Trainer("e","e",25);
        trainer5.setId(-2L);
        trainerService.updateTrainer(trainer5);
    }

    @Test
    public void testGetAllTrainers(){
        assertEquals(trainerService.getAll().size(), 3);
    }

    @Test
    public void testFilterByName(){
        String name = "a";
        assertEquals(trainerService.filterTrainersByName(name).size(), 1);
    }
}