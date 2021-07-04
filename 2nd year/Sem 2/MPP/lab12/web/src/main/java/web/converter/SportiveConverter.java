package web.converter;

import core.domain.Sportive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import web.dto.SportiveDTO;

@Component
public class SportiveConverter extends BaseConverter<Long, Sportive, SportiveDTO> {
    public static final Logger logger = LoggerFactory.getLogger(SportiveConverter.class);

    @Autowired
    private TeamConverter teamConverter;

    @Override
    public Sportive convertDtoToModel(SportiveDTO dto) {
        var model = new Sportive();
        model.setId(dto.getId());
        model.setFirstName(dto.getFirstName());
        model.setLastName(dto.getLastName());
        model.setAge(dto.getAge());
        model.setTeam(teamConverter.convertDtoToModel(dto.getTeam()));
        return model;
    }

    @Override
    public SportiveDTO convertModelToDto(Sportive sportive) {
        var dto = new SportiveDTO(teamConverter.convertModelToDto(sportive.getTeam()), sportive.getFirstName(), sportive.getLastName(), sportive.getAge());
        dto.setId(sportive.getId());
        return dto;
    }
}
