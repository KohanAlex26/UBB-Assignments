package catalog.repository;

import domain.entities.SportiveTrainer;
import domain.validators.SportiveTrainerValidator;
import domain.validators.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.InMemoryRepository;
import repository.file.SportiveTrainerFileRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class FileRepositorySportiveTrainerTest {
    private Validator<SportiveTrainer> sportiveTrainerValidator;
    private InMemoryRepository<Long, SportiveTrainer> sportiveTrainerRepository;

    @Before
    public void setUp() {
        sportiveTrainerValidator = new SportiveTrainerValidator();
        sportiveTrainerRepository = new SportiveTrainerFileRepository(sportiveTrainerValidator,"data/test/sportivesTrainers.txt");
    }

    @After
    public void tearDown() throws Exception {
        sportiveTrainerRepository =null;
    }

    @Test
    public void testSaveAllToFile(){
        SportiveTrainer sportiveTrainer = new SportiveTrainer(1L,1L,"weight", 21);
        sportiveTrainer.setId(1L);
        sportiveTrainerRepository.save(sportiveTrainer);
        InMemoryRepository<Long,SportiveTrainer> testRepositoryTest = new SportiveTrainerFileRepository(sportiveTrainerValidator,"data/test/sportivesTrainers.txt");
        Set<SportiveTrainer> sportiveTrainers = (HashSet<SportiveTrainer>) testRepositoryTest.findAll();
        assertEquals(sportiveTrainers.size(),1);
    }

    @Test
    public void testLoadData() {
        Set<SportiveTrainer> sportiveTrainers = (HashSet<SportiveTrainer>) sportiveTrainerRepository.findAll();
        assertEquals(sportiveTrainers.size(), 1);
    }
}
