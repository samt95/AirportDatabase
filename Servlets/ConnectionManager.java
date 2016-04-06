import java.sql.*;
import java.util.*;

public class ConnectionManager {
    private static ConnectionManager instance = null;
    private Stack<Connection> connections;
    
    private ConnectionManager () {
        connections = new Stack<Connection>();
        try { 
	        DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
        } catch (Exception ex) { 
            System.out.println(ex); 
        }
    }

    public static ConnectionManager getInstance() {
        if (instance == null) instance = new ConnectionManager();
        return instance;
    }

    public Connection getConnection() {
        Connection conn = null;
        
        if (!connections.empty())
            conn = (Connection) connections.pop();
        else { //No one left in the stack, create a new one
            try {
                conn = DriverManager.getConnection 			 
			("connectionName", "userName", "password"); // change this line
            } catch (SQLException ex) { 
                System.out.println("SQLException: " + ex); 
            }
        }
        return conn;
    }
    
    public void returnConnection(Connection conn) {
        if (conn != null) connections.push(conn);
    }      
}
