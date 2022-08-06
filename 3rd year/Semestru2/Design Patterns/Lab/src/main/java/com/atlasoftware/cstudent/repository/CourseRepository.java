package com.atlasoftware.cstudent.repository;

import com.atlasoftware.cstudent.domain.CourseActivityDao;
import com.atlasoftware.cstudent.domain.CourseDao;
import com.atlasoftware.cstudent.domain.ProfessorDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<CourseDao, UUID> {

    CourseDao getByCourseCode(String courseCode);
}
