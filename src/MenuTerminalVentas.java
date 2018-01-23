
import date_base.DateBase;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuTerminalVentas extends MenuTerminal{
    
    public MenuTerminalVentas(DateBase db){
        super(db);
    }
    public void display_menu(){
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
 
        while (!salir) {
 
            System.out.println("1. Pasar per caixa");
            System.out.println("2. Generar Llistats");
            System.out.println("0. Salir");
 
            try {
 
                System.out.println("Escribe una de les opcions");
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
}
