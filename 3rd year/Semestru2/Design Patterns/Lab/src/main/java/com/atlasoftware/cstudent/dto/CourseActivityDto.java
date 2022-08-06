package com.atlasoftware.cstudent.dto;

import lombok.*;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseActivityDto {
    private String id;
    private String activityType;
    private String courseCode;
    private String courseName;
    private String formula;
    private String startTime;
    private String endTime;
    private String day;
    private boolean weekly;
    private boolean week;
    private boolean online;
}
