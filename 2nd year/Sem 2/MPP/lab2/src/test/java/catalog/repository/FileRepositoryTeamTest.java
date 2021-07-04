package catalog.repository;

import domain.entities.Team;
import domain.entities.Trainer;
import domain.validators.TeamValidator;
import domain.validators.TrainerValidator;
import domain.validators.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.InMemoryRepository;
import repository.file.TeamFileRepository;
import repository.file.TrainerFileRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class FileRepositoryTeamTest {
    private Validator<Team> teamValidator;
    private InMemoryRepository<Long, Team> teamRepository;

    @Before
    public void setUp() {
        teamValidator = new TeamValidator();
        teamRepository = new TeamFileRepository(teamValidator,"data/test/teams.txt");
    }

    @After
    public void tearDown() throws Exception {
        teamRepository =null;
    }

    @Test
    public void testSaveAllToFile(){
        Team team = new Team("a");
        team.setId(1L);
        teamRepository.save(team);
        InMemoryRepository<Long,Team> testRepositoryTest = new TeamFileRepository(teamValidator,"data/test/teams.txt");
        Set<Team> trainers = (HashSet<Team>) testRepositoryTest.findAll();
        assertEquals(trainers.size(),1);
    }

    @Test
    public void testLoadData() {
        Set<Team> teams = (HashSet<Team>) teamRepository.findAll();
        assertEquals(teams.size(), 1);
    }
}
