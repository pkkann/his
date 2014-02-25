/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hismtilhis;

import date.ADate;
import db.DBTool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Person;

/**
 *
 * @author Patrick
 */
public class HismDAO {

    private Connection conn;

    public ArrayList<Person> getPersons() throws SQLException {
        conn = DBTool.getInstanceHISM();
        Statement s = conn.createStatement();
        ArrayList<Person> persons = new ArrayList<>();

        ResultSet rs = s.executeQuery("SELECT * FROM person");

        while (rs.next()) {
            String firstname = rs.getString("firstname");
            String middlename = rs.getString("middlename");
            String lastname = rs.getString("lastname");
            String address = rs.getString("address");
            ADate birthday = new ADate(rs.getString("birthday"));
            ADate expirationDate = new ADate(rs.getString("expirationdate"));
            ADate creationDate = new ADate(rs.getString("creationdate"));
            
            Person p = new Person(firstname, middlename, lastname, address, birthday, creationDate, expirationDate, true, true);
            persons.add(p);
        }

        rs.close();
        s.close();
        conn.close();
        return persons;
    }

}
