import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class InsertRoute extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response) 
                            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String acode = request.getParameter("Airline Code");
        String rnum = request.getParameter("Rnum");
        String planemodel = request.getParameter("planemodel");
        
        //acode = "6";
        //rnum = "555";
        //planemodel = "555";

        String statementString = 
        "INSERT INTO Routes(acode, rnum, planemodel) " +
        "VALUES( '" + acode + "', '" + rnum + "', '" + planemodel + "')";        
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
