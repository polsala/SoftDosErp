
import date_base.DateBase;
import date_base.Inventari;
import date_base.Producte;
import java.util.Map;
import java.util.Set;

public class GenerarLlistats {
    
    public static String fixedLengthString(String s, int n) {
        return String.format("%1$-" + n + "s", s);
        //return String.format("%1$"+length+ "s", string);
    }
    
    public static void llistar_invenaris(DateBase cursor){
        final String header = "    Codi                      Article";
        Map<Object,Object> inventaris = cursor.search_table("Inventari");
        if (inventaris != null){
            System.out.print(fixedLengthString(header,77));
            inventaris.entrySet().forEach((entry) -> {
                    Inventari i = Inventari.class.cast(entry.getValue());
                    String name = i._name;
                    System.out.print(fixedLengthString(name,16));
                });
            System.out.print("\n");
            Set<Object> prod_keys = cursor.get_table_keys("Producte");
            for(Object o : prod_keys){
                Long id_p = (Long) o;
                Producte pp = Producte.class.cast(cursor.search_by_id("Producte", id_p));
                System.out.print(pp._id + "     " + fixedLengthString(pp._name,50) + "    ");
                inventaris.entrySet().forEach((entry) -> {
                    Inventari i = Inventari.class.cast(entry.getValue());
                    Integer unitats = i.consultar_unitats(id_p);
                    System.out.print("      " + unitats + "   ____");
                });
                System.out.print("\n");
            }
        }else{
            System.out.println("Inventaris not found in db");
        }
    }
}
