package repository.file;


import domain.entities.Trainer;
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

public class TrainerFileRepository extends InMemoryRepository<Long, Trainer> {
    private String fileName;

    public TrainerFileRepository(Validator<Trainer> validator, String fileName){
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    /**
     * Reads the trainers from the trainers file into memory.
     */
    public void loadData(){
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                Long id = Long.valueOf(items.get(0));
                String firstName = items.get(1);
                String lastName = items.get(2);
                int age = Integer.parseInt(items.get(3));

                Trainer trainer = new Trainer(firstName,lastName,age);
                trainer.setId(id);

                try {
                    super.save(trainer);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Trainer> save(Trainer entity) throws ValidatorException {
        Optional<Trainer> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    /**
     * Save a trainer into file.
     */
    private void saveToFile(Trainer entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write( entity.getId() + "," + entity.getFirstName() + "," + entity.getLastName() + "," + entity.getAge());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Trainer>update(Trainer entity) throws ValidatorException{
        Optional<Trainer> optional = super.update(entity);
        if (optional.isPresent()) {
            writeToFile();
            return optional;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Trainer> delete(Long id){
        Optional<Trainer> optional = super.delete(id);
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
                String str = entity.getId() + "," + entity.getFirstName() + "," + entity.getLastName() + "," + entity.getAge();
                printWriter.println(str);
            });
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
