package domain.validators;

import domain.entities.Sportive;
import domain.exceptions.ValidatorException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author alex
 * A Validator class for the Sportive class, implementing the Validator interface
 */
@Component
public class SportiveValidator implements Validator<Sportive> {
    /**
     * Checks if the attributes of a sportive are valid
     * @param entity an object of type Sportive
     * @throws ValidatorException if the attributes are no valid
     */
    @Override
    public void validate(Sportive entity) throws ValidatorException {
        Optional.of(entity).filter(sportive -> sportive!=null).orElseThrow(()-> {
            throw new ValidatorException("Entity is null"); });

//        Optional.of(entity).filter(sportive -> sportive.getId()>0).orElseThrow(()-> {
//            throw new ValidatorException("Try id>0"); });

        Optional.of(entity).filter(sportive -> !(sportive.getFirstName().equals(""))).orElseThrow(()-> {
            throw new ValidatorException("First name should have at least one character"); });

        Optional.of(entity).filter(sportive -> !(sportive.getLastName().equals(""))).orElseThrow(()-> {
            throw new ValidatorException("Last name should have at least one character"); });

        Optional.of(entity).filter(sportive -> sportive.getAge()>0).orElseThrow(()-> {
            throw new ValidatorException("Age should be a number greater than 0"); });

        Optional.of(entity).filter(sportive -> sportive.getTid()>0).orElseThrow(()-> {
            throw new ValidatorException("The team id should be a number greater than 0"); });
    }
}
