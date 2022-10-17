package com.ideas2it.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import com.ideas2it.constant.Constants;

/**
 * Contain the validation part 
 *
 * @version 1.0 22-SEP-2022
 * @author Venkatesh TM
 */
public class ValidationUtil {
    
    /** 
     * Check the given email matches to the given format 
     * 
     * @param  email   email need to be validated 
     * @return boolean true if the given data matches the format else false
     */
    public boolean isValidEmail(String email) {
        return email.matches(Constants.EMAIL_FORMAT);
    }

    /** 
     * Check the given password matches to the given format 
     * 
     * @param  password password need to be validated 
     * @return boolean  true if the given data matches the format else false
     */
    public boolean isValidPassword(String password) {
        return password.matches(Constants.PASSWORD_FORMAT);
    }
    
    /** 
     * Check the given dateOfBith matches to the given format 
     * 
     * @param  dateOfBirth dateOfBirth need to be validated 
     * @return boolean     true if the given data matches the format else false
     */
    public boolean isValidDateOfBirth(String dateOfBirth) throws DateTimeParseException {
        LocalDate dob = LocalDate.parse(dateOfBirth);

        if (dob.toString().equals(dateOfBirth)) {
            return true;
        }
        return false;
    }
    
    /**
     * Check the given phoneNumber matches the given format
     *
     * @param phoneNumber phone number given by the user 
     * @return boolean    true if the given data matches the format else false
     */
    public boolean isValidPhoneNumber(String phoneNumber) { 
        return phoneNumber.matches(Constants.PHONE_NUMBER_FORMAT);
    }
    
    /**
     * Check the given name matches the given format
     *
     * @param  name    name given by the user 
     * @return boolean true if the given data matches the format else false
     */
    public boolean isValidName(String name) {
        return name.matches(Constants.NAME_FORMAT);
    }
    
    /**
     * Check the given userName matches the given format
     *
     * @param  userName  username given by the user
     * @return boolean   true if the given data matches the format else false
     */
    public boolean isValidUserName(String userName) { 
        return userName.matches(Constants.USERNAME_FORMAT);
    }
}