package Usuarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Sistema.util.NombreSucursal;
import Sistema.util.SucursalActual;
import Sistema.util.Tools;
import Usuarios.util.DatosComun;
import Usuarios.util.Rol;
import Usuarios.util.Usuario;
import Usuarios.util.UsuarioEnSesion;

public class Inversionista extends Usuario {
    LocalDate fechaMovimiento;
    LocalDate fechaRegistro;
    double fondosAportados;

    public Inversionista(String nombre, String apellidos, LocalDate fechaDeNacimiento, String ciudad, String estado,
            boolean eshombre, String nombreUsuario, String contraseña, NombreSucursal sucursal) {
        super(nombre, apellidos, fechaDeNacimiento, ciudad, estado, eshombre, nombreUsuario, contraseña, Rol.Inversionista, sucursal);
        this.fechaRegistro = LocalDate.now();
        this.fondosAportados = 0;
        this.fechaMovimiento = LocalDate.now();
    }

    public static void registrarInversionista(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ArrayList<String> datosComun = DatosComun.obtenerDatosComun();
        String nombre = datosComun.get(0);
        String apellido = datosComun.get(1);
        LocalDate fechaNacimiento = LocalDate.parse(datosComun.get(2), format);
        String ciudad = datosComun.get(4);
        String estado = datosComun.get(5);
        boolean eshombre = datosComun.get(6).equalsIgnoreCase("hombre");
        String nombreUsuario = datosComun.get(7);
        String contrasena = datosComun.get(8);
        SucursalActual.getInstancia().getSucursalActual().usuarios.get(Rol.Inversionista)
        .add(new Inversionista(nombre, apellido, fechaNacimiento, ciudad, estado, eshombre, nombreUsuario, contrasena, SucursalActual.getInstancia().getSucursalActual().getNombre()));
    }

    public void invertir(){
        Tools.printHeader("INVERTIR");
        System.out.printf("Cuanto dinero desea invertir en el banco %s ",
        SucursalActual.getInstancia().getSucursalActual().getNombre());
        double inversion = Tools.nextDouble();
        this.fondosAportados += inversion;
    }

    public void retirar(){
        Tools.printHeader("RETIRAR");
        System.out.printf("Cuanto dinero desea retirar del banco %s ",
        SucursalActual.getInstancia().getSucursalActual().getNombre());
        double inversion = Tools.nextDouble();
        this.fondosAportados -= inversion;
    }
    
}
