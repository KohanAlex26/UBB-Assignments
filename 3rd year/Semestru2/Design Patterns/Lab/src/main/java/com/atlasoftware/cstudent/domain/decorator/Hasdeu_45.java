package com.atlasoftware.cstudent.domain.decorator;

import com.atlasoftware.cstudent.domain.LocationDao;
import com.atlasoftware.cstudent.domain.LocationType;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;


public class Hasdeu_45 extends LocationDao {
    public Hasdeu_45(UUID id, String name, String address, String latLong, LocationType type, int totalRoomNumberByFloor, int totalNumberOfFloors){
        super(id,name,address,latLong,type,totalRoomNumberByFloor,totalNumberOfFloors,"0264 405 300");
    }
}
