import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
		
		String url  = "jdbc:mysql://localhost:3306/db-nations";
		String user = "root";
		String password = "root";
		
		try (Connection con = DriverManager.getConnection(url, user, password)){
			
			System.out.println("Connesso!");
		    
			String sql = " SELECT countries.country_id , countries.name , regions.name AS regione , continents.name AS continente\r\n"
					+ "FROM countries \r\n"
					+ "JOIN regions ON countries.region_id = regions.region_id \r\n"
					+ "JOIN continents ON regions.continent_id = continents.continent_id \r\n"
					+ "ORDER BY continents.name; ";
			
			try (PreparedStatement ps = con.prepareStatement(sql)) {
							
							
							try (ResultSet rs = ps.executeQuery()) {
								
								while(rs.next()) {
									int countryId = rs.getInt(1);
									String countryName = rs.getString(2);
									String regionsName = rs.getString(3);
									String continentsName = rs.getString(4);
									
									System.out.println( countryId + "-"  + countryName + "-" + regionsName + "-" + continentsName );
								}
								
								
							}				
						} catch (SQLException ex) {
							System.err.println("Query not well formed");
						}
			
			
		} catch (SQLException ex) {
			System.err.println("Error during connection to db");
		}
	
	
	}
}
