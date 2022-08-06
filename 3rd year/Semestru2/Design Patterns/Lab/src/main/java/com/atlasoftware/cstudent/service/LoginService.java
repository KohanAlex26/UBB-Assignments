package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.StudentDao;
import com.atlasoftware.cstudent.service.exceptions.LoginException;
import com.atlasoftware.cstudent.utils.ForgotPasswordForm;
import com.atlasoftware.cstudent.utils.LoginForm;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface LoginService {

    private Boolean validateEmailByRegex(String email) {
        return null;
    }

    private Boolean validatePassword(String password) {
        return null;
    }

    private Boolean basicDataValidation(LoginForm loginForm) {
        return null;
    }

    private Boolean dbDataValidation(LoginForm loginForm) {
        return null;
    }

    public Boolean validateLoginForm(LoginForm loginForm);

    public Boolean forgotPassword(ForgotPasswordForm forgotPasswordForm) throws LoginException;

    private Boolean verifiedEmail() {
        return null;
    }

    private Boolean notifyFrontEnd(Boolean successfulLogin, String message) {
        return null;
    }

    private StringBuilder generateNewUserPassword() {
        return null;
    }

    private Boolean sendVerificationEmail(String email, String newPassword) {
        return null;
    }
}
