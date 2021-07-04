package repository;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SportiveTrainerRepository implements RepositoryInterface<Long, SportiveTrainer>{
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<SportiveTrainer> save(SportiveTrainer entity) {
        String sql = "insert into sportivetrainer (sportiveID,trainerID,trainingType,cost) values (?, ?, ?, ?)";
        if (jdbcOperations.update(sql, entity.getSportiveID(), entity.getTrainerID(), entity.getTrainingType(), entity.getCost()) > 0) {
            return Optional.empty();
        } else {
            return Optional.of(entity);
        }
    }

    @Override
    public Optional<SportiveTrainer> delete(Long aLong) {
        String sql = " delete from sportivetrainer where id =?";
            Optional<SportiveTrainer> entity = this.findById(aLong);
            if(jdbcOperations.update(sql, aLong.longValue()) == 0)
                return Optional.empty();
            else
                return entity;
    }

    @Override
    public Optional<SportiveTrainer> update(SportiveTrainer entity) {
        String sql = "update sportivetrainer set sportiveID=?, trainerID=?, trainingType=?, cost=? where id=?";
        if (jdbcOperations.update(sql, entity.getSportiveID(), entity.getTrainerID(), entity.getTrainingType(), entity.getCost()) > 0) {
            return Optional.empty();
        } else {
            return Optional.of(entity);
        }
    }

    @Override
    public Optional<SportiveTrainer> findById(Long aLong) {
        String sql = "select * from sportivetrainer where id=?";
        List<SportiveTrainer> results = jdbcOperations.query(sql, (rs, rowNum) -> {
           Long id = rs.getLong("id");
           Long sportiveId = rs.getLong("sportiveID");
           Long trainerId = rs.getLong("trainerID");
           String trainingType = rs.getString("trainingType");
           int cost = rs.getInt("cost");
           SportiveTrainer st = new SportiveTrainer(sportiveId, trainerId, trainingType, cost);
           st.setId(id);
           return st;
        });
        if(results.size() == 0){
            return Optional.empty();
        }
        else{
            return Optional.of(results.get(0));
        }
    }

    @Override
    public Iterable<SportiveTrainer> getAll() {
        String sql = "select * from sportivetrainer";
        return jdbcOperations.query(sql, (rs, rowNum) ->{
            Long id = rs.getLong("id");
            Long sportiveId = rs.getLong("sportiveID");
            Long trainerId = rs.getLong("trainerID");
            String trainingType = rs.getString("trainingType");
            int cost = rs.getInt("cost");
            SportiveTrainer st = new SportiveTrainer(sportiveId, trainerId, trainingType, cost);
            st.setId(id);
            return st;
        });
    }
}
