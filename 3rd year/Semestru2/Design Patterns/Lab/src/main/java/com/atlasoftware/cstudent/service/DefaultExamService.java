package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.CourseDao;
import com.atlasoftware.cstudent.domain.ExamDao;
import com.atlasoftware.cstudent.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("examService")
public class DefaultExamService implements ExamService{
    @Autowired
    ExamRepository examRepository;

    @Override
    public List<ExamDao> getAllExam() {
        return examRepository.findAll();
    }

    @Override
    public ExamDao getExamByID(UUID id) {
        return examRepository.getById(id);
    }

    @Override
    public void deleteExamByID(UUID id) {
        examRepository.deleteById(id);
    }

    @Override
    public void deleteAllExamByID(List<UUID> ids)
    {
        examRepository.deleteAllById(ids);
    }

    @Override
    public ExamDao updateAndAddExam(ExamDao Exam) {
        return examRepository.saveAndFlush(Exam);
    }

    @Override
    public List<ExamDao> getExamsByCourseCode(String courseCode) {
        return examRepository.getAllByCourseCode(courseCode);
    }

}
