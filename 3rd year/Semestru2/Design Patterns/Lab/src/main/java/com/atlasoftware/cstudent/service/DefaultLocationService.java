package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.LocationDao;
import com.atlasoftware.cstudent.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service("defaultLocationRepositoryService")
public class DefaultLocationService implements LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<LocationDao> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public LocationDao getLocationByID(UUID id) {
        return locationRepository.getById(id);
    }

    @Override
    public void deleteLocationByID(UUID id) {
        locationRepository.deleteById(id);
    }

    @Override
    public void deleteAllLocationByID(List<UUID> ids) {
        locationRepository.deleteAllById(ids);
    }

    @Override
    public LocationDao updateAndAddLocation(LocationDao location) {
        return locationRepository.save(location);
    }

    @Override
    public LocationDao findByLocationName(String studentHousingName) {
        return locationRepository.findByName(studentHousingName);
    }
}

