package repository;

import domain.entities.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.Optional;

public class TeamRepository implements RepositoryInterface<Long, Team>{

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<Team> save(Team entity) {
        String sql = "insert into team(teamname) values (?)";
        if(jdbcOperations.update(sql, entity.getTeamName()) > 0){
            return Optional.empty();
        }
        return Optional.of(entity);
    }

    @Override
    public Optional<Team> delete(Long aLong) {
        String sql = "delete from team where id=?";
        Optional<Team> team = this.findById(aLong);
        if(jdbcOperations.update(sql, aLong.longValue()) > 0){
            return team;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Team> update(Team entity) {
        String sql = "update team set teamname=? where id=?";
        if(jdbcOperations.update(sql, entity.getTeamName(), entity.getId()) > 0){
            return Optional.empty();
        }
        return Optional.of(entity);
    }

    @Override
    public Optional<Team> findById(Long aLong) {
        String sql = "select * from team where id=" + aLong;
        List<Team> results = jdbcOperations.query(sql,(rs, rowNum) -> {
            long id = rs.getLong("id");
            String teamName = rs.getString("teamname");
            Team team = new Team(teamName);
            team.setId(id);
            return team;
        });
        if(results.size() == 0)
            return Optional.empty();
        else
            return Optional.of(results.get(0));
    }

    @Override
    public Iterable<Team> getAll() {
        String sql = "select * from team";
        return jdbcOperations.query(sql,(rs, rowNum) -> {
            long id = rs.getLong("id");
            String teamName = rs.getString("teamname");
            Team team = new Team(teamName);
            team.setId(id);
            return team;
        });
    }
}
