package catalog.service;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Trainer;
import domain.exceptions.ValidatorException;
import domain.validators.SportiveTrainerValidator;
import domain.validators.SportiveValidator;
import domain.validators.TrainerValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.InMemoryRepository;
import service.SportiveTrainerService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class SportiveTrainerServiceTest {
    private SportiveTrainerService stService;
    private InMemoryRepository<Long, Sportive> sRepo;
    private InMemoryRepository<Long, Trainer> tRepo;
    private Sportive s1;
    private Sportive s2;
    private Sportive s3;
    private Trainer t1;
    private Trainer t2;
    private Trainer t3;

    @Before
    public void setUp() throws Exception{
        sRepo = new InMemoryRepository<>(new SportiveValidator());
        tRepo = new InMemoryRepository<>(new TrainerValidator());
        stService = new SportiveTrainerService(new InMemoryRepository<>(new SportiveTrainerValidator()), sRepo, tRepo);
        s1=new Sportive("a","a",1,1);
        s1.setId(1L);
        sRepo.save(s1);
        s2=new Sportive("b","b",2,1);
        s2.setId(2L);
        sRepo.save(s2);
        s3=new Sportive("c","c",3,2);
        s3.setId(3L);
        sRepo.save(s3);
        t1=new Trainer("a", "a", 1);
        t1.setId(1L);
        tRepo.save(t1);
        t2=new Trainer("b", "b", 2);
        t2.setId(2L);
        tRepo.save(t2);
        t3=new Trainer("c", "c", 3);
        t3.setId(3L);
        tRepo.save(t3);

    }

    @After
    public void tearDown(){
        stService = null;
    }

    @Test
    public void testAssociateSportiveTrainer() throws Exception{
        stService.associateSportiveTrainer(1L, s1, t1, "continuous", 34);
        assertEquals("The association should be in service", stService.getAll().size(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssociateSportiveTrainerException() throws Exception {
        stService.associateSportiveTrainer(2L, s1, t2, "abc", 50);
        assertEquals("Should raise an exception because the type is not available", stService.getAll().size(), 1);
    }

    @Test
    public void testGetAll() throws Exception{
        stService.associateSportiveTrainer(3L, s2, t2, "weight", 100);
        stService.associateSportiveTrainer(4L, s2, t3, "resistance", 20);
        StringBuilder s= new StringBuilder();
        for(SportiveTrainer sr: stService.getAll()){
            s.append(sr);
        }
        boolean flag = false;
        String expected1 = "SportiveTrainer{sportiveID=2, trainerID=2, cost=100, trainingType='weight'}";
        String expected2 = "SportiveTrainer{sportiveID=2, trainerID=3, cost=20, trainingType='resistance'}";
        if(s.toString().equals(expected1+expected2) || s.toString().equals(expected2+expected1)){
            flag = true;
        }
        assertTrue("All the elements shoul be in the set!", flag);
    }

    @Test
    public void testDissociationSportiveTrainer() throws Exception{
        stService.associateSportiveTrainer(3L, s2, t2, "weight", 100);
        stService.dissociationSportiveTrainer(3L);
        assertEquals("The dissociation should be made", stService.getAll().size(), 0);
    }

    @Test
    public void testGetOneSportive() throws Exception{
        Sportive s = new Sportive("a","a",1,1);
        s.setId(1L);
        sRepo.save(s);
        assertEquals("The sportive should be found", Optional.of(s).toString(), stService.getOneSportive(s.getId()).toString());
    }

    @Test
    public void testGetOneTrainer() throws Exception{
        Trainer t = new Trainer("a", "a", 1);
        t.setId(1L);
        tRepo.save(t);
        assertEquals("The trainer should be found", Optional.of(t).toString(), stService.getOneTrainer(t.getId()).toString());
    }

    @Test
    public void testAllTrainersOfOneSportive() throws Exception{
        Set<Trainer> tr = new HashSet<>();
        tr.add(t2);
        tr.add(t3);
        stService.associateSportiveTrainer(3L, s1, t2, "weight", 100);
        stService.associateSportiveTrainer(4L, s2, t3, "resistance", 20);
        stService.associateSportiveTrainer(5L, s1, t3, "weight", 120);
        assertTrue("All the trainers should be in the result set", tr.containsAll(stService.allTrainersOfOneSportive(s1)));
    }

    @Test
    public void testAllSportivesOfOneTrainer() throws Exception{
        Set<Sportive> sr = new HashSet<>();
        sr.add(s1);
        sr.add(s2);
        stService.associateSportiveTrainer(3L, s1, t2, "weight", 100);
        stService.associateSportiveTrainer(4L, s2, t3, "resistance", 20);
        stService.associateSportiveTrainer(5L, s1, t3, "weight", 120);
        assertTrue("All the sportives should be in the result set", sr.containsAll(stService.allSportivesOfOneTrainer(t3)));
    }

    @Test
    public void testMaxCost() throws Exception{
        stService.associateSportiveTrainer(3L, s1, t2, "weight", 100);
        stService.associateSportiveTrainer(4L, s2, t3, "resistance", 20);
        stService.associateSportiveTrainer(5L, s1, t3, "weight", 150);
        assertEquals("The cost shoul be maximum", 150, stService.maxCost());
    }

    @Test
    public void testMostExpensiveTraining() throws Exception{
        stService.associateSportiveTrainer(3L, s1, t2, "weight", 100);
        stService.associateSportiveTrainer(4L, s2, t3, "resistance", 20);
        stService.associateSportiveTrainer(5L, s1, t3, "weight", 120);
        String str = "ID: 5-> Sportive{id='1', firstName='a', lastName='a', age=1, teamId=1}\n" +
                " \t + Trainer{id='3', firstName='c', lastName='c', age=3}; type: weight; cost: 120";
        assertEquals("The two training should be the same", str, stService.mostExpensiveTraining());
    }
}
