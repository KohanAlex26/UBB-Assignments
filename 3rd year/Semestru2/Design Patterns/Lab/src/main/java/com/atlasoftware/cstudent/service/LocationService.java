package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.LocationDao;

import java.util.List;
import java.util.UUID;
public interface LocationService {
    List<LocationDao> getAllLocations();
    LocationDao getLocationByID(UUID id);
    void deleteLocationByID(UUID id);
    void deleteAllLocationByID(List<UUID> ids);
    LocationDao updateAndAddLocation(LocationDao location);
    LocationDao findByLocationName(String studentHousingName);
}
