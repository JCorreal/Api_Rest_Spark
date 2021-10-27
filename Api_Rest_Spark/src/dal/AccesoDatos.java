package dal; // DAL: Data Access Layer - Capa Acceso Datos con MySQL

import bo.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccesoDatos implements IAccesoDatos{
   private  CallableStatement cst;  // Procedimientos Almacenados
   private  ResultSet Rset;         // Cursor
   private  Connection con;         // Alias para la Conexión 
   
  
   @Override
    public List<Usuario> obtenerListadoUsuarios()
    {        
     List<Usuario> ListaUsuarios  = new ArrayList<>();
     try
     {  
       con = Conexion.obtenerConexion(); 
       cst = con.prepareCall("{call spr_Listados(?)}");
       cst.setInt(1, 0);
       Rset = cst.executeQuery();
        while(Rset.next())
        {               
              Usuario usuario = new Usuario();      
              usuario.setUsuario_id(Integer.parseInt(Rset.getString(1)));
              usuario.setNombres(Rset.getString(2));
              usuario.setApellidos(Rset.getString(3));
              ListaUsuarios.add(usuario);
        }
        Rset.close();
     }             
     catch (SQLException ex) 
     {
        System.out.println("Error : " +ex);
        liberarRecursos();
     }
     catch (Exception ex) 
     {
        System.out.println("Error : " +ex);
        liberarRecursos();
     }
     finally
     {
        liberarRecursos();
     }
     return ListaUsuarios;   
    }   
    
   @Override
    public Usuario obtenerUsuario(int DatoBuscar)
    {     
      Usuario usuario = new Usuario();
      try
      {  
        con = Conexion.obtenerConexion(); 
        cst = con.prepareCall("{call spr_Listados(?)}");
        cst.setInt(1, DatoBuscar);
        Rset = cst.executeQuery();
        while(Rset.next())
        {                     
              usuario.setUsuario_id(Integer.parseInt(Rset.getString(1)));
              usuario.setNombres(Rset.getString(2));
              usuario.setApellidos(Rset.getString(3));        
        }
        Rset.close();  
      }     
      catch (SQLException ex) 
      {
        System.out.println("Error : " +ex);
        liberarRecursos();
      }
      catch (Exception ex) 
      {
        System.out.println("Error : " +ex);
        liberarRecursos();
      }
      finally
      {
        liberarRecursos();
      }
      return usuario;
    }
   
   @Override
    public synchronized int guardarUsuario(Usuario usuario) 
    {
        int Resultado = -1;
          try {
              con=Conexion.obtenerConexion();           
              cst = con.prepareCall("{call spr_IUUsuarios (?, ?, ?, ?)}");                  
              cst.setInt(1, usuario.getUsuario_id());
              cst.setString(2, usuario.getNombres());
              cst.setString(3, usuario.getApellidos());             
              cst.registerOutParameter(4, java.sql.Types.INTEGER);
              cst.executeUpdate();
              Resultado = cst.getByte(4);           
          }
          catch (SQLException ex) {
              System.out.println("Error : " +ex);            
          } 
          catch (Exception ex) {
              System.out.println("Error : " +ex);            
          } 
          finally{
                   liberarRecursos();
              }
           return Resultado;
      }
    
   @Override
    public synchronized int eliminarUsuario(int DatoEliminar) 
    {
        int Resultado = -1;
           try
           {            
            con=Conexion.obtenerConexion();
            cst = con.prepareCall("{call spr_DUsuario (?,?)}");
            cst.setInt(1, DatoEliminar);            
            cst.registerOutParameter(2,java.sql.Types.INTEGER);
            cst.execute();
            Resultado = cst.getByte(2);           
           }           
           catch (SQLException ex) {
           System.out.println("Error: " +ex);
           liberarRecursos();
           }
           catch (Exception ex) {
           System.out.println("Error: " +ex);
           liberarRecursos();
           }
           finally{
	      liberarRecursos();
	    } 
           return Resultado;
      }
    
 private synchronized void liberarRecursos()          
  { // Garantizar que se cierren todos los objetos asociados a la Conexion
        try {            
            cst.close();
            con.close();            
        } catch (Exception ex) {
           System.out.println("Error Liberando Recursos: " +ex);           
        }              
  }   

}
