package catalog.repository;

import domain.entities.Team;
import domain.entities.Trainer;
import domain.exceptions.ValidatorException;
import domain.validators.TeamValidator;
import domain.validators.TrainerValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.InMemoryRepository;
import repository.Repository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InMemoryRepoTeamTest {
    private Repository<Long, Team> teamRepository;
    Team team1;
    Team team2;
    Team team3;
    Team team4;
    Team team5;


    @Before
    public void setUp() throws Exception{
        TeamValidator teamValidator = new TeamValidator();
        teamRepository = new InMemoryRepository<>(teamValidator);
        this.team1 = new Team("a");
        this.team2 = new Team("b");
        this.team3 = new Team("aa");
        team1.setId(1L);
        team2.setId(2L);
        team3.setId(3L);
        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);
    }

    @After
    public void tearDown() throws Exception{
        teamRepository = null;
    }

    @Test
    public void testFindOne() throws Exception {
        assertEquals("Team should be in the repo", teamRepository.findOne(2L), Optional.ofNullable(team2));
    }

    @Test
    public void testFindAll() throws Exception{
        Iterable<Team> teams = teamRepository.findAll();
        StringBuilder st= new StringBuilder();
        for(Team t: teams) {
            st.append(t.toString());
        }

        String expected1 = team1.toString();
        String expected2 = team2.toString();
        String expected3 = team3.toString();

        boolean flag = false;
        if(st.toString().equals(expected1+expected2+expected3) ||
                st.toString().equals(expected1+expected3+expected2) ||
                st.toString().equals(expected2+expected1+expected3) ||
                st.toString().equals(expected2+expected3+expected1) ||
                st.toString().equals(expected3+expected1+expected2) ||
                st.toString().equals(expected3+expected2+expected1)) {
            flag = true;
        }

        assertTrue("Should list all the elements", flag);
    }

    @Test
    public void testSave(){
        Team team4 = new Team("TheNewCommers");
        team4.setId(4L);
        teamRepository.save(team4);
        assertEquals("The team should be saved in repo", Optional.ofNullable(team4), teamRepository.findOne(team4.getId()));
    }

    @Test(expected = ValidatorException.class)
    public void testSaveException() throws Exception {
        team5 = new Team("");
        team5.setId(5L);
        assertEquals("Should raise exception because teamName's length is 0", teamRepository.save(team5),ValidatorException.class);
    }

    @Test
    public void testDelete() throws Exception {
        teamRepository.delete(1L);
        assertEquals("The team should not be in repo", Optional.empty(), teamRepository.findOne(1L));
    }


    @Test
    public void testUpdate() throws Exception {
        Team team1new = new Team("UpdateClan");
        team1new.setId(1L);
        teamRepository.update(team1new);
        assertEquals("The team should be updated",Optional.ofNullable(team1new), teamRepository.findOne(1L));
    }

}
