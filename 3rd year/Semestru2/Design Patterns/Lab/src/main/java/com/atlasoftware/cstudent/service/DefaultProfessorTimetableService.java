package com.atlasoftware.cstudent.service;


import com.atlasoftware.cstudent.domain.CourseActivityDao;
import com.atlasoftware.cstudent.domain.ProfessorDao;
import com.atlasoftware.cstudent.repository.CourseActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("defaultProfessorTimetableService")
public class DefaultProfessorTimetableService  implements ProfessorTimetableService{

    @Autowired
    CourseActivityRepository courseActivityRepository;

    @Override
    public List<CourseActivityDao> getCourseActivityByProfessorDao(ProfessorDao professorDao)
    {return courseActivityRepository.findAllByProfessorDao(professorDao);}

}