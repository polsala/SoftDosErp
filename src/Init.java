
import date_base.DateBase;
import date_base.Inventari;
import java.io.File;
import java.util.Vector;

public class Init {
    static String[] _tables = {"Tarifa", "Empleat", "FamiliaProducte", "Producte", "Client", "Inventari", "Factura", "Transaccio"};
    static String[] _inventaris = {"Magatzem", "Botiga"};
    
    static void load_demo_test(DateBase db){
        File fXmlFile = new File("src/demo_data.xml");
        ProcessarFitxer.procesar_fitxer(db, fXmlFile);

        GenerarLlistats.llistar_invenaris(db); 
         
        try{
            GenerarLlistats.generate_llistats_csv(db);
        }catch(Exception e){
            System.err.println("\nError al generar csv");
        }
        
        try{
            PasarPerCaixa.pasar_per_caixa(db);
        }catch(Exception e){
            System.err.println("\nError al pasar per caixa");
        }
        
        Vector<Object> kk = db.search_in_table_by_value("Producte", "_name", "Catsup, Primo");
        if(kk != null){
            System.out.println("Manolo lo logramos!!!!!");
        }else{
            System.out.println("La cagaste wey!!!!!");
        }
        
        ProcessarFitxer.read_and_set_inventorys(db, "src/demo_stock.csv");
        
    }
    
    static void init_db(DateBase db, boolean test){
        //Create tables generic Map<Object,Object> on Map<Object,Map<Object,Object>> with name as key in global map
        for (String tabl : _tables) db.create_table(tabl);
        
        //Create inventorys in table Inventari
        for (String inv : _inventaris){
            Long inv_id = db.new_id_for_table("Inventari");
            Inventari inv_obj = new Inventari(inv_id, inv);
            db.add_obj("Inventari", inv_obj);
        }
        
        if(test) load_demo_test(db);
        
    }
}
