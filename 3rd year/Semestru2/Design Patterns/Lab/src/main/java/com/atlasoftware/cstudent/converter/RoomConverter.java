package com.atlasoftware.cstudent.converter;

import com.atlasoftware.cstudent.domain.RoomDao;
import com.atlasoftware.cstudent.dto.RoomDto;

import java.util.UUID;
import java.util.stream.Collectors;

public class RoomConverter {
    public static RoomDao convertDtoToModel(RoomDto roomDto) {
        return new RoomDao(UUID.fromString(roomDto.getId()),null, roomDto.getName(),
                roomDto.getCourseActivities().stream().map(CourseActivityConverter::convertDtoToModel).collect(Collectors.toList()), roomDto.getStudents().stream().map(StudentConverter::convertDtoToModel).collect(Collectors.toList()));
    }
    public static RoomDto convertModelToDto(RoomDao roomDao) {
        if (roomDao != null) {
            return new RoomDto(roomDao.getId().toString(), roomDao.getName(),
                    roomDao.getCourseActivities().stream().map(CourseActivityConverter::convertModelToDto).collect(Collectors.toList()), roomDao.getStudents().stream().map(StudentConverter::convertModelToDto).collect(Collectors.toList()));

        }
        return null;
    }
}
