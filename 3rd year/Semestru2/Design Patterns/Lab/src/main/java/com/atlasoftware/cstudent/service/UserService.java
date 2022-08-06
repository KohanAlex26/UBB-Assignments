package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.AbstractUserDao;
import com.atlasoftware.cstudent.domain.ProfessorDao;
import com.atlasoftware.cstudent.domain.RoomDao;
import com.atlasoftware.cstudent.domain.StudentDao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    StudentDao updateStudentData(StudentDao studentDao);
    ProfessorDao updateProfessorData(ProfessorDao professorDao);
    StudentDao findByRegistrationNumber(Integer registrationNumber);
    StudentDao findByEmail(String email);
    Optional<AbstractUserDao> findByID(UUID id);
    List<StudentDao> findByRoomDao(RoomDao room);
}
