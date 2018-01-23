
import date_base.DateBase;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class MenuTerminal {
    DateBase cursor;
    
    public MenuTerminal(DateBase db){
        cursor = db;
    }
    
    public abstract void display_menu();
    
    
    public void show_description_program(){
        System.out.println("Sistema ERP de gestió de botigas");
        System.out.println("Descripció d'utilitats:");
        System.out.println("    - Sistema generic per emmagatzemar dades (permet treballar com si fos una base de dades)");
        System.out.println("    - Sistema d'importacio de dades via fitxers xml(productes, empleats...) i csv per inventaris");
        System.out.println("    - Sistema de gestió de compres (Gestió del cas pasar per caixa)");
        System.out.println("    - Sistema de llistats, permét llistar els inventar");
        System.out.println("    - Sistema d'exportació d'inventaris: permét exportar els inventaris en format csv per treballari");
    }
    
    public void menu_llistats(){
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
 
        while (!salir) {
            System.out.println("    MENU LLISTATS   ");
            System.out.println("________________________");
            System.out.println("1. Llistar per pantalla");
            System.out.println("2. Generar fitxer CSV");
            System.out.println("0. Salir");
 
            try {
 
                System.out.println("Escriu una de les opcions");
                opcion = sn.nextInt();
 
                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion 1");
                        GenerarLlistats.llistar_invenaris(cursor);
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion 2");
                        try{
                            String csv_n = "";
                            System.out.println("Entre el nom del fitxer o enter per nom per defecte inventari+data+.csv:");
                            csv_n = sn.next();
                            GenerarLlistats.generate_llistats_csv(cursor, csv_n);
                            
                        }catch(Exception e){
                            System.err.println("\nError al generar csv");
                        }
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Només números entre 0 i 2");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: la opció ha de ser numérica");
                sn.next();
            }
        }
    }
    
    public void menu_pasar_per_caixa(){
        try{
            PasarPerCaixa.pasar_per_caixa(cursor);
        }catch(Exception e){
            System.err.println("\nError al pasar per caixa");
        }
    }
}
