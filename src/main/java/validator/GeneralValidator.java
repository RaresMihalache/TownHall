package validator;

import org.apache.commons.lang3.math.NumberUtils;

public class GeneralValidator {

    public  GeneralValidator(){

    }

    /**
     *
     * @param email
     * @param username
     * @param password
     * @param passwordConfirmation
     * @return 1 -> success
     *        -1 -> passwords don't match
     *        -2 -> username is empty
     *        -3 -> password is empty
     *        -4 -> wrong email format
     */
    public static final int registerValidation(String email, String username, String password, String passwordConfirmation){
        if(email.isEmpty() == true) return -4;

        char emailStartingChar = email.charAt(0);
        if(((emailStartingChar >= 'A' && emailStartingChar <= 'Z')  ||
                (emailStartingChar >= 'a' && emailStartingChar <= 'z'))
                && email.contains("@") && email.contains(".") &&
                email.isEmpty() == false) {

            if (password.isEmpty() == false) {
                if (username.isEmpty() == false) {
                    if (password.compareTo(passwordConfirmation) == 0) return 1; // success
                    else return -1; // error, passwords don't match
                }
                else return -2; // error, username is empty
            }
            else return -3; // error, password is empty
        }

        return -4; // error, wrong email format
    }

    /**
     *
     * @param street
     * @param no
     * @param bl
     * @param ap
     * @return -1 -> no is not in integer format
     *         -2 -> ap is not in integer format
     *         1  -> success
     */
    public static final int addHouseValidation(String street, String no, String bl, String ap){
        int noInt = NumberUtils.toInt(no, 0);
        int apInt = NumberUtils.toInt(ap, 0);
        if(noInt == 0)
            return -1;
        else if(apInt == 0)
            return -2;
        return 1;
    }
}
