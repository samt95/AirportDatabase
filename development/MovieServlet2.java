import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class MovieServlet2 extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response)  						throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	   String year = request.getParameter("year");
        
        Connection conn = ConnectionManager.getInstance().getConnection();
        try { Statement stmt = conn.createStatement();
               ResultSet rset = stmt.executeQuery(
                        "SELECT title, year " +
                        "FROM Movies " +
                        "WHERE year = " + year);

       		out.println("<table>");
    		while (rset.next()) {
    			out.println("<tr>");
    			out.print (
    				"<td>"+rset.getString("title")+"</td>" + 
    				"<td>"+rset.getString("year")+"</td>");
    				out.println("</tr>");
    			}
    			out.println("</table>");
                stmt.close();
            }
            catch(SQLException e) { out.println(e); }        
            ConnectionManager.getInstance().returnConnection(conn);
        }
       protected void doGet(HttpServletRequest request, HttpServletResponse response)
        					throws ServletException, IOException {
            processRequest(request, response); }
       protected void doPost(HttpServletRequest request, HttpServletResponse response)
        					throws ServletException, IOException { 
            processRequest(request, response); }
       public String getServletInfo() {  return "Short description"; }
    }
               