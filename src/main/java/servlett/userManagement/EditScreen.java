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

@WebServlet("/editurl")
public class EditScreen extends HttpServlet 
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
		out.print("<marquee><h2 class='text-primary'>Edit User Details</h2></marquee>");

		String id = req.getParameter("id");	
		
		try(
				Connection c = DBConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT name,email,mobile,dob,city,gender FROM user_management_servlet WHERE id=?  ");
				)
		{
			ps.setString(1, id);
			try(ResultSet rs = ps.executeQuery();)
			{

				out.print("<div style='margin:auto; width:800px; margin-top:100px;'>");
				out.print("<form action='editbackend?id="+id+"' method='post'>");
				out.print("<table class='table table-hover table-striped'>");	
				
				if(rs.next())
				{
					out.print("<tr><th>Name</th>");
					out.print("<td><input type='text' name='userName' value='"+rs.getString("name")+"'></td></tr>");
					
					out.print("<tr><th>Email</th>");
					out.print("<td><input type='text' name='email' value='"+rs.getString("email")+"'></td></tr>");
					
					out.print("<tr><th>Mobile</th>");
					out.print("<td><input type='text' name='mobile' value='"+rs.getString("mobile")+"'></td></tr>");
					
					out.print("<tr><th>DOB</th>");
					out.print("<td><input type='text' name='dob' value='"+rs.getString("dob")+"'></td></tr>");

					out.print("<tr><th>City</th>");
					out.print("<td><input type='text' name='city' value='"+rs.getString("city")+"'></td></tr>");

					out.print("<tr><th>Gender</th>");
					out.print("<td><input type='text' name='gender' value='"+rs.getString("gender")+"'></td></tr>");
					
					out.print("<tr><td><input type='submit' value='submit'></td>");
					out.print("<td><input type='reset'  value='Cancel'></td></tr>");
				}	
				else
				{
					out.print("<h2 class='bg-danger text-light text-center'>NO DETAILS FOUND</h2>");
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

		out.print("<button class='btn btn-outline-success'><a href='Index.html'>Home</a></button>&nbsp;&nbsp;");
		out.print("<button class='btn btn-outline-success'><a href='showdata'>Show User</a></button>");
		out.print("</form>");
		out.print("</div>");
		out.close();
	}

}
