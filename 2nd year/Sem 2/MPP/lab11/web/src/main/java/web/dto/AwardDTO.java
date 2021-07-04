package web.dto;

import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AwardDTO extends BaseDTO<Long> {
    private String date;
}
