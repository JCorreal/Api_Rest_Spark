package bll; // Esta es una clase transversal, desde donde se instancia el Factory Method 

import dal.AccesoDatos;

public class Funciones {
    
    private static AccesoDatos accesoDatos;
            
    public static Controlador CrearControlador()
    {
        accesoDatos = (AccesoDatos) AccesoDatosFactory.GetAccesoDatos();
        return new Controlador(accesoDatos);
    }
}
