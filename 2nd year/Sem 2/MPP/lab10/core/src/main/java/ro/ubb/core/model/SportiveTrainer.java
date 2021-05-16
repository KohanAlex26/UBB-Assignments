package ro.ubb.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class SportiveTrainer extends BaseEntity<Long>{
    @NonNull
    private Long sportiveID;
    @NonNull
    private Long trainerID;
    @Min(0)
    @Max(200)
    private int cost;
    @NonNull
    private String trainingType;
}
