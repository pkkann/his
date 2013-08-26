/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import DB.tool.DBTool;
import Model.Person;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pkann
 */
public class PersonDAO {

    private Connection dbConn;

    public PersonDAO() {
        dbConn = DBTool.getInstance();
    }

    public void insertPerson(Person p) {
        try {
            Statement st = dbConn.createStatement();
            
            st.execute("INSERT INTO person (navn, adresse, fodselsdag, udlobsdato, billedPath, hone) VALUES('"+p.getNavn()+"', '"+p.getAdresse()+"', '"+p.getFodselsdag()+"', '"+p.getUdlobsdato()+"', '"+p.getImagePath()+"', '"+p.isHone()+"')");
            st.close();
        } catch (SQLException ex) {
        }
    }
}
