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
import model.User;

/**
 *
 * @author Patrick
 */
public class HismDAO {

    private Connection conn;

    public ArrayList<Person> getPersons() throws SQLException {
        System.out.println("Getting alle persons");
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
            Boolean oneone = false;
            if(rs.getInt("oneone") == 1) {
                oneone = true;
            }
            boolean hoene = false;
            if(rs.getInt("hone") == 1) {
                hoene = true;
            }
            if(hoene) {
                oneone = false;
            }
            
            Person p = new Person(firstname, middlename, lastname, address, birthday, creationDate, expirationDate, oneone, hoene);
            persons.add(p);
        }

        rs.close();
        s.close();
        conn.close();
        System.out.println("DONE");
        return persons;
    }
    
    public ArrayList<User> getUsers() throws SQLException {
        System.out.println("Getting all users");
        conn = DBTool.getInstanceHISM();
        Statement s = conn.createStatement();
        ArrayList<User> users = new ArrayList<>();
        
        ResultSet rs = s.executeQuery("SELECT * FROM user");
        
        while(rs.next()) {
            String email = rs.getString("email");
            String password = rs.getString("password");
            String firstname = rs.getString("firstname");
            String middlename = rs.getString("middlename");
            String lastname = rs.getString("lastname");
            ADate creationdate = new ADate(rs.getString("creationdate"));
            boolean admin = false;
            if(rs.getInt("administrator") == 1) {
                admin = true;
            }
            boolean reserve = false;
            if(rs.getInt("reserve") == 1) {
                reserve = true;
            }
            
            User u = new User(email, password, firstname, middlename, lastname, creationdate, admin, reserve);
            users.add(u);
            
        }
        
        rs.close();
        s.close();
        conn.close();
        System.out.println("DONE");
        return users;
    }

}
