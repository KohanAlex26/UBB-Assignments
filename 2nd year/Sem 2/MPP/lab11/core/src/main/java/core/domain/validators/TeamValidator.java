package core.domain.validators;


import org.springframework.stereotype.Component;
import core.domain.Team;
import core.exceptions.ValidatorException;

import java.util.Optional;

@Component
public class TeamValidator implements Validator<Team>{

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
