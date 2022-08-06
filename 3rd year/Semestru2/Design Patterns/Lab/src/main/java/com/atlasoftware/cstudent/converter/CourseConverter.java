package com.atlasoftware.cstudent.converter;

import com.atlasoftware.cstudent.domain.CourseDao;
import com.atlasoftware.cstudent.domain.CourseType;
import com.atlasoftware.cstudent.domain.ExamType;
import com.atlasoftware.cstudent.dto.CourseDto;

import java.util.UUID;
import java.util.stream.Collectors;

public class CourseConverter {
    public static CourseDao convertDtoToModel(CourseDto courseDto) {
        return new CourseDao(UUID.fromString(courseDto.getId()), courseDto.getCourseCode(),courseDto.getCourseName(),courseDto.getCredits(),
                courseDto.getGradeFormula(), courseDto.isExam(), courseDto.isPracticalExam(),
                courseDto.isPartialExam(), ExamType.valueOf(courseDto.getExamType()), CourseType.valueOf(courseDto.getCourseType()), null, courseDto.getCourseActivities().stream().map(CourseActivityConverter::convertDtoToModel).collect(Collectors.toList()));
    }
    public static CourseDto convertModelToDto(CourseDao courseDao) {
        if(courseDao.getCredits()==null)
        {
            return new CourseDto(courseDao.getId().toString(), courseDao.getCourseCode(),courseDao.getCourseName(), 0,
                    courseDao.getGradeFormula(), true, false,
                    true, null, null, courseDao.getCourseActivities().stream().map(CourseActivityConverter::convertModelToDto).collect(Collectors.toList()));

        }

        return new CourseDto(courseDao.getId().toString(), courseDao.getCourseCode(),courseDao.getCourseName(), courseDao.getCredits(),
                courseDao.getGradeFormula(), courseDao.getExam(), courseDao.getPracticalExam(),
                courseDao.getPartialExam(), courseDao.getExamType().toString(), courseDao.getCourseType().toString(), courseDao.getCourseActivities().stream().map(CourseActivityConverter::convertModelToDto).collect(Collectors.toList()));
    }
}
