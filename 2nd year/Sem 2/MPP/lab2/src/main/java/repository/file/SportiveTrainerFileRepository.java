package repository.file;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
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

public class SportiveTrainerFileRepository extends InMemoryRepository<Long, SportiveTrainer> {
    private String fileName;

    public SportiveTrainerFileRepository(Validator<SportiveTrainer> validator, String fileName){
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    /**
     * Reads the sportiveTrainers from the sportiveTrainers file into memory.
     */
    public void loadData(){
        Path path = Paths.get(fileName);

        try{
            Files.lines(path).forEach(line ->{
                List<String> items = Arrays.asList(line.split(","));

                Long id = Long.valueOf(items.get(0));
                Long sportiveID = Long.valueOf(items.get(1));
                Long trainerID = Long.valueOf(items.get(2));
                String trainingType = items.get(3);
                int cost = Integer.parseInt(items.get(4));

                SportiveTrainer st = new SportiveTrainer(sportiveID, trainerID, trainingType, cost);
                st.setId(id);
                try{
                    super.save(st);
                } catch(ValidatorException ex){
                    ex.printStackTrace();
                }
            });
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<SportiveTrainer> save(SportiveTrainer entity) throws ValidatorException {
        Optional<SportiveTrainer> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    @Override
    public Optional<SportiveTrainer> delete(Long id){
        Optional<SportiveTrainer> optional = super.delete(id);
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
                String str = entity.getId() + "," + entity.getSportiveID() + "," + entity.getTrainerID() + "," + entity.getTrainingType() + "," + entity.getCost();
                pw.println(str);
            });
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Save a sportiveTrainer into file.
     */
    private void saveToFile(SportiveTrainer entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write( entity.getId() + "," + entity.getSportiveID() + "," + entity.getTrainerID() + "," + entity.getTrainingType() + "," + entity.getCost());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
