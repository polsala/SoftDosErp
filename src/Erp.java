/**
 *
 * @author exor_p
 */
import base_gui.main_menu;

public class Erp {

    /**
     * @param args the command line arguments
     */
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
