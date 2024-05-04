package Sistema;

import Sistema.util.*;

import java.util.*;

public class Menu {

    private static Scanner sc = new Scanner(System.in);
    private static Sistema sistema = new Sistema();

    public static void ejecutarMenu(){
        Sucursal sucursal = null;
        int opc = 1;
        while(opc<1 || opc>3){
            Designer.printHeader("¡BIENVENIDO A POOBANK!");
            System.out.println("Por favor ingrese la sucursal a la que quiere entrar");
            System.out.println("1. Acueducto");
            System.out.println("2. Madero");
            System.out.println("3. Salir del programa");
            System.out.print(">> ");
            opc = sc.nextInt();
            switch (opc) {
                case 1 -> {
                    sucursal = sistema.getAcueducto();
                }
                case 2 -> {
                    sucursal = sistema.getMadero();
                }
            }
        }
    }
}
