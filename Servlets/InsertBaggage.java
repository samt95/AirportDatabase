import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class InsertBaggage extends HttpServlet {
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
                
                String bID     = result[0];
                String weight  = result[1];
                String pID    = result[2];

                String statementPeople = "INSERT INTO BAGGAGE VALUES(" + bID + ", " + weight +  ", " + pID + ")";
            
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
