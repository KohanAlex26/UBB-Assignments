package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.CourseActivityDao;

import java.util.List;
import java.util.UUID;

public interface CourseActivityService {

    List<CourseActivityDao> getAllCourseActivities();
    CourseActivityDao getCourseActivityByID(UUID id);
    void deleteCourseActivityByID(UUID id);
    void deleteAllCourseActivityByID(List<UUID> ids);
    CourseActivityDao updateAndAddCourseActivity(CourseActivityDao courseActivity);

    List<CourseActivityDao> findCourseActivityByCourseCode(String courseCode);
}
