package com.atlasoftware.cstudent.dto;

import com.atlasoftware.cstudent.domain.CourseDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDto extends AbstractUserDto{
    private int group;
    private int registrationNumber;
    private boolean semigroup;
    private List<CourseActivityDto> timeTable;
    private String section;
}
