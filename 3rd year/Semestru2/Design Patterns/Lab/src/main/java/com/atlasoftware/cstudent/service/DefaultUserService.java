package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.AbstractUserDao;
import com.atlasoftware.cstudent.domain.ProfessorDao;
import com.atlasoftware.cstudent.domain.RoomDao;
import com.atlasoftware.cstudent.domain.StudentDao;
import com.atlasoftware.cstudent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("defaultUserService")
public class DefaultUserService implements UserService{

    @Autowired
    UserRepository userRepository;


    @Override
    public StudentDao updateStudentData(StudentDao studentDao)
    {
        return (StudentDao) userRepository.save(studentDao);
    }

    @Override
    public ProfessorDao updateProfessorData(ProfessorDao professorDao) {
        return (ProfessorDao) userRepository.save(professorDao);
    }

    @Override
    public StudentDao findByRegistrationNumber(Integer registrationNumber) {
        return userRepository.findByRegistrationNumber(registrationNumber);
    }

    @Override
    public StudentDao findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<AbstractUserDao> findByID(UUID id) { return userRepository.findById(id);}

    @Override
    public List<StudentDao> findByRoomDao(RoomDao room) {
        return userRepository.findAllByRoomDao(room);
    }
}
