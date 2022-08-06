package com.atlasoftware.cstudent.service;


import com.atlasoftware.cstudent.domain.CourseActivityDao;
import com.atlasoftware.cstudent.repository.CourseActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("defaultCourseActivityService")
public class DefaultCourseActivityService implements CourseActivityService {
    @Autowired
    CourseActivityRepository courseActivityRepository;
    @Override
    public List<CourseActivityDao> getAllCourseActivities() {
        return courseActivityRepository.findAll();
    }

    @Override
    public CourseActivityDao getCourseActivityByID(UUID id) {
        return courseActivityRepository.getById(id);
    }

    @Override
    public void deleteCourseActivityByID(UUID id) {
        courseActivityRepository.deleteById(id);

    }

    @Override
    public void deleteAllCourseActivityByID(List<UUID> ids) {
        courseActivityRepository.deleteAllById(ids);
    }

    @Override
    public CourseActivityDao updateAndAddCourseActivity(CourseActivityDao courseActivity) {
        return courseActivityRepository.save(courseActivity);
    }

    @Override
    public List<CourseActivityDao> findCourseActivityByCourseCode(String courseCode) {
        return courseActivityRepository.findAllByCourseCode(courseCode);
    }

}
