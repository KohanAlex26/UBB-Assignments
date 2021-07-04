package catalog.repository;

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
import repository.Repository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InMemoryRepositorySportiveTrainerTest {
    private Repository<Long, SportiveTrainer> stRepo;
    private Repository<Long, Sportive> sRepo;
    private SportiveValidator sv;
    private Repository<Long, Trainer> tRepo;
    private TrainerValidator tv;
    private SportiveTrainerValidator stv;
    private Sportive s1;
    private Sportive s2;
    private Sportive s3;
    private Trainer t1;
    private Trainer t2;
    private Trainer t3;
    private SportiveTrainer st1;
    private SportiveTrainer st2;
    private SportiveTrainer st3;
    private SportiveTrainer st4;
    private SportiveTrainer st5;

    @Before
    public void setUp(){
        stv = new SportiveTrainerValidator();
        stRepo = new InMemoryRepository<>(stv);
        sv = new SportiveValidator();
        sRepo = new InMemoryRepository<>(sv);
        tv = new TrainerValidator();
        tRepo = new InMemoryRepository<>(tv);
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

        st1 = new SportiveTrainer(s1.getId(), t1.getId(), "continuous", 100);
        st1.setId(1L);
        stRepo.save(st1);
        st2 = new SportiveTrainer(s1.getId(), t2.getId(), "fartlek", 50);
        st2.setId(2L);
        stRepo.save(st2);
    }

    @After
    public void tearDown() throws Exception{
        stRepo = null;
    }

    @Test
    public void testFindOne() throws Exception{
        assertEquals("The association should be in the repo", stRepo.findOne(1L), Optional.ofNullable(st1));
    }

    @Test
    public void testFindAll() throws Exception{
        Iterable<SportiveTrainer> all = stRepo.findAll();
        StringBuilder s = new StringBuilder();
        for(SportiveTrainer st: all){
            s.append(st);
        }
        String expected1 = "SportiveTrainer{sportiveID=1, trainerID=1, cost=100, trainingType='continuous'}";
        String expected2 = "SportiveTrainer{sportiveID=1, trainerID=2, cost=50, trainingType='fartlek'}";
        boolean flag = false;
        if(s.toString().equals(expected1+expected2) || s.toString().equals(expected2+expected1)){
            flag = true;
        }
        assertEquals("Should list all the elements", flag, true);
    }

    @Test
    public void testSave() throws Exception{
        st3 = new SportiveTrainer(s2.getId(), t1.getId(), "weight", 75);
        st3.setId(3L);
        stRepo.save(st3);
        assertEquals("The entity should be saved in the repo", Optional.ofNullable(st3), stRepo.findOne(3L));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveException() throws Exception{
        st4 = new SportiveTrainer(s2.getId(), t3.getId(), "abc", 34);
        st4.setId(4L);
        assertEquals("Should raise exception because training type is not available", stRepo.save(st4), ValidatorException.class);
    }

    @Test
    public void testDelete() throws Exception {
        stRepo.delete(1L);
        assertEquals("The entity should not be in the repo",Optional.empty(),stRepo.findOne(1L));
    }

    @Test(expected = ValidatorException.class)
    public void testUpdateException() throws Exception {
        st5 = new SportiveTrainer(s3.getId(), t3.getId(), "resistance", -1);
        st5.setId(1L);
        assertEquals("Should raise exception because cost < 0",stRepo.update(st5),ValidatorException.class);
    }
}
