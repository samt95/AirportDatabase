import java.io.*;


import java.sql.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class addPeopleAndBags {
        
    public static void doStuff(Scanner s){
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

                

                String statementPeople = "INSERT INTO Passengers(pID, name, gov_issued_id, dob,pob,depID,arrID)"+   
                                    "VALUES('" + pID + "', '"+ name +"', '" + gov_issued_id + "', '" + dob + "', '" +
                                    pob + "', '" + depID + "', '" + arrID + "')";
                
               
                System.out.println(statementPeople);


                Connection conn = ConnectionManager.getInstance().getConnection();
               
                try {
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(statementPeople);
                    stmt.close();
                    System.out.println("success");

                    //ResultSet rset =s

                }
                catch(SQLException e) { System.out.println(e); }
               

                ConnectionManager.getInstance().returnConnection(conn);
            

            }


    }    
    public static void main(String[] args){
    
        if(args.length != 2){
           System.exit(-1);

        }else{
            File filePeeps = new File(args[0]);
            File fileBags  = new File(args[1]);
            try{
          
                Scanner s = new Scanner(filePeeps);
                doStuff(s); 
          }catch(FileNotFoundException e){
                System.out.println(e);
            }  
            

        }



    }
  



}
