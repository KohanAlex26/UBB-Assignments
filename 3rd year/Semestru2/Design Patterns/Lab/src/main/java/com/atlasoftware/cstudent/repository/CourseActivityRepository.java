package com.atlasoftware.cstudent.repository;

import com.atlasoftware.cstudent.domain.CourseActivityDao;
import com.atlasoftware.cstudent.domain.CourseDao;
import com.atlasoftware.cstudent.domain.ProfessorDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseActivityRepository extends JpaRepository<CourseActivityDao, UUID> {
    List<CourseActivityDao> findAllByCourseCode(String courseCode);
    List<CourseActivityDao> findAllByProfessorDao(ProfessorDao professorDao);
}
