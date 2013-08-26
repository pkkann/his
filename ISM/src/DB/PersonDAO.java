/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import DB.tool.DBTool;
import java.sql.Connection;

/**
 *
 * @author pkann
 */
public class PersonDAO {
    
    private Connection dbConn;
    
    public PersonDAO() {
        dbConn = DBTool.getInstance();
    }
    
}
