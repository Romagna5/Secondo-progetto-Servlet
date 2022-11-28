package it.domenico;


import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import srv.ArticoloSrv;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		 Tomcat tomcat = new Tomcat();

	        tomcat.setPort(8080);

	        Context ctx = (Context) tomcat.addContext("/articolo", new File(".").getAbsolutePath());
	        
	        ArticoloSrv articolosrv = new ArticoloSrv();
	        
	        Tomcat.addServlet(ctx, "articolo", articolosrv);

	        ctx.addServletMapping("/*", "articolo");

	        try {
				tomcat.start();
			} catch (LifecycleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	        tomcat.getServer().await();
		
		
	}

}
