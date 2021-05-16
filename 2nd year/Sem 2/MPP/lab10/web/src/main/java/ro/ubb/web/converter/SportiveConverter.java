package ro.ubb.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.core.model.Sportive;
import ro.ubb.web.dto.SportiveDto;

/**
 * Created by radu.
 */
@Component
public class SportiveConverter extends BaseConverter<Long, Sportive, SportiveDto> {
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
