package web.dto;

import lombok.*;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
@ToString()
//@ToString(callSuper = true)
public class AwardDTO implements Serializable {
    private String date;

//    @Override
//    public String toString() {
//        return "AwardDTO{" +
//                "date='" + date + '\'' +
//                ", id=" + id +
//                '}';
//    }
}
