package com.pss.booking.Utils;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by Manu on 9/9/2016.
 */
public class ValidationUtils {

//    public static boolean isValidEmailAddress(String mEmail){
//
//        return true;
//    }
//
//
//    public static boolean isValidPassword(String mPassword){
//
//        return true;
//    }
//
//    public static boolean isValidPhoneNumber(String mPhonenumber){
//
//        return true;
//    }


    /**
     * Default constructor
     */
    private ValidationUtils() {

    }

    public static boolean validatePassword(String value) {
        if (StringUtils.isBlank(value)) {
            return false;
        }
//
//        if (!StringUtils.checkPattern("^[0-9a-zA-Z][\\w+_.-]*@\\w[\\w+_.-]*\\.[a-zA-Z]{2,9}$", value))
//        {
//            return false;
//        }

        return true;
    }

    /**
     * Validate Phonenumber.
     *
     * @param value value
     * @return true is Phonenumber is valid
     */
    public static boolean validatePhoneNumber(String value) {
        if (StringUtils.isBlank(value)) {
            return true;
        }

        if (!StringUtils.checkPattern("^[0-9a-zA-Z][\\w+_.-]*@\\w[\\w+_.-]*\\.[a-zA-Z]{2,9}$", value)) {
            return false;
        }

        return true;
    }


    public static boolean isValidMobile(String mobile) {
        if (!TextUtils.isEmpty(mobile)) {
            return Patterns.PHONE.matcher(mobile).matches();
        }
        return false;
    }


    /**
     * Validate Email.
     *
     * @param value value
     * @return true is email is valid
     */
    public static boolean validateEmail(String value) {
        if (StringUtils.isBlank(value)) {
            return false;
        }

        if (!StringUtils.checkPattern("^[0-9a-zA-Z][\\w+_.-]*@\\w[\\w+_.-]*\\.[a-zA-Z]{2,9}$", value)) {
            return false;
        }

        return true;
    }

    /**
     * Validate name.
     *
     * @param name name
     * @return true is name is valid
     */
    public static boolean validateName(String name) {
        boolean validate = true;
        if (StringUtils.isBlank(name)) {
            validate = false;
        } else if (name.length() < 2) {
            validate = false;
        } else if (!StringUtils.checkPattern("^(?![0-9])[a-z A-Z]{1,}$", name)) {
            validate = false;
        }
        return validate;
    }

    /**
     * Validate initials.
     *
     * @param initials initials
     * @return true is initials is valid
     */
    public static boolean validateInitials(String initials) {
        boolean validate = true;
        if (StringUtils.isBlank(initials)) {
            validate = false;
        } else if (!StringUtils.checkPattern("^(?![0-9])[a-z .A-Z]{1,}$", initials)) {
            validate = false;
        }
        return validate;
    }

    /**
     * Validate prefix.
     *
     * @param prefix prefix
     * @return true is prefix is valid
     */
    public static boolean validatePrefix(String prefix) {
        boolean validate = true;
        if (StringUtils.isNotBlank(prefix) && !StringUtils.checkPattern("^(?![0-9])[a-z A-Z]{1,}$", prefix)) {
            validate = false;
        }
        return validate;
    }

    /**
     * Validate surname.
     *
     * @param surname surname
     * @return true is email is surname
     */
    public static boolean validateSurname(String surname) {
        boolean validate = true;
        if (StringUtils.isBlank(surname)) {
            validate = false;
        } else if (surname.length() < 2) {
            validate = false;
        } else if (!StringUtils.checkPattern("^(?![0-9])[a-zA-Z ]{1,}$", surname)) {
            validate = false;
        }
        return validate;
    }

    /**
     * Validate birthDate.
     *
     * @param birthDate birthDate
     * @return true is birthDate is valid
     */
    public static boolean validateBirthdate(String birthDate) {
        boolean validate = true;
        if (StringUtils.isBlank(birthDate)) {
            validate = false;
        }
        return validate;

    }

    /**
     * Validate address.
     *
     * @param address address
     * @return true is address is valid
     */
    public static boolean validateAddress(String address) {
        boolean validate = true;
        if (StringUtils.isBlank(address)) {
            validate = false;
        } else if (address.length() < 2) {
            validate = false;
        } else if (!StringUtils.checkPattern("^(?![0-9])[a-zA-Z ]{1,}$", address)) {
            validate = false;
        }
        return validate;
    }

    /**
     * Validate houseNumber.
     *
     * @param houseNumber houseNumber
     * @return true is email is houseNumber
     */
    public static boolean validateNumber(String houseNumber) {
        boolean validate = true;
        if (StringUtils.isBlank(houseNumber)) {
            validate = false;
        } else if (!StringUtils.checkPattern("^(?![A-Za-z])[1-9]{1}[0-9]{0,}$", houseNumber)) {
            validate = false;
        }
        return validate;
    }

    /**
     * Validate zipcode.
     *
     * @param zipCode zipcode
     * @return true is zipcode is valid
     */
    public static boolean validateZipcode(String zipCode) {
        boolean validate = true;
        if (StringUtils.isBlank(zipCode)) {
            validate = false;
        } else if (!StringUtils.checkPattern("^[0-9]{4}[a-z|A-Z]{2}$", zipCode)) {
            validate = false;
        }
        return validate;
    }

    /**
     * Validate city.
     *
     * @param city city
     * @return true is city is valid
     */
    public static boolean validateCity(String city) {
        boolean validate = true;
        if (StringUtils.isBlank(city)) {
            validate = false;
        } else if (city.length() < 2) {
            validate = false;
        } else if (!StringUtils.checkPattern("^(?![0-9])[a-zA-Z ]{1,}$", city)) {
            validate = false;
        }
        return validate;
    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private static boolean validationPhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
            //validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
            //return false if nothing matches the input
        else return false;

    }
}
