package repository.database;

import domain.entities.Trainer;
import domain.validators.Validator;
import repository.InMemoryRepository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class TrainerSqlRepository extends InMemoryRepository<Long, Trainer> {
    private final String url;
    private final String user;
    private final String password;

    public TrainerSqlRepository(Validator<Trainer> validator, String url, String user, String password){
        super(validator);
        this.url = url;
        this.user = user;
        this.password = password;
        loadData();
    }

    public void loadData() {
        String sql = "select * from trainer";

        try (var connection = DriverManager.getConnection(url, user, password);
             var statement = connection.prepareStatement(sql);
             var result = statement.executeQuery()) {
            while (result.next()) {
                Long id = result.getLong("id");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                int age = result.getInt("age");
                Trainer trainer = new Trainer(firstName, lastName, age);
                trainer.setId(id);
                super.save(trainer);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public Optional<Trainer> save(Trainer trainer){
        String sql = "insert into trainer (firstName, lastName, age) values (?, ?, ?)";
        try (var connection = DriverManager.getConnection(url, user, password);

             var statement = connection.prepareStatement(sql)) {
            statement.setString(1, trainer.getFirstName());
            statement.setString(2, trainer.getLastName());
            statement.setInt(3, trainer.getAge());

            statement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        Optional<Trainer> optional = super.save(trainer);
        if (optional.isPresent()) {
            return optional;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Trainer> delete(Long id){
        String sql = "delete from trainer where id =?";
        try (var connection = DriverManager.getConnection(url, user, password);
             var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        Optional<Trainer> optional = super.delete(id);
        if(optional.isPresent()){
            return optional;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Trainer> update(Trainer trainer){
        String sql = "update trainer set firstname = ?, lastname = ?, age = ? where id = ?";
        try (var connection = DriverManager.getConnection(url, user, password);
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, trainer.getFirstName());
            statement.setString(2, trainer.getLastName());
            statement.setInt(3, trainer.getAge());
            statement.setLong(4, trainer.getId());
            statement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        Optional<Trainer> optional = super.update(trainer);
        if(optional.isPresent()){
            return optional;
        }
        return Optional.empty();
    }
}