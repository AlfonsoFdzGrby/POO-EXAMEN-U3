package Sistema.util;

import java.util.Scanner;

public class Designer {
    private static Scanner sc = new Scanner(System.in);

    public static void printHeader(String header){
        System.out.println("==========================================");
        System.out.println(header);
        System.out.println("==========================================");
    }

    public static void next(){
        System.out.print("Presione enter para continuar...");
        sc.nextLine();
    }
}
