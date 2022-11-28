package srv;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticoloDao;
import it.domenico.Articolo;


public class ArticoloSrv extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	{
		ArticoloDao aDao=new ArticoloDao();
		String descrizione=request.getParameter("descrizione");
		String quant=request.getParameter("quantita");
		int quantita= Integer.parseInt(quant);
		Articolo a = new Articolo();
		a.setDescrizione(descrizione);
		a.setQuantita(quantita);
		
		aDao.insert(a);
		select(a, request, response);
		
	}
	public void select(Articolo a,HttpServletRequest request, HttpServletResponse response) {
		
		Connection conn=null;
		PreparedStatement cmd=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url="jdbc:mysql://localhost:3306/romagna_db";
			String name="dom";
			String password="rom";
			
			try {
				conn = DriverManager.getConnection(url, name, password);
				String query_in="SELECT * FROM articolo";
				
				try {
					cmd = conn.prepareStatement(query_in);
					
					try {
						Writer w=response.getWriter();
						ResultSet ris=cmd.executeQuery();
						w.write("<table>");
						while(ris.next()){  
							w.write("<tr><td>"+ris.getString(1)+"</td>"
									+"<td>"+ris.getString(2)+"</td>"
									+"<td>"+ris.getString(3)+"</td></tr>");
						}
						w.write("</table>");
						
					}
					catch (Exception e) {
						
						System.out.println(e);
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
}
