package Sistema.util;

import java.util.*;

import Usuarios.util.*;
import Usuarios.util.Tarjeta.*;
import Usuarios.*;

public class Sucursal {
    NombreSucursal nombre;
    Empleado gerente;

    public static HashMap<Rol, ArrayList<Usuario>> usuarios = new HashMap<>();
    public static ArrayList<Solicitud> solicitudesActualizacion = new ArrayList<>();

    public Sucursal(NombreSucursal nombre) {
        this.nombre = nombre;
        usuarios.put(Rol.Capturista, new ArrayList<Usuario>());
        usuarios.put(Rol.Ejecutivo, new ArrayList<Usuario>());
        usuarios.put(Rol.Cliente, new ArrayList<Usuario>());
        usuarios.put(Rol.Inversionista, new ArrayList<Usuario>());
    }

    public void agregarSolicitud(Solicitud solicitud){
        solicitudesActualizacion.add(solicitud);
    }

    public NombreSucursal getNombre() {
        return nombre;
    }

    public void setGerente(Empleado gerente) {
        this.gerente = gerente;
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
