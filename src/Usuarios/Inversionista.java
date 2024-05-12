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

    @Override
    public String toString() {
        return String.format("%s   * Fecha de registro: %s\n   * Fecha de último movimiento: %s\n   * Fondos aportados: %.2f\n   * Fecha de registro: %s\n", super.toString() , this.fechaRegistro.toString(), this.fechaMovimiento.toString(), this.fondosAportados);
    }

    public static void registrarInversionista(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ArrayList<String> datosComun = DatosComun.obtenerDatosComun();
        String nombre = datosComun.get(0);
        String apellido = datosComun.get(1);
        LocalDate fechaNacimiento = LocalDate.parse(datosComun.get(2), format);
        String ciudad = datosComun.get(3);
        String estado = datosComun.get(4);
        boolean eshombre = datosComun.get(5).equalsIgnoreCase("hombre");
        String nombreUsuario = datosComun.get(6);
        String contrasena = datosComun.get(7);
        SucursalActual.getInstancia().getSucursalActual().usuarios.get(Rol.Inversionista)
        .add(new Inversionista(nombre, apellido, fechaNacimiento, ciudad, estado, eshombre, nombreUsuario, contrasena, SucursalActual.getInstancia().getSucursalActual().getNombre()));
        System.out.println("\n== EL INVERSIONISTA FUE REGISTRADO EXITOSAMENTE! ==\n");
        Tools.next();
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

    public static void printInversionistas(){
        Tools.printHeader("INVERSIONISTAS REGISTRADOS EN LA SUCURSAL");
        for (Usuario inversionista : SucursalActual.getInstancia().getSucursalActual().usuarios.get(Rol.Inversionista)) {
            System.out.println(inversionista.toString());
        }
    }
    
}
