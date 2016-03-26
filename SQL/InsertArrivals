import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class InsertDepartures extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response) 
                            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String acode = request.getParameter("acode");
        String rnum = request.getParameter("Rnum");
        

        String depID = request.getParameter("depID");
        String gate = request.getParameter("gate");
        String depT = request.getParameter("depT");
        
     

        String statementString = 
        "INSERT INTO Departures(depID, gate, depT,rnum, acode) " +
        "VALUES( '" + depID + "', '" + gate + "',  TO_DATE('" + depT + "', 'dd/mm/yyyy hh24:mi:ss')," + " '"+ rnum + "', '" + acode + "')";        

      //'" + depId + "',
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(statementString);
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
