 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class AddPassengers {
    
    public static void main(String[] args){


        File people = new File(args[0]);
        Scanner s;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try{
            s = new Scanner(people);
        
            while(s.hasNextLine()){
                String line             = s.nextLine();
                String[] result         = line.split(",");
                
                String pID              = result[0];
                String name             = result[1];
                String gov_issued_id    = result[2];
                String dob              = result[3];
                String pob              = result[4];
                String depID            = result[5];
                String arrID            = result[6];

                String statementPeople = "INSERT INTO Passengers(pID, name, gov_issued_id, dob, pob ,depID, arrID) " +   
                                         "VALUES(" + pID + ", '"+ name +"', " + gov_issued_id + ", TO_DATE('" + dob + "', 'dd/mm/yyyy'), '" +
                                                    pob + "', " + depID + ", " + arrID + ")";
    

               

                Statement stmt = conn.createStatement();
                stmt.executeUpdate(statementPeople);
                stmt.close();
                System.out.println("Insertion Successful!");

            }
        }
        catch(FileNotFoundException e){}
        catch(SQLException e) { 
            ConnectionManager.getInstance().returnConnection(conn);
            System.out.println(e); 
        }
    }

}