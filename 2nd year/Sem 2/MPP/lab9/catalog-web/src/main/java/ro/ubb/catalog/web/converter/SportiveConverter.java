package ro.ubb.catalog.web.converter;


import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Sportive;
import ro.ubb.catalog.web.dto.SportiveDto;

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
