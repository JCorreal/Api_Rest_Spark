package bll;

import dal.AccesoDatos;
import dal.IAccesoDatos;
  
/*  Aquí emplearemos el patrón de Creación: Factory Method al cual
    le pasaremos como argumento la Interfaz IAccesoDatos, e invocaremos
    el método: CrearControlador alojado en la clase Funciones que nos  
    devolverá, ya la clase AccesoDatos instanciada.
 
    Este patrón nos ahorra trabajo, puesto que nos libera sobre la forma 
    correcta de crear objetos, dada su flexibilidad al utilizar una clase 
    constructora (al estilo del Abstract Factory). 
 */

public class AccesoDatosFactory {
    
    public static IAccesoDatos GetAccesoDatos()
    {
        return new AccesoDatos();
    }
}
