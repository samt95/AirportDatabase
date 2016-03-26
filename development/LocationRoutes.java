import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LocationRoutes extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    										throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      String airline = request.getParameter("airline");
        
	  Connection conn = ConnectionManager.getInstance().getConnection();
      try { 
    	  Statement stmt = conn.createStatement();
    	  ResultSet rset = stmt.executeQuery(
                              "SELECT * "
                            + "FROM Routes "
                            + "WHERE rnum IN ( "
                            + "  SELECT rnum "
                            + "  FROM OutgoingRoutes "
                            + "  WHERE destination='Toronto' "
                            + "  ) "
                            + "  OR rnum IN ( "
                            + "  SELECT rnum "
                            + "  FROM IncomingRoutes "
                            + "  WHERE source='Toronto' "
                            + "  )"
                        );
      
    	  out.println("<table>");

        out.println(
              "<tr>" +
                  "<td>acode</td>" + 
                  "<td>rnum</td>"+
                  "<td>planemodel</td>"+
              "</tr>");

          while (rset.next()) {
        	  out.println(
        	  "<tr>" +
               	  "<td>"+rset.getString("acode")+"</td>" + 
               	  "<td>"+rset.getString("rnum")+"</td>"+
                  "<td>"+rset.getString("planemodel")+"</td>"+
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
    	processRequest(request, response);     }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                   						throws ServletException, IOException {
    	processRequest(request, response);     }
    
    public String getServletInfo() {  return "Get Airline Routes Servlet"; }
}
