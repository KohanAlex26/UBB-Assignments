package com.atlasoftware.cstudent.utils;


import com.atlasoftware.cstudent.domain.LocationDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentRegistrationForm {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Integer registrationNumber;
    private boolean semigroup;
    private String studentHousingName;
    private Integer roomNumber;
}
