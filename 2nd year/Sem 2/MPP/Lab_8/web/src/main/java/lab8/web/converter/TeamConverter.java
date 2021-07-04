package lab8.web.converter;

import lab8.core.model.Team;
import lab8.web.dto.TeamDto;
import org.springframework.stereotype.Component;

@Component
public class TeamConverter extends BaseConverter<Team, TeamDto>{
    public Team convertDtoToModel(TeamDto dto) {
        Team team = Team.builder()
                .teamName(dto.getTeamName())
                .build();
        team.setId(dto.getId());
        return team;
    }

    @Override
    public TeamDto convertModelToDto(Team team) {
        TeamDto dto = TeamDto.builder()
                .teamName(team.getTeamName())
                .build();
        dto.setId(team.getId());
        return dto;
    }

}
