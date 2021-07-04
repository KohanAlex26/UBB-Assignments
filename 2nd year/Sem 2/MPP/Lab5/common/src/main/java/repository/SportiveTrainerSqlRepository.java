package repository;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.exceptions.ValidatorException;
import domain.validators.Validator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SportiveTrainerSqlRepository extends DatabaseRepository<Long, SportiveTrainer> {
    public SportiveTrainerSqlRepository(Validator<SportiveTrainer> validator, String dbCredentialsFilename) {
        super(validator, dbCredentialsFilename);
//        loadData();
    }

    public void loadData() {
        String sql = "select * from sportivetrainer";

        try (var connection = dbConnection();
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
                save(st);
            }
        } catch (SQLException | ValidatorException | IOException | TransformerException | SAXException | ParserConfigurationException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<SportiveTrainer> save(SportiveTrainer st) throws ValidatorException, IOException, TransformerException, SAXException, ParserConfigurationException, SQLException {
        String sql = "insert into sportivetrainer (sportiveID,trainerID,trainingType,cost) values (?, ?, ?, ?)";
        try (var connection = dbConnection();
             var ps = connection.prepareStatement(sql)) {
            ps.setLong(1, st.getSportiveID());
            ps.setLong(2, st.getTrainerID());
            ps.setString(3, st.getTrainingType());
            ps.setInt(4, st.getCost());
            ps.executeUpdate();
            return Optional.empty();
        } catch (SQLException throwables) {
            return Optional.ofNullable(st);
        }
    }

    @Override
    public Optional<SportiveTrainer> delete(Long id) throws IOException, TransformerException, ParserConfigurationException, SQLException {
        String sql = " delete from sportivetrainer where id =?";
        try (var connection = dbConnection();
             var ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try {
                Optional<SportiveTrainer> deleted = this.findOne(id);
                ps.executeUpdate();
                return deleted;
            } catch (SQLException throwables) {
                return Optional.empty();
            }
        }
    }
    @Override
    public Optional<SportiveTrainer> update(SportiveTrainer st) throws ValidatorException, SQLException {
        String sql = "update sportivetrainer set sportiveID=?, trainerID=?, trainingType=?, cost=? where id=?";
        try (var connection = dbConnection();
             var ps = connection.prepareStatement(sql)) {
            ps.setLong(1, st.getSportiveID());
            ps.setLong(2, st.getTrainerID());
            ps.setString(3, st.getTrainingType());
            ps.setInt(4, st.getCost());
            ps.executeUpdate();
            return Optional.empty();
        } catch (SQLException throwables) {
            return Optional.ofNullable(st);
        }

    }
    @Override
    public Optional<SportiveTrainer> findOne(Long id) throws SQLException {
        SportiveTrainer sportiveTrainer = null;
        Connection connection = dbConnection();
        String sql = "select * from sportivetrainer where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            SportiveTrainer st = new SportiveTrainer(resultSet.getLong("sportiveID"),resultSet.getLong("trainerID"),resultSet.getString("trainingType"),resultSet.getInt("cost"));
            st.setId(id);
        }
        return Optional.ofNullable(sportiveTrainer);
    }
    @Override
    public Iterable<SportiveTrainer> findAll() throws SQLException {
        Set<SportiveTrainer> sts = new HashSet<>();
        Connection connection = dbConnection();
        String sql = "select * from sportivetrainer";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Long id = resultSet.getLong("id");
            Long sportiveID = resultSet.getLong("sportiveID");
            Long trainerID = resultSet.getLong("trainerID");
            String trainingType = resultSet.getString("trainingType");
            int cost = resultSet.getInt("cost");
            SportiveTrainer st=new SportiveTrainer(sportiveID,trainerID,trainingType,cost);
            st.setId(id);
            sts.add(st);
        }
        return sts;
    }
}
