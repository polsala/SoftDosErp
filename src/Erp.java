import date_base.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Erp {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        boolean fast_on = false;
        boolean test_on = false;
        for (String arg : args) {
            if (arg.equals("-f")) {
                fast_on = true;
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
                            "╚══════╝ ╚═════╝ ╚═╝        ╚═╝   ╚═════╝  ╚═════╝ ╚══════╝╚══════╝╚═╝  ╚═╝╚═╝"
        );
        //create db
        DateBase db = new DateBase();
        Init.init_db(db, test_on, fast_on);
        MenuTerminal admin = new MenuTerminalAdmin(db);
        admin.display_menu();
        
        
    }
    
}
