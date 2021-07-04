package repository.database;

import domain.entities.Team;
import domain.validators.Validator;
import repository.InMemoryRepository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class TeamSqlRepository extends InMemoryRepository<Long, Team> {
    private final String url;
    private final String user;
    private final String password;

    public TeamSqlRepository(Validator<Team> validator, String url, String user, String password){
        super(validator);
        this.url = url;
        this.user = user;
        this.password = password;
        loadData();
    }

    public void loadData() {
        String sql = "select * from team";

        try (var connection = DriverManager.getConnection(url, user, password);
             var statement = connection.prepareStatement(sql);
             var result = statement.executeQuery()) {
            while (result.next()) {
                Long id = result.getLong("id");
                String teamName = result.getString("teamName");
                Team team = new Team(teamName);
                team.setId(id);
                super.save(team);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public Optional<Team> save(Team team){
        String sql = "insert into team (teamName) values (?)";
        try (var connection = DriverManager.getConnection(url, user, password);

             var statement = connection.prepareStatement(sql)) {
            statement.setString(1, team.getTeamName());

            statement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        Optional<Team> optional = super.save(team);
        if (optional.isPresent()) {
            return optional;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Team> delete(Long id){
        String sql = "delete from team where id =?";
        try (var connection = DriverManager.getConnection(url, user, password);
             var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        Optional<Team> optional = super.delete(id);
        if(optional.isPresent()){
            return optional;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Team> update(Team team){
        String sql = "update team set teamName = ? where id = ?";
        try (var connection = DriverManager.getConnection(url, user, password);
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, team.getTeamName());
            statement.setLong(2, team.getId());
            statement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        Optional<Team> optional = super.update(team);
        if(optional.isPresent()){
            return optional;
        }
        return Optional.empty();
    }
}