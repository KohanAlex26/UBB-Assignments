package repository;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Trainer;
import domain.validators.Validator;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TrainerSqlRepository extends DatabaseRepository<Long,Trainer> {
    public TrainerSqlRepository(Validator<Trainer> validator, String dbCredentialsFilename){
        super(validator,dbCredentialsFilename);
//        loadData();
    }

    public void loadData() {
        String sql = "select * from trainer";

        try (var connection = dbConnection();
             var statement = connection.prepareStatement(sql);
             var result = statement.executeQuery()) {
            while (result.next()) {
                Long id = result.getLong("id");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                int age = result.getInt("age");
                Trainer trainer = new Trainer(firstName, lastName, age);
                trainer.setId(id);
                save(trainer);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public Optional<Trainer> findOne(Long id) throws SQLException {
        Trainer trainer = null;
        Connection connection = dbConnection();
        String sql = "select * from trainer where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            trainer = new Trainer(resultSet.getString("firstName"),resultSet.getString("lastName"),resultSet.getInt("age"));
            trainer.setId(id);
        }
        return Optional.ofNullable(trainer);
    }

    @Override
    public Iterable<Trainer> findAll() throws SQLException{
        Set<Trainer> trainers = new HashSet<>();
        Connection connection = dbConnection();
        String sql = "select * from trainer";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Long id = resultSet.getLong("id");
            String lastName = resultSet.getString("lastName");
            String firstName = resultSet.getString("firstName");
            int age = resultSet.getInt("age");
            Trainer trainer = new Trainer(firstName,lastName,age);
            trainer.setId(id);
            trainers.add(trainer);
        }
        return trainers;
    }


    @Override
    public Optional<Trainer> save(Trainer trainer){
        String sql = "insert into trainer (firstName, lastName, age) values (?, ?, ?)";
        try (var connection = dbConnection();

             var statement = connection.prepareStatement(sql)) {
            statement.setString(1, trainer.getFirstName());
            statement.setString(2, trainer.getLastName());
            statement.setInt(3, trainer.getAge());
            statement.executeUpdate();
            return Optional.empty();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return Optional.ofNullable(trainer);
        }
    }

    @Override
    public Optional<Trainer> delete(Long id){
        String sql = "delete from trainer where id =?";
        try (var connection = dbConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            Optional<Trainer> deleted = this.findOne(id);
            statement.executeUpdate();
            return deleted;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Trainer> update(Trainer trainer){
        String sql = "update trainer set firstname = ?, lastname = ?, age = ? where id = ?";
        try (var connection = dbConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, trainer.getFirstName());
            statement.setString(2, trainer.getLastName());
            statement.setInt(3, trainer.getAge());
            statement.setLong(4, trainer.getId());
            statement.executeUpdate();
            return Optional.empty();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return Optional.ofNullable(trainer);
        }

    }


}