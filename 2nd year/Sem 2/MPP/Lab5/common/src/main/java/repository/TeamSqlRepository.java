package repository;

import domain.entities.Sportive;
import domain.entities.Team;
import domain.entities.Trainer;
import domain.validators.Validator;

import java.net.ConnectException;
import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TeamSqlRepository extends DatabaseRepository<Long, Team> {

    public TeamSqlRepository(Validator<Team> validator, String dbCredentialsFilename){
        super(validator,dbCredentialsFilename);
//        loadData();
    }

    public void loadData() {
        String sql = "select * from team";

        try (var connection = dbConnection();
             var statement = connection.prepareStatement(sql);
             var result = statement.executeQuery()) {
            while (result.next()) {
                Long id = result.getLong("id");
                String teamName = result.getString("teamName");
                Team team = new Team(teamName);
                team.setId(id);
                save(team);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public Optional<Team> findOne(Long id) throws SQLException {
        Team team = null;
        Connection connection = dbConnection();
        String sql = "select * from team where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            team = new Team(resultSet.getString("teamname"));
            team.setId(id);
        }
        return Optional.ofNullable(team);
    }

    @Override
    public Iterable<Team> findAll() throws SQLException {
        Set<Team> teams = new HashSet<>();
        Connection connection = dbConnection();
        String sql = "select * from team";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Long id = resultSet.getLong("id");
            String teamName = resultSet.getString("teamname");
            Team team = new Team(teamName);
            team.setId(id);
            teams.add(team);
        }
        return teams;
    }

    @Override
    public Optional<Team> save(Team team){
        String sql = "insert into team (teamName) values (?)";
        try (var connection = dbConnection();

             var statement = connection.prepareStatement(sql)) {
            statement.setString(1, team.getTeamName());
            statement.executeUpdate();
            return Optional.ofNullable(team);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Team> delete(Long id){
        String sql = "delete from team where id =?";
        try (var connection = dbConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            Optional<Team> deleted = this.findOne(id);
            statement.executeUpdate();
            return deleted;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Team> update(Team team){
        String sql = "update team set teamName = ? where id = ?";
        try (var connection = dbConnection();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, team.getTeamName());
            statement.setLong(2, team.getId());
            statement.executeUpdate();
            return Optional.ofNullable(team);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return Optional.empty();
        }
    }
}