package com.atlasoftware.cstudent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDto {
    private String id;
    private String name;
    private List<CourseActivityDto> courseActivities;
    private List<StudentDto> students;
}