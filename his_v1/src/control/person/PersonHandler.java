/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.person;

import control.picture.PictureHandler;
import date.ADate;
import db.person.PersonDAO;
import file.FileTool;
import file.csv.CSVTool;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Guest;
import model.Person;

/**
 *
 * @author patrick
 */
public class PersonHandler {

    private PersonRegister personRegister;
    private PictureHandler pictureHandler;
    private PersonDAO personDAO;

    public PersonHandler(PersonRegister personRegister, PictureHandler pictureHandler, PersonDAO personDAO) {
        this.personDAO = personDAO;
        this.personRegister = personRegister;
        this.pictureHandler = pictureHandler;
    }

    public void removeGuest(Person p, int id) {
        p.removeGuest(p.getGuestByID(id));
        removeGuestFromDB(id);
    }

    public void removeAllGuests(Person p) {
        for (Guest g : p.getGuests()) {
            removeGuestFromDB(g.getId());
        }
        p.setGuests(new ArrayList<Guest>());
    }

    public void removeGuestFromDB(int id) {
        try {
            personDAO.removeGuest(id);
        } catch (SQLException ex) {
            Logger.getLogger(PersonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addGuestToPerson(Person p, String firstname, String middlename, String lastname, ADate birthday) {
        Guest g = new Guest(firstname, middlename, lastname, birthday, new ADate());
        p.addGuest(g);
        int gID = 0;
        try {
            gID = personDAO.saveGuest(p, g);
        } catch (SQLException ex) {
            Logger.getLogger(PersonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.setId(gID);
    }

    public void savePerson(Person p, boolean pictureChanged) {
        if (pictureChanged) {
            File newPicturePath = new File(FileTool.getDefaultFolder() + "\\personPicture" + pictureHandler.getpictureID() + ".jpg");
            FileTool.copyFile(p.getPicturePath(), newPicturePath);
            p.setPicturePath(newPicturePath);
            pictureHandler.insertPicture(newPicturePath);
        }
        try {
            personDAO.savePerson(p);
        } catch (SQLException ex) {
            Logger.getLogger(PersonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void populatePersonsFromDB() {
        try {
            ArrayList<Person> persons = personDAO.getAllPersons();
            if (!persons.isEmpty()) {
                personRegister.setPersons(persons);
                for (Person p : persons) {
                    pictureHandler.insertPicture(p.getPicturePath());
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Person getPerson(int id) {
        for (Person p : personRegister.getPersons()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void createPerson(String firstname, String middlename, String lastname, String address, ADate birthdayDate, ADate expirationDate, File picturePath, ADate creationDate, boolean oneOne, boolean hone) {
        Person p = new Person(firstname, middlename, lastname, address, birthdayDate, expirationDate, picturePath, creationDate, oneOne);
        p.setHone(hone);
        personRegister.add(p);
        File newPicturePath = new File(FileTool.getDefaultFolder() + "\\personPicture" + pictureHandler.getpictureID() + ".jpg");
        FileTool.copyFile(picturePath, newPicturePath);
        p.setPicturePath(newPicturePath);
        pictureHandler.insertPicture(newPicturePath);
        int id = 0;
        try {
            id = personDAO.insertPerson(p);
        } catch (SQLException ex) {
            Logger.getLogger(PersonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setId(id);
    }

    public void checkExpirationDates(ADate date) {
        ArrayList<Person> persons = personRegister.getPersons();
        //System.out.println(date);

        Calendar currentC = Calendar.getInstance();
        currentC.set(date.getYear(), date.getMonth(), date.getDay());

        for (Person p : persons) {
            Calendar personC = Calendar.getInstance();
            personC.set(p.getExpirationDate().getYear(), p.getExpirationDate().getMonth(), p.getExpirationDate().getDay());

            if (!p.isOneOne()) {
                if (currentC.after(personC)) {
                    p.setExpired(true);
                }
            }
        }
    }

    public ArrayList<Person> search(String s) {
        if (!s.isEmpty()) {
            ArrayList<Person> result = new ArrayList<>();

            for (Person p : personRegister.getPersons()) {
                if (p.getFirstname().equalsIgnoreCase(s) || p.getMiddlename().equalsIgnoreCase(s) || p.getLastname().equalsIgnoreCase(s) || (p.getFirstname() + " " + p.getMiddlename()).equalsIgnoreCase(s) || (p.getFirstname() + " " + p.getMiddlename() + " " + p.getLastname()).equalsIgnoreCase(s) || (p.getFirstname() + " " + p.getLastname()).equalsIgnoreCase(s)) {
                    result.add(p);
                } else if (p.getBirthdayDate().equals(s)) {
                    result.add(p);
                }
            }
            return result;
        } else {
            return personRegister.getPersons();
        }
    }

    public ArrayList<Person> getPersons() {
        return personRegister.getPersons();
    }

    public void setPersons(ArrayList<Person> persons) {
        personRegister.setPersons(persons);
    }

    public void removePerson(Person p) {
        personRegister.remove(p);
        try {
            personDAO.deletePerson(p);
        } catch (SQLException ex) {
            Logger.getLogger(PersonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generateReport(boolean resetReport) {
        System.out.println("GENRATING");
        ArrayList<Person> enrolled = new ArrayList<>();
        for (Person p : personRegister.getPersons()) {
            if (p.isEnrolled()) {
                enrolled.add(p);
            }
        }

        ArrayList<String[]> data = new ArrayList<>();
        
        if(resetReport) {
            String[] f = {"RESET REPORT"};
            data.add(f);
        }
        
        String[] f = {"Total: " + enrolled.size()};
        data.add(f);
        
        Calendar c = Calendar.getInstance();
        ADate date = new ADate();
        String hoursS = String.valueOf(c.get(Calendar.HOUR_OF_DAY)) ;
        String minutesS = String.valueOf(c.get(Calendar.MINUTE));
        
        if(hoursS.length() != 2) {
            hoursS = "0" + hoursS;
        }
        if(minutesS.length() != 2) {
            minutesS = "0" + minutesS;
        }
        int hours = Integer.valueOf(hoursS);
        int minutes = Integer.valueOf(minutesS);
        
        String time = "" + hours + minutes;
        String[] ftime = {"Date: " + ADate.formatADate(date, "/") + " Time: " + time};
        data.add(ftime);
        
        String[] columns = {"Navn", "Fødselsdag", "Gæst1", "Gæst2", "Gæst3", "Gæst4", "Gæst5"};
        data.add(columns);
        
        for (Person p : enrolled) {
            System.out.println("FOUND ONE");
            String hone = "Nej";
            if (p.isHone()) {
                hone = "Ja";
            }
            String gs1 = "";
            String gs2 = "";
            String gs3 = "";
            String gs4 = "";
            String gs5 = "";
            int gSize = p.getGuests().size();
            if (gSize == 1) {
                Guest g1 = p.getGuest(0);
                gs1 = g1.getFirstname() + " " + g1.getLastname() + ": " + ADate.formatADate(g1.getBirthday(), "/");
            } else if (gSize == 2) {
                Guest g1 = p.getGuest(0);
                Guest g2 = p.getGuest(1);
                gs1 = g1.getFirstname() + " " + g1.getLastname() + ": " + ADate.formatADate(g1.getBirthday(), "/");
                gs2 = g2.getFirstname() + " " + g2.getLastname() + ": " + ADate.formatADate(g2.getBirthday(), "/");
            } else if (gSize == 3) {
                Guest g1 = p.getGuest(0);
                Guest g2 = p.getGuest(1);
                Guest g3 = p.getGuest(2);
                gs1 = g1.getFirstname() + " " + g1.getLastname() + ": " + ADate.formatADate(g1.getBirthday(), "/");
                gs2 = g2.getFirstname() + " " + g2.getLastname() + ": " + ADate.formatADate(g2.getBirthday(), "/");
                gs3 = g3.getFirstname() + " " + g3.getLastname() + ": " + ADate.formatADate(g3.getBirthday(), "/");
            } else if (gSize == 4) {
                Guest g1 = p.getGuest(0);
                Guest g2 = p.getGuest(1);
                Guest g3 = p.getGuest(2);
                Guest g4 = p.getGuest(3);
                gs1 = g1.getFirstname() + " " + g1.getLastname() + ": " + ADate.formatADate(g1.getBirthday(), "/");
                gs2 = g2.getFirstname() + " " + g2.getLastname() + ": " + ADate.formatADate(g2.getBirthday(), "/");
                gs3 = g3.getFirstname() + " " + g3.getLastname() + ": " + ADate.formatADate(g3.getBirthday(), "/");
                gs4 = g4.getFirstname() + " " + g4.getLastname() + ": " + ADate.formatADate(g4.getBirthday(), "/");
            } else if (gSize == 5) {
                Guest g1 = p.getGuest(0);
                Guest g2 = p.getGuest(1);
                Guest g3 = p.getGuest(2);
                Guest g4 = p.getGuest(3);
                Guest g5 = p.getGuest(4);
                gs1 = g1.getFirstname() + " " + g1.getLastname() + ": " + ADate.formatADate(g1.getBirthday(), "/");
                gs2 = g2.getFirstname() + " " + g2.getLastname() + ": " + ADate.formatADate(g2.getBirthday(), "/");
                gs3 = g3.getFirstname() + " " + g3.getLastname() + ": " + ADate.formatADate(g3.getBirthday(), "/");
                gs4 = g4.getFirstname() + " " + g4.getLastname() + ": " + ADate.formatADate(g4.getBirthday(), "/");
                gs5 = g5.getFirstname() + " " + g5.getLastname() + ": " + ADate.formatADate(g5.getBirthday(), "/");
            }

            String[] s = {p.getFirstname() + " " + p.getMiddlename() + " " + p.getLastname(), ADate.formatADate(p.getBirthdayDate(), "/"), gs1, gs2, gs3, gs4, gs5};
            data.add(s);

        }

        
        if (resetReport) {
            try {
                CSVTool.generateReport(data, FileTool.reportDir + "/report_" + ADate.formatADate(date, "") + "_" + time + "_resetReport");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PersonHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PersonHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                CSVTool.generateReport(data, FileTool.reportDir + "/report_" + ADate.formatADate(date, "") + "_" + time);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PersonHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PersonHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

    public void resetSystem() {
        ArrayList<Person> persons = personRegister.getPersons();
        for (Person p : persons) {
            for (Guest g : p.getGuests()) {
                try {
                    personDAO.removeGuest(g.getId());
                } catch (SQLException ex) {
                    Logger.getLogger(PersonHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                p.getGuests().remove(g);
            }
            p.setEnrolled(false);
            try {
                personDAO.savePerson(p);
            } catch (SQLException ex) {
                Logger.getLogger(PersonHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
