package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.domenico.Articolo;

public class ArticoloDao {

	public void insert(Articolo a) {
		
		Connection conn=null;
		PreparedStatement cmd=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url="jdbc:mysql://localhost:3306/romagna_db";
			String name="dom";
			String password="rom";
			
			try {
				conn = DriverManager.getConnection(url, name, password);
				String query_in="INSERT INTO articolo(descrizione, quantita)VALUES(?,?)";
				
				try {
					cmd = conn.prepareStatement(query_in);
					
					cmd.setString(1, a.getDescrizione());
					
					cmd.setInt(2, a.getQuantita());
					
					try {
						
						cmd.execute();
					}
					catch (Exception e) {
						
						System.out.println(e);
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
