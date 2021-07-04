package repository.file;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Trainer;
import domain.validators.Validator;
import domain.exceptions.ValidatorException;
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

/**
 * @author radu.
 */
public class SportiveFileRepository extends InMemoryRepository<Long, Sportive> {
    private String fileName;

    public SportiveFileRepository(Validator<Sportive> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    /**
     * Reads the sportives from the sportives file into memory.
     */
    private void loadData() {
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                Long id = Long.valueOf(items.get(0));
                String firstName = items.get(1);
                String lastName = items.get(2);
                int age = Integer.parseInt(items.get(3));
                int teamId = Integer.parseInt(items.get(4));

                Sportive sportive = new Sportive(firstName,lastName,age,teamId);
                sportive.setId(id);

                try {
                    super.save(sportive);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Sportive> save(Sportive entity) throws ValidatorException {
        Optional<Sportive> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    /**
     * Save a sportive into file.
     */
    private void saveToFile(Sportive entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(entity.getId() + "," + entity.getFirstName() + "," + entity.getLastName() + "," + entity.getAge() + "," + entity.getTeamId());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Sportive> delete(Long id){
        Optional<Sportive> optional = super.delete(id);
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
        try(PrintWriter pw = new PrintWriter(fileName)){
            findAll().forEach(entity ->{
                String str =  entity.getId() + "," + entity.getLastName() + "," + entity.getFirstName() + "," + entity.getAge()+ "," + entity.getTeamId();
                pw.println(str);
            });
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Sportive>update(Sportive entity) throws ValidatorException{
        Optional<Sportive> optional = super.update(entity);
        if (optional.isPresent()) {
            writeToFile();
            return optional;
        }
        return Optional.empty();
    }

}
