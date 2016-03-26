import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class InsertIncoming extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response) 
                            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String acode = request.getParameter("acode");
        String rnum = request.getParameter("Rnum");
        String source = request.getParameter("source");
        String incT = request.getParameter("incT");
        
     

        String statementString = 
        "INSERT INTO IncomingRoutes(source, incT, rnum, acode) " +
        "VALUES( '" + source + "', TO_DATE('" + incT + "', 'dd/mm/yyyy hh24:mi:ss'), '" + rnum +"', '"+ acode+"')";        
      
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
