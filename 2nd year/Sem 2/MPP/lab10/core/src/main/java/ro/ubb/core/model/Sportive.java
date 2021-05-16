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
public class Sportive extends BaseEntity<Long>{
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @Min(0)
    @Max(200)
    private int age;
    @Min(0)
    @Max(100000000)
    private int tid;
}
