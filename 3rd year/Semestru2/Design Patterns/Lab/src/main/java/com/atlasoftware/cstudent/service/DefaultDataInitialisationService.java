package com.atlasoftware.cstudent.service;


import com.atlasoftware.cstudent.domain.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

@Service("defaultDataInitialisationService")
public class DefaultDataInitialisationService implements DataInitialisationService {

    @Autowired
    UserService defaultUserService;
    @Autowired
    CourseService defaultCourseService;
    @Autowired
    CourseActivityService defaultCourseActivityService;
    @Autowired
    LocationService defaultLocationService;

    private String mapGroupToSection(String group) {
        String section = "";
        if(group.charAt(0) == '8') {
            section += "MIE";
            if(group.charAt(1) == '1') {
                section += "1";
            }
            else if(group.charAt(1) == '2') {
                section += "2";
            }
            else {
                section += "3";
            }
        }
        else if (group.charAt(0) == '9') {
            section += "IE";
            if(group.charAt(1) == '1') {
                section += "1";
            }
            else if(group.charAt(1) == '2') {
                section += "2";
            }
            else {
                section += "3";
            }
        }
        else if (group.charAt(0) == '5') {
            section += "IM";
            if(group.charAt(1) == '1') {
                section += "1";
            }
            else if(group.charAt(1) == '2') {
                section += "2";
            }
            else {
                section += "3";
            }
        }
        else if (group.charAt(0) == '6') {
            section += "MIM";
            if(group.charAt(1) == '1') {
                section += "1";
            }
            else if(group.charAt(1) == '2') {
                section += "2";
            }
            else {
                section += "3";
            }
        }
        else if (group.charAt(0) == '1') {
            section += "M";
            if(group.charAt(1) == '1') {
                section += "1";
            }
            else if(group.charAt(1) == '2') {
                section += "2";
            }
            else {
                section += "3";
            }
        }
        else if (group.charAt(0) == '3') {
            section += "MI";
            if(group.charAt(1) == '1') {
                section += "1";
            }
            else if(group.charAt(1) == '2') {
                section += "2";
            }
            else {
                section += "3";
            }
        }
        else if (group.charAt(0) == '2') {
            section += "I";
            if(group.charAt(1) == '1') {
                section += "1";
            }
            else if(group.charAt(1) == '2') {
                section += "2";
            }
            else {
                section += "3";
            }
        }
        else if (group.charAt(0) == '7') {
            section += "IG";
            if(group.charAt(1) == '1') {
                section += "1";
            }
            else if(group.charAt(1) == '2') {
                section += "2";
            }
            else {
                section += "3";
            }
        }
        else if (group.charAt(0) == '4') {
            section += "MM";
            if(group.charAt(1) == '1') {
                section += "1";
            }
            else if(group.charAt(1) == '2') {
                section += "2";
            }
            else {
                section += "3";
            }
        }
        return section;
    }

    private void loadStudentData() throws IOException {
        BufferedReader studentData = new BufferedReader(new FileReader("src/main/resources/InitialStudentData.txt"));
        String line;
        int counter = 0;
        while ((line = studentData.readLine()) != null) {
            if(counter>30)
            {
                counter = 0;
            }
            List<String> studentDataList = Arrays.asList(line.split(";"));
            StudentDao tempStudentDao = new StudentDao();
            tempStudentDao.setEmail(studentDataList.get(0));
            tempStudentDao.setFirstName(studentDataList.get(1));
            tempStudentDao.setLastName(studentDataList.get(2));
            tempStudentDao.setGroup(Integer.parseInt(studentDataList.get(3)));
            tempStudentDao.setRegistrationNumber(Integer.parseInt(studentDataList.get(4)));
            tempStudentDao.setSection(mapGroupToSection(String.valueOf(tempStudentDao.getGroup())));
            if(counter<15)
            {
                tempStudentDao.setSemigroup(false);
            }
            else
            {
                tempStudentDao.setSemigroup(true);
            }
            tempStudentDao.setPassword("cartof5");
            List<CourseDao> courseCodes = new ArrayList<>();
            courseCodes.add(defaultCourseService.getCourseByCourseCode("MLM7001"));
            courseCodes.add(defaultCourseService.getCourseByCourseCode("MLM5012"));
            courseCodes.add(defaultCourseService.getCourseByCourseCode("MLR0024"));
            courseCodes.add(defaultCourseService.getCourseByCourseCode("MLR5044"));
            courseCodes.add(defaultCourseService.getCourseByCourseCode("MLM5013"));
            courseCodes.add(defaultCourseService.getCourseByCourseCode("TUTOR"));
            tempStudentDao.setCourses(courseCodes);
            tempStudentDao.setUserType(MyUserType.Student);
            defaultUserService.updateStudentData(tempStudentDao);
            counter++;
        }
    }

