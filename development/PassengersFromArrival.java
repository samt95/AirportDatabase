import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class PassengersFromArrival extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    										throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      String arrID = request.getParameter("arrID");
        
	  Connection conn = ConnectionManager.getInstance().getConnection();
      try { 
    	  Statement stmt = conn.createStatement();
    	  ResultSet rset = stmt.executeQuery(
                          "SELECT * "
                        + "FROM Passengers "
                        + "WHERE arrID=" + Integer.valueOf(arrID)
                        );
      
      out.println("<h2>Passengers on Arrival " + Integer.valueOf(arrID) + "</h2>");
    	  out.println("<table>");

        out.println(
              "<tr>" +
                  "<td>pID</td>" + 
                  "<td>name</td>"+
                  "<td>gov_issued_id</td>"+
                  "<td>dob</td>"+
                  "<td>pob</td>"+
                  "<td>depID</td>"+
                  "<td>arrID</td>"+
              "</tr>");

          while (rset.next()) {
        	  out.println(
              "<tr>" +
                  "<td>" + rset.getString("pID") + "</td>" + 
                  "<td>" + rset.getString("name") + "</td>"+
                  "<td>" + rset.getString("gov_issued_id") + "</td>"+
                  "<td>" + rset.getString("dob") + "</td>"+
                  "<td>" + rset.getString("pob") + "</td>"+
                  "<td>" + rset.getString("depID") + "</td>"+
                  "<td>" + rset.getString("arrID") + "</td>"+
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
