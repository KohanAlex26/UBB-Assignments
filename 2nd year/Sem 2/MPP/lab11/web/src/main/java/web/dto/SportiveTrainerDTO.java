package web.dto;


import core.domain.BaseEntity;
import lombok.*;
import core.domain.SportiveTrainerPrimaryKey;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SportiveTrainerDTO  extends BaseDTO<SportiveTrainerPrimaryKey>{
    SportiveDTO sportive;
    TrainerDTO trainer;
    int cost;
    String trainingType;
}
