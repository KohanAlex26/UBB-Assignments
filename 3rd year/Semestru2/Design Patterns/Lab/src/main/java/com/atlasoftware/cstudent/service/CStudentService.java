package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.*;
import com.atlasoftware.cstudent.service.exceptions.StudentRegistrationException;
import com.atlasoftware.cstudent.service.exceptions.TimeTableException;
import com.atlasoftware.cstudent.utils.LoginForm;
import com.atlasoftware.cstudent.utils.StudentRegistrationForm;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CStudentService {
    List<CourseActivityDao> getAllCourseActivities();

    CourseActivityDao getCourseActivityByID(UUID id);

    void deleteCourseActivityByID(UUID id);

    void deleteAllCourseActivityByID(List<UUID> ids);

    CourseActivityDao updateAndAddCourseActivityByID(CourseActivityDao courseActivity);

    CourseDao getCourseByID(UUID id);

    List<CourseDao> getAllCourse();

    void deleteCourseByID(UUID id);

    void deleteAllCourseByID(List<UUID> ids);

    CourseDao updateAndAddCourseByID(CourseDao course);

    List<LocationDao> getAllLocations();

    LocationDao getLocationByID(UUID id);

    void deleteLocationByID(UUID id);

    void deleteAllLocationByID(List<UUID> ids);

    LocationDao updateAndAddLocation(LocationDao location);

    void initialiseData() throws IOException;

    List<CourseActivityDao> deliverTimeTable(StudentDao studentDao) throws TimeTableException;

    StudentDao findByRegistrationNumber(Integer registrationNumber);

    List<ExamDao> getAllExam();

    ExamDao getExamByID(UUID id);

    void deleteExamByID(UUID id);

    void deleteAllExamByID(List<UUID> ids);

    ExamDao updateAndAddExam(ExamDao Exam);

    Boolean validateLoginForm(LoginForm loginForm);

    Boolean validateRegistrationForm(StudentRegistrationForm studentRegistrationForm) throws StudentRegistrationException;

    List<CourseActivityDao> getCourseActivitiesByProfessorID(UUID id);

    List<CourseDao> getCoursesByProfessorID(UUID id);

    List<ExamDao> getExamsByProfessorID(UUID id);

    List<StudentDao> findByRoomDao(UUID id);

    Set<StudentDao> getAllStudents(UUID id);

    List<ExamDao> getExamsByStudentRegistrationNumber(Integer registrationNumber) throws TimeTableException;

    CourseDao getCourseByCourseCode(String courseCode);
}
