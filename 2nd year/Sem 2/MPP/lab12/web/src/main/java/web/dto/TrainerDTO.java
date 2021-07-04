package web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
//@ToString(callSuper = true)
public class TrainerDTO extends BaseDTO<Long>{
    private AwardDTO awardDTO;
    private String firstName;
    private String lastName;
    private int age;

    @Override
    public String toString() {
        return "TrainerDTO{" +
                "id=" + id +
                ", awardDTO=" + awardDTO +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

//    public TrainerDTO(String firstName,String lastName,int age)
//    {
//        this.firstName=firstName;
//        this.lastName=lastName;
//        this.age=age;
//    }
}

