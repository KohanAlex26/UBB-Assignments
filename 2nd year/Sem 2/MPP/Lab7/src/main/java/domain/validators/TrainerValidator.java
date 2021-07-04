package domain.validators;

import domain.entities.Trainer;
import domain.exceptions.ValidatorException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author oprea
 * A Validator class for the Trainer class, implementing the Validator interface
 */
@Component
public class TrainerValidator implements Validator<Trainer> {
    /**
     * Checks if the attributes of a trainer are valid
     * @param entity an object of type Trainer
     * @throws ValidatorException if the attributes are no valid
     */
    @Override
    public void validate(Trainer entity) throws ValidatorException {
        Optional.of(entity).filter(trainer -> trainer != null).orElseThrow(()-> {
            throw new ValidatorException("Entity is null"); });

//        Optional.of(entity).filter(trainer -> trainer.getId()>0).orElseThrow(()-> {
//            throw new ValidatorException("id should be a positive number"); });

        Optional.of(entity).filter(trainer -> !(trainer.getFirstName().equals(""))).orElseThrow(()-> {
            throw new ValidatorException("First name should have at least one character"); });

        Optional.of(entity).filter(trainer -> !(trainer.getLastName().equals(""))).orElseThrow(()-> {
            throw new ValidatorException("Last name should have at least one character"); });

        Optional.of(entity).filter(trainer -> trainer.getAge()>0).orElseThrow(()-> {
            throw new ValidatorException("Age should be a positive number"); });
    }
}
