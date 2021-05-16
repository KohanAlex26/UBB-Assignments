package ro.ubb.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.core.model.Team;
import ro.ubb.web.dto.TeamDto;

@Component
public class TeamConverter extends BaseConverter<Long, Team, TeamDto>{
    public Team convertDtoToModel(TeamDto dto) {
        Team team = Team.builder()
                .teamName(dto.getTeamName())
                .build();
        team.setId(dto.getId());
        return team;
    }

    @Override
    public TeamDto convertModelToDto(Team team) {
        TeamDto dto = new TeamDto(team.getTeamName());
        dto.setId(team.getId());
        return dto;
    }
}
