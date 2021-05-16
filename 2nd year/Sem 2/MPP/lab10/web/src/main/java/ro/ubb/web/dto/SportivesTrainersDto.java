package ro.ubb.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SportivesTrainersDto implements Serializable {
    private List<SportiveTrainerDto> sportivesTrainers;
}
