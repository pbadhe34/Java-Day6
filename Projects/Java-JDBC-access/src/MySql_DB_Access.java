import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySql_DB_Access {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root", "MyRootPass123");
		Statement stmt = connDB.createStatement();
		String uname = "Ashok";
		float userIncome = 12345f;

		boolean res = false;
		
		String sqlStatement = "create table UserInfo(userNum int not null,userName varchar(255),userIncome float,primary key(userNum))";
		
		 res = stmt.execute(sqlStatement);
		
		System.out.println("The resut of table statement execution is "+res);
		stmt.close();
		
		
		stmt = connDB.createStatement();
		String insertStatement = "insert into UserInfo values("+123 + ","+"'"+uname+"'"+","+userIncome+")";
		System.out.println("The insert statement is "+insertStatement);
		res = stmt.execute(insertStatement);
	
		System.out.println("The resut of sql insert execution is "+res);
		stmt.close();
		
		stmt = connDB.createStatement();
		ResultSet results = stmt.executeQuery("select * from UserInfo");
		
		while(results.next())
		{
			int num = results.getInt(1);
			String name = results.getString("userName");
			float income = results.getFloat(3);
			System.out.println("The record found with "+num+" "+name + " "+income);
		}
		
		stmt.close();
		results.close();
		connDB.close();
		
	}

}
