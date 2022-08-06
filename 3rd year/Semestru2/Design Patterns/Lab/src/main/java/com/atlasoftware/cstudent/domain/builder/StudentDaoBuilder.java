//package com.atlasoftware.cstudent.domain.builder;
//
//import com.atlasoftware.cstudent.domain.*;
//
//import java.util.List;
//
//public class StudentDaoBuilder {
//
//    private Integer group;
//    private Integer registrationNumber;
//    private Boolean semigroup;
//    private LocationDao locationDao;
//    private RoomDao roomDao;
//    private String section;
//
//    public StudentDaoBuilder buildGroup(Integer group)
//    {
//        this.group=group;
//        return this;
//    }
//
//    public StudentDaoBuilder buildRegistrationNumber(Integer registrationNumber)
//    {
//        this.registrationNumber=registrationNumber;
//        return this;
//    }
//
//    public StudentDaoBuilder buildSemigroup(Boolean semigroup)
//    {
//        this.semigroup=semigroup;
//        return this;
//    }
//
//    public StudentDaoBuilder buildLocationDao(LocationDao locationDao)
//    {
//        this.locationDao=locationDao;
//        return this;
//    }
//
//    public StudentDaoBuilder buildRoomDao(RoomDao roomDao)
//    {
//        this.roomDao=roomDao;
//        return this;
//    }
//
//    public StudentDaoBuilder buildSection(String section)
//    {
//        this.section=section;
//        return this;
//    }
//
//    public StudentDao getStudentDao()
//    {
//        return new StudentDao(group,registrationNumber,semigroup,locationDao,roomDao,section);
//    }
//}
