package lab8.web.converter;

import lab8.core.model.Sportive;
import lab8.web.dto.SportiveDto;
import org.springframework.stereotype.Component;

@Component
public class SportiveConverter extends BaseConverter<Sportive, SportiveDto>{
    public Sportive convertDtoToModel(SportiveDto dto) {
        Sportive sportive = Sportive.builder()
                .age(dto.getAge())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .tid(dto.getTid())
                .build();
        sportive.setId(dto.getId());
        return sportive;
    }

    @Override
    public SportiveDto convertModelToDto(Sportive sportive) {
        SportiveDto dto = new SportiveDto(sportive.getFirstName(), sportive.getLastName(),sportive.getAge(),sportive.getTid());
        dto.setId(sportive.getId());
        return dto;
    }
}
