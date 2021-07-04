package catalog.repository;

import domain.entities.Sportive;
import domain.validators.SportiveValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import domain.exceptions.ValidatorException;
import repository.InMemoryRepository;
import repository.Repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import java.util.Optional;

/**
 * @author radu.
 */
public class InMemoryRepositorySportiveTest {

    private SportiveValidator sv;
    private Repository<Long, Sportive> sr;
    private Sportive m1;
    private Sportive m2;
    private Sportive m3;
    private Sportive m4;
    private Sportive m5;
    private Sportive m6;
    private Sportive m7;
    @Before
    public void setUp() throws Exception {
        sv = new SportiveValidator();
        sr = new InMemoryRepository<>(sv);
        m1=new Sportive("a","a",1,1);
        m1.setId(1L);
        sr.save(m1);

        m2=new Sportive("b","b",2,2);
        m2.setId(2L);
        sr.save(m2);

        m3=new Sportive("c","c",3,3);
        m3.setId(3L);
        sr.save(m3);
    }

    @After
    public void tearDown() throws Exception {
        sr=null;
    }


    @Test
    public void testFindOne() throws Exception {
        assertEquals("Sportive should be in the repo",sr.findOne(2L),Optional.ofNullable(m2));
    }


    @Test
    public void testFindAll() throws Exception {
        Iterable<Sportive> materials=sr.findAll();
        String st="";
        for(Sportive s: materials) {
            st=st+s;
        }
//        System.out.println(st);
        String expected1 = "Sportive{id='1', firstName='a', lastName='a', age=1, teamId=1}";
        String expected2 = "Sportive{id='2', firstName='b', lastName='b', age=2, teamId=2}";
        String expected3 = "Sportive{id='3', firstName='c', lastName='c', age=3, teamId=3}";

        boolean flag = false;
        if(st.equals(expected1+expected2+expected3) ||
                st.equals(expected1+expected3+expected2) ||
                st.equals(expected2+expected1+expected3) ||
                st.equals(expected2+expected3+expected1) ||
                st.equals(expected3+expected1+expected2) ||
                st.equals(expected3+expected2+expected1))
            flag = true;

        assertEquals("Should list all the elements", flag, true);
    }


    @Test
    public void testSave() throws Exception {
        m4=new Sportive("d","d",4,4);
        m4.setId(4L);
        sr.save(m4);
        assertEquals("The sportive should be saved in repo",Optional.ofNullable(m4),sr.findOne(m4.getId()));
    }


    @Test(expected = ValidatorException.class)
    public void testSaveException() throws Exception {
        m6=new Sportive("f","f",-1,1);
        m6.setId(6L);
        assertEquals("Should raise exception because age<0",sr.save(m6),ValidatorException.class);
    }


    @Test
    public void testDelete() throws Exception {
        sr.delete(1L);
        assertEquals("The sportive should not be in repo",Optional.empty(),sr.findOne(1L));
    }


    @Test
    public void testUpdate() throws Exception {
        m5=new Sportive("e","e",5,5);
        m5.setId(3L);
        sr.update(m5);
        assertEquals("The sportive should be updated",Optional.ofNullable(m5),sr.findOne(3L));
    }

    @Test(expected = ValidatorException.class)
    public void testUpdateException() throws Exception {
        m7=new Sportive("f","f",1,-1);
        m7.setId(1L);
        assertEquals("Should raise exception because teamId",sr.update(m7),ValidatorException.class);
    }
}