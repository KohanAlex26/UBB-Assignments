package com.atlasoftware.cstudent.service;


import com.atlasoftware.cstudent.domain.CourseDao;

import com.atlasoftware.cstudent.domain.StudentDao;
import com.atlasoftware.cstudent.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service("defaultCourseRepositoryService")
public class DefaultCourseService implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Override
    public List<CourseDao> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public CourseDao getCourseByID(UUID id) {
        return courseRepository.getById(id);
    }

    @Override
    public CourseDao getCourseByCourseCode(String courseCode) {
        return courseRepository.getByCourseCode(courseCode);
    }

    @Override
    public void deleteCourseByID(UUID id) {
        courseRepository.deleteById(id);
    }


    @Override
    public void deleteAllCourseByID(List<UUID> ids)
    {
        courseRepository.deleteAllById(ids);
    }

    @Override
    public CourseDao updateAndAddCourse(CourseDao course) {
        return courseRepository.save(course);
    }

    @Override
    public Set<StudentDao> getAllStudents(UUID id) {
        return courseRepository.findById(id).get().getStudents();
    }

}
