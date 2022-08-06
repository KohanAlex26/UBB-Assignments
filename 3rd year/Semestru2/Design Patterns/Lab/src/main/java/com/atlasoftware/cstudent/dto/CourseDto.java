package com.atlasoftware.cstudent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseDto {
    private String id;
    private String courseCode;
    private String courseName;
    private int credits;
    private String gradeFormula;
    private boolean exam;
    private boolean practicalExam;
    private boolean partialExam;
    private String examType;
    private String courseType;
    private List<CourseActivityDto> courseActivities;
}
