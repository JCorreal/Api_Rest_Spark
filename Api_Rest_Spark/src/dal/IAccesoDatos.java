package dal; // Interface que expone todo lo que el DAL (Capa Acceso Datos) implementa  

import bo.Usuario;
import java.util.List;

public interface IAccesoDatos {
    
  public List<Usuario> obtenerListadoUsuarios();
  public Usuario obtenerUsuario (int DatoBuscar);
  public int guardarUsuario(Usuario usuario);
  public int eliminarUsuario(int DatoEliminar);
  
}
