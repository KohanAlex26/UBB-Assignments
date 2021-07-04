package catalog.repository;

import domain.entities.Sportive;
import domain.entities.Trainer;
import domain.validators.SportiveValidator;
import domain.validators.TrainerValidator;
import domain.validators.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.InMemoryRepository;
import repository.file.SportiveFileRepository;
import repository.file.TrainerFileRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class FileRepositorySportiveTest {
    private Validator<Sportive> sportiveValidator;
    private InMemoryRepository<Long, Sportive> sportiveRepository;

    @Before
    public void setUp() {
        sportiveValidator = new SportiveValidator();
        sportiveRepository = new SportiveFileRepository(sportiveValidator,"data/test/sportives.txt");
    }

    @After
    public void tearDown() throws Exception {
        sportiveRepository =null;
    }

    @Test
    public void testSaveAllToFile(){
        Sportive sportive = new Sportive("a","b", 21,1);
        sportive.setId(1L);
        sportiveRepository.save(sportive);
        InMemoryRepository<Long,Sportive> testRepositoryTest = new SportiveFileRepository(sportiveValidator,"data/test/sportives.txt");
        Set<Sportive> trainers = (HashSet<Sportive>) testRepositoryTest.findAll();
        assertEquals(trainers.size(),1);
    }

    @Test
    public void testLoadData() {
        Set<Sportive> trainers = (HashSet<Sportive>) sportiveRepository.findAll();
        assertEquals(trainers.size(), 1);
    }
}
