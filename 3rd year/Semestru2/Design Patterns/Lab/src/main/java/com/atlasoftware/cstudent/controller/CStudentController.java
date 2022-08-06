package com.atlasoftware.cstudent.controller;

import com.atlasoftware.cstudent.converter.*;
import com.atlasoftware.cstudent.domain.CourseActivityDao;
import com.atlasoftware.cstudent.domain.CourseDao;
import com.atlasoftware.cstudent.domain.ExamDao;
import com.atlasoftware.cstudent.domain.StudentDao;
import com.atlasoftware.cstudent.dto.*;
import com.atlasoftware.cstudent.service.CStudentService;
import com.atlasoftware.cstudent.service.exceptions.StudentRegistrationException;
import com.atlasoftware.cstudent.service.exceptions.TimeTableException;
import com.atlasoftware.cstudent.utils.LoginForm;
import com.atlasoftware.cstudent.utils.StudentRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CStudentController {
    @Autowired
    CStudentService cStudentService;


    @RequestMapping(value = "/load", method = RequestMethod.GET)
    private void loadData() {
        try {
            cStudentService.initialiseData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/timetable/{registrationNumber}", method = RequestMethod.GET)
    private ResponseEntity<Object> getTimeTable(@PathVariable Integer registrationNumber) {
        try {
            StudentDao student = cStudentService.findByRegistrationNumber(registrationNumber);
            List<CourseActivityDao> courseActivities = cStudentService.deliverTimeTable(student);
            courseActivities.forEach(courseActivityDao -> courseActivityDao.setCourseDao(null));
            return new ResponseEntity<>(courseActivities, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private ResponseEntity<Object> validateLoginForm(@RequestBody LoginForm loginForm) {
        if (cStudentService.validateLoginForm(loginForm)) {
            return new ResponseEntity<>("Successful Login", HttpStatus.OK);
        }
        return new ResponseEntity<>("Login Failed", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/addExam", method = RequestMethod.POST)
    private ResponseEntity<Object> addExam(@RequestBody ExamDto examDto) {
        cStudentService.updateAndAddExam(ExamConverter.convertDtoToModel(examDto));
        return new ResponseEntity<>("Exam added successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteExam/{examId}", method = RequestMethod.DELETE)
    private ResponseEntity<Object> deleteExam(@PathVariable String examId) {
        if (cStudentService.getExamByID(UUID.fromString(examId)) != null) {
            cStudentService.deleteExamByID(UUID.fromString(examId));
            return new ResponseEntity<>("Exam deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Exam doesnt exist", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/updateExam", method = RequestMethod.POST)
    private ResponseEntity<Object> updateExam(@RequestBody ExamDto examDto) {
        cStudentService.updateAndAddExam(ExamConverter.convertDtoToModel(examDto));
        return new ResponseEntity<>("Exam updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    private ResponseEntity<Object> registerStudent(@RequestBody StudentRegistrationForm regDto) {
        try {
            cStudentService.validateRegistrationForm(regDto);
            return new ResponseEntity<>("Successfully created your account!", HttpStatus.OK);
        } catch (StudentRegistrationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    private ResponseEntity<Object> getAllLocations() {
        return new ResponseEntity<>(cStudentService.getAllLocations().stream().map(LocationConverter::convertModelToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @RequestMapping(value = "/timetable/professor/{ID}", method = RequestMethod.GET)
    private ResponseEntity<List<CourseActivityDto>> getCourseActivityByProfessorID(@PathVariable UUID ID) {
        return new ResponseEntity<>(cStudentService.getCourseActivitiesByProfessorID(ID).stream().map(CourseActivityConverter::convertModelToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @RequestMapping(value = "/room/{id}", method = RequestMethod.GET)
    private ResponseEntity<Object> getAllStudentsByRoom(@PathVariable String id) {
        List<StudentDto> students = cStudentService.findByRoomDao(UUID.fromString(id)).stream().map(StudentConverter::convertModelToDto).collect(Collectors.toList());
        if (students.size() != 0) {
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
        return new ResponseEntity<>("No students in this room", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
    private ResponseEntity<Object> getAllStudents(@PathVariable String id) {
        List<StudentDto> students = cStudentService.getAllStudents(UUID.fromString(id)).stream().map(StudentConverter::convertModelToDto).collect(Collectors.toList());
        if (students.size() != 0) {
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
        return new ResponseEntity<>("No students enrolled to this course", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
    private ResponseEntity<Object> getAllCoursesForProfessor(@PathVariable String id) {
        List<CourseDto> courses = cStudentService.getCoursesByProfessorID(UUID.fromString(id)).stream().map(CourseConverter::convertModelToDto).collect(Collectors.toList());
        if (courses.size() != 0) {
            return new ResponseEntity<>(courses, HttpStatus.OK);
        }
        return new ResponseEntity<>("You have no courses", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/exams/professor/{id}", method = RequestMethod.GET)
    private ResponseEntity<Object> getAllExamsForProfessor(@PathVariable String id) {
        List<ExamDao> exams = cStudentService.getExamsByProfessorID(UUID.fromString(id));
        if (exams.size() != 0) {
            return new ResponseEntity<>(exams, HttpStatus.OK);
        }
        return new ResponseEntity<>("You have no exams", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/exams/{registrationNumber}", method = RequestMethod.GET)
    private ResponseEntity<Object> getAllExamsForStudent(@PathVariable Integer registrationNumber) {
        List<ExamDao> exams = null;
        try {
            exams = cStudentService.getExamsByStudentRegistrationNumber(registrationNumber);
            if (exams.size() != 0) {
                return new ResponseEntity<>(exams.stream().map(ExamConverter::convertModelToDto).collect(Collectors.toList()).stream().map(examDto ->{
                    examDto.setCourseName(cStudentService.getCourseByCourseCode(examDto.getCourseCode()).getCourseName());
                    return examDto;
                }), HttpStatus.OK);
            }
            return new ResponseEntity<>("You have no exams", HttpStatus.BAD_REQUEST);
        } catch (TimeTableException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/addLocation", method = RequestMethod.POST)
    private ResponseEntity<Object> addLocation(@RequestBody LocationDto locationDto) {
        cStudentService.updateAndAddLocation(LocationConverter.convertDtoToModel(locationDto));
        return new ResponseEntity<>("Location added successfully", HttpStatus.OK);
    }
}
