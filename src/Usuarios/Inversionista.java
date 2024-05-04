package Usuarios;

import java.time.LocalDate;

import Usuarios.util.Usuario;

public class Inversionista extends Usuario {
    LocalDate fechaMovimiento;
    LocalDate fechaRegistro;
    double fondosAportados;

    public Inversionista(String nombre, String apellidos, LocalDate fechaDeNacimiento, String ciudad, String estado,
            boolean eshombre) {
        super(nombre, apellidos, fechaDeNacimiento, ciudad, estado, eshombre);
        this.fechaRegistro = LocalDate.now();
        this.fondosAportados = 0;
        this.fechaMovimiento = LocalDate.now();
    }

    public void proveerFondos(double fondos){
        this.fondosAportados+=fondos;
        System.out.printf("$%.2f han sido aportados al banco por %s", fondos, getNombreCompleto());
    }

    public void retirarFondos(double fondos){
        
    }
    
}
