package web.converter;

import core.domain.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import web.dto.TeamDTO;

@Component
public class TeamConverter extends BaseConverter<Long, Team, TeamDTO> {
    public static final Logger logger = LoggerFactory.getLogger(TeamConverter.class);

    @Override
    public Team convertDtoToModel(TeamDTO dto) {
        var model = new Team();
        model.setId(dto.getId());
        model.setTeamName(dto.getTeamName());
        return model;
    }

    @Override
    public TeamDTO convertModelToDto(Team team) {
        var teamDto = new TeamDTO(team.getTeamName());
        teamDto.setId(team.getId());
        return teamDto;
    }
}
