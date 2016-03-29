import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class PassengerBaggage extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    										throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      String passengerID = request.getParameter("passengerID");
        
	  Connection conn = ConnectionManager.getInstance().getConnection();
      try { 
    	  Statement stmt = conn.createStatement();
    	  ResultSet rset = stmt.executeQuery(
                                "SELECT * "
                                + "FROM Baggage "
                                + "WHERE pID = ( "
                                + "  SELECT pID "
                                + "  FROM Passengers "
                                + "  WHERE pid=" + passengerID
                                + "  )"
                                );
      
      out.println("<h2>Baggage for Passenger " + Integer.valueOf(passengerID) + "</h2>");
    	  out.println("<table>");

        out.println(
              "<tr>" +
                  "<td>pID</td>" + 
                  "<td>bID</td>"+
                  "<td>weight</td>"+
              "</tr>");

          while (rset.next()) {
        	  out.println(
              "<tr>" +
                  "<td>" + rset.getString("pID") + "</td>" + 
                  "<td>" + rset.getString("bID") + "</td>"+
                  "<td>" + rset.getString("weight") + "</td>"+
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
