package bag;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Bag {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public Bag() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/PizzaKing";
			String dbID = "root";
			String dbPassword = "mirim2";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			System.out.println("연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}