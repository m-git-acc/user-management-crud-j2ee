package servlett.userManagement;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection 
{
	private static Connection cc;
	public static Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			cc = DriverManager.getConnection("jdbc:mysql:///advance_projects","root","mohit123");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return cc;
	}

}
