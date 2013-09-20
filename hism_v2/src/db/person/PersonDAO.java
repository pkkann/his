/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db.person;

import date.ADate;
import db.DBTool;
import file.FileTool;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Person;

/**
 *
 * @author patrick
 */
public class PersonDAO {

    private Connection conn;

    public PersonDAO() {
        conn = DBTool.getInstance();
    }

    public int insertPerson(Person p) throws SQLException {
        Statement s = conn.createStatement();

        String firstname = p.getFirstname();
        String middlename = p.getMiddlename();
        String lastname = p.getLastname();
        String address = p.getAddress();
        String birthday = ADate.formatADate(p.getBirthdayDate(), "");
        String expirationDate = ADate.formatADate(p.getExpirationDate(), "");
        File picturePathF = p.getPicturePath();
        String picturePath = FileTool.getDefaultFolder() + "/" + picturePathF.getName();
        int quarantine = 0;
        String quarantineExpirationDate = "";
        if (p.getQuarantineExpirationDate() != null) {
            quarantineExpirationDate = p.getQuarantineExpirationDate().toString();
        }
        int oneOne = 0;
        String creationDate = ADate.formatADate(p.getCreationDate(), "");
        if (p.isQuarantine()) {
            quarantine = 1;
        }
        if (p.isOneOne()) {
            oneOne = 1;
        }
        int enrolled = 0;
        if (p.isEnrolled()) {
            enrolled = 1;
        }
        int hone = 0;
        if(p.isHone()) {
            hone = 1;
        }

        s.executeUpdate("INSERT INTO person (firstname, middlename, lastname, address, birthday, expirationdate, picturepath, quarantine, quarantineexpirationdate, oneone, creationdate, enrolled, hone) "
                + "VALUES('" + firstname + "', '" + middlename + "', '" + lastname + "', '" + address + "', '" + birthday + "', '" + expirationDate + "', '" + picturePath + "', '" + quarantine + "', '" + quarantineExpirationDate + "', '" + oneOne + "', '" + creationDate + "', '"+enrolled+"', '"+hone+"')");
        ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
        rs.next();
        int idback = rs.getInt(1);
        
        rs.close();
        s.close();
        return idback;
    }

    public void savePerson(Person p) throws SQLException {
        Statement s = conn.createStatement();

        int idperson = p.getId();
        String firstname = p.getFirstname();
        String middlename = p.getMiddlename();
        String lastname = p.getLastname();
        String address = p.getAddress();
        String birthday = ADate.formatADate(p.getBirthdayDate(), "");
        String expirationDate = ADate.formatADate(p.getExpirationDate(), "");
        File picturePathF = p.getPicturePath();
        String picturePath = FileTool.getDefaultFolder() + "/" + picturePathF.getName();
        int quarantine = 0;
        String quarantineExpirationDate = "";
        if (p.getQuarantineExpirationDate() != null) {
            quarantineExpirationDate = p.getQuarantineExpirationDate().toString();
        }
        int oneOne = 0;
        if (p.isQuarantine()) {
            quarantine = 1;
        }
        if (p.isOneOne()) {
            oneOne = 1;
        }
        int enrolled = 0;
        if(p.isEnrolled()) {
            enrolled = 1;
        }
        int hone = 0; {
        if(p.isHone()) {
            hone = 1;
        }
    }

        s.executeUpdate("UPDATE person SET firstname = '"+firstname+"', middlename = '"+middlename+"', lastname = '"+lastname+"', address = '"+address+"', birthday = '"+birthday+"', expirationdate = '"+expirationDate+"', picturepath = '"+picturePath+"', oneone = "+oneOne+", quarantine = "+quarantine+", enrolled = "+enrolled+", hone= "+hone+" WHERE idperson = "+idperson+"");
        s.close();
    }

    public ArrayList<Person> getAllPersons() throws SQLException {
        Statement s = conn.createStatement();
        ArrayList<Person> persons = new ArrayList<>();

        ResultSet rs = s.executeQuery("SELECT * FROM person");
        while (rs.next()) {
            int idperson = rs.getInt("idperson");
            String firstname = rs.getString("firstname");
            String middlename = rs.getString("middlename");
            String lastname = rs.getString("lastname");
            String address = rs.getString("address");
            ADate birthday = new ADate(rs.getString("birthday"));
            ADate expirationDate = new ADate(rs.getString("expirationdate"));
            File picturePath = new File(rs.getString("picturepath"));
            ADate quarantineExpirationDate = null;
            if (!rs.getString("quarantineexpirationdate").isEmpty()) {
                quarantineExpirationDate = new ADate(rs.getString("quarantineexpirationdate"));
            }
            ADate creationDate = new ADate(rs.getString("creationdate"));
            boolean oneOne = false;
            if (rs.getInt("oneone") == 1) {
                oneOne = true;
            }
            boolean quarantine = false;
            if(rs.getInt("quarantine") == 1) {
                quarantine = true;
            }
            boolean enrolled = false;
            if(rs.getInt("enrolled") == 1) {
                enrolled = true;
            }
            boolean hone = false;
            if(rs.getInt("hone") == 1) {
                hone = true;
            }

            Person p = new Person(firstname, middlename, lastname, address, birthday, expirationDate, picturePath, creationDate, oneOne);
            p.setId(idperson);
            p.setQuarantine(quarantine);
            p.setQuarantineExpirationDate(quarantineExpirationDate);
            p.setEnrolled(enrolled);
            p.setHone(hone);
            persons.add(p);

        }
        rs.close();
        s.close();
        return persons;
    }
    
    public void deletePerson(Person p) throws SQLException {
        Statement s = conn.createStatement();
        int idperson = p.getId();
        
        s.execute("DELETE FROM person WHERE idperson = "+idperson+"");
        s.close();
    }
}
