package core.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import core.ITConfig;
import core.domain.Award;
import core.domain.Trainer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/dbtest/db-data.xml")
public class TrainerServiceTest {
    @Autowired
    private TrainerService trainerService;

    @Test
    public void getTrainersFromRepository() {
        List<Trainer> trainers = trainerService.getTrainersFromRepository();
        assertEquals("there should be four trainers", 4, trainers.size());
    }


    @Test
    public void addTrainer() {
        trainerService.addTrainer("e", "e", 5, new Award(1L,"a"));
        assertEquals("there should now be 5 trainers", 5, trainerService.getTrainersFromRepository().size());
    }

    @Test
    public void deleteTrainer() {
        trainerService.deleteTrainer(4L);
        assertEquals("there should now be 3 trainers", 3, trainerService.getTrainersFromRepository().size());
    }

    @Test
    public void updateTrainer() {
        trainerService.updateTrainer(1L, "z", "z", 10, new Award(1L,"a"));
        for (Trainer trainer : trainerService.getTrainersFromRepository())
            if (trainer.getId() == 1L) {
                assertEquals(trainer.getFirstName(), "z");
                assertEquals(trainer.getLastName(), "z");
                assertEquals(trainer.getAge(), 10);
                return;
            }
        fail();
    }

    @Test
    public void findSportiveTrainerOfTrainerByTrainerId() {
        assertEquals("The first trainer should have one sportiveTrainer", 1, trainerService.findSportiveTrainerOfTrainerByTrainerId(1).size());
        assertEquals("The second trainer should have no sportiveTrainer", 0, trainerService.findSportiveTrainerOfTrainerByTrainerId(2).size());
    }
}
