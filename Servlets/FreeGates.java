import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class FreeGates extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response) 
                            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();  

        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
          Statement stmt = conn.createStatement();
          ResultSet rset = stmt.executeQuery("SELECT gate FROM Gates WHERE isFree=1");
      
      out.println("<h2>Free Gates</h2>");
          out.println("<table>");

        out.println(
              "<tr>" +
                  "<td>gate</td>" + 
              "</tr>");

          while (rset.next()) {
              out.println(
              "<tr>" +
                  "<td>" + rset.getString("gate") + "</td>" + 
              "</tr>");
          }
          out.println("</table>");
          stmt.close();
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
