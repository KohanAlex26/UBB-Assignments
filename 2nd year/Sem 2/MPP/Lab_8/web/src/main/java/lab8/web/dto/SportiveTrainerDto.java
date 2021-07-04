package lab8.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class SportiveTrainerDto extends BaseDto{
    private Long sportiveID;
    private Long trainerID;
    private int cost;
    private String trainingType;
}
