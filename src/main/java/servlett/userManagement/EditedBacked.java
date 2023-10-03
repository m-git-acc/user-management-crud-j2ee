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

@WebServlet("/editbackend")
public class EditedBacked extends HttpServlet 
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

		int id = Integer.parseInt(req.getParameter("id"));

		String userName = req.getParameter("userName");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String dob = req.getParameter("dob");
		String city = req.getParameter("city");
		String gender = req.getParameter("gender");	
		
		try(
				Connection c = DBConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("UPDATE user_management_servlet SET name=?,email=?,mobile=?,dob=?,city=?,gender=? WHERE id=?");
				)
		{
			ps.setString(1, userName);
			ps.setString(2, email);
			ps.setString(3, mobile);
			ps.setString(4, dob);
			ps.setString(5, city);
			ps.setString(6, gender);
			ps.setInt(7, id);
			
			int count = ps.executeUpdate();
			
			out.print("<div class='card' style='margin:auto; width:300px; margin-top:100px;'>");
			if(count==1)
			{
				out.print("<h2 class='bg-danger text-light text-center'>Record Updated Successfully.</h2>");
			}
			else
			{
				out.print("<h2>Record Not Updated.</h2>");
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
		out.print("<button class='btn btn-outline-success'><a href='showdata'>User List</a></button>");
		out.print("</div>");
		out.close();
	}

}
