import java.sql.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.*;
import javax.servlet.*;
import java.text.*;
import javax.servlet.http.*;

public class addPeopleAndBags {
        
    public static void main(String[] args){
        File fileBags;
        if(args.length == 1){
             File filePeeps = new File(args[0]);

             //fileBags  = new File(args[1]);
            doStuff(filePeeps);
        }else{
            
            System.exit(-1);

        }
       



    }
  
    public static void doStuff2(Scanner peopleScanner, Statement stmt){


            while(peopleScanner.hasNextLine()){
                String line             = peopleScanner.nextLine();
                String[] result         = line.split(",");
                
                String pID              = result[0];
                String name             = result[1];
                String gov_issued_id    = result[2];
                String dob              = result[3];
                String pob              = result[4];
                String depID            = result[5];
                String arrID            = result[6];

            


                String statementPeople = "INSERT INTO Passengers(pID, name, gov_issued_id, dob,pob,depID,arrID)"+   
                            "VALUES('" + pID + "', '"+ name +"', '" + gov_issued_id + "', '" + dob + "', '" +
                                pob + "', '" + depID + "', '" + arrID + "')";
            
       


            try{
            stmt.executeUpdate(statementPeople);
            stmt.close();
            }
            catch(SQLException e) { System.out.println(e); }        }
    }


    public static void doStuff(File filePeeps){
         Connection conn;
        try{
            conn = ConnectionManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            System.out.println("connects");
           Scanner peopleScanner;
           try {
            peopleScanner = new Scanner(filePeeps);
            doStuff2( peopleScanner, stmt);
            } catch(FileNotFoundException fnfe) { 
            System.out.println(fnfe.getMessage());
            } 
           
    



}    catch(SQLException e) { System.out.println(e); }
       // ConnectionManager.getInstance().returnConnection(conn);
    





    }



}
