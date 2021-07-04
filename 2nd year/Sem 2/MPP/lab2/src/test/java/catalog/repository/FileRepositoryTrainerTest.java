package catalog.repository;

import domain.entities.Trainer;
import domain.validators.TrainerValidator;
import domain.validators.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.InMemoryRepository;
import repository.file.TrainerFileRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileRepositoryTrainerTest {
    private Validator<Trainer> trainerValidator;
    private InMemoryRepository<Long, Trainer> trainerRepository;

    @Before
    public void setUp() {
        trainerValidator = new TrainerValidator();
        trainerRepository = new TrainerFileRepository(trainerValidator,"data/test/trainers.txt");
    }

    @After
    public void tearDown() throws Exception {
        trainerRepository=null;
    }

    @Test
    public void testSaveAllToFile(){
        Trainer trainer = new Trainer("a","b", 21);
        trainer.setId(1L);
        trainerRepository.save(trainer);
        InMemoryRepository<Long,Trainer> testRepositoryTest = new TrainerFileRepository(trainerValidator,"data/test/trainers.txt");
        Set<Trainer> trainers = (HashSet<Trainer>) testRepositoryTest.findAll();
        assertEquals(trainers.size(),1);
    }

    @Test
    public void testLoadData() {
        Set<Trainer> trainers = (HashSet<Trainer>) trainerRepository.findAll();
        assertEquals(trainers.size(), 1);
    }
}
