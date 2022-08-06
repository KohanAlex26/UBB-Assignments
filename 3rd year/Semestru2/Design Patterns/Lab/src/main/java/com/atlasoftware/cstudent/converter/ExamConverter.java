package com.atlasoftware.cstudent.converter;

import com.atlasoftware.cstudent.domain.CourseDao;
import com.atlasoftware.cstudent.domain.CourseType;
import com.atlasoftware.cstudent.domain.ExamDao;
import com.atlasoftware.cstudent.domain.ExamType;
import com.atlasoftware.cstudent.dto.CourseDto;
import com.atlasoftware.cstudent.dto.ExamDto;
import com.atlasoftware.cstudent.service.CStudentService;
import com.atlasoftware.cstudent.service.CourseService;
import com.atlasoftware.cstudent.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.stream.Collectors;

public class ExamConverter {
    public static ExamDao convertDtoToModel(ExamDto examDto) {
        return new ExamDao.ExamDaoBuilder(UUID.fromString(examDto.getId()))
                .buildDate(examDto.getDate())
                .buildCourseCode(examDto.getCourseCode())
                .buildExamType(ExamType.valueOf(examDto.getExamType()))
                .buildFormula(examDto.getFormula())
                .build();
    }
    public static ExamDto convertModelToDto(ExamDao examDao) {
        return new ExamDto(examDao.getId().toString(), examDao.getDate(), examDao.getCourseCode(), examDao.getExamType().toString(), examDao.getFormula(),null);
    }
}
