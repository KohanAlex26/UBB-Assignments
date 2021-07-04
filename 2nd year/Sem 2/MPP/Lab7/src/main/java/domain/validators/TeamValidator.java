package domain.validators;

import domain.entities.Team;
import domain.exceptions.ValidatorException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * A Validator class for the Team class, implementing the Validator interface
 */
@Component
public class TeamValidator implements Validator<Team>{
    /**
     * Checks if the attributes of a team are valid
     * @param entity an object of type Team
     * @throws ValidatorException if the attributes are no valid
     */
    @Override
    public void validate(Team entity) throws ValidatorException {
        Optional.of(entity).filter(team -> team != null).orElseThrow(()-> {
            throw new ValidatorException("Entity is null"); });

//        Optional.of(entity).filter(team -> team.getId()>0).orElseThrow(()-> {
//            throw new ValidatorException("id should be a positive number"); });

        Optional.of(entity).filter(team -> !(team.getTeamName().equals(""))).orElseThrow(()-> {
            throw new ValidatorException("Team name should have at least one character"); });
    }
}
