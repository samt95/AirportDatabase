import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.File;
import java.util.Scanner;

public class addPeopleAndBags {
        
    public static void main(String[] args){
    
        if(args.len == 1){
            File filePeeps = new File(args[0]);
            File fileBags  = File(args[1]);

        }else{
            System.exit(-1);

        }

        while(filePeeps.hasNextLine()){
            String line             = filePeeps.nextLine();
            String[] result         = line.split(",");
            
            String pID              = result[0];
            String name             = result[1];
            String gov_issued_id    = result[2];
            String dob              = result[3];
            String pob              = result[4];
            String depID            = result[5];
            String arrID            = result[6];

        }


        String statementPeople = "INSERT INTO Passengers(pID, name, gov_issued_id, dob,pob,depID,arrID)"+   
                        "VALUES('" + pID + "', '"+ name +"', '" + gov_issued_id + "', '" + dob + "', '" +
                            pob + "', '" + depID + "', '" + arrID + "')";
        
       



        Connection conn = ConnectionManager.getInstance().getConnection();
       
        try {
            Statement stmt = conn.createStatement();
            ResultSet rset =stmt.executeUpdate(statementString);
            stmt.close();


        }
        catch(SQLException e) { out.println(e); }
        ConnectionManager.getInstance().returnConnection(conn);
    







    }
  



}
