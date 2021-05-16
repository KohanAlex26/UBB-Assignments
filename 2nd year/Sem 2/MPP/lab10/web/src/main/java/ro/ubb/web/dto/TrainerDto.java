package ro.ubb.web.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class TrainerDto extends BaseDto<Long> implements Serializable {
    private String firstName;
    private String lastName;
    private int age;

    public TrainerDto(long id, String firstName, String lastName, int age) {
        super(id);
        this.firstName = firstName;
        this.lastName=lastName;
        this.age=age;
    }
}
