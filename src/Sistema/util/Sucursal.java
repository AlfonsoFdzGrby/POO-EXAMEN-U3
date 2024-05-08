package Sistema.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import Usuarios.util.*;
import Usuarios.*;

public class Sucursal {
    NombreSucursal nombre;
    Empleado gerente;

    public static HashMap<Rol, ArrayList<Usuario>> usuarios = new HashMap<>();

    public Sucursal(NombreSucursal nombre) {
        this.nombre = nombre;
        usuarios.put(Rol.Capturista, new ArrayList<Usuario>());
        usuarios.put(Rol.Ejecutivo, new ArrayList<Usuario>());
        usuarios.put(Rol.Cliente, new ArrayList<Usuario>());
        usuarios.put(Rol.Inversionista, new ArrayList<Usuario>());
        usuarios.get(Rol.Cliente).add(new Cliente("Paquito", "Stanley Camaney", LocalDate.of(2005, 2, 19), "Morelia", "Michoacán", true, "pqstan", "1234", "Calle banqueta #523",NombreSucursal.Acueducto));
    }

    public NombreSucursal getNombre() {
        return nombre;
    }

    public void agregarUsuario(Rol rol, Usuario usuario){
        ArrayList<Usuario> user = new ArrayList<>();
        user.add(usuario);
        usuarios.get(rol).addAll(user);
    }

    public static HashMap<Rol, ArrayList<Usuario>> getUsuarios() {
        return usuarios;
    }

    public static void quitarUsuario(Rol rol, Usuario usuario){
        usuarios.get(rol).remove(usuario);
    }
}
