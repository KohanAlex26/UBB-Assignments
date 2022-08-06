package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.CourseActivityDao;
import com.atlasoftware.cstudent.domain.ProfessorDao;

import java.util.List;
import java.util.UUID;

public interface ProfessorTimetableService {
    List<CourseActivityDao> getCourseActivityByProfessorDao(ProfessorDao professorDao);
}