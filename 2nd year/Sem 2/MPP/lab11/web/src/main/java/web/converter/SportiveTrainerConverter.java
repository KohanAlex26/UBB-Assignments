package web.converter;

import core.domain.SportiveTrainerPrimaryKey;
import core.domain.SportiveTrainer;
import core.service.SportiveServiceImpl;
import core.service.TrainerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.dto.SportiveTrainerDTO;

@Component
public class SportiveTrainerConverter extends BaseConverter<SportiveTrainerPrimaryKey, SportiveTrainer, SportiveTrainerDTO> {
    @Autowired
    private SportiveConverter sportiveConverter;

    @Autowired
    private TrainerConverter trainerConverter;


    @Override
    public SportiveTrainer convertDtoToModel(SportiveTrainerDTO dto) {
        var model = new SportiveTrainer();
        model.setId(dto.getId());
        model.setSportive(sportiveConverter.convertDtoToModel(dto.getSportive()));
        model.setTrainer(trainerConverter.convertDtoToModel(dto.getTrainer()));
        model.setCost(dto.getCost());
        model.setTrainingType(dto.getTrainingType());
        return model;
    }

    @Override
    public SportiveTrainerDTO convertModelToDto(SportiveTrainer sportiveTrainer) {
        var sportiveTrainerDTO = new SportiveTrainerDTO();
        sportiveTrainerDTO.setSportive(sportiveConverter.convertModelToDto(sportiveTrainer.getSportive()));
        sportiveTrainerDTO.setTrainer(trainerConverter.convertModelToDto(sportiveTrainer.getTrainer()));
        sportiveTrainerDTO.setCost(sportiveTrainer.getCost());
        sportiveTrainerDTO.setTrainingType(sportiveTrainer.getTrainingType());
        sportiveTrainerDTO.setId(sportiveTrainer.getId());
        return sportiveTrainerDTO;
    }
}
