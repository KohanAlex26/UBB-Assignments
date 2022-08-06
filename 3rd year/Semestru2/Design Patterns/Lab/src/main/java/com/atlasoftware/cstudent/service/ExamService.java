package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.CourseDao;
import com.atlasoftware.cstudent.domain.ExamDao;

import java.util.List;
import java.util.UUID;

public interface ExamService {
    List<ExamDao> getAllExam();
    ExamDao getExamByID(UUID id);
    void deleteExamByID(UUID id);
    void deleteAllExamByID(List<UUID> ids);
    ExamDao updateAndAddExam(ExamDao Exam);
    List<ExamDao> getExamsByCourseCode(String courseCode);
}
