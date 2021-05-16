package ro.ubb.web.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class TeamDto extends BaseDto<Long> implements Serializable {
    private String teamName;

    public TeamDto(long id, String teamName) {
        super(id);
        this.teamName = teamName;
    }
}
