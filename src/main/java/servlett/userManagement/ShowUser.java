package servlett.userManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/showdata")
public class ShowUser extends HttpServlet
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		doGet(req,res);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		out.print("<link rel='stylesheet' href='css/bootstrap.css'>");
		out.print("<marquee><h2 class='text-primary'>User Data</h2></marquee>");

		String userName = req.getParameter("userName");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String dob = req.getParameter("dob");
		String city = req.getParameter("city");
		String gender = req.getParameter("gender");	
		
		try(
				Connection c = DBConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT id,name,email,mobile,dob,city,gender FROM user_management_servlet");
				)
		{
			try(ResultSet rs = ps.executeQuery();)
			{

				out.print("<div style='margin:auto; width:800px; margin-top:100px;'>");
				out.print("<table class='table table-hover table-striped'>");	
				out.print("<tr>");	
				out.print("<th>ID</th>");
				out.print("<th>Name</th>");
				out.print("<th>Email</th>");
				out.print("<th>Mobile</th>");
				out.print("<th>DOB</th>");
				out.print("<th>City</th>");
				out.print("<th>Gender</th>");
				out.print("<th>Edit</th>");
				out.print("<th>Delte</th>");
				out.print("</tr>");
				
				while(rs.next())
				{
					out.print("<tr>");
					out.print("<td>"+rs.getInt("id")+"</td>");
					out.print("<td>"+rs.getString("name")+"</td>");
					out.print("<td>"+rs.getString("email")+"</td>");	
					out.print("<td>"+rs.getString("mobile")+"</td>");
					out.print("<td>"+rs.getString("dob")+"</td>");
					out.print("<td>"+rs.getString("city")+"</td>");
					out.print("<td>"+rs.getString("gender")+"</td>");

					out.print("<td><a href='editurl?id="+rs.getInt("id")+"'>edit</a></td>");
					out.print("<td><a href='deleteurl?id="+rs.getInt("id")+"'>delete</a></td>");
					out.print("</tr>");				
				}
				out.print("</table>");
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				out.print("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
			}
			catch(Exception e)
			{
				e.printStackTrace();
				out.print("<h2>"+e.getMessage()+"</h2>");	
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			out.print("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			out.print("<h2>"+e.getMessage()+"</h2>");	
		}
		
		out.print("<button class='btn btn-outline-success'><a href='Index.html'>Home</a></button>");
		out.print("</div>");
		out.close();
	}
}
