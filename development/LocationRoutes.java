import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LocationRoutes extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    										throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      String location = request.getParameter("location");
        
	  Connection conn = ConnectionManager.getInstance().getConnection();
      try { 
    	  Statement stmt = conn.createStatement();
    	  ResultSet rset = stmt.executeQuery(
                              "SELECT acode, rnum, TO_CHAR(outT, 'hh24:mi') AS outTime "
                            + "FROM OutgoingRoutes "
                            + "  WHERE destination='" + location + "'"
                        );
      
        out.println("<h2>Routes to " + location + "</h2>");
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
                  "<td>"+rset.getString("outTime")+"</td>"+
              "</tr>");
          }
          out.println("</table>");


////////********************** new query

          rset = stmt.executeQuery(
                              "SELECT acode, rnum, TO_CHAR(incT, 'hh24:mi') AS incomingTime "
                            + "FROM IncomingRoutes "
                            + "  WHERE source='" + location + "'"
                        );

        out.println("<h2>Routes from " + location + "</h2>");
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
                  "<td>"+rset.getString("incomingTime")+"</td>"+
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
