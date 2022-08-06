package com.atlasoftware.cstudent.domain.decorator;

import com.atlasoftware.cstudent.domain.LocationDao;
import com.atlasoftware.cstudent.domain.LocationType;

import java.util.UUID;

public class Hasdeu_90_92 extends LocationDao {
    public Hasdeu_90_92(UUID id, String name, String address, String latLong, LocationType type, int totalRoomNumberByFloor, int totalNumberOfFloors){
        super(id,name,address,latLong,type,totalRoomNumberByFloor,totalNumberOfFloors,"0264 431 910");
    }
}
