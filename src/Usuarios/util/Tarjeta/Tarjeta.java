package Usuarios.util.Tarjeta;

import Usuarios.*;
import Sistema.util.*;

import java.time.*;

public class Tarjeta {
    private TipoDeTarjeta tipoDeTarjeta;
    private String numTarjeta; // 16 dígitos
    private LocalDate fechaCreacion;
    private double saldo;
    private int cvv; // 3 dígitos
    private String clabe; // 18 dígitos
    private LocalDate fechaVencimiento; // 5 años después de creada
    private LocalDateTime ultimoMovimiento; // establecer en .now()

    public Tarjeta(TipoDeTarjeta tipoDeTarjeta, double saldo, NombreSucursal sucursal) {
        this.tipoDeTarjeta = tipoDeTarjeta;
        this.numTarjeta = Generators.GenerateCardNum();
        this.fechaCreacion = LocalDate.now();
        this.cvv = Generators.GenerateCVV();
        this.clabe = Generators.GenerateCLABE(sucursal);
        this.fechaVencimiento = LocalDate.now().plusYears(5);
        this.ultimoMovimiento = LocalDateTime.now();
        this.saldo = saldo;
    }

    // TODO
    @Override
    public String toString() {
        return String.format(clabe, null);
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void realizarRetiro(double cantidad){
        this.saldo -= cantidad;
    }

    public void realizarDeposito(double cantidad){
        this.saldo += cantidad;
    }

    
}
