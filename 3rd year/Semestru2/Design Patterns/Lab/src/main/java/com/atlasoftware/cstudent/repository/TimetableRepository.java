package com.atlasoftware.cstudent.repository;

import com.atlasoftware.cstudent.domain.TimeTableDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TimetableRepository extends JpaRepository<TimeTableDao, UUID> {
}
