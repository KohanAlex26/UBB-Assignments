package repository.database;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Trainer;
import domain.validators.Validator;
import repository.InMemoryRepository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SportiveSqlRepository extends InMemoryRepository<Long, Sportive> {
    private String url;
    private String user;
    private String password;


    public SportiveSqlRepository(Validator<Sportive> validator, String url, String user, String password) {
        super(validator);
        this.url = url;
        this.user = user;
        this.password = password;
        loadData();
    }

    public void loadData() {
        String sql = "select * from sportive";

        try (var connection = DriverManager.getConnection(url, user, password);
             var ps = connection.prepareStatement(sql);
             var rs = ps.executeQuery()) {
            while (rs.next()) {
                Long id = rs.getLong("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int age = rs.getInt("age");
                int teamId = rs.getInt("tId");
                Sportive sportive = new Sportive(lastName,firstName,age,teamId);
                sportive.setId(id);
                super.save(sportive);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public Optional<Sportive> save(Sportive newSportive) {
        String sql = "insert into sportive (lastName,firstName,age,tId) values (?, ?, ?, ?)";
        try (var connection = DriverManager.getConnection(url, user, password);

             var ps = connection.prepareStatement(sql)) {
            ps.setString(1, newSportive.getLastName());
            ps.setString(2, newSportive.getFirstName());
            ps.setInt(3, newSportive.getAge());
            ps.setInt(4, newSportive.getTeamId());
            System.out.println(ps);
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Optional<Sportive> optional = super.save(newSportive);
        if (optional.isPresent()) {
            return optional;
        }
        return Optional.empty();
    }

    public Optional<Sportive> update(Sportive newSportive) {
        String sql = "update sportive set lastName=?, firstName=?, age=?, tId=? where id=?";
        try (var connection = DriverManager.getConnection(url, user, password);
             var ps = connection.prepareStatement(sql)) {
            ps.setString(1, newSportive.getLastName());
            ps.setString(2, newSportive.getFirstName());
            ps.setInt(3, newSportive.getAge());
            ps.setInt(4, newSportive.getTeamId());
            ps.setLong(5, newSportive.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Optional<Sportive> optional = super.update(newSportive);
        if(optional.isPresent()){
            return optional;
        }
        return Optional.empty();
    }

    public Optional<Sportive> delete(Long id) {
        String sql = " delete from sportive where id =?";
        try (var connection = DriverManager.getConnection(url, user, password);
             var ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Optional<Sportive> optional = super.delete(id);
        if(optional.isPresent()){
            return optional;
        }
        return Optional.empty();
    }
}