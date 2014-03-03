/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import static control.HismHandlerIF.BIRTHDAY_FORMAT_ERROR;
import static control.HismHandlerIF.EXPIRATION_DATE_ERROR;
import static control.HismHandlerIF.EXPIRATION_FORMAT_ERROR;
import static control.HismHandlerIF.FIELDS_NOT_FILLED_ERROR;
import entity.Enrollment;
import entity.Guest;
import entity.Person;
import file.FileTool;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import model.EnrollmentRegister;
import model.PersonRegister;

/**
 *
 * @author patrick
 */
public class PersonHandler implements HismHandlerIF {

    private PersonRegister peR;
    private EnrollmentRegister enR;

    public PersonHandler(PersonRegister peR, EnrollmentRegister enR) {
        this.peR = peR;
        this.enR = enR;
    }

    /**
     * Create a person
     *
     * @param firstname
     * @param middlename
     * @param lastname
     * @param address
     * @param email
     * @param phone
     * @param birthdayDate (DD/MM/YYYY)
     * @param expirationDate (MM/YYYY)
     * @param creationDate (DDMMYYYY)
     * @param hoene
     * @param reserve
     * @param oneOne
     * @param picturePath
     * @return Error code : Integer
     */
    public int createPerson(String firstname, String middlename, String lastname, String address, String email, String phone, String birthdayDate, String expirationDate, String creationDate, boolean hoene, boolean reserve, boolean oneOne, String picturePath) {

        // Check fields are filled
        if (!hoene && !reserve && !oneOne) {
            if (firstname.isEmpty() || lastname.isEmpty() || address.isEmpty() || birthdayDate.isEmpty() || expirationDate.isEmpty() || creationDate.isEmpty()) {
                return FIELDS_NOT_FILLED_ERROR;
            }
        } else {
            if (firstname.isEmpty() || lastname.isEmpty() || address.isEmpty() || birthdayDate.isEmpty() || creationDate.isEmpty()) {
                return FIELDS_NOT_FILLED_ERROR;
            }
        }

        // Check birthday is written correctly
        try {

            String[] birth_Split = birthdayDate.split("/");
            String birth_Day = birth_Split[0];
            String birth_Month = birth_Split[1];
            String birth_Year = birth_Split[2];
            try {
                String s = birth_Day + birth_Month + birth_Year;
                int i = Integer.valueOf(s);
            } catch (NumberFormatException ex) {
                return BIRTHDAY_FORMAT_ERROR;
            }

            if (birth_Day.length() != 2 || birth_Month.length() != 2 || birth_Year.length() != 4) {
                return BIRTHDAY_FORMAT_ERROR;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            return BIRTHDAY_FORMAT_ERROR;
        }

        // Check expiration is written correctly
        if (!hoene && !reserve && !oneOne) {

            String expire_Month;
            String expire_Year;
            try {
                String[] expire_Split = expirationDate.split("/");
                expire_Month = expire_Split[0];
                expire_Year = expire_Split[1];
                try {
                    String s = expire_Month + expire_Year;
                    int i = Integer.valueOf(s);
                } catch (NumberFormatException ex) {
                    return EXPIRATION_FORMAT_ERROR;
                }

                if (expire_Month.length() != 2 || expire_Year.length() != 4) {
                    return EXPIRATION_FORMAT_ERROR;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                return EXPIRATION_FORMAT_ERROR;
            }

            Calendar today = Calendar.getInstance();
            today.set(Calendar.MONTH, today.get(Calendar.MONTH) + 1);
            Calendar expire = Calendar.getInstance();
            expire.set(Calendar.MONTH, Integer.valueOf(expire_Month));
            expire.set(Calendar.YEAR, Integer.valueOf(expire_Year));
            if (expire.before(today) || expire.equals(today)) {
                return EXPIRATION_DATE_ERROR;
            }
        }

        // Check picturepath
        boolean copyPic = false;
        if (picturePath.isEmpty()) {
            return PICTUREPATH_EMPTY_ERROR;
        } else if (!picturePath.equals("N")) {
            copyPic = true;
        }

        // Check phone number format
        try {
            if (!phone.isEmpty()) {
                int i = Integer.valueOf(phone);
            }
        } catch (NumberFormatException ex) {
            return PHONE_FORMAT_ERROR;
        }

        // Create person
        Person p = new Person(firstname, middlename, lastname, address, email, phone, birthdayDate, expirationDate, creationDate, hoene, reserve, oneOne, picturePath);

        // Register person
        Serializable sz = peR.registerPerson(p);

        // Copy picture
        if (copyPic) {
            String oldPicturePath = picturePath;
            String newPicturePath = his.His.picDir + "/persons/" + (Integer) sz + "/face.jpg";
            FileTool.copyFile(new File(oldPicturePath), new File(newPicturePath));
            p.setPicturePath(newPicturePath);
            peR.savePerson(p);
        }

        return NO_ERROR;
    }

    /**
     * Save a person
     *
     * @param personID
     * @param firstname
     * @param middlename
     * @param lastname
     * @param address
     * @param email
     * @param phone
     * @param birthdayDate (DD/MM/YYYY)
     * @param expirationDate
     * @param reserve
     * @param hoene
     * @param oneOne
     * @param picturePath
     * @return Error code : Integer
     */
    public int savePerson(int personID, String firstname, String middlename, String lastname, String address, String email, String phone, String birthdayDate, String expirationDate, boolean hoene, boolean reserve, boolean oneOne, String picturePath) {

        // Check fields are filled
        if (!hoene && !reserve && !oneOne) {
            if (firstname.isEmpty() || lastname.isEmpty() || address.isEmpty() || birthdayDate.isEmpty() || expirationDate.isEmpty() || picturePath.isEmpty()) {
                return FIELDS_NOT_FILLED_ERROR;
            }
        } else if (firstname.isEmpty() || lastname.isEmpty() || address.isEmpty() || birthdayDate.isEmpty() || picturePath.isEmpty()) {
            return FIELDS_NOT_FILLED_ERROR;
        }

        // Check birthday is written correctly
        try {

            String[] birth_Split = birthdayDate.split("/");
            String birth_Day = birth_Split[0];
            String birth_Month = birth_Split[1];
            String birth_Year = birth_Split[2];
            try {
                String s = birth_Day + birth_Month + birth_Year;
                int i = Integer.valueOf(s);
            } catch (NumberFormatException ex) {
                return BIRTHDAY_FORMAT_ERROR;
            }

            if (birth_Day.length() != 2 || birth_Month.length() != 2 || birth_Year.length() != 4) {
                return BIRTHDAY_FORMAT_ERROR;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            return BIRTHDAY_FORMAT_ERROR;
        }

        // Check expiration is written correctly
        if (!hoene && !reserve && !oneOne) {

            String expire_Month;
            String expire_Year;
            try {
                String[] expire_Split = expirationDate.split("/");
                expire_Month = expire_Split[0];
                expire_Year = expire_Split[1];
                try {
                    String s = expire_Month + expire_Year;
                    int i = Integer.valueOf(s);
                } catch (NumberFormatException ex) {
                    return EXPIRATION_FORMAT_ERROR;
                }

                if (expire_Month.length() != 2 || expire_Year.length() != 4) {
                    return EXPIRATION_FORMAT_ERROR;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                return EXPIRATION_FORMAT_ERROR;
            }
        }

        // Check picturepath
        boolean copyPic = false;
        if (picturePath.isEmpty()) {
            return PICTUREPATH_EMPTY_ERROR;
        } else if (!picturePath.equals("N")) {
            copyPic = true;
        }

        // Check phone number format
        try {
            if (!phone.isEmpty()) {
                int i = Integer.valueOf(phone);
            }
        } catch (NumberFormatException ex) {
            return PHONE_FORMAT_ERROR;
        }

        // Set person
        Person p = peR.getPerson(personID);
        if (p == null) {
            return GET_ERROR;
        } else {
            p.setFirstname(firstname);
            p.setMiddlename(middlename);
            p.setLastname(lastname);
            p.setAddress(address);
            p.setEmail(email);
            p.setPhone(phone);
            p.setBirthdayDate(birthdayDate);
            p.setExpirationDate(expirationDate);
            p.setHoene(hoene);
            p.setReserve(reserve);
            p.setOneOne(oneOne);
            p.setPicturePath(picturePath);
        }

        // Copy picture
        if (copyPic) {
            String oldPicturePath = picturePath;
            String newPicturePath = his.His.picDir + "/persons/" + (Integer) personID + "/face.jpg";
            FileTool.copyFile(new File(oldPicturePath), new File(newPicturePath));
            p.setPicturePath(newPicturePath);
        } else {
            String newPicturePath = his.His.picDir + "/persons/" + (Integer) personID + "/face.jpg";
            FileTool.deleteFile(new File(newPicturePath));
        }

        // Register person
        peR.savePerson(p);

        return NO_ERROR;
    }

    /**
     * Save a person
     *
     * @param personID
     * @param firstname
     * @param middlename
     * @param lastname
     * @param address
     * @param birthdayDate (DD/MM/YYYY)
     * @param expirationDate
     * @param reserve
     * @param hoene
     * @param oneOne
     * @return Error code : Integer
     */
    public int savePerson(int personID, String firstname, String middlename, String lastname, String address, String email, String phone, String birthdayDate, String expirationDate, boolean hoene, boolean reserve, boolean oneOne) {

        // Check fields are filled
        if (!hoene && !reserve && !oneOne) {
            if (firstname.isEmpty() || lastname.isEmpty() || address.isEmpty() || birthdayDate.isEmpty() || expirationDate.isEmpty()) {
                return FIELDS_NOT_FILLED_ERROR;
            }
        } else if (firstname.isEmpty() || lastname.isEmpty() || address.isEmpty() || birthdayDate.isEmpty()) {
            return FIELDS_NOT_FILLED_ERROR;
        }

        // Check birthday is written correctly
        try {

            String[] birth_Split = birthdayDate.split("/");
            String birth_Day = birth_Split[0];
            String birth_Month = birth_Split[1];
            String birth_Year = birth_Split[2];
            try {
                String s = birth_Day + birth_Month + birth_Year;
                int i = Integer.valueOf(s);
            } catch (NumberFormatException ex) {
                return BIRTHDAY_FORMAT_ERROR;
            }

            if (birth_Day.length() != 2 || birth_Month.length() != 2 || birth_Year.length() != 4) {
                return BIRTHDAY_FORMAT_ERROR;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            return BIRTHDAY_FORMAT_ERROR;
        }

        // Check expiration is written correctly
        if (!hoene && !reserve && !oneOne) {

            String expire_Month;
            String expire_Year;
            try {
                String[] expire_Split = expirationDate.split("/");
                expire_Month = expire_Split[0];
                expire_Year = expire_Split[1];
                try {
                    String s = expire_Month + expire_Year;
                    int i = Integer.valueOf(s);
                } catch (NumberFormatException ex) {
                    return EXPIRATION_FORMAT_ERROR;
                }

                if (expire_Month.length() != 2 || expire_Year.length() != 4) {
                    return EXPIRATION_FORMAT_ERROR;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                return EXPIRATION_FORMAT_ERROR;
            }
        }

        // Check phone number format
        try {
            if (!phone.isEmpty()) {
                int i = Integer.valueOf(phone);
            }
        } catch (NumberFormatException ex) {
            return PHONE_FORMAT_ERROR;
        }

        // Set person
        Person p = peR.getPerson(personID);
        if (p == null) {
            return GET_ERROR;
        } else {
            p.setFirstname(firstname);
            p.setMiddlename(middlename);
            p.setLastname(lastname);
            p.setAddress(address);
            p.setEmail(email);
            p.setPhone(phone);
            p.setBirthdayDate(birthdayDate);
            p.setExpirationDate(expirationDate);
            p.setHoene(hoene);
            p.setReserve(reserve);
            p.setOneOne(oneOne);
        }

        // Register person
        peR.savePerson(p);

        return NO_ERROR;
    }

    /**
     * Remove a person
     *
     * @param personID
     * @return Error code : Integer
     */
    public int removePerson(int personID) {
        Person p = peR.getPerson(personID);

        if (p == null) {
            return GET_ERROR;
        } else {
            FileTool.deleteFile(new File(p.getPicturePath()));
            peR.deletePerson(p);
        }

        return NO_ERROR;
    }

    /**
     * Returns number of enrolled persons
     *
     * @return count : Integer
     */
    public int getEnrolledCount() {
        int count = 0;

        for (Enrollment en : enR.getEnrollments()) {
            count++;
            for (Guest g : en.getGuests()) {
                count++;
            }
        }

        return count;
    }

    /**
     * Returns a result based on name, address and birthday
     *
     * @param firstname
     * @param middlename
     * @param lastname
     * @param address
     * @param birthdayDate
     * @return data : ArrayList<String[]>
     */
    public ArrayList<String[]> searchPersonLon(String firstname, String middlename, String lastname, String address, String birthdayDate) {
        // Create collection
        ArrayList<String[]> data = new ArrayList<>();

        // Make full name
        String name = firstname + " " + middlename + " " + lastname;

        // Make iterator
        Iterator<Person> i = peR.getPersons().iterator();

        // Loop through and search
        while (i.hasNext()) {
            Person p = i.next();
            if ((p.getFirstname() + " " + p.getMiddlename() + " " + p.getLastname()).equalsIgnoreCase(name) || p.getAddress().equals(address) || p.getBirthdayDate().equals(birthdayDate)) {
                String[] dat = {p.getFirstname() + " " + p.getMiddlename() + " " + p.getLastname(), p.getAddress(), p.getBirthdayDate()};
                data.add(dat);
            }
        }

        return data;
    }

    /**
     * Returns a result based on a string
     *
     * @param search
     * @param onlyEnrolled
     * @return data : ArrayList<String[]>
     */
    public ArrayList<String[]> searchPerson(String search, boolean onlyEnrolled) {
        // Create collection
        ArrayList<String[]> data = new ArrayList<>();

        // Split string into words
        String[] sSplit = search.split(" ");

        // Do search
        if (search.isEmpty()) {
            for (Person p : peR.getPersons()) {
                String hoene = "Nej";
                if (p.isHoene()) {
                    hoene = "Ja";
                }
                String oneOne = "Nej";
                if (p.isOneOne()) {
                    oneOne = "Ja";
                }
                String reserve = "Nej";
                if (p.isReserve()) {
                    reserve = "Ja";
                }
                if (onlyEnrolled) {
                    Enrollment en = enR.getEnrollment(p.getIdPerson());
                    if (en != null) {
                        String[] dat = {String.valueOf(p.getIdPerson()), p.getFirstname() + " " + p.getMiddlename() + " " + p.getLastname(), p.getAddress(), p.getBirthdayDate(), p.getExpirationDate(), p.getCreationDate(), hoene, reserve, oneOne};
                        data.add(dat);
                    }
                } else {
                    String[] dat = {String.valueOf(p.getIdPerson()), p.getFirstname() + " " + p.getMiddlename() + " " + p.getLastname(), p.getAddress(), p.getBirthdayDate(), p.getExpirationDate(), p.getCreationDate(), hoene, reserve, oneOne};
                    data.add(dat);
                }

            }
        } else {
            Iterator<Person> i = peR.getPersons().iterator();

            while (i.hasNext()) {
                Person p = i.next();

                String birthday = p.getBirthdayDate().replaceAll("/", "");
                String expiration = p.getExpirationDate().replaceAll("/", "");
                String hoene = "Nej";
                if (p.isHoene()) {
                    hoene = "Ja";
                }
                String oneOne = "Nej";
                if (p.isOneOne()) {
                    oneOne = "Ja";
                }
                String reserve = "Nej";
                if (p.isReserve()) {
                    reserve = "Ja";
                }
                for (String s : sSplit) {
                    if (String.valueOf(p.getIdPerson()).equalsIgnoreCase(s) || p.getFirstname().equalsIgnoreCase(s) || p.getMiddlename().equalsIgnoreCase(s) || p.getLastname().equalsIgnoreCase(s) || p.getAddress().equalsIgnoreCase(s) || p.getBirthdayDate().equalsIgnoreCase(s) || p.getExpirationDate().equalsIgnoreCase(s) || birthday.equalsIgnoreCase(s) || expiration.equalsIgnoreCase(s)) {
                        if (onlyEnrolled) {
                            Enrollment en = enR.getEnrollment(p.getIdPerson());
                            if (en != null) {
                                String[] dat = {String.valueOf(p.getIdPerson()), p.getFirstname() + " " + p.getMiddlename() + " " + p.getLastname(), p.getAddress(), p.getBirthdayDate(), p.getExpirationDate(), p.getCreationDate(), hoene, reserve, oneOne};
                                data.add(dat);
                            }
                        } else {
                            String[] dat = {String.valueOf(p.getIdPerson()), p.getFirstname() + " " + p.getMiddlename() + " " + p.getLastname(), p.getAddress(), p.getBirthdayDate(), p.getExpirationDate(), p.getCreationDate(), hoene, reserve, oneOne};
                            data.add(dat);
                        }
                        break;
                    }
                }
            }
        }
        
        return data;
    }

    /**
     * Returns a person based on an Integer
     *
     * @param id
     * @return p : Person
     */
    public Person getPerson(int id) {
        Iterator<Person> i = peR.getPersons().iterator();
        while (i.hasNext()) {
            Person p = i.next();
            if (p.getIdPerson() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Returns whether a person is enrolled or not
     *
     * @param idPerson
     * @return boolean
     */
    public boolean isEnrolled(int idPerson) {
        Enrollment en = enR.getEnrollment(idPerson);
        if (en == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks all persons expiration dates, and sets there expired status
     * accordingly
     */
    public void checkExpirationDates() {
        Calendar current = Calendar.getInstance();

        Iterator<Person> i = peR.getPersons().iterator();

        while (i.hasNext()) {
            Person p = i.next();
            if (!p.getExpirationDate().isEmpty()) {
                String[] expireString = p.getExpirationDate().split("/");
                String month = expireString[0];
                String year = expireString[1];

                Calendar expiration = Calendar.getInstance();
                expiration.set(Calendar.MONTH, (Integer.valueOf(month) - 1));
                expiration.set(Calendar.YEAR, Integer.valueOf(year));
                expiration.set(Calendar.DATE, expiration.getActualMaximum(Calendar.DAY_OF_MONTH));

                if (current.after(expiration)) {
                    if (!this.isEnrolled(p.getIdPerson())) {
                        p.setExpired(true);
                    }
                } else {
                    if (!this.isEnrolled(p.getIdPerson())) {
                        p.setExpired(false);
                    }
                }
            }
        }
    }

    /**
     * Renews a person
     *
     * @param idPerson
     * @param newExpirationDate
     * @return Error code : Integer
     */
    public int renew(int idPerson, String newExpirationDate) {
        String expire_Month;
        String expire_Year;
        try {
            String[] expire_Split = newExpirationDate.split("/");
            expire_Month = expire_Split[0];
            expire_Year = expire_Split[1];

            if (expire_Month.length() != 2 || expire_Year.length() != 4) {
                return EXPIRATION_FORMAT_ERROR;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            return EXPIRATION_FORMAT_ERROR;
        }

        Calendar today = Calendar.getInstance();
        today.set(Calendar.MONTH, today.get(Calendar.MONTH) + 1);
        Calendar expire = Calendar.getInstance();
        expire.set(Calendar.MONTH, Integer.valueOf(expire_Month));
        expire.set(Calendar.YEAR, Integer.valueOf(expire_Year));
        if (expire.before(today) || expire.equals(today)) {
            return EXPIRATION_DATE_ERROR;
        }

        Person p = getPerson(idPerson);
        if (p == null) {
            return GET_ERROR;
        }

        p.setExpirationDate(newExpirationDate);
        p.setExpired(false);
        peR.savePerson(p);

        return NO_ERROR;
    }
}
