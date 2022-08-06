package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.LocationDao;
import com.atlasoftware.cstudent.domain.RoomDao;
import com.atlasoftware.cstudent.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("defaultRoomService")
public class DefaultRoomService implements RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Override
    public RoomDao findByNameAndLocation(String roomName, LocationDao locationDao) {
        return roomRepository.findByNameAndLocationDao(roomName, locationDao);
    }

    @Override
    public RoomDao findById(UUID id) {
        return roomRepository.findById(id).get();
    }

    @Override
    public RoomDao save(RoomDao roomDao) {
        return roomRepository.save(roomDao);
    }
}
