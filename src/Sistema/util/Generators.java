package Sistema.util;

import java.time.*;
import java.util.*;

public class Generators {

    private static Random ran = new Random();

    // -------------------------------------------- CURP --------------------------------------------

    public static String GenerateCURP(String nombre, String apellidos, LocalDate fechaNac, boolean esHombre, String estado){
        String[] arrApellidos = apellidos.split(" ");
        String CURP="";
        char letra;

        // Nombres y apellidos

        for (int i = 0; i < 2; i++) {
            letra = apellidos.charAt(i);
            CURP += Character.toUpperCase(letra);
        }

        letra = arrApellidos[1].charAt(0);
        CURP += Character.toUpperCase(letra);
        letra = nombre.charAt(0);
        CURP += Character.toUpperCase(letra);
        
        // Fecha de nacimiento

        CURP += String.format("%02d",fechaNac.getYear() % 100);
        CURP += String.format("%02d",fechaNac.getMonthValue() % 100);
        CURP += String.format("%02d",fechaNac.getDayOfMonth() % 100);

        // Género

        if(esHombre){
            CURP += "H";
        }else{
            CURP += "M";
        }

        // Entidad federativa

        for (int i = 0; i < 2; i++) {
            letra = estado.charAt(i);
            CURP += Character.toUpperCase(letra);
        }

        // Primeras consonantes internas de apellidos

        String apTemp;
        
        for (int j = 0; j < arrApellidos.length; j++) {
            apTemp = arrApellidos[j];
            for (int j2 = 1; j2 < apTemp.length(); j2++) {
                letra = Character.toUpperCase(apTemp.charAt(j2));
                if(letra!='A' && letra!='E' && letra!='I' && letra!='O' && letra!='U'){
                    CURP += letra;
                    break;
                }
            }
        }

        // Primera consonante interna del nombre

        for (int i = 1; i < nombre.length(); i++) {
            letra = Character.toUpperCase(nombre.charAt(i));
            if(letra!='A' && letra!='E' && letra!='I' && letra!='O' && letra!='U'){
                CURP += letra;
                break;
            }
        }

        // Ultimos dos dígitos que genera el algoritmo

        for (int i = 0; i < 2; i++) {
            int num = ran.nextInt(10);
            CURP += num;
        }

        return CURP;
    }

    // --------------------------------------------- RFC ---------------------------------------------

    public static String GenerateRFC(String nombre, String apellidos, LocalDate fechaNac){
        String[] arrApellidos = apellidos.split(" ");
        String RFC="";
        char letra;

        // Nombres y apellidos

        for (int i = 0; i < 2; i++) {
            letra = apellidos.charAt(i);
            RFC += Character.toUpperCase(letra);
        }

        letra = arrApellidos[1].charAt(0);
        RFC += Character.toUpperCase(letra);
        letra = nombre.charAt(0);
        RFC += Character.toUpperCase(letra);
        
        // Fecha de nacimiento

        RFC += String.format("%02d",fechaNac.getYear() % 100);
        RFC += String.format("%02d",fechaNac.getMonthValue() % 100);
        RFC += String.format("%02d",fechaNac.getDayOfMonth() % 100);

        // Homoclave generada por el algoritmo

        for (int i = 0; i < 3; i++) {
            int num = ran.nextInt(10);
            RFC += num;
        }

        return RFC;
    }

    // ------------------------------- NUMERO DE TARJETA (16 DÍGITOS) --------------------------------

    public static String GenerateCardNum(){
        String NumTarjeta = "4000 0012";
        int num;

        // Genera los siguientes 8 dígitos

        for (int i = 0; i < 2; i++) {
            num = ran.nextInt(10000);
            NumTarjeta += String.format(" %04d", num);
        }

        return NumTarjeta;
    }


    // ------------------------------------- CLABE (18 DÍGITOS) --------------------------------------

    public static String GenerateCLABE(NombreSucursal sucursal){
        String CLABE = "481";

        if(sucursal == NombreSucursal.Acueducto){
            CLABE += "001";
        }else{
            CLABE += "002";
        }

        // Genera los siguientes 12 dígitos

        for (int i = 0; i < 12; i++) {
            int num = ran.nextInt(10);
            CLABE += num;
        }

        return CLABE;
    }

    public static int GenerateCVV(){
        return ran.nextInt(1000);
    }

}
