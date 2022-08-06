package com.atlasoftware.cstudent.converter;

import com.atlasoftware.cstudent.domain.ActivityType;
import com.atlasoftware.cstudent.domain.CourseActivityDao;
import com.atlasoftware.cstudent.dto.CourseActivityDto;
import org.apache.tomcat.jni.Local;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.UUID;

public class CourseActivityConverter {
    public static CourseActivityDao convertDtoToModel(CourseActivityDto courseActivityDto) {
        return new CourseActivityDao(UUID.fromString(courseActivityDto.getId()),ActivityType.valueOf(courseActivityDto.getActivityType()),null,null,courseActivityDto.getCourseCode(), courseActivityDto.getCourseName(),courseActivityDto.getFormula(),
                (courseActivityDto.getStartTime()), (courseActivityDto.getEndTime()), DayOfWeek.valueOf(courseActivityDto.getDay()),null,courseActivityDto.isWeekly(),courseActivityDto.isWeek(),courseActivityDto.isOnline());
    }
    public static CourseActivityDto convertModelToDto(CourseActivityDao courseActivityDao) {
        return new CourseActivityDto(String.valueOf(courseActivityDao.getId()),String.valueOf(courseActivityDao.getActivityType()),courseActivityDao.getCourseCode(), courseActivityDao.getCourseName(),
                courseActivityDao.getFormula(),courseActivityDao.getStartTime(),courseActivityDao.getEndTime(),courseActivityDao.getDay().toString(),courseActivityDao.getWeekly(), courseActivityDao.getWeek(), courseActivityDao.getOnline());
    }
}
