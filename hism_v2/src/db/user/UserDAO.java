/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db.user;

import date.ADate;
import db.DBTool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.User;

/**
 *
 * @author patrick
 */
public class UserDAO {

    private Connection conn;

    public int insertUser(User u) throws SQLException {
        conn = DBTool.getInstance();

        System.out.println("Inserting user...");
        if (conn.isValid(10000)) {
            Statement s = conn.createStatement();
            String email = u.getEmail();
            String password = u.getPassword();
            String firstname = u.getFirstname();
            String middlename = u.getMiddlename();
            String lastname = u.getLastname();
            String creationDate = ADate.formatADate(u.getCreationDate(), "");
            int administrator = 0;
            int reserve = 0;

            if (u.isAdministrator()) {
                administrator = 1;
            }
            if (u.isReserve()) {
                reserve = 1;
            }

            s.execute("INSERT INTO user (email, password, firstname, middlename, lastname, creationdate, administrator, reserve) "
                    + "VALUES('" + email + "', '" + password + "', '" + firstname + "', '" + middlename + "', '" + lastname + "', '" + creationDate + "', '" + administrator + "', '" + reserve + "')");
            ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
            rs.next();
            int idback = rs.getInt(1);
            rs.close();
            s.close();
            System.out.println("Insertion complete!");
            conn.close();
            return idback;
        } else {
            System.out.println("Connection not valid");
            conn.close();
            return -1;
        }
    }

    public ArrayList<User> getAllUsers() throws SQLException {
        conn = DBTool.getInstance();

        System.out.println("Getting all users...");
        if (conn.isValid(10000)) {
            Statement s = conn.createStatement();
            ArrayList<User> users = new ArrayList<>();

            ResultSet rs = s.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                int iduser = rs.getInt("iduser");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String firstname = rs.getString("firstname");
                String middlename = rs.getString("middlename");
                String lastname = rs.getString("lastname");
                ADate creationDate = new ADate(rs.getString("creationdate"));
                boolean administrator = false;
                boolean reserve = false;
                if (rs.getInt("administrator") == 1) {
                    administrator = true;
                }
                if (rs.getInt("reserve") == 1) {
                    reserve = true;
                }
                User u = new User(email, password, firstname, middlename, lastname, creationDate, administrator, reserve);
                u.setId(iduser);
                users.add(u);
            }
            rs.close();
            s.close();
            System.out.println("Get complete!");
            conn.close();
            return users;
        } else {
            System.out.println("Connection not valid");
            conn.close();
            return null;
        }
    }

    public void deleteUser(User u) throws SQLException {
        conn = DBTool.getInstance();

        System.out.println("Deleting user...");
        if (conn.isValid(10000)) {
            Statement s = conn.createStatement();

            s.execute("DELETE FROM user WHERE iduser = '" + u.getId() + "'");
            s.close();
            System.out.println("Delete complete!");
            conn.close();
        } else {
            System.out.println("Connection not valid");
            conn.close();
        }
    }

    public void saveUser(User u) throws SQLException {
        conn = DBTool.getInstance();

        System.out.println("Saving user...");
        if (conn.isValid(10000)) {
            Statement s = conn.createStatement();

            int iduser = u.getId();
            String firstname = u.getFirstname();
            String middlename = u.getMiddlename();
            String lastname = u.getLastname();
            String email = u.getEmail();
            String password = u.getPassword();
            int admin = 0;
            if (u.isAdministrator()) {
                admin = 1;
            }
            int reserve = 0;
            if (u.isReserve()) {
                reserve = 1;
            }

            s.executeUpdate("UPDATE user SET firstname = '" + firstname + "', middlename = '" + middlename + "', lastname = '" + lastname + "', email = '" + email + "', password = '" + password + "', administrator = " + admin + ", reserve = " + reserve + " WHERE iduser = " + iduser + "");
            s.close();
            System.out.println("Save complete!");
            conn.close();
        } else {
            System.out.println("Connection not valid");
            conn.close();
        }
    }
}
