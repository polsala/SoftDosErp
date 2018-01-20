
import date_base.DateBase;
import date_base.Inventari;
import date_base.Producte;
import java.util.Map;
import java.util.Set;

public class GenerarLlistats {
    
    public static void llistar_invenaris(DateBase cursor){
        final String header = "    Codi       Article      ";
        Map<Object,Object> inventaris = cursor.search_table("Inventari");
        if (inventaris != null){
            System.out.print(header);
            inventaris.entrySet().forEach((entry) -> {
                    Inventari i = Inventari.class.cast(entry.getValue());
                    String name = i._name;
                    System.out.print(i + "   ");
                });
            System.out.print("\n");
            Set<Object> prod_keys = cursor.get_table_keys("Producte");
            for(Object o : prod_keys){
                Long id_p = (Long) o;
                Producte pp = Producte.class.cast(cursor.search_by_id("Producte", id_p));
                System.out.print(pp._id + "     " + pp._name + "    ");
                inventaris.entrySet().forEach((entry) -> {
                    Inventari i = Inventari.class.cast(entry.getValue());
                    Integer unitats = i.consultar_unitats(id_p);
                    System.out.print(unitats + "   ____");
                });
                System.out.print("\n");
            }
        }else{
            System.out.println("Inventaris not found in db");
        }
    }
}
