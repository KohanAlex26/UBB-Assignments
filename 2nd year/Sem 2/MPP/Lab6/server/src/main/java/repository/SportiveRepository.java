package repository;

import domain.entities.Sportive;
import domain.exceptions.ValidatorException;
import domain.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SportiveRepository implements RepositoryInterface<Long, Sportive> {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<Sportive> save(Sportive entity) {
        String sql = "insert into sportive (firstName,lastName,age,tId) values (?, ?, ?, ?)";
        if(jdbcOperations.update(sql, entity.getFirstName(), entity.getLastName(), entity.getAge(),entity.getTeamId()) > 0)
            return Optional.empty();
        else
            return Optional.of(entity);
    }

    @Override
    public Optional<Sportive> delete(Long aLong) {
        String sql = "delete from sportive where id=?";
        String sql2 = "delete from sportivetrainer where sportiveID=?";
        Optional<Sportive> entity = this.findById(aLong);
        jdbcOperations.update(sql2, aLong.longValue());
        if(jdbcOperations.update(sql, aLong.longValue()) == 0)
            return Optional.empty();
        else
            return entity;

    }

    @Override
    public Optional<Sportive> update(Sportive entity) {
        String sql = "update sportive set firstName=?, lastName=?, age=?, tId=? where id=?";
        if(jdbcOperations.update(sql,entity.getFirstName(), entity.getLastName(), entity.getAge(),entity.getTeamId(),entity.getId()) > 0)
            return Optional.empty();
        else
            return Optional.of(entity);
    }

    @Override
    public Optional<Sportive> findById(Long aLong) {
        String sql = "select * from sportive where id=" + aLong;
        List<Sportive> results = jdbcOperations.query(sql,(rs, rowNum) -> {
            long id = rs.getLong("id");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            int age = rs.getInt("age");
            int teamId= rs.getInt("tId");
            Sportive s= new Sportive(firstName, lastName, age,teamId);
            s.setId(id);
            return s;
        });
        if(results.size() == 0)
            return Optional.empty();
        else
            return Optional.of(results.get(0));
    }

    @Override
    public Iterable<Sportive> getAll() {
        String sql = "select * from sportive";
        return jdbcOperations.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            int age = rs.getInt("age");
            int teamId = rs.getInt("tId");
            Sportive s= new Sportive(firstName,lastName,age,teamId);
            s.setId(id);
            return s;
        });
    }
}