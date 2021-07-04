package repository;

import domain.entities.Sportive;
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
import java.util.stream.Collectors;

public class SportiveSqlRepository  extends DatabaseRepository<Long,Sportive> {
//    private String url;
//    private String user;
//    private String password;


    public SportiveSqlRepository(Validator<Sportive> validator, String dbCredentialsFilename) {
        super(validator,dbCredentialsFilename);
//        loadData();
    }

    public void loadData() {
        String sql = "select * from sportive";
//        Connection connection = dbConnection();
        try (var connection = dbConnection();
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
                save(sportive);
//                super.save(sportive);
            }

        } catch (SQLException | ValidatorException | IOException | TransformerException | SAXException | ParserConfigurationException  throwables) {
            throwables.printStackTrace();
        }

    }




    public Optional<Sportive> save(Sportive newSportive) throws ValidatorException , IOException , TransformerException , SAXException , ParserConfigurationException , SQLException  {
        String sql = "insert into sportive (firstName,lastName,age,tId) values (?, ?, ?, ?)";
        try (var connection = dbConnection();

             var ps = connection.prepareStatement(sql)) {
            ps.setString(1, newSportive.getFirstName());
            ps.setString(2, newSportive.getLastName());
            ps.setInt(3, newSportive.getAge());
            ps.setInt(4, newSportive.getTeamId());
            System.out.println(ps);
            ps.executeUpdate();
            return Optional.empty();
            
        } catch (SQLException throwables) {
            return Optional.ofNullable(newSportive);
        }
        
    }

    @Override
    public Optional<Sportive> delete(Long id) throws IOException, TransformerException, ParserConfigurationException, SQLException {
        Connection connection = dbConnection();
        String sql = "delete from sportive where id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1,id);
        try{
            Optional<Sportive> deleted = this.findOne(id);
            preparedStatement.executeUpdate();
            return deleted;
        }
        catch (SQLException se){
            return Optional.empty();
        }
    }

    public Optional<Sportive> update(Sportive newSportive) throws ValidatorException, SQLException {
        String sql = "update sportive set lastName=?, firstName=?, age=?, tId=? where id=?";
        try (var connection = dbConnection();
             var ps = connection.prepareStatement(sql)) {
            ps.setString(1, newSportive.getLastName());
            ps.setString(2, newSportive.getFirstName());
            ps.setInt(3, newSportive.getAge());
            ps.setInt(4, newSportive.getTeamId());
            ps.setLong(5, newSportive.getId());
            ps.executeUpdate();
            return Optional.empty();
        } catch (SQLException throwables) {
            return Optional.ofNullable(newSportive);
        }

    }



    @Override
    public Optional<Sportive> findOne(Long id) throws SQLException {
        Sportive sportive = null;
        Connection connection = dbConnection();
        String sql = "select * from sportive where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            sportive = new Sportive(resultSet.getString("firstName"),resultSet.getString("lastName"),resultSet.getInt("age"),resultSet.getInt("tId"));
            sportive.setId(id);
        }
        return Optional.ofNullable(sportive);
    }

    @Override
    public Iterable<Sportive> findAll() throws SQLException {
        Set<Sportive> Sportives = new HashSet<>();
        Connection connection = dbConnection();
        String sql = "select * from sportive";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Long id = resultSet.getLong("id");
            String lastName = resultSet.getString("lastName");
            String firstName = resultSet.getString("firstName");
            int age = resultSet.getInt("age");
            int teamId = resultSet.getInt("tId");
            Sportive s=new Sportive(firstName,lastName,age,teamId);
            s.setId(id);
            Sportives.add(s);
        }
        return Sportives;
    }
}