    private ProfessorTitle professorTitleMapper(String professorData)
    {
        List<String> title = Arrays.asList(professorData.split(" "));
        String professorTitleString = title.get(0);
        if(professorTitleString.equals("Prof."))
            return ProfessorTitle.Professor;
        if(professorTitleString.equals("C.d.asociat"))
            return ProfessorTitle.AssociateProfessor;
        if(professorTitleString.equals("Lect."))
            return ProfessorTitle.LecturerProfessor;
        if(professorTitleString.equals("Conf."))
            return ProfessorTitle.Prelector;
        if(professorTitleString.equals("Drd."))
            return ProfessorTitle.DoctoralStudent;
        if(professorTitleString.equals("Asist."))
            return ProfessorTitle.Assistant;
        return null;
    }
    private void loadProfessorData() throws IOException{
        BufferedReader professorData = new BufferedReader(new FileReader("src/main/resources/InitialProfessorData.txt"));
        String line;
        while ((line = professorData.readLine()) != null) {
            List<String> professorDataList = Arrays.asList(line.split(";"));
            ProfessorDao tempProfessorDao = new ProfessorDao();
            tempProfessorDao.setTitle(professorTitleMapper(professorDataList.get(0).toString()));
            List<String> names = Arrays.asList(professorDataList.get(0).split(" "));
            tempProfessorDao.setFirstName(names.get(2));
            tempProfessorDao.setLastName(names.get(1));
            tempProfessorDao.setEmail(professorDataList.get(1));
            tempProfessorDao.setPersonalWebsite(professorDataList.get(2));
            tempProfessorDao.setUserType(MyUserType.Professor);
            defaultUserService.updateProfessorData(tempProfessorDao);
        }
    }

    private void loadStudentHousingData() throws IOException{
        BufferedReader studentHousing = new BufferedReader(new FileReader("src/main/resources/InitialStudentHousingData.txt"));
        String line;
        while ((line = studentHousing.readLine()) != null) {
            List<String> studentHousingList = Arrays.asList(line.split(";"));
            LocationDao tempLocationDao = new LocationDao();
            tempLocationDao.setAddress(studentHousingList.get(0));
            tempLocationDao.setLatLong(studentHousingList.get(1));
            tempLocationDao.setName(studentHousingList.get(2));
            tempLocationDao.setType(LocationType.StudentHousing);
            tempLocationDao.setTotalNumberOfFloors(Integer.parseInt(studentHousingList.get(3)));
            tempLocationDao.setTotalRoomNumberByFloor(Integer.parseInt(studentHousingList.get(3)));
            defaultLocationService.updateAndAddLocation(tempLocationDao);
        }
    }
    
    

    private void loadCourseData() throws IOException {
        BufferedReader courseData = new BufferedReader(new FileReader("src/main/resources/InitialCourseData.txt"));
        String line;
        while ((line = courseData.readLine()) != null) {
            List<String> courseDataList = Arrays.asList(line.split(";"));
            CourseDao tempCourseDao = new CourseDao();
            tempCourseDao.setCourseCode(courseDataList.get(0));
            tempCourseDao.setCourseName(courseDataList.get(1));
            defaultCourseService.updateAndAddCourse(tempCourseDao);
        }
    }

