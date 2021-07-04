package core.domain.validators;

import org.springframework.stereotype.Component;
import core.domain.Sportive;
import core.exceptions.ValidatorException;

import java.util.Optional;

@Component
public class SportiveValidator implements Validator<Sportive> {

    @Override
    public void validate(Sportive entity) throws ValidatorException {
        Optional.of(entity).filter(sportive -> sportive!=null).orElseThrow(()-> {
            throw new ValidatorException("Entity is null"); });

        Optional.of(entity).filter(sportive -> sportive.getId()>0).orElseThrow(()-> {
            throw new ValidatorException("Try id>0"); });

        Optional.of(entity).filter(sportive -> !(sportive.getFirstName().equals(""))).orElseThrow(()-> {
            throw new ValidatorException("First name should have at least one character"); });

        Optional.of(entity).filter(sportive -> !(sportive.getLastName().equals(""))).orElseThrow(()-> {
            throw new ValidatorException("Last name should have at least one character"); });

        Optional.of(entity).filter(sportive -> sportive.getAge()>0).orElseThrow(()-> {
            throw new ValidatorException("Age should be a number greater than 0"); });

    }
}
