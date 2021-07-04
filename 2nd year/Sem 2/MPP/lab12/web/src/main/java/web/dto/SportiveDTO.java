package web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SportiveDTO extends BaseDTO<Long> {
    TeamDTO team;
    String firstName;
    String lastName;
    int age;
}
