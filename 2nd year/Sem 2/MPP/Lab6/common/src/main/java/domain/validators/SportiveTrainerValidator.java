package domain.validators;

import domain.entities.SportiveTrainer;
import domain.enums.TrainingType;
import domain.exceptions.ValidatorException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author dzen
 * A Validator class for the SportiveTrainer class, implementing the Validator interface
 */
public class SportiveTrainerValidator implements Validator<SportiveTrainer>{
    /**
     * Checks if the attributes of a sportive-trainer are valid
     * @param entity an object of type SportiveTrainer
     * @throws ValidatorException if the attributes are no valid
     */
    @Override
    public void validate(SportiveTrainer entity){
        List<TrainingType> types = Arrays.asList(TrainingType.values());
        Optional.of(entity).filter(sportiveTrainer -> sportiveTrainer != null).orElseThrow(() ->{
            throw new ValidatorException("Entity is null!"); });
        Optional.of(entity).filter(sportiveTrainer -> sportiveTrainer.getSportiveID() > 0).orElseThrow(() ->{
            throw new ValidatorException("SportiveID must be a positive number!"); });
        Optional.of(entity).filter(sportiveTrainer -> sportiveTrainer.getTrainerID() > 0).orElseThrow(() ->{
            throw new ValidatorException("TrainerID must be a positive number!"); });
        if(!types.contains(TrainingType.valueOf(entity.getTrainingType()))){
            throw new ValidatorException("The training type must exist!");
        }
       // Optional.of(entity).filter(types.contains(TrainingType.valueOf(entity.getTrainingType()).orElseThrow(() ->{
            //throw new ValidatorException("The training type must exist!"); });
        Optional.of(entity).filter(sportiveTrainer -> sportiveTrainer.getCost() > 0).orElseThrow(() ->{
            throw new ValidatorException("The cost must be a positive number!"); });

    }
}
