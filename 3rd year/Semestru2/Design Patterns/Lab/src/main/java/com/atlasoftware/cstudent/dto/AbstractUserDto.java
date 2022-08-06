package com.atlasoftware.cstudent.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "userDtoType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProfessorDto.class, name = "professorDto"),
        @JsonSubTypes.Type(value = StudentDto.class, name = "studentDto")
})

@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class AbstractUserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String userType;
    private String password;
    private String email;
}
