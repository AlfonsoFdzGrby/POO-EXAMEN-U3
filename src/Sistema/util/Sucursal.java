package Sistema.util;

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
    }

    public NombreSucursal getNombre() {
        return nombre;
    }

    public void agregarUsuario(Rol rol, Usuario usuario){
        usuarios.get(rol).add(usuario);
    }

    public static HashMap<Rol, ArrayList<Usuario>> getUsuarios() {
        return usuarios;
    }
}
