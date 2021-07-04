package repository;

import domain.entities.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.Optional;

public class TrainerRepository implements RepositoryInterface<Long, Trainer>{
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<Trainer> save(Trainer entity) {
        String sql = "insert into trainer (firstName,lastName,age) values (?, ?, ?)";
        if(jdbcOperations.update(sql, entity.getFirstName(), entity.getLastName(), entity.getAge()) > 0)
            return Optional.empty();
        else
            return Optional.of(entity);
    }

    @Override
    public Optional<Trainer> delete(Long aLong) {
        String sql = "delete from trainer where id=?";
        String sql2 = "delete from sportivetrainer where trainerID=?";
        Optional<Trainer> entity = this.findById(aLong);
        jdbcOperations.update(sql2, aLong.longValue());
        if(jdbcOperations.update(sql, aLong.longValue()) == 0)
            return Optional.empty();
        else
            return entity;
    }

    @Override
    public Optional<Trainer> update(Trainer entity) {
        String sql = "update trainer set firstName=?, lastName=?, age=? where id=?";
        if(jdbcOperations.update(sql, entity.getFirstName(), entity.getLastName(), entity.getAge(), entity.getId()) > 0)
            return Optional.empty();
        else
            return Optional.of(entity);
    }

    @Override
    public Optional<Trainer> findById(Long aLong) {
        String sql = "select * from trainer where id=" + aLong;
        List<Trainer> results = jdbcOperations.query(sql,(rs, rowNum) -> {
            long id = rs.getLong("id");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            int age = rs.getInt("age");
            Trainer trainer = new Trainer(firstName, lastName, age);
            trainer.setId(id);
            return trainer;
        });
        if(results.size() == 0)
            return Optional.empty();
        else
            return Optional.of(results.get(0));
    }

    @Override
    public Iterable<Trainer> getAll() {
        String sql = "select * from trainer";
        return jdbcOperations.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            int age = rs.getInt("age");
            Trainer trainer = new Trainer(firstName, lastName, age);
            trainer.setId(id);
            return trainer;
        });
    }
}
