import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class InsertPassengers extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response) 
                            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String file = request.getParameter("file");

        Connection conn = ConnectionManager.getInstance().getConnection();
        try {

            File people = new File("/Users/SamsImac/Courses/CSC370/project/AirportDatabase/passengersAndBaggage/" + file);
            Scanner s = new Scanner(people);
            Statement stmt = conn.createStatement();

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
    

                
                stmt.executeUpdate(statementPeople);
 
                out.println("Insertion Successful!");


            }

            stmt.close();
            out.println("Insertion Successful!");
        }
        catch(SQLException e) { out.println(e); }
        ConnectionManager.getInstance().returnConnection(conn);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    public String getServletInfo() {  return "Insert"; }
}
