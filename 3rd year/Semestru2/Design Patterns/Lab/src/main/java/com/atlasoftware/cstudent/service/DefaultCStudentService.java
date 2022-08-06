package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.*;
import com.atlasoftware.cstudent.service.exceptions.StudentRegistrationException;
import com.atlasoftware.cstudent.service.exceptions.TimeTableException;
import com.atlasoftware.cstudent.utils.LoginForm;
import com.atlasoftware.cstudent.utils.StudentRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("defaultCStudentService")
public class DefaultCStudentService implements CStudentService {
    @Autowired
    CourseActivityService courseActivityService;
    @Autowired
    CourseService courseService;
    @Autowired
    LocationService locationService;
    @Autowired
    DataInitialisationService dataInitialisationService;
    @Autowired
    TimeTableService timeTableService;
    @Autowired
    UserService userService;
    @Autowired
    ExamService examService;
    @Autowired
    LoginService loginService;
    @Autowired
    StudentRegistrationService studentRegistrationService;
    @Autowired
    ProfessorTimetableService professorTimetableService;
    @Autowired
    RoomService roomService;

    @Override
    public List<CourseActivityDao> getAllCourseActivities() {
        return courseActivityService.getAllCourseActivities();
    }

    @Override
    public CourseActivityDao getCourseActivityByID(UUID id) {
        return courseActivityService.getCourseActivityByID(id);
    }

    @Override
    public void deleteCourseActivityByID(UUID id) {
        courseActivityService.deleteCourseActivityByID(id);
    }

    @Override
    public void deleteAllCourseActivityByID(List<UUID> ids) {
        courseActivityService.deleteAllCourseActivityByID(ids);
    }

    @Override
    public CourseActivityDao updateAndAddCourseActivityByID(CourseActivityDao courseActivity) {
        return courseActivityService.updateAndAddCourseActivity(courseActivity);
    }

    @Override
    public CourseDao getCourseByID(UUID id) {
        return courseService.getCourseByID(id);
    }

    @Override
    public List<CourseDao> getAllCourse() {
        return courseService.getAllCourse();
    }

    @Override
    public void deleteCourseByID(UUID id) {
        courseService.deleteCourseByID(id);
    }

    @Override
    public void deleteAllCourseByID(List<UUID> ids) {
        courseService.deleteAllCourseByID(ids);
    }

    @Override
    public CourseDao updateAndAddCourseByID(CourseDao course) {
        return courseService.updateAndAddCourse(course);
    }

    @Override
    public List<LocationDao> getAllLocations() {
        return locationService.getAllLocations();
    }

    @Override
    public LocationDao getLocationByID(UUID id) {
        return locationService.getLocationByID(id);
    }

    @Override
    public void deleteLocationByID(UUID id) {
        locationService.deleteLocationByID(id);
    }

    @Override
    public void deleteAllLocationByID(List<UUID> ids) {
        locationService.deleteAllLocationByID(ids);
    }

    @Override
    public LocationDao updateAndAddLocation(LocationDao location) {
        return locationService.updateAndAddLocation(location);
    }

    @Override
    public void initialiseData() throws IOException {
        dataInitialisationService.initialiseData();
    }

    @Override
    public List<CourseActivityDao> deliverTimeTable(StudentDao studentDao) throws TimeTableException {
        return timeTableService.deliverTimeTable(studentDao);
    }

    @Override
    public StudentDao findByRegistrationNumber(Integer registrationNumber) {
        return userService.findByRegistrationNumber(registrationNumber);
    }

    @Override
    public List<ExamDao> getAllExam() {
        return examService.getAllExam();
    }

    @Override
    public ExamDao getExamByID(UUID id) {
        return examService.getExamByID(id);
    }

    @Override
    public void deleteExamByID(UUID id) {
        examService.deleteExamByID(id);
    }

    @Override
    public void deleteAllExamByID(List<UUID> ids) {
        examService.deleteAllExamByID(ids);
    }

    @Override
    public ExamDao updateAndAddExam(ExamDao examDao) {
        return examService.updateAndAddExam(examDao);
    }

    @Override
    public Boolean validateLoginForm(LoginForm loginForm) {
        return loginService.validateLoginForm(loginForm);
    }

    @Override
    public Boolean validateRegistrationForm(StudentRegistrationForm studentRegistrationForm) throws StudentRegistrationException {
        return studentRegistrationService.validateRegistrationForm(studentRegistrationForm);
    }

    @Override
    public List<CourseActivityDao> getCourseActivitiesByProfessorID(UUID id) {
        ProfessorDao professorDao = (ProfessorDao) userService.findByID(id).get();
        return professorTimetableService.getCourseActivityByProfessorDao(professorDao);
    }

    @Override
    public List<CourseDao> getCoursesByProfessorID(UUID id) {
        ProfessorDao professorDao = (ProfessorDao) userService.findByID(id).get();
        return professorTimetableService.getCourseActivityByProfessorDao(professorDao).stream().map(CourseActivityDao::getCourseDao).distinct().collect(Collectors.toList());
    }

    @Override
    public List<ExamDao> getExamsByProfessorID(UUID id) {
        ProfessorDao professorDao = (ProfessorDao) userService.findByID(id).get();
        List<ExamDao> tempExamList = new ArrayList<>();
        List<List<ExamDao>> tempExamListList =
        professorTimetableService.getCourseActivityByProfessorDao(professorDao).stream().map(CourseActivityDao::getCourseDao).distinct().collect(Collectors.toList()).stream().map(courseDao -> {
            return examService.getExamsByCourseCode(courseDao.getCourseCode());
        }).collect(Collectors.toList());
        tempExamListList.forEach(examDaos -> examDaos.forEach(examDao -> tempExamList.add(examDao)));
        return tempExamList;
    }

    @Override
    public List<ExamDao> getExamsByStudentRegistrationNumber(Integer registrationNumber) throws TimeTableException {
        StudentDao studentDao = (StudentDao) userService.findByRegistrationNumber(registrationNumber);
        List<ExamDao> tempExamList = new ArrayList<>();
        List<List<ExamDao>> tempExamListList =
                timeTableService.deliverTimeTable(studentDao).stream().map(CourseActivityDao::getCourseDao).distinct().collect(Collectors.toList()).stream().map(courseDao -> {
                    return examService.getExamsByCourseCode(courseDao.getCourseCode());
                }).collect(Collectors.toList());
        tempExamListList.forEach(examDaos -> examDaos.forEach(examDao -> tempExamList.add(examDao)));
        return tempExamList;
    }

    @Override
    public CourseDao getCourseByCourseCode(String courseCode) {
        return courseService.getCourseByCourseCode(courseCode);
    }


    @Override
    public List<StudentDao> findByRoomDao(UUID id) {
        RoomDao room = roomService.findById(id);
        return userService.findByRoomDao(room);
    }

    @Override
    public Set<StudentDao> getAllStudents(UUID id) {
        return courseService.getAllStudents(id);
    }
}
