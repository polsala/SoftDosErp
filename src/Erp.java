import base_gui.main_menu;
import date_base.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
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
        boolean test_on = false;
        for (String arg : args) {
            if (arg.equals("-g")) {
                gui_on = true;
            }
            if (arg.equals("-t")) {
                test_on = true;
            }
            System.out.println(arg);
        }
        System.out.println( "███████╗ ██████╗ ███████╗████████╗██████╗  ██████╗ ███████╗███████╗██████╗ ██████╗ \n" +
                            "██╔════╝██╔═══██╗██╔════╝╚══██╔══╝██╔══██╗██╔═══██╗██╔════╝██╔════╝██╔══██╗██╔══██╗\n" +
                            "███████╗██║   ██║█████╗     ██║   ██║  ██║██║   ██║███████╗█████╗  ██████╔╝██████╔╝\n" +
                            "╚════██║██║   ██║██╔══╝     ██║   ██║  ██║██║   ██║╚════██║██╔══╝  ██╔══██╗██╔═══╝ \n" +
                            "███████║╚██████╔╝██║        ██║   ██████╔╝╚██████╔╝███████║███████╗██║  ██║██║     \n" +
                            "╚══════╝ ╚═════╝ ╚═╝        ╚═╝   ╚═════╝  ╚═════╝ ╚══════╝╚══════╝╚═╝  ╚═╝╚═╝");
        
        DateBase db = new DateBase();
        if (test_on){
            //main_menu.main(args);
            // TEST
            Init.init_db(db, true);
        }else{
            Init.init_db(db, false);
        }
        
        
    }
    
}
