package repository.file;


import domain.entities.Team;
import domain.exceptions.ValidatorException;
import domain.validators.Validator;
import repository.InMemoryRepository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TeamFileRepository extends InMemoryRepository<Long, Team> {
    private String fileName;

    public TeamFileRepository(Validator<Team> validator, String fileName){
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    /**
     * Reads the teams from the team file into memory.
     */
    public void loadData(){
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                Long id = Long.valueOf(items.get(0));
                String teamName = items.get(1);

                Team team = new Team(teamName);
                team.setId(id);

                try {
                    super.save(team);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Team> save(Team entity) throws ValidatorException {
        Optional<Team> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    /**
     * Save a team into file.
     */
    private void saveToFile(Team entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write( entity.getId() + "," + entity.getTeamName());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Team>update(Team entity) throws ValidatorException{
        Optional<Team> optional = super.update(entity);
        if (optional.isPresent()) {
            writeToFile();
            return optional;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Team> delete(Long id){
        Optional<Team> optional = super.delete(id);
        if(optional.isPresent()){
            writeToFile();
            return optional;
        }
        return Optional.empty();
    }

    /**
     * Saves all the current entities in the repository to the file.
     */
    private void writeToFile(){
        try(PrintWriter printWriter = new PrintWriter(fileName)){
            findAll().forEach(entity ->{
                String str = entity.getId() + "," + entity.getTeamName();
                printWriter.println(str);
            });
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
