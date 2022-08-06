package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.LocationDao;
import com.atlasoftware.cstudent.domain.RoomDao;

import java.util.UUID;

public interface RoomService {
    RoomDao findByNameAndLocation(String roomName, LocationDao locationDao);
    RoomDao findById(UUID id);
    RoomDao save(RoomDao roomDao);
}
