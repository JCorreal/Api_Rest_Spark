package bll;
import bo.Usuario;
import java.util.List;
 
public class Servicio {

    
    private List<Usuario> usuariosList;

    
    public Servicio() {}

    // Realiza una busqueda en la base de datos y retorna los mismos en un array      
    public List<Usuario> buscarTodo() {
      Controlador controlador = Funciones.CrearControlador();
      usuariosList = controlador.obtenerListadoUsuarios();
      return usuariosList;
    }
    
    // Realiza una busqueda en la base de datos de un registro dado su ID     
    public Usuario buscar(int usuario_id) {
      Controlador controlador = Funciones.CrearControlador();  
      Usuario usuario = (Usuario) controlador.obtenerUsuario(usuario_id);
      return usuario;
    }

    // Registra o actualiza un usuario en la base de datos     
    public boolean agregar(Usuario usuario) {
        int Resultado = -1;  
        Controlador controlador = Funciones.CrearControlador();  
        Resultado = controlador.guardarUsuario(usuario);
        if (Resultado == 0)
        {
            return true;
        }    
        else
        {            
           return false;
        } 
    }
    
    public boolean eliminar(int usuario_id) {
      int Resultado = -1;  
      Controlador controlador = Funciones.CrearControlador();  
      Resultado = controlador.eliminarUsuario(usuario_id);  
      if (Resultado == 0)
      {
           return true;   
      }    
      else 
      {            
          return false;
      }       
    } 
}