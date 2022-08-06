package com.atlasoftware.cstudent.converter;

import com.atlasoftware.cstudent.domain.MyUserType;
import com.atlasoftware.cstudent.domain.StudentDao;
import com.atlasoftware.cstudent.dto.StudentDto;

import java.util.UUID;
import java.util.stream.Collectors;

public class StudentConverter {
    public static StudentDao convertDtoToModel(StudentDto studentDto) {
        StudentDao studentDao = new StudentDao(studentDto.getGroup(), studentDto.getRegistrationNumber(), studentDto.isSemigroup(),
                null,null, null, studentDto.getTimeTable().stream().map(CourseActivityConverter::convertDtoToModel).collect(Collectors.toList()), studentDto.getSection());
        studentDao.setFirstName(studentDto.getFirstName());
        studentDao.setLastName(studentDto.getLastName());
        studentDao.setUserType(MyUserType.valueOf(studentDto.getUserType()));
        studentDao.setId(UUID.fromString(studentDto.getId()));
        studentDao.setEmail(studentDto.getEmail());
        studentDao.setPassword(studentDto.getPassword());
        return studentDao;
    }
    public static StudentDto convertModelToDto(StudentDao studentDao) {
        StudentDto studentDto = new StudentDto(studentDao.getGroup(), studentDao.getRegistrationNumber(), studentDao.getSemigroup(),
                 studentDao.getTimeTable().stream().map(CourseActivityConverter::convertModelToDto).collect(Collectors.toList()), studentDao.getSection());
        studentDto.setFirstName(studentDao.getFirstName());
        studentDto.setLastName(studentDao.getLastName());
        studentDto.setUserType(studentDao.getUserType().toString());
        studentDto.setId(studentDao.getId().toString());
        studentDto.setEmail(studentDao.getEmail());
        studentDto.setPassword(studentDao.getPassword());
        return studentDto;
    }
}
