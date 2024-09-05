package tw.RC.tutor;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.print.DocFlavor.INPUT_STREAM;

import tw.RC.apis.Bike;

/*
SELECT e.EmployeeID, e.LastName, SUM(od.UnitPrice*od.Quantity) total FROM orders o
JOIN orderdetails od on (o.OrderID = Od.OrderID)
JOIN employees e on (o.EmployeeID = e.EmployeeID)
GROUP BY o.EmployeeID
ORDER BY total DESC

id = 5 =>75567.7500

SELECT sum(UnitPrice*Quantity) FROM orderdetails 
WHERE orderID in ( 
SELECT orderID FROM orders 
WHERE employeeID = 5 
);

 */



public class JDBC15 {
	private static final String USER = "root";// 固定常數
	private static final String PASSWD = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/nothwind";
	private static final String SQL_QUERY = "SELECT e.EmployeeID, e.LastName, SUM(od.UnitPrice*od.Quantity) total "+ 
											"FROM orders o " +
											"JOIN orderdetails od on (o.OrderID = Od.OrderID) " +
											"JOIN employees e on (o.EmployeeID = e.EmployeeID) " +
											"GROUP BY o.EmployeeID " +
											"ORDER BY total DESC ";

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.put("user", USER);
		prop.put("password", PASSWD);

		try {
			Connection conn = DriverManager.getConnection(URL, prop);
			PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("EmployeeID");
				String name = rs.getString("LastName");
				String total = rs.getString("total");
				System.out.printf("%s : %s : %s\n", id, name, total);
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
