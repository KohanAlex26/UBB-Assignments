package com.atlasoftware.cstudent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfessorDto extends AbstractUserDto{
    private String title;
    private String personalWebsite;
    private List<CourseActivityDto> courseActivities;
}
