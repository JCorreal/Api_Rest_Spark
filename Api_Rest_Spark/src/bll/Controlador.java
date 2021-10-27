package bll; // Este es el Controlador Manager general de todo el sistema. Todo tiene que pasar por esta clase

import bo.Usuario;
import dal.IAccesoDatos;
import java.util.List;

public class Controlador {
    
    private IAccesoDatos _accesoDatos; 
    
    public Controlador(IAccesoDatos accesoDatos)
    {
       _accesoDatos = accesoDatos;
    }
    
    public List<Usuario> obtenerListadoUsuarios()
    {
       return _accesoDatos.obtenerListadoUsuarios();
    }
    
    public Usuario obtenerUsuario(int DatoBuscar)
    {
       return  _accesoDatos.obtenerUsuario(DatoBuscar);
    }
    
    public int guardarUsuario(Object objeto) 
    {   
        return _accesoDatos.guardarUsuario((Usuario) objeto);        
    }
    
    public int eliminarUsuario(int DatoEliminar)
    {
        return _accesoDatos.eliminarUsuario(DatoEliminar);
    }
 
}
