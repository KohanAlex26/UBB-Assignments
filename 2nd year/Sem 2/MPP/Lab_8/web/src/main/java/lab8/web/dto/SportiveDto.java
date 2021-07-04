package lab8.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class SportiveDto extends BaseDto{
    private String firstName;
    private String lastName;
    private int age;
    private int tid;
}
