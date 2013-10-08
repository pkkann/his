/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author patrick
 */
public interface HismHandler {

    /**
     * No Error
     */
    public static final int NO_ERROR = 0;
    /**
     * Will be send if some required fields are empty
     */
    public static final int FIELDS_NOT_FILLED_ERROR = 1;
    /**
     * Will be send if the birthday date format is wrong
     */
    public static final int BIRTHDAY_FORMAT_ERROR = 2;
    /**
     * Will be send if the expiration date format is wrong
     */
    public static final int EXPIRATION_FORMAT_ERROR = 3;
    /**
     * Will be send if the expiration date can not be used
     */
    public static final int EXPIRATION_DATE_ERROR = 4;
    /**
     * Will be send if the picture path is empty, and should not be
     */
    public static final int PICTUREPATH_EMPTY_ERROR = 5;
    /**
     * Will be send if a get failed somewhere
     */
    public static final int GET_ERROR = 6;
    /**
     * Will be send if you try to create a guest, but the number of guests allowed are reached
     */
    public static final int TOO_MANY_GUESTS = 7;
    /**
     * Will be send if the quarantine expiration date format is wrong
     */
    public static final int QUARANTINE_FORMAT_ERROR = 8;
    /**
     * Will be send if the username is already in use
     */
    public static final int USERNAME_NOT_FREE_ERROR = 9;
    /**
     * Will be send if the password is not long enough
     */
    public static final int PASSWORD_NOT_LONG_ENOUGH_ERROR = 10;
}
