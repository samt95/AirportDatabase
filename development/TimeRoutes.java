import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class TimeRoutes extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    										throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      String dateTime = request.getParameter("dateTime");
        
	  Connection conn = ConnectionManager.getInstance().getConnection();
      try { 
    	  Statement stmt = conn.createStatement();
    	  ResultSet rset = stmt.executeQuery(
                                  "SELECT * "
                                + "FROM Departures "
                                + "WHERE 24*ABS(depT-to_date('" + dateTime + "', 'dd/mm/yyyy hh24:mi') ) <= 1"
                        );
      

        out.println("<h2>Routes departing around " + dateTime + "</h2>");
    	  out.println("<table>");

        out.println(
              "<tr>" +
                  "<td>acode</td>" + 
                  "<td>rnum</td>"+
                  "<td>depID</td>"+
                  "<td>depT</td>"+
              "</tr>");

          while (rset.next()) {
        	  out.println(
        	  "<tr>" +
               	  "<td>"+rset.getString("acode")+"</td>" + 
               	  "<td>"+rset.getString("rnum")+"</td>"+
                  "<td>"+rset.getString("depID")+"</td>"+
                  "<td>"+rset.getString("depT")+"</td>"+
              "</tr>");
          }
          out.println("</table>");

///////************ new query

        rset = stmt.executeQuery(
                                  "SELECT * "
                                + "FROM Arrivals "
                                + "WHERE 24*ABS(arrT-to_date('" + dateTime + "', 'dd/mm/yyyy hh24:mi') ) <= 1"
                        );
      

        out.println("<h2>Routes arriving around " + dateTime + "</h2>");
        out.println("<table>");

        out.println(
              "<tr>" +
                  "<td>acode</td>" + 
                  "<td>rnum</td>"+
                  "<td>arrID</td>"+
                  "<td>arrT</td>"+
              "</tr>");

          while (rset.next()) {
            out.println(
            "<tr>" +
                  "<td>"+rset.getString("acode")+"</td>" + 
                  "<td>"+rset.getString("rnum")+"</td>"+
                  "<td>"+rset.getString("arrID")+"</td>"+
                  "<td>"+rset.getString("arrT")+"</td>"+
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
