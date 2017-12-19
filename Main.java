/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacio;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author aula
 */
public class Aplicacio {

    static void carregarBaseDades() throws FileNotFoundException {

        Scanner teclat = new Scanner(System.in);
        String nomFitxer = "baseDades.txt";
        Scanner fitxer = new Scanner(new File(nomFitxer));

        BasesDades = new BasesDades(fitxer);
        fitxer.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        carregarBaseDades();
    }

}
