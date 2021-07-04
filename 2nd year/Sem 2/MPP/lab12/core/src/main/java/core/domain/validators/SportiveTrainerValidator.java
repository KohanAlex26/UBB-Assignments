package core.domain.validators;


import org.springframework.stereotype.Component;
import core.enums.TrainingType;
import core.domain.SportiveTrainer;
import core.exceptions.ValidatorException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
public class SportiveTrainerValidator implements Validator<SportiveTrainer>{

    @Override
    public void validate(SportiveTrainer entity){
        List<TrainingType> types = Arrays.asList(TrainingType.values());
        Optional.of(entity).filter(sportiveTrainer -> sportiveTrainer != null).orElseThrow(() ->{
            throw new ValidatorException("Entity is null!"); });
//        Optional.of(entity).filter(sportiveTrainer -> sportiveTrainer.getSportive().getId() > 0).orElseThrow(() ->{
//            throw new ValidatorException("SportiveID must be a positive number!"); });
//        Optional.of(entity).filter(sportiveTrainer -> sportiveTrainer.getTrainer().getId() > 0).orElseThrow(() ->{
//            throw new ValidatorException("TrainerID must be a positive number!"); });
        if(!types.contains(TrainingType.valueOf(entity.getTrainingType()))){
            throw new ValidatorException("The training type must exist!");
        }
       // Optional.of(entity).filter(types.contains(TrainingType.valueOf(entity.getTrainingType()).orElseThrow(() ->{
            //throw new ValidatorException("The training type must exist!"); });
        Optional.of(entity).filter(sportiveTrainer -> sportiveTrainer.getCost() > 0).orElseThrow(() ->{
            throw new ValidatorException("The cost must be a positive number!"); });

    }
}
