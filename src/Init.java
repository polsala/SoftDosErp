
import date_base.DateBase;
import date_base.Inventari;
import java.io.File;
import java.util.Vector;

public class Init {
    static String[] _tables = {"Tarifa", "Empleat", "FamiliaProducte", "Producte", "Client", "Inventari", "Factura", "Transaccio"};
    static String[] _inventaris = {"Magatzem", "Botiga"};
    
    static void load_demo_test(DateBase db){
        ProcessarFitxer.procesar_fitxer(db, "src/demo_data.xml");

        ProcessarFitxer.read_and_set_inventorys(db, "src/demo_stock.csv");
        
        
    }
    static void fast_demo_test(DateBase db){
        GenerarLlistats.llistar_invenaris(db);   
        try{
            GenerarLlistats.generate_llistats_csv(db, "inventari_file_");
        }catch(Exception e){
            System.err.println("\nError al generar csv");
        }
    
        try{
            PasarPerCaixa.pasar_per_caixa(db);
        }catch(Exception e){
            System.err.println("\nError al pasar per caixa");
        }
        
        GenerarLlistats.llistar_invenaris(db); 
         
        try{
            GenerarLlistats.generate_llistats_csv(db,"inventari2_file_");
        }catch(Exception e){
            System.err.println("\nError al generar csv");
        }
    }
    
    static void init_db(DateBase db, boolean test, boolean fast){
        //Create tables generic Map<Object,Object> on Map<Object,Map<Object,Object>> with name as key in global map
        for (String tabl : _tables) db.create_table(tabl);
        
        //Create inventorys in table Inventari
        for (String inv : _inventaris){
            Long inv_id = db.new_id_for_table("Inventari");
            Inventari inv_obj = new Inventari(inv_id, inv);
            db.add_obj("Inventari", inv_obj);
        }
        
        if(test && fast){
            load_demo_test(db);
            fast_demo_test(db);
        }else if (test){
            load_demo_test(db);
        }
        
    }
}
