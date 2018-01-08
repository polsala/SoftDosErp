/**
 *
 * @author exor_p
 */
import base_gui.main_menu;
import date_base.*;
import java.io.File;
import java.util.Scanner;

public class Erp {

    /**
     * @param args the command line arguments
     */
    
    static void carregarBaseDades() throws FileNotFoundException {

        Scanner teclat = new Scanner(System.in);
        String nomFitxer = "baseDades.txt";
        //Scanner fitxer = new Scanner(new File(nomFitxer));
        File file = new File(nomFitxer);
        DateBase bd = new DateBase(file);
        //fitxer.close();
    }
    
    public static void main(String[] args) {
        boolean gui_on = false;
        for (String arg : args) {
            if (arg.equals("-g")) {
                gui_on = true;
            }
            System.out.println(arg);
        }
        if (gui_on){
            main_menu.main(args);
            
        }
        // TODO code application logic here
        
    }
    
}
