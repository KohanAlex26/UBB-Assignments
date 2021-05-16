package ro.ubb.web.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Created by radu.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class SportiveDto extends BaseDto<Long> implements Serializable {
    private String firstName;
    private String lastName;
    private int age;
    private int tid;

    public SportiveDto(long id, String firstName, String lastName, int age,int tid) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.tid=tid;
    }
}
