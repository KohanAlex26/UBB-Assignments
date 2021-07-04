package web.converter;

import core.domain.Award;
import core.domain.Trainer;

import org.springframework.stereotype.Component;
import web.dto.AwardDTO;
import web.dto.TrainerDTO;


@Component
public class TrainerConverter extends BaseConverter<Long, Trainer,  TrainerDTO> {

    @Override
    public Trainer convertDtoToModel(TrainerDTO dto) {
        var trainer = new Trainer();
        trainer.setId(dto.getId());
        trainer.setFirstName(dto.getFirstName());
        trainer.setLastName(dto.getLastName());
        trainer.setAge(dto.getAge());
        Award award = null;
        if (dto.getAward() != null)
            award = new Award(dto.getAward().getDate());
        trainer.setAward(award);
        return trainer;
    }

    @Override
    public TrainerDTO convertModelToDto(Trainer trainer) {
        AwardDTO awardDTO = null;
        if (trainer.getAward() != null)
            awardDTO = new AwardDTO(trainer.getAward().getDate());
        var trainerDTO = new TrainerDTO(awardDTO, trainer.getFirstName(), trainer.getLastName(), trainer.getAge());
        trainerDTO.setId(trainer.getId());
        return trainerDTO;
    }
}
