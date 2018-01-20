
import date_base.DateBase;
import date_base.Inventari;
import date_base.Producte;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
    
    public static boolean generate_llistats_csv(DateBase cursor) throws FileNotFoundException{
        
        Map<Object,Object> inventaris = cursor.search_table("Inventari");
        if (inventaris != null){
            PrintWriter pw = new PrintWriter(new File("inventory.csv"));
            StringBuilder sb = new StringBuilder();

            sb.append("Codi");
            sb.append(';');
            sb.append("Article");
            inventaris.entrySet().forEach((entry) -> {
                    Inventari i = Inventari.class.cast(entry.getValue());
                    String name = i._name;
                    sb.append(";");
                    sb.append(name);
                    sb.append(";");
                    sb.append("Stock real");
                });
            sb.append('\n');
            
            Set<Object> prod_keys = cursor.get_table_keys("Producte");
            for(Object o : prod_keys){
                Long id_p = (Long) o;
                Producte pp = Producte.class.cast(cursor.search_by_id("Producte", id_p));
                sb.append(pp._id);
                sb.append(";");
                sb.append(pp._name);
         
                inventaris.entrySet().forEach((entry) -> {
                    Inventari i = Inventari.class.cast(entry.getValue());
                    Integer unitats = i.consultar_unitats(id_p);
                    sb.append(";");
                    sb.append(unitats);
                    sb.append(";");
                    sb.append(" ");
                });
                sb.append("\n");
            }
            pw.write(sb.toString());
            pw.close();
            
            System.out.println("done!");
            return true;
        
        }else{
            System.out.println("Inventaris not found in db");
        }
        return false;
    }
}
