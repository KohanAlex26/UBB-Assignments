package com.atlasoftware.cstudent.repository;

import com.atlasoftware.cstudent.domain.CourseDao;
import com.atlasoftware.cstudent.domain.ExamDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExamRepository extends JpaRepository<ExamDao, UUID> {

    List<ExamDao> getAllByCourseCode(String courseCode);
}

