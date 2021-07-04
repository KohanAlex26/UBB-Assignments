package lab8.web.converter;

import lab8.core.model.SportiveTrainer;
import lab8.web.dto.SportiveTrainerDto;
import lab8.web.dto.TeamDto;
import org.springframework.stereotype.Component;

@Component
public class SportiveTrainerConverter extends BaseConverter<SportiveTrainer, SportiveTrainerDto>{

    @Override
    public SportiveTrainer convertDtoToModel(SportiveTrainerDto dto) {
        SportiveTrainer st = SportiveTrainer.builder()
                .sportiveID(dto.getSportiveID())
                .trainerID(dto.getTrainerID())
                .trainingType(dto.getTrainingType())
                .cost(dto.getCost())
                .build();
        st.setId(dto.getId());
        return st;
    }

    @Override
    public SportiveTrainerDto convertModelToDto(SportiveTrainer st) {
         SportiveTrainerDto dto = SportiveTrainerDto.builder()
                 .sportiveID(st.getSportiveID())
                 .trainerID(st.getTrainerID())
                 .trainingType(st.getTrainingType())
                 .cost(st.getCost())
                 .build();
        dto.setId(st.getId());
        return dto;
    }
}
