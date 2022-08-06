package com.atlasoftware.cstudent.converter;

import com.atlasoftware.cstudent.domain.MyUserType;
import com.atlasoftware.cstudent.domain.ProfessorDao;
import com.atlasoftware.cstudent.domain.ProfessorTitle;
import com.atlasoftware.cstudent.dto.ProfessorDto;

import java.util.UUID;
import java.util.stream.Collectors;

public class ProfessorConverter {
    public static ProfessorDao convertDtoToModel(ProfessorDto professorDto) {
        ProfessorDao professorDao = new ProfessorDao(ProfessorTitle.valueOf(professorDto.getTitle()), professorDto.getPersonalWebsite(), professorDto.getCourseActivities().stream().map(CourseActivityConverter::convertDtoToModel).collect(Collectors.toList()));
        professorDao.setFirstName(professorDto.getFirstName());
        professorDao.setLastName(professorDto.getLastName());
        professorDao.setUserType(MyUserType.valueOf(professorDto.getUserType()));
        professorDao.setId(UUID.fromString(professorDto.getId()));
        professorDao.setEmail(professorDto.getEmail());
        professorDao.setPassword(professorDto.getPassword());
        return professorDao;
    }
    public static ProfessorDto convertModelToDto(ProfessorDao professorDao) {
        ProfessorDto professorDto = new ProfessorDto(professorDao.getTitle().toString(), professorDao.getPersonalWebsite(), professorDao.getCourseActivities().stream().map(CourseActivityConverter::convertModelToDto).collect(Collectors.toList()));
        professorDto.setFirstName(professorDao.getFirstName());
        professorDto.setLastName(professorDao.getLastName());
        professorDto.setUserType(professorDao.getUserType().toString());
        professorDto.setId(professorDao.getId().toString());
        professorDto.setEmail(professorDao.getEmail());
        professorDto.setPassword(professorDao.getPassword());
        return professorDto;
    }
}
