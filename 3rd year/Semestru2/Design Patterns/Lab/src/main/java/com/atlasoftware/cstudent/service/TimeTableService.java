package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.CourseActivityDao;
import com.atlasoftware.cstudent.domain.CourseDao;
import com.atlasoftware.cstudent.domain.StudentDao;
import com.atlasoftware.cstudent.domain.TimeTableDao;
import com.atlasoftware.cstudent.service.exceptions.TimeTableException;

import java.util.List;
import java.util.UUID;

public interface TimeTableService {
    List<CourseActivityDao> deliverTimeTable(StudentDao studentDao) throws TimeTableException;
}
