package Sistema;

import java.time.LocalDate;

import Sistema.util.*;
import Usuarios.Cliente;
import Usuarios.Empleado;
import Usuarios.Inversionista;
import Usuarios.util.Rol;

public class Sistema {
    static Sucursal acueducto;
    static Sucursal madero;

    public Sistema(){
        madero = new Sucursal(NombreSucursal.Madero);
        acueducto = new Sucursal(NombreSucursal.Acueducto);
        
        //--------------------------------------- ACUEDUCTO ---------------------------------------

        //Gerente
        Empleado gerenteAc = new Empleado("Samuel", "Villegas Carmona", LocalDate.of(2005, 10, 26), "Morelia",
        "Michoacan", true, "samuel999", "samuelvillegas", "Lomas #12", NombreSucursal.Acueducto, 400000, Rol.Gerente);
        acueducto.setGerente(gerenteAc);

        //Clientes
        Cliente clienteAc = new Cliente("Oscar", "Arias Corona", LocalDate.of(2005, 9, 28),
        "Morelia", "Michoacan", true, "oscar133", "oscarcorona",
        "Terranova #259", NombreSucursal.Acueducto);
        acueducto.agregarUsuario(Rol.Cliente, clienteAc);

        Cliente cliente2Ac = new Cliente("Maria", "Lopez Reyes", LocalDate.of(1989, 8, 12), "Puruandiro",
        "Colima", false, "maria666", "marialopez", "Puruandiro #302", NombreSucursal.Acueducto);
        acueducto.agregarUsuario(Rol.Cliente, cliente2Ac);

        //Ejecutivo
        Empleado empleadoAc = new Empleado("Alfonso", "Fernandez Garibay", LocalDate.of(2005, 4, 29), "Morelia",
        "Michoacan", true, "alfonso444", "alfonsomaron", "Manchester #111", NombreSucursal.Acueducto, 18500, Rol.Ejecutivo);
        acueducto.agregarUsuario(Rol.Ejecutivo, empleadoAc);
          
        //Capturista
        Empleado empleado2Ac = new Empleado("Jose", "Perez Gonzalez", LocalDate.of(1993, 3, 18), "Patzcuaro",
        "Michoacan", true, "jose333", "joseperez", "Av Hidalgo #14", NombreSucursal.Acueducto, 25000, Rol.Capturista);
        acueducto.agregarUsuario(Rol.Capturista, empleado2Ac);

        //Inversionista
        Inversionista inversionistaAc = new Inversionista("Sandra", "Suarez Galvan", LocalDate.of(1966, 11, 3), "Monterrey",
        "Nuevo Leon", false, "sandra555", "sandrasuarez", NombreSucursal.Acueducto);
        acueducto.agregarUsuario(Rol.Inversionista, inversionistaAc);

        //--------------------------------------- MADERO ---------------------------------------

        //Gerente
        Empleado gerenteMad = new Empleado("Rodrigo", "Sanchez Martinez", LocalDate.of(2000, 3, 25), "Nuevo León",
        "Monterrey", true, "RodriSaMa", "sanchezMartinez525", "Calle Matamoros #953", NombreSucursal.Madero, 480000, Rol.Gerente);
        madero.setGerente(gerenteMad);
        
        //Clientes
        Cliente clienteMad = new Cliente("Javier", "Hernandez Camaney", LocalDate.of(1998, 5, 23), "Lázaro Cárdenas", 
        "Michoacan", true, "JaviHC", "tukituki123", "Batalla de Matamoros #538", NombreSucursal.Madero);
        madero.agregarUsuario(Rol.Cliente, clienteMad);

        Cliente cliente2Mad = new Cliente("Andrea", "Ortiz Chavez", LocalDate.of(2003, 5, 8), "Las Flores", 
        "Chihuahua", false, "AndyOrtiz", "gatitobonito23", "Av. Principal #385", NombreSucursal.Madero);
        madero.agregarUsuario(Rol.Cliente, cliente2Mad);

        //Ejecutivo
        Empleado empleadoMad = new Empleado("Luis", "Jimenez Alvarado", LocalDate.of(1983, 2, 15), "Saltillo", 
        "Cohahuila", true, "LuisJiAl", "LuiJimAlv125", "Calle Aguilar Huerta #569", NombreSucursal.Madero, 15000, Rol.Ejecutivo);
        madero.agregarUsuario(Rol.Ejecutivo, empleadoMad);

        //Capturista
        Empleado empleado2Mad = new Empleado("Gabriela", "Andrade Benitez", LocalDate.of(1995, 3, 15), "Hermosillo",
        "Sonora", false, "GabyAndrade", "HeladoDeVainilla23", "Calle Victoriano Huerta #678", NombreSucursal.Madero, 20000, Rol.Capturista);
        madero.agregarUsuario(Rol.Capturista, empleado2Mad);

        //Inversionista
        Inversionista inversionistaMad = new Inversionista("Fernando", "Aguilar Benitez", LocalDate.of(2000, 12, 25), "Morelia", 
        "Michoacán", false, "FerAguilar", "FerAguilar1346", NombreSucursal.Madero);
        madero.agregarUsuario(Rol.Inversionista, inversionistaMad);

    }

    public Sucursal getAcueducto() {
        return acueducto;
    }

    public Sucursal getMadero() {
        return madero;
    }

    public static Sucursal getAcueductoStatic(){
        return acueducto;
    }

    public static Sucursal getMaderoStatic(){
        return madero;
    }

}
