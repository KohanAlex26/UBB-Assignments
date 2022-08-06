package com.atlasoftware.cstudent.service;


import com.atlasoftware.cstudent.domain.CourseDao;
import com.atlasoftware.cstudent.domain.StudentDao;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CourseService {
    List<CourseDao> getAllCourse();
    CourseDao getCourseByID(UUID id);
    CourseDao getCourseByCourseCode(String courseCode);
    void deleteCourseByID(UUID id);
    void deleteAllCourseByID(List<UUID> ids);
    CourseDao updateAndAddCourse(CourseDao course);
    Set<StudentDao> getAllStudents(UUID id);
}
