package Sistema.util;

import java.util.*;

import Usuarios.util.*;
import Usuarios.util.Tarjeta.*;
import Usuarios.*;

public class Sucursal {
    NombreSucursal nombre;
    Empleado gerente;
    private final String superPassword = "banco123";

    public HashMap<Rol, ArrayList<Usuario>> usuarios = new HashMap<>();
    public ArrayList<Solicitud> solicitudesActualizacion = new ArrayList<>();
    public ArrayList<Movimientos> movimientos = new ArrayList<>();

    public Sucursal(NombreSucursal nombre) {
        this.nombre = nombre;
        usuarios.put(Rol.Capturista, new ArrayList<Usuario>());
        usuarios.put(Rol.Ejecutivo, new ArrayList<Usuario>());
        usuarios.put(Rol.Cliente, new ArrayList<Usuario>());
        usuarios.put(Rol.Inversionista, new ArrayList<Usuario>());
    }

    public Empleado getGerente() {
        return gerente;
    }

    public String getSuperPassword(){
        return superPassword;
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
        usuarios.get(rol).add(usuario);
    }

    public void quitarUsuario(Rol rol, Usuario usuario){
        usuarios.get(rol).remove(usuario);
    }

    public void imprimirUsuarios(){
        System.out.println("Gerente: " + gerente.getNombreCompleto());
        System.out.println("---------------------------------------------------------------");
        System.out.println("Capturistas:");
        for (Usuario user : usuarios.get(Rol.Capturista)) {
            if(user.getRol().equals(Rol.Capturista)){
                System.out.println("   * " + user.getNombreCompleto());
            }
        }

        System.out.println("---------------------------------------------------------------");
        System.out.println("Ejecutivos:");
        for (Usuario user : usuarios.get(Rol.Ejecutivo)) {
            if(user.getRol().equals(Rol.Ejecutivo)){
                System.out.println("   * " + user.getNombreCompleto());
            }
        }

        System.out.println("---------------------------------------------------------------");
        System.out.println("Inversionistas:");
        for (Usuario user : usuarios.get(Rol.Inversionista)) {
            if(user.getRol().equals(Rol.Inversionista)){
                System.out.println("   * " + user.getNombreCompleto());
            }
        }

        System.out.println("---------------------------------------------------------------");
        System.out.println("Cliente:");
        for (Usuario user : usuarios.get(Rol.Cliente)) {
            if(user.getRol().equals(Rol.Cliente)){
                System.out.println("   * " + user.getNombreCompleto());
            }
        }
    }
}
