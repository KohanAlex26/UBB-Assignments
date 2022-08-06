package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.*;
import com.atlasoftware.cstudent.repository.TimetableRepository;
import com.atlasoftware.cstudent.service.exceptions.TimeTableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("defaultTimeTableService")
public class DefaultTimeTableService implements TimeTableService {

    @Autowired
    DefaultUserService userService;
    @Autowired
    DefaultCourseActivityService courseActivityService;
    @Autowired
    DefaultCourseService courseService;
    @Autowired
    TimetableRepository timetableRepository;

    private boolean validateStudent(StudentDao studentDao) {
        return userService.findByRegistrationNumber(studentDao.getRegistrationNumber()) != null;
    }

    private List<String> getStudentEnrolledCourses(StudentDao studentDao) {
        List<CourseDao> courses = userService.findByRegistrationNumber(studentDao.getRegistrationNumber()).getCourses();
        List<String> courseCodes = new ArrayList<>();
        for (CourseDao courseDao : courses) {
            courseCodes.add(courseDao.getCourseCode());
        }
        return courseCodes;
    }

    private List<CourseActivityDao> getStudentCourseActivities(List<String> courseCodes) {
        List<CourseActivityDao> courseActivities = new ArrayList<>();
        for (String courseCode : courseCodes) {
            courseActivities.addAll(courseActivityService.findCourseActivityByCourseCode(courseCode));
        }
        return courseActivities;
    }

    private List<CourseActivityDao> getAllLectures(List<CourseActivityDao> courseActivityDaos, StudentDao studentDao) {
        List<CourseActivityDao> lectures = new ArrayList<>();
        for (CourseActivityDao lecture : courseActivityDaos) {
            if (lecture.getActivityType().equals(ActivityType.Lecture) && studentDao.getSection().equals(lecture.getFormula())) {
                lectures.add(lecture);
            }
        }
        return lectures;
    }

    //TODO: regex for whole year code, needs to be fixed
    //([A-Za-z]{1,3}[1-9]{1})
    private List<CourseActivityDao> getSeminaries(List<CourseActivityDao> courseActivityDaos, StudentDao studentDao) {
        List<CourseActivityDao> seminaries = new ArrayList<>();
        for (CourseActivityDao seminary : courseActivityDaos) {
            if (seminary.getActivityType().equals(ActivityType.Seminary)) {
                {
                    if (seminary.getFormula().equals(studentDao.getSection()) ) {
                        seminaries.add(seminary);
                    } else {
                        if (seminary.getFormula().equals(studentDao.getGroup().toString())) {
                            seminaries.add(seminary);
                        }
                    }
                }
            }
        }
        return seminaries;
    }

    private List<CourseActivityDao> getLaboratories(List<CourseActivityDao> courseActivityDaos, StudentDao studentDao) {
        List<CourseActivityDao> laboratories = new ArrayList<>();

        for (CourseActivityDao laboratory : courseActivityDaos) {
            if (laboratory.getActivityType().equals(ActivityType.Laboratory)) {
                {
                    if (laboratory.getFormula().equals(studentDao.getSection())) {
                        laboratories.add(laboratory);
                    } else {
                        if (laboratory.getFormula().equals(studentDao.getGroup().toString())) {
                            laboratories.add(laboratory);
                        } else {
                            if (laboratory.getFormula().equals(studentDao.getGroup() + "/1") && !studentDao.getSemigroup()) {
                                laboratories.add(laboratory);
                            } else if (laboratory.getFormula().equals(studentDao.getGroup() + "/2") && studentDao.getSemigroup()) {
                                laboratories.add(laboratory);
                            }
                        }
                    }
                }
            }
        }
        return laboratories;
    }


    //TODO: handle retake courses

    public void generateTimeTable(StudentDao studentDao) throws TimeTableException {

        Boolean lecturesExist = false;
        Boolean seminariesExist = false;
        Boolean laboratoriesExist = false;
        if (!validateStudent(studentDao)) {
            throw new TimeTableException("Studentul cu numarul matricol " + studentDao.getRegistrationNumber() + " nu a fost gasit!");
        } else {
            List<String> studentEnrolledCourseCodes = getStudentEnrolledCourses(studentDao);
            if (studentEnrolledCourseCodes.isEmpty()) {
                throw new TimeTableException("Studentul nu este inscris la nici un curs!");
            } else {
                List<CourseActivityDao> courseActivities = getStudentCourseActivities(studentEnrolledCourseCodes);
                if (courseActivities.isEmpty()) {
                    throw new TimeTableException("Nu exista nici o activitate pentru materiile selectate!");
                } else {
                    List<CourseActivityDao> lectures = getAllLectures(courseActivities, studentDao);
                    lecturesExist = loadActivities(studentDao, lectures);
                    List<CourseActivityDao> seminaries = getSeminaries(courseActivities, studentDao);
                    seminariesExist = loadActivities(studentDao, seminaries);
                    List<CourseActivityDao> laboratories = getLaboratories(courseActivities, studentDao);
                    laboratoriesExist = loadActivities(studentDao, laboratories);
                }
            }
        }
        if (!lecturesExist && !seminariesExist && !laboratoriesExist) {
            throw new TimeTableException("Studentul cu numarul matricol " + studentDao.getRegistrationNumber() + " nu are nici o activitate!");
        }
    }

    private Boolean loadActivities(StudentDao studentDao, List<CourseActivityDao> activities) {
        if (!activities.isEmpty()) {
//            for (CourseActivityDao lecture : activities) {
//                TimeTableDao timeTableDao = new TimeTableDao();
//                timeTableDao.setStudent(studentDao);
//                timeTableDao.setCourseActivity(lecture);
//                //timetableRepository.save(timeTableDao);
//            }
            studentDao.addToTimeTable(activities);
        }
        return true;
    }

    public List<CourseActivityDao> deliverTimeTable(StudentDao studentDao) throws TimeTableException {
        if (studentDao.getTimeTable().size() == 0) {
            generateTimeTable(studentDao);
        }
        return studentDao.getTimeTable();
    }
}
