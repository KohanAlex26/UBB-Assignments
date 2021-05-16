package ro.ubb.web.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class SportiveTrainerDto extends BaseDto<Long> implements Serializable {
    private Long sportiveID;
    private Long trainerID;
    private int cost;
    private String trainingType;

    public SportiveTrainerDto(long id, Long sportiveID, Long trainerID, int cost,String trainingType) {
        super(id);
        this.sportiveID = sportiveID;
        this.trainerID = trainerID;
        this.cost = cost;
        this.trainingType=trainingType;
    }
}
