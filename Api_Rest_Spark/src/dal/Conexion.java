package dal; // Clase que nos devuelve la conexion con el proveedor que se desee

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion
{
    
   public static synchronized Connection obtenerConexion() {
        Connection cn = null;
        try 
        {   
           Class.forName("com.mysql.jdbc.Driver");
           cn = DriverManager.getConnection("jdbc:mysql://localhost/ApiRest_DB","root","");   
        }
        catch (Exception ex) 
        {
            cn = null;
        }
        finally
        {
            return cn;
        }
    }
}
