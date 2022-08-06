package com.atlasoftware.cstudent.service;

import com.atlasoftware.cstudent.domain.StudentDao;
import com.atlasoftware.cstudent.service.exceptions.LoginException;
import com.atlasoftware.cstudent.service.exceptions.StudentRegistrationException;
import com.atlasoftware.cstudent.utils.ForgotPasswordForm;
import com.atlasoftware.cstudent.utils.LoginForm;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("defaultLoginService")
public class DefaultLoginService implements LoginService{
    @Autowired
    DefaultUserService userService;


    private Boolean validateEmailByRegex(String email) {

        Pattern pattern = Pattern.compile("([A-Za-z]+[.]{1}+[A-Za-z]+)+@stud.ubbcluj.ro");
        Matcher matcher = pattern.matcher(email);
        Boolean validated = matcher.matches();
        return validated;
    }

    private Boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcher = pattern.matcher(password);
        Boolean validated = matcher.matches();
        return validated;
    }

    private Boolean basicDataValidation(LoginForm loginForm) {
        if (validateEmailByRegex(loginForm.getEmail())) {
            if (validatePassword(loginForm.getPassword())) {
                return true;
            }
        }
        return false;
    }

    private Boolean dbDataValidation(LoginForm loginForm) {
        if (userService.findByEmail(loginForm.getEmail()) != null) {
            if (userService.findByEmail(loginForm.getEmail()).getPassword().equals(loginForm.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public Boolean validateLoginForm(LoginForm loginForm) {
        Boolean successfulLogin = false;
        String message;
        if(loginForm.getEmail().contains("math.ubbcluj.ro"))
        {
            successfulLogin = true;
            return successfulLogin;
        }

        if(loginForm.getEmail().contains("cs.ubbcluj.ro"))
        {
            successfulLogin = true;
            return successfulLogin;
        }
        try {
            if (basicDataValidation(loginForm)) {
                if (dbDataValidation(loginForm)) {
                    successfulLogin = true;
                    message = "Te-ai logat cu succes!";
                } else {
                    throw new LoginException("Ati introdus e-mailul sau parola gresita!");
                }
            } else {
                throw new LoginException("Ati introdus date gresite!");
            }
        } catch (LoginException e) {
            message = "Nu te-ai putut conecta!" + e.getMessage();
        }
        notifyFrontEnd(successfulLogin, message);
        return successfulLogin;
    }

    public Boolean forgotPassword(ForgotPasswordForm forgotPasswordForm) throws LoginException {
        Boolean successfulReset=false;
        String message = null;
        try
        {
            if(validateEmailByRegex(forgotPasswordForm.getEmail()))
            {
                if(userService.findByEmail(forgotPasswordForm.getEmail())!=null)
                {
                    StudentDao oldStudentInfo = userService.findByEmail(forgotPasswordForm.getEmail());
                    String newPassword = generateNewUserPassword().toString();
                    oldStudentInfo.setPassword(newPassword);
                    userService.updateStudentData(oldStudentInfo);
                    Boolean emailVerified = verifiedEmail();
                    //TODO: if the page has been accessed, receive message true, emailVerified becomes true
                    if(emailVerified)
                    {
                        successfulReset=true;
                        message = "Ti-ai resetat parola cu succes! Vei primi un mail cu o parola generata automat.";
                    }
                    else
                    {
                        throw new LoginException("Nu ati verificat adresa de e-mail");
                    }
                }
                else
                {
                    throw new LoginException("Adresa de email "+forgotPasswordForm.getEmail()+" nu exista!");
                }
            }
        }catch (LoginException e)
        {
            message = "Resetarea parolei a esuat: "+e.getMessage();
        }
        notifyFrontEnd(successfulReset,message);
        return successfulReset;
    }

    private Boolean verifiedEmail() {
        return false;
    }

    private Boolean notifyFrontEnd(Boolean successfulLogin, String message) {
        return false;
    }

    private StringBuilder generateNewUserPassword() {
        SecureRandom secureRandom = new SecureRandom();
        int randomInt = 0;
        int generatedPasswordLength = 10;
        char[] acceptedCharacters = new char[]{'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h', 'I', 'i',
                'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't', 'U', 'u', 'V', 'v',
                'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= generatedPasswordLength - 1; i++) {
            randomInt = secureRandom.nextInt(acceptedCharacters.length);
            stringBuilder.append(acceptedCharacters[randomInt]);
        }
        return stringBuilder;
    }

    private Boolean sendVerificationEmail(String email, String newPassword) {
        return false;
    }
}


