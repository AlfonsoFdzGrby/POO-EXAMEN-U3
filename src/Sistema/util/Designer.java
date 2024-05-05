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
}