    private DayOfWeek dayMapper(String day)
    {
        if(day.equals("Luni"))
        {
            return DayOfWeek.MONDAY;
        }
        if(day.equals("Marti"))
        {
            return DayOfWeek.TUESDAY;
        }
        if(day.equals("Miercuri"))
        {
            return DayOfWeek.WEDNESDAY;
        }
        if(day.equals("Joi"))
        {
            return DayOfWeek.THURSDAY;
        }
        if(day.equals("Vineri"))
        {
            return DayOfWeek.FRIDAY;
        }
        if(day.equals("Sambata"))
        {
            return DayOfWeek.SATURDAY;
        }
        if(day.equals("Duminica"))
        {
            return DayOfWeek.SUNDAY;
        }
        return null;
    }

    private List<String> timeIntervalMapper(String time)
    {
        List<String> timeInterval = Arrays.asList(time.split("-"));
        List<String> returnedTimeInterval = new ArrayList<>();
        if(timeInterval.get(0).length()==4)
        {
            returnedTimeInterval.add("0"+timeInterval.get(0).replace(".",":"));
        }
        else
        {
            returnedTimeInterval.add(timeInterval.get(0).replace(".",":"));
        }
        if(timeInterval.get(1).length()==4)
        {
            returnedTimeInterval.add("0"+timeInterval.get(1).replace(".",":"));
        }
        else
        {
            returnedTimeInterval.add(timeInterval.get(1).replace(".",":"));
        }
        return returnedTimeInterval;
    }

    private List<String> courseCodeAndNameMapper(String courseTitle)
    {
        List<String> courseCodeAndName = Arrays.asList(courseTitle.split(":"));
        List<String> returnValue = new ArrayList<>();
        returnValue.add(courseCodeAndName.get(0).replace(" ", ""));
        returnValue.add(courseCodeAndName.get(1));
        return returnValue;
    }
    private ActivityType courseActivityTypeMapper(String activityType)
    {
        if(activityType.equals("Curs"))
        {
            return ActivityType.Lecture;
        }
        if(activityType.equals("Seminar"))
        {
            return ActivityType.Seminary;
        }
        if(activityType.equals("Laborator"))
        {
            return ActivityType.Laboratory;
        }
        return null;
    }
    public void loadCourseActivityData() throws IOException
    {
        BufferedReader courseActivityData = new BufferedReader(new FileReader("src/main/resources/InitialCourseActivityData.txt"));
        String line;
        while ((line = courseActivityData.readLine()) != null) {
            List<String> courseActivityDataList = Arrays.asList(line.split(";"));
            CourseActivityDao tempCourseActivityDao = new CourseActivityDao();
            tempCourseActivityDao.setCourseDao(defaultCourseService.getCourseByCourseCode(courseCodeAndNameMapper(courseActivityDataList.get(8)).get(0)));
            tempCourseActivityDao.setCourseCode(courseCodeAndNameMapper(courseActivityDataList.get(8)).get(0));
            tempCourseActivityDao.setCourseName(courseCodeAndNameMapper(courseActivityDataList.get(8)).get(1));
            tempCourseActivityDao.setActivityType(courseActivityTypeMapper(courseActivityDataList.get(6)));
            tempCourseActivityDao.setDay(dayMapper(courseActivityDataList.get(0)));
            String startTime = timeIntervalMapper(courseActivityDataList.get(1)).get(0);
            String endTime = timeIntervalMapper(courseActivityDataList.get(1)).get(1);
            tempCourseActivityDao.setStartTime((startTime));
            tempCourseActivityDao.setEndTime((endTime));
            //TODO: add roomDao
            tempCourseActivityDao.setOnline(courseActivityDataList.get(3).equals("Online"));
            if(courseActivityDataList.get(2)==null)
            {
                tempCourseActivityDao.setWeekly(true);
            }
            else
            {
                if(courseActivityDataList.get(2).contains("1"))
                {
                    tempCourseActivityDao.setWeekly(false);
                    tempCourseActivityDao.setWeek(false);
                }
                else
                {
                    tempCourseActivityDao.setWeekly(false);
                    tempCourseActivityDao.setWeek(true);
                }
            }

            tempCourseActivityDao.setFormula(courseActivityDataList.get(5));

            defaultCourseActivityService.updateAndAddCourseActivity(tempCourseActivityDao);
        }
    }

    public void initialiseData() throws IOException {
        loadCourseData();
        loadCourseActivityData();
        loadStudentData();
        loadProfessorData();
        loadStudentHousingData();
    }

}
