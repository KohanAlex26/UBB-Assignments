package ro.ubb.web.converter;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ro.ubb.core.model.Sportive;
import ro.ubb.core.model.SportiveTrainer;
import ro.ubb.web.dto.SportiveDto;
import ro.ubb.web.dto.SportiveTrainerDto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Component
public class SportiveTrainerConverter extends BaseConverter<Long, SportiveTrainer, SportiveTrainerDto>{
    public SportiveTrainer convertDtoToModel(SportiveTrainerDto dto) {
        SportiveTrainer sportiveTrainer = SportiveTrainer.builder()
                .sportiveID(dto.getSportiveID())
                .trainerID(dto.getTrainerID())
                .cost(dto.getCost())
                .trainingType(dto.getTrainingType())
                .build();
        sportiveTrainer.setId(dto.getId());
        return sportiveTrainer;
    }


    @Override
    public SportiveTrainerDto convertModelToDto(SportiveTrainer sportiveTrainer) {
        SportiveTrainerDto dto = new SportiveTrainerDto(sportiveTrainer.getSportiveID(), sportiveTrainer.getTrainerID(),sportiveTrainer.getCost(),sportiveTrainer.getTrainingType());
        dto.setId(sportiveTrainer.getId());
        return dto;
    }
}
