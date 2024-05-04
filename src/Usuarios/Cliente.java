package Usuarios;

import java.time.*;

import Usuarios.util.Usuario;
import Sistema.util.*;

public class Cliente extends Usuario {
    String rfc;
    String curp;
    String direccion;
    LocalDate fechaRegistro;
    Sucursal sucursal;

    public Cliente(String nombre, String apellidos, LocalDate fechaDeNacimiento, String ciudad, String estado,
            boolean eshombre, String direccion, LocalDate fechaRegistro, Sucursal sucursal) {
        super(nombre, apellidos, fechaDeNacimiento, ciudad, estado, eshombre);
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.sucursal = sucursal;
        this.curp = Generators.GenerateCURP(nombre, apellidos, fechaRegistro, eshombre, estado);
    }

    

}
