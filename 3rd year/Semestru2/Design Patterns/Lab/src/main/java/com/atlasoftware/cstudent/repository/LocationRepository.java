package com.atlasoftware.cstudent.repository;

import com.atlasoftware.cstudent.domain.LocationDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<LocationDao, UUID> {
    LocationDao findByName (String locationName);
}
