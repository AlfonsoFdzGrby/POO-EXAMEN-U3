package Usuarios;

import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;

import Usuarios.util.*;
import Usuarios.util.Tarjeta.*;
import Sistema.util.*;

public class Cliente extends Usuario {
    String rfc;
    String curp;
    String direccion;
    LocalDate fechaRegistro;
    NombreSucursal sucursal;
    HashMap<TipoDeTarjeta, ArrayList<Tarjeta>> tarjetas = new HashMap<>();

    public Cliente(String nombre, String apellidos, LocalDate fechaDeNacimiento, String ciudad, String estado,
            boolean eshombre, String nombreUsuario, String contraseña, String direccion, NombreSucursal sucursal) {
        super(nombre, apellidos, fechaDeNacimiento, ciudad, estado, eshombre, nombreUsuario, contraseña);
        this.direccion = direccion;
        this.fechaRegistro = LocalDate.now();
        this.sucursal = sucursal;
        this.curp = Generators.GenerateCURP(nombre, apellidos, fechaRegistro, eshombre, estado);
        tarjetas.put(TipoDeTarjeta.Simplicity, new ArrayList<Tarjeta>());
        tarjetas.put(TipoDeTarjeta.Platino, new ArrayList<Tarjeta>());
        tarjetas.put(TipoDeTarjeta.Oro, new ArrayList<Tarjeta>());
    }

    public void agregarTarjeta(TipoDeTarjeta tipo, Tarjeta tarjeta){
        this.tarjetas.get(tipo).add(tarjeta);
    }

}
