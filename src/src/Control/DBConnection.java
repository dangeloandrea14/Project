/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {

	//cambiare porta -->3306
	
	private static String url = "jdbc:mysql://localhost:3308/libreriamanoscritti?useSSL=false";
	private static String usr = "root";
	private static String psw = "root";
	
    public static ResultSet query(String str) {
	try {

	    
	    Connection con = DriverManager.getConnection(url, usr, psw);
	    Statement stm = con.createStatement();
	    ResultSet result = stm.executeQuery(str);

	    return result;

	} catch (Exception e) {

	    System.out.println(e.getMessage());
	    return null;
	}
    }//end method

    //method used to insert users into DB
    public static boolean update(String email, String password, String nome, String titolodistudio, String professione) {

	try {

	
	    Connection con = DriverManager.getConnection(url, usr, psw);
	    Statement stm = con.createStatement();
	    stm.executeUpdate("INSERT INTO utente(ruolo,email,password,nome,titolodistudio,professione)"
		    + " values(1,'" + email + "'," + "'" + password + "'," + "'" + nome + "',"
		    + "'" + titolodistudio + "'," + "'" + professione + "'" + ")");
	    con.close();
	    return true;

	} catch (Exception e) {

	    System.out.println(e.getMessage());
	    return false;
	}
    }//end update

    public static boolean uploadPage(int numero, int manoscritto,String scanPath) {
	try {
	 
	    Connection con = DriverManager.getConnection(url, usr, psw);
	    Statement stm = con.createStatement();
	    stm.executeUpdate("insert into Page (Numero, Manoscritto, Scanpath, Trascrizione) "
		    + "values(" + numero + "," + manoscritto +"," + "'" + scanPath + "'" + ",\"Trascrizione non disponibile\")");
	   System.out.println("insert into Page (Numero, Manoscritto, Scanpath, Trascrizione) "
			    + "values(" + numero + "," + manoscritto +"," + "'" + scanPath + "'" + ",\"Trascrizione non disponibile\")");
	    return true;
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return false;
	}
    }

}
