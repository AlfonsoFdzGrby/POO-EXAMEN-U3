package Usuarios.util;

import java.time.LocalDateTime;
import java.util.Scanner;

import Sistema.util.SucursalActual;
import Sistema.util.Tools;
import Usuarios.Inversionista;

public class Movimientos {
    static Scanner sc=new Scanner(System.in);
    LocalDateTime fechaMovimiento;
    String NombreInversionista;
    Double movimientoDinero;
    String tipoMovimiento;

    public Movimientos(String NombreInversionista, double movimientoDinero, String tipoMovimiento){
        this.fechaMovimiento=LocalDateTime.now();
        this.NombreInversionista=NombreInversionista;
        this.movimientoDinero = movimientoDinero;
    }

    public String getNombreInversionista(){
        return this.NombreInversionista;
    }

    @Override
    public String toString(){
        return String.format(" * Nombre del Inversionista: %s\n * Se realizo el movimiento: %s\n * Dinero que se movio: %f \n * Tipo de Movimiento: %s\n",
        NombreInversionista, fechaMovimiento, movimientoDinero, tipoMovimiento);
    }

    public static void ImprimirOrdenes(){
        if (SucursalActual.getInstancia().getSucursalActual().movimientos.isEmpty()) {
            System.out.println("\nNO HAY MOVIMIENTOS EN EL SISTEMA\n");
            Tools.next();
            return;
        }
        Tools.printHeader("MOVIMIENTOS");
        for (Movimientos movimiento : SucursalActual.getInstancia().getSucursalActual().movimientos) {
            System.out.println(movimiento.toString());
        }
        Tools.next();
    }

    public static void RealizarInversiones(Inversionista inversionista){
        System.out.println("Ingrese la cantidad a invertir: ");
        double inversion=Tools.nextDouble();
        boolean flag=true;
        while (flag==true) {
            if (inversion > 0) {
                inversionista.inversion(inversion);
                System.out.printf("$%.2f han sido aportados al banco", inversion);
                flag = false;
                Tools.next();
            } else {
                System.out.println("El valor a ingresar debe ser positivo");
                System.out.println("Ingrese la cantidad a retirar: ");
                inversion = Tools.nextDouble();
            }
        }
        String nombre = inversionista.getNombreCompleto();
        Movimientos movimiento = new Movimientos(nombre, inversion, "Inversion");
        SucursalActual.getInstancia().getSucursalActual().movimientos.add(movimiento);
    }

    public static void RealizarRetiro(Inversionista inversionista){
        System.out.println("Ingrese la cantidad a retirar: ");
        double retiro= Tools.nextDouble();
        boolean flag=true;
        while (flag==true){
            if (retiro <= inversionista.getFondosAportados() && retiro>0){
                retiro += retiro*0.10;
                inversionista.retiro(retiro);
                flag=false;
                Tools.next();
            }
            else {
                System.out.println("Ingreso un retiro incorrecto, intente de nuevo");
                System.out.println("Ingrese la cantidad a retirar: ");
                retiro= Tools.nextDouble();
            }
        }
        String nombre = inversionista.getNombreCompleto();
        Movimientos movimiento = new Movimientos(nombre, retiro, "Retiro");
        SucursalActual.getInstancia().getSucursalActual().movimientos.add(movimiento);
    }
}
