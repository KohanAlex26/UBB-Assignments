package web.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
@ToString(callSuper = true)
public class SportiveTrainerPrimaryKeyDTO {
    Long sportiveId, trainerId;
    int cost;
    String trainingType;
}
