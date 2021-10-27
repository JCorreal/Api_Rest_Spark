package api_rest_spark;

import bll.Respuesta;
import bll.Servicio;
import bo.Usuario;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
// Librerias Spark
import static spark.Spark.after;
import static spark.Spark.delete;
import static spark.Spark.get; 
import static spark.Spark.post;
import static spark.Spark.put;

public class Api_Rest_Spark {
    private static Servicio servicio = new Servicio();
    
    public static void main(String[] args) {        
         
        Gson gson = new Gson();

        // El tipo de contenido de respuesta para todos las solicitudes sera JSON        
        after((request, response) -> {
            response.type("application/json");
        });
      
        // retorna lista completa de usuarios
        get("/usuarios", (request, response)
                -> {
            response.status(200); // Codigo de respuesta
            List result = servicio.buscarTodo(); // obtiene listado de Usuarios
            return result;
        }, gson::toJson);

        // retorna registro de un usuario si existe, sino retorna mensaje
        get("/usuarios/:usuario_id", (request, response)
                -> {
            Usuario usuario = servicio.buscar(Integer.parseInt(request.params(":usuario_id")));
            if (usuario != null) {//registro existe
                response.status(200);
                return usuario;
            } else {//registro no existe
                response.status(404); // 404 Not found
                return new Respuesta("Usuario no encontrado");
            }
        }, gson::toJson);
        
         // Agrega un nuevo registro Los datos de entrada corresponden a un JSON        
         post("/usuarios", (request, response)
                -> {
            if (request.body() != null) {//existe contenido                
                try {
                    //Obtiene objeto JSON de tipo Usuario
                    Usuario usuario = gson.fromJson(request.body(), Usuario.class);
                    servicio.agregar(usuario);
                    response.status(201);//Codigo de respuesta                
                    return new Respuesta("Nuevo Usuario agregado");
                } catch (JsonParseException ex) {
                    response.status(400);//Bad Request
                    return new Respuesta("El objeto JSON no es valido");
                }
            } else {
                response.status(400);//Bad Request
                return new Respuesta("El objeto JSON es obligatorio");
            }
        }, gson::toJson);
         
        // Actualiza un Usuario         
         put("/usuarios", (request, response)
                 -> {
            if (request.body() != null) {//existe contenido                
                try {
                    //Obtiene objeto JSON de tipo Usuario
                    Usuario usuario = gson.fromJson(request.body(), Usuario.class);
                    servicio.agregar(usuario);
                    response.status(201);//Codigo de respuesta                
                    return new Respuesta("Registro actualizado");
                } catch (JsonParseException ex) {
                    response.status(400);//Bad Request
                    return new Respuesta("El objeto JSON no es valido");
                }
            } else {
                response.status(400);//Bad Request
                return new Respuesta("El objeto JSON es obligatorio");
            }
        }, gson::toJson);
         
         
        // Elimina un registro dado su ID         
        delete("/usuarios/:usuario_id", (request, response)
                -> {
            response.status(200);//Codigo de respuesta 
            if (Integer.parseInt(request.params(":usuario_id")) != 0) {
                servicio.eliminar(Integer.parseInt(request.params(":usuario_id")));
                return new Respuesta("Usuario eliminado");
            } else {//no existe
                response.status(400);//Codigo de respuesta  
                return new Respuesta("No existe usuario");
            }
        }, gson::toJson);
    }    
}
