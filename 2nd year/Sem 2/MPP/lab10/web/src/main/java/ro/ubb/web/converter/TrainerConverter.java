package ro.ubb.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.core.model.Trainer;
import ro.ubb.web.dto.TrainerDto;

@Component
public class TrainerConverter extends BaseConverter<Long, Trainer, TrainerDto>{
    public Trainer convertDtoToModel(TrainerDto dto) {
        Trainer trainer = Trainer.builder()
                .age(dto.getAge())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
        trainer.setId(dto.getId());
        return trainer;
    }

    @Override
    public TrainerDto convertModelToDto(Trainer trainer) {
        TrainerDto dto = new TrainerDto(trainer.getFirstName(), trainer.getLastName(),trainer.getAge());
        dto.setId(trainer.getId());
        return dto;
    }
}
