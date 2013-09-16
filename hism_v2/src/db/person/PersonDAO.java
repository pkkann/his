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

        int id = p.getId();
        String firstname = p.getFirstname();
        String middlename = p.getMiddlename();
        String lastname = p.getLastname();
        String address = p.getAddress();
        String birthday = p.getBirthdayDate().toString();
        String expirationDate = p.getExpirationDate().toString();
        File picturePathF = p.getPicturePath();
        String picturePath = FileTool.getDefaultFolder() + "/" + picturePathF.getName();
        int quarantine = 0;
        String quarantineExpirationDate = "";
        if (p.getQuarantineExpirationDate() != null) {
            quarantineExpirationDate = p.getQuarantineExpirationDate().toString();
        }
        int oneOne = 0;
        String creationDate = p.getCreationDate().toString();
        if (p.isQuarantine()) {
            quarantine = 1;
        }
        if (p.isOneOne()) {
            oneOne = 1;
        }

        s.executeUpdate("INSERT INTO person (firstname, middlename, lastname, address, birthday, expirationdate, picturepath, quarantine, quarantineexpirationdate, oneone, creationdate) "
                + "VALUES('" + firstname + "', '" + middlename + "', '" + lastname + "', '" + address + "', '" + birthday + "', '" + expirationDate + "', '" + picturePath + "', '" + quarantine + "', '" + quarantineExpirationDate + "', '" + oneOne + "', '" + creationDate + "')");
        ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
        rs.next();
        int idback = rs.getInt(1);
        
        rs.close();
        s.close();
        return idback;
    }

    public void savePerson(Person p) throws SQLException {
        Statement s = conn.createStatement();

        int id = p.getId();
        String firstname = p.getFirstname();
        String middlename = p.getMiddlename();
        String lastname = p.getLastname();
        String address = p.getAddress();
        String birthday = p.getBirthdayDate().toString();
        String expirationDate = p.getExpirationDate().toString();
        File picturePathF = p.getPicturePath();
        String picturePath = FileTool.getDefaultFolder() + "/" + picturePathF.getName();
        int quarantine = 0;
        String quarantineExpirationDate = "";
        if (p.getQuarantineExpirationDate() != null) {
            quarantineExpirationDate = p.getQuarantineExpirationDate().toString();
        }
        int oneOne = 0;
        String creationDate = p.getCreationDate().toString();
        if (p.isQuarantine()) {
            quarantine = 1;
        }
        if (p.isOneOne()) {
            oneOne = 1;
        }

        s.executeUpdate("UPDATE person SET firstname = '"+firstname+"', middlename = '"+middlename+"', lastname = '"+lastname+"', address = '"+address+"', birthday = '"+birthday+"', expirationdate = '"+expirationDate+"', picturepath = '"+picturePath+"', oneone = "+oneOne+", quarantine = "+quarantine+"");
        s.close();
    }

    public ArrayList<Person> getAllPersons() throws SQLException {
        Statement s = conn.createStatement();
        ArrayList<Person> persons = new ArrayList<>();

        ResultSet rs = s.executeQuery("SELECT * FROM person");
        while (rs.next()) {
            int id = rs.getInt("id");
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

            Person p = new Person(firstname, middlename, lastname, address, birthday, expirationDate, picturePath, creationDate, oneOne);
            p.setId(id);
            p.setQuarantine(quarantine);
            p.setQuarantineExpirationDate(quarantineExpirationDate);
            persons.add(p);

        }
        rs.close();
        s.close();
        return persons;
    }
    
    public void deletePerson(Person p) throws SQLException {
        Statement s = conn.createStatement();
        int id = p.getId();
        
        s.execute("DELETE FROM person WHERE id = "+id+"");
        s.close();
    }
}
