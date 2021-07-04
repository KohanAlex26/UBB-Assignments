package catalog.repository;

import domain.entities.Sportive;
import domain.entities.Trainer;
import domain.exceptions.ValidatorException;
import domain.validators.TrainerValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.InMemoryRepository;
import repository.Repository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author oprea.
 */
public class InMemoryRepositoryTrainerTest {
    private Repository<Long, Trainer> trainerRepository;
    private Trainer trainer2;
    private Trainer trainer5;

    @Before
    public void setUp() throws Exception{
        TrainerValidator trainerValidator = new TrainerValidator();
        trainerRepository = new InMemoryRepository<>(trainerValidator);
        Trainer trainer1 = new Trainer("a", "a", 21);
        trainer2 = new Trainer("b", "b", 22);
        Trainer trainer3 = new Trainer("c", "c", 23);
        trainer1.setId(1L);
        trainer2.setId(2L);
        trainer3.setId(3L);
        trainerRepository.save(trainer1);
        trainerRepository.save(trainer2);
        trainerRepository.save(trainer3);
    }

    @After
    public void tearDown() throws Exception{
        trainerRepository = null;
    }

    @Test
    public void testFindOne() throws Exception {
        assertEquals("Trainer should be in the repo",trainerRepository.findOne(2L), Optional.ofNullable(trainer2));
    }


    @Test
    public void testFindAll() throws Exception {
        Iterable<Trainer> trainers = trainerRepository.findAll();
        StringBuilder st= new StringBuilder();
        for(Trainer s: trainers) {
            st.append(s);
        }

        String expected1 = "Trainer{id='1', firstName='a', lastName='a', age=21}";
        String expected2 = "Trainer{id='2', firstName='b', lastName='b', age=22}";
        String expected3 = "Trainer{id='3', firstName='c', lastName='c', age=23}";

        boolean flag = false;
        if(st.toString().equals(expected1+expected2+expected3) ||
                st.toString().equals(expected1+expected3+expected2) ||
                st.toString().equals(expected2+expected1+expected3) ||
                st.toString().equals(expected2+expected3+expected1) ||
                st.toString().equals(expected3+expected1+expected2) ||
                st.toString().equals(expected3+expected2+expected1))
            flag = true;

        assertTrue("Should list all the elements", flag);
    }


    @Test
    public void testSave() throws Exception {
        Trainer trainer4 = new Trainer("d", "d", 24);
        trainer4.setId(4L);
        trainerRepository.save(trainer4);
        assertEquals("The trainer should be saved in repo",Optional.ofNullable(trainer4),trainerRepository.findOne(trainer4.getId()));
    }


    @Test(expected = ValidatorException.class)
    public void testSaveException() throws Exception {
        trainer5 = new Trainer("e","e",-1);
        trainer5.setId(5L);
        assertEquals("Should raise exception because age<0",trainerRepository.save(trainer5),ValidatorException.class);
    }


    @Test
    public void testDelete() throws Exception {
        trainerRepository.delete(1L);
        assertEquals("The trainer should not be in repo",Optional.empty(),trainerRepository.findOne(1L));
    }


    @Test
    public void testUpdate() throws Exception {
        trainer5 = new Trainer("e","e",25);
        trainer5.setId(3L);
        trainerRepository.update(trainer5);
        assertEquals("The trainer should be updated",Optional.ofNullable(trainer5),trainerRepository.findOne(3L));
    }
}
