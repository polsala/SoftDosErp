
import date_base.DateBase;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuTerminalAdmin extends MenuTerminal{
    
    public MenuTerminalAdmin(DateBase db){
        super(db);
    }
    public void display_menu(){
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
 
        while (!salir) {
 
            System.out.println("1. Pasar per caixa");
            System.out.println("2. Generar Llistats");
            System.out.println("3. Creacio i modificació manual");
            System.out.println("4. Importació de dades");
            System.out.println("0. Sortir");
 
            try {
 
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();
 
                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion 1");
                        this.menu_pasar_per_caixa();
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion 2");
                        this.menu_llistats();
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion 3");
                        //TODO call menu creacio
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion 4");
                        menu_importacio();
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Només números entre 0 i 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: la opció ha de ser numérica");
                sn.next();
            }
        }
    }
    
    public void menu_imp_xml(){
        Scanner sn = new Scanner(System.in);
        String csv_n = "";
        System.out.println("Entre la ruta del fitxer, si està a la mateixa ruta que l'executable només el nom ex. src/demo_data.xml");
        csv_n = sn.next();
        ProcessarFitxer.procesar_fitxer(cursor, csv_n);
    }
    
    public void menu_imp_csv(){
        Scanner sn = new Scanner(System.in);
        String csv_n = "";
        System.out.println("Entre la ruta del fitxer, si està a la mateixa ruta que l'executable només el nom ex. src/demo_stock.csv");
        csv_n = sn.next();
        ProcessarFitxer.read_and_set_inventorys(cursor, csv_n);
    }
    
    public void menu_importacio(){
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
 
        while (!salir) {
            System.out.println("    MENU D'IMPORTACIÓ   ");
            System.out.println("________________________");
            System.out.println("1. Importar fitxer XML de dades (Empleats, Clients, Families...)");
            System.out.println("2. Importar CSV inventari");
            System.out.println("0. Salir");
 
            try {
 
                System.out.println("Escribe una de les opcions");
                opcion = sn.nextInt();
 
                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion IMPORTACIO XML");
                        menu_imp_xml();
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion IMPORTAR CSV");
                        menu_imp_csv();
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Només nùmeros entre 0 i 2");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: la opció ha de ser numérica");
                sn.next();
            }
        }
    }
}
