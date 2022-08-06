package com.atlasoftware.cstudent.repository;

import com.atlasoftware.cstudent.domain.LocationDao;
import com.atlasoftware.cstudent.domain.RoomDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface  RoomRepository extends JpaRepository<RoomDao, UUID> {
    RoomDao findByNameAndLocationDao (String roomName, LocationDao locationDao);
}
