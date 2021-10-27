package bo; // Clase que representa la estructura en BD para Usuarios
 
public class Usuario {
    private int usuario_id = 0;
    private String nombres;
    private String apellidos;

    public Usuario(int usuario_id, String nombres, String apellidos) {
        this.usuario_id = usuario_id;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public Usuario() { }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }
    
    

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    
}
