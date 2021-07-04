package core.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import core.ITConfig;
import core.domain.Trainer;
import core.domain.SportiveTrainer;
import core.repository.trainer.TrainerRepository;
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
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/dbtest/db-data.xml")
public class TrainerRepositoryTest {
    @Autowired
    private TrainerRepository trainerRepository;

//    @Test
//    public void findTrainerByIdWithSportives() {
//        Optional<Trainer> trainer = trainerRepository.findTrainerByIdWithSportives(1L);
//        assertTrue(trainer.isPresent());
//        assertEquals("Trainer 1 should have no sportives", 0, trainer.get().getSportives().size());
//        trainer = trainerRepository.findTrainerByIdWithSportives(2L);
//        assertTrue(trainer.isPresent());
//        assertEquals("Trainer 2 should have 1 sportive", 1, trainer.get().getSportives().size());
//        trainer = trainerRepository.findTrainerByIdWithSportives(10L);
//        assertFalse("Trainer 10 should not exist", trainer.isPresent());
//    }

    @Test
    public void findSportiveTrainersOfTrainerByTrainerIdJPQL() {
        List<SportiveTrainer> sportiveTrainers = trainerRepository.findSportiveTrainerOfTrainerByTrainerIdJPQL(1L);
        assertEquals("Trainer 1 should have one sportiveTrainer", sportiveTrainers.size(), 1);
        sportiveTrainers = trainerRepository.findSportiveTrainerOfTrainerByTrainerIdJPQL(2L);
        assertEquals("Trainer 2 should have no sportiveTrainers", sportiveTrainers.size(), 0);
    }

//    @Test
//    public void findSportiveTrainersOfTrainerByTrainerIdCriteria() {
//        List<SportiveTrainer> sportiveTrainers = trainerRepository.findSportiveTrainersOfTrainerByTrainerIdCriteria(1L);
//        assertEquals("Trainer 1 should have one sportiveTrainer", sportiveTrainers.size(), 1);
//        sportiveTrainers = trainerRepository.findSportiveTrainersOfTrainerByTrainerIdCriteria(2L);
//        assertEquals("Trainer 2 should have no sportiveTrainers", sportiveTrainers.size(), 0);
//    }

    @Test
    public void findSportiveTrainersOfTrainerByTrainerIdNative() {
        List<SportiveTrainer> sportiveTrainers = trainerRepository.findSportiveTrainerOfTrainerByTrainerIdNative(1L);
        assertEquals("Trainer 1 should have one sportiveTrainer", sportiveTrainers.size(), 1);
        sportiveTrainers = trainerRepository.findSportiveTrainerOfTrainerByTrainerIdNative(2L);
        assertEquals("Trainer 2 should have no sportiveTrainers", sportiveTrainers.size(), 0);
    }
}
