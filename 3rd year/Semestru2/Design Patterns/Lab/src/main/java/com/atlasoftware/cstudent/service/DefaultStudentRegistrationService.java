package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.LocationDao;
import com.atlasoftware.cstudent.domain.RoomDao;
import com.atlasoftware.cstudent.domain.StudentDao;
import com.atlasoftware.cstudent.service.exceptions.StudentRegistrationException;
import com.atlasoftware.cstudent.utils.StudentRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("defaultStudentRegistrationService")
public class DefaultStudentRegistrationService implements StudentRegistrationService {


    @Autowired
    DefaultUserService userService;
    @Autowired
    DefaultLocationService locationRepositoryService;
    @Autowired
    DefaultRoomService roomRepositoryService;


    private Boolean validateEmailByRegex(String email) {

        Pattern pattern = Pattern.compile("([A-Za-z]+[.]{1}+[A-Za-z]+)+@stud.ubbcluj.ro");
        Matcher matcher = pattern.matcher(email);
        Boolean validated = matcher.matches();
        return validated;
    }

    private Boolean validateEmailByName(String firstName, String lastName, String email) {
        String[] parts = email.split("@");
        String[] names = parts[0].split("\\.");
        Boolean validLastName = false;
        if (names[names.length - 1].equals(lastName))
            validLastName = true;
        if (validLastName == false)
            return false;
        for (int i = 0; i <= names.length - 2; i++) {
            if (firstName.contains(names[i]) == false) {
                return false;
            }
        }

        return true;
    }

    private Boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcher = pattern.matcher(password);
        Boolean validated = matcher.matches();
        return validated;
    }

    private Boolean validateRegistrationNumber(Integer registrationNumber) {
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(registrationNumber.toString());
        Boolean validated = matcher.matches();
        return validated;
    }

    private Boolean validateRoomNumber(Integer roomNumber) {
        if (roomNumber == null) {
            return true;
        }
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(roomNumber.toString());
        Boolean validated = matcher.matches();
        return validated;
    }

    private Boolean validateLocation(String studentHousingName, Integer roomNumber) throws StudentRegistrationException {
        if (studentHousingName == null) {
            return true;
        }
        if (validateRoomNumber(roomNumber) == false) {
            throw new StudentRegistrationException("Numarul camerei nu exista!");
        }
        LocationDao location = locationRepositoryService.findByLocationName(studentHousingName);
        if (location != null) {
            if (roomNumber / 100 <= location.getTotalNumberOfFloors() && roomNumber > 0) {
                if (roomNumber % 100 <= location.getTotalRoomNumberByFloor() - 1) {
                    return true;
                }
                throw new StudentRegistrationException("Numarul camerei nu exista!");
            }
            throw new StudentRegistrationException("Numarul camerei nu exista!");

        }
        throw new StudentRegistrationException("Caminul nu exista!");
    }


    private Boolean basicDataValidation(StudentRegistrationForm studentRegistrationForm) throws StudentRegistrationException {

        if (validateEmailByRegex(studentRegistrationForm.getEmail())) {
            if (validateEmailByName(studentRegistrationForm.getFirstName(), studentRegistrationForm.getLastName(), studentRegistrationForm.getEmail())) {
                if (validatePassword(studentRegistrationForm.getPassword())) {
                    if (validateRegistrationNumber(studentRegistrationForm.getRegistrationNumber())) {
                        if (studentRegistrationForm.getStudentHousingName() != null) {
                            if (validateLocation(studentRegistrationForm.getStudentHousingName(), studentRegistrationForm.getRoomNumber())) {
                                return true;
                            }
                        } else {
                            return true;
                        }

                    }
                }
            }
        }
        return false;
    }

    private Boolean dbDataValidation(StudentRegistrationForm studentRegistrationForm) {


        if (studentRegistrationForm.getEmail().equals(userService.findByRegistrationNumber(studentRegistrationForm.getRegistrationNumber()).getEmail())) {
            if (studentRegistrationForm.getLastName().equals(userService.findByRegistrationNumber(studentRegistrationForm.getRegistrationNumber()).getLastName()))
                ;
            {
                if (studentRegistrationForm.getFirstName().equals(userService.findByRegistrationNumber(studentRegistrationForm.getRegistrationNumber()).getFirstName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean validateRegistrationForm(StudentRegistrationForm studentRegistrationForm) throws StudentRegistrationException {
        Boolean successfulRegistration = false;
        String message;
        if (basicDataValidation(studentRegistrationForm)) {
            if (dbDataValidation(studentRegistrationForm)) {
                populateStudentTable(studentRegistrationForm);
                successfulRegistration = true;
            }

        } else {
            throw new StudentRegistrationException("Ati introdus date invalide");
        }

        return successfulRegistration;
    }

    private StudentDao populateStudentTable(StudentRegistrationForm studentRegistrationForm) {
        StudentDao oldStudentInfo = userService.findByRegistrationNumber(studentRegistrationForm.getRegistrationNumber());
        if (studentRegistrationForm.getStudentHousingName() == null) {
            oldStudentInfo.setSemigroup(studentRegistrationForm.isSemigroup());
            oldStudentInfo.setPassword(studentRegistrationForm.getPassword());
            oldStudentInfo.setLocationDao(null);
            oldStudentInfo.setRoomDao(null);
            return userService.updateStudentData(oldStudentInfo);
        }
        RoomDao newRoomInfo;
        if(roomRepositoryService.findByNameAndLocation(studentRegistrationForm.getRoomNumber().toString(),locationRepositoryService.findByLocationName(studentRegistrationForm.getStudentHousingName()))!=null)
        {
            newRoomInfo = roomRepositoryService.findByNameAndLocation(studentRegistrationForm.getRoomNumber().toString(),locationRepositoryService.findByLocationName(studentRegistrationForm.getStudentHousingName()));
        }
        else
        {
            newRoomInfo = new RoomDao(UUID.randomUUID(),locationRepositoryService.findByLocationName(studentRegistrationForm.getStudentHousingName()),studentRegistrationForm.getRoomNumber().toString(),null,null);
        }

        roomRepositoryService.save(newRoomInfo);
        oldStudentInfo.setSemigroup(studentRegistrationForm.isSemigroup());
        oldStudentInfo.setPassword(studentRegistrationForm.getPassword());
        oldStudentInfo.setLocationDao(locationRepositoryService.findByLocationName(studentRegistrationForm.getStudentHousingName()));
        oldStudentInfo.setRoomDao(roomRepositoryService.findByNameAndLocation(newRoomInfo.getName(),newRoomInfo.getLocationDao()));
        return userService.updateStudentData(oldStudentInfo);
    }
}
