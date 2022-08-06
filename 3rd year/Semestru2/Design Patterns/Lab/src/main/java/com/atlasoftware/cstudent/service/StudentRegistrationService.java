package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.LocationDao;
import com.atlasoftware.cstudent.domain.StudentDao;
import com.atlasoftware.cstudent.service.exceptions.StudentRegistrationException;
import com.atlasoftware.cstudent.utils.StudentRegistrationForm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface StudentRegistrationService
{
    private Boolean validateEmailByRegex(String email) {
        return null;
    }

    private Boolean validateEmailByName(String firstName, String lastName, String email) {
        return null;
    }

    private Boolean validatePassword(String password) {
        return null;
    }

    private Boolean validateRegistrationNumber(Integer registrationNumber) {
        return null;
    }

    private Boolean validateRoomNumber(Integer roomNumber) {
        return null;
    }

    private Boolean validateLocation(String studentHousingName, Integer roomNumber) throws StudentRegistrationException {
        return null;
    }


    private Boolean basicDataValidation(StudentRegistrationForm studentRegistrationForm) throws StudentRegistrationException {
        return null;
    }

    private Boolean dbDataValidation(StudentRegistrationForm studentRegistrationForm) {
        return null;
    }

    public Boolean validateRegistrationForm(StudentRegistrationForm studentRegistrationForm) throws StudentRegistrationException;

    private Boolean verifiedEmail() {
        return null;
    }

    private Boolean sendVerificationEmail(String email) {
        return null;
    }

    private Boolean notifyFrontEnd(Boolean successfulRegistration, String message) {
        return null;
    }

    private StudentDao populateStudentTable(StudentRegistrationForm studentRegistrationForm) {
        return null;
    }
}
