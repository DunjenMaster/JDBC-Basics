import java.sql.Connection;
import java.sql.DriverManager;
//import static java.sql.DriverManager.getConnection;
import java.sql.Statement;
import java.sql.ResultSet;



class JDBCCOnnection {
	public static void main(String[] args)throws Exception{

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/girrafe", "", "");
		System.out.println("Connection Estabilished Successfully!");

		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery("SELECT * FROM student");
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
	}
}