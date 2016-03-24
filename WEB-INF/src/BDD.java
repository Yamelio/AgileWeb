import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class BDD {

	private InputStream inputStream;
	private static Connection con;

	public void open() throws IOException {
		try {
			Properties prop = new Properties();
			String propFileName = "bdd.txt";
			inputStream = getClass().getClassLoader().getResourceAsStream(
					propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '"
						+ propFileName + "' not found in the classpath");
			}
			String driver = prop.getProperty("driver");
			Class.forName(driver);
			String url = prop.getProperty("url");
			String nom = prop.getProperty("nom");
			String mdp = prop.getProperty("mdp");
			con = DriverManager.getConnection(url, nom, mdp);

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
	}

	public void close() throws SQLException {
		con.close();
	}

	public ResultSet execute(String query) throws SQLException {
		return con.createStatement().executeQuery(query);
	}

	public int update(String query) throws SQLException {
		return con.createStatement().executeUpdate(query);
	}
	
	public ResultSet execute(PreparedStatement ps) throws SQLException {
		return ps.executeQuery();
	}

	public int update(PreparedStatement ps) throws SQLException {
		return ps.executeUpdate();
	}

	public PreparedStatement preparedStatement(String string) {
		try {
			return con.prepareStatement(string);
		} catch (SQLException e) {
			return null;
		}
	}

}
