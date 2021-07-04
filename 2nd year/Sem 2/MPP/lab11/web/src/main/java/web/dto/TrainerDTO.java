package web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TrainerDTO extends BaseDTO<Long>{
    private AwardDTO award;
    private String firstName;
    private String lastName;
    private int age;
}

