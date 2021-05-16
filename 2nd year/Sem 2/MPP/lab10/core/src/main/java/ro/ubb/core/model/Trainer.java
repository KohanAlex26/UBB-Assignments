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
public class Trainer extends BaseEntity<Long>{
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @Min(0)
    @Max(100)
    private int age;
}
