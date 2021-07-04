package repository.database;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.validators.Validator;
import repository.InMemoryRepository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SportiveTrainerSqlRepository extends InMemoryRepository<Long, SportiveTrainer> {
    private String url;
    private String user;
    private String password;

    public SportiveTrainerSqlRepository(Validator<SportiveTrainer> validator, String url, String user, String password){
        super(validator);
        this.url = url;
        this.user = user;
        this.password = password;
        loadData();
    }

    public void loadData() {
        String sql = "select * from sportivetrainer";

        try (var connection = DriverManager.getConnection(url, user, password);
             var ps = connection.prepareStatement(sql);
             var rs = ps.executeQuery()) {
            while (rs.next()) {
                Long id = rs.getLong("id");
                Long sportiveID = rs.getLong("sportiveID");
                Long trainerID = rs.getLong("trainerID");
                String trainingType = rs.getString("trainingType");
                int cost = rs.getInt("cost");
                SportiveTrainer st = new SportiveTrainer(sportiveID, trainerID, trainingType, cost);
                st.setId(id);
                super.save(st);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    public Optional<SportiveTrainer> save(SportiveTrainer st){
        String sql = "insert into sportivetrainer (sportiveID,trainerID,trainingType,cost) values (?, ?, ?, ?)";
        try (var connection = DriverManager.getConnection(url, user, password);

             var ps = connection.prepareStatement(sql)) {
            ps.setLong(1, st.getSportiveID());
            ps.setLong(2, st.getTrainerID());
            ps.setString(3, st.getTrainingType());
            ps.setInt(4, st.getCost());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Optional<SportiveTrainer> optional = super.save(st);
        if (optional.isPresent()) {
            return optional;
        }
        return Optional.empty();
    }
    @Override
    public Optional<SportiveTrainer> delete(Long id){
        String sql = " delete from sportivetrainer where id =?";
        try (var connection = DriverManager.getConnection(url, user, password);
             var ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Optional<SportiveTrainer> optional = super.delete(id);
        if(optional.isPresent()){
            return optional;
        }
        return Optional.empty();
    }
}
