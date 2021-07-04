package lab8.core.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class SportiveTrainer extends BaseEntity<Long>{
    private Long sportiveID;
    private Long trainerID;
    private int cost;
    private String trainingType;
}
