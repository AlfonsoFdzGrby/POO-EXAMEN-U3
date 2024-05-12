package Sistema.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import Usuarios.util.Tarjeta.Solicitud;

public class Tools {
    private static Scanner sc = new Scanner(System.in);

    public static void printHeader(String header){
        System.out.println("===============================================================");
        System.out.println(header);
        System.out.println("===============================================================");
    }

    public static void next(){
        System.out.print("Presione enter para continuar...");
        sc.nextLine();
    }

    public static int nextInt(){
        String num;
        int res;
        while(true){
            num = sc.nextLine();
            try {
                res = Integer.parseInt(num);
                break;
            } catch (Exception e) {
                System.out.println("Por favor ingrese un número");
            }
        }
        return res;
    }

    public static double nextDouble(){
        String num;
        double res;
        while(true){
            num = sc.nextLine();
            try {
                res = Double.parseDouble(num);
                break;
            } catch (Exception e) {
                System.out.println("Por favor ingrese un número");
            }
        }
        return res;
    }

    public static boolean validarRetiro(double fondos, double cantidad){
        boolean exito = false;
        if(!(cantidad<0 || cantidad>fondos)){
            exito = true;
        }
        return exito;
    }

    public static boolean AskForYesOrNo(char opc){
        boolean answer = false;
        switch (Character.toLowerCase(opc)) {
            case 'y' -> answer = true;
            case 's' -> answer = true;
            default -> answer = false;
        }
        return answer;
    }

    public static int encontrarSolicitud(int id){
        for (Solicitud solicitud : SucursalActual.getInstancia().getSucursalActual().solicitudesActualizacion) {
            if (id == solicitud.getId()) {
                return SucursalActual.getInstancia().getSucursalActual().solicitudesActualizacion.indexOf(solicitud);
            }
        }
        return -1;
    }

    public static LocalDate askForDate(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = null;
        while (true) {
            System.out.println("Ingrese la fecha en el formato dd-MM-yyyy");
            System.out.print(">> ");
            try{
                fecha = LocalDate.parse(sc.nextLine(), format);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("La fecha ingresada no es válida");
            }
        }
        return fecha;
    }
}
