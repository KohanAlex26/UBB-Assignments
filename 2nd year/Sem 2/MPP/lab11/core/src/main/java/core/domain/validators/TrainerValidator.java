package core.domain.validators;

import org.springframework.stereotype.Component;
import core.domain.Trainer;
import core.exceptions.ValidatorException;

import java.util.Optional;


@Component
public class TrainerValidator implements Validator<Trainer> {

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
