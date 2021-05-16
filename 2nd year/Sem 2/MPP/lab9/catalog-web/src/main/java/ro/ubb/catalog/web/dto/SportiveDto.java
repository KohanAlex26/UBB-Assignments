package ro.ubb.catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
/*@ToString(callSuper = true)*/
@Builder
public class SportiveDto extends BaseDto{
    private String firstName;
    private String lastName;
    private int age;
    private int tid;

    @Override
    public String toString() {
        return "SportiveDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", teamId=" + tid +
                "} " + super.toString();
    }
}
