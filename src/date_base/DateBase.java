/*
 * This class represents a Date Base of shop
 * Map with String as key (table name), values are maps with long as key.   
 */
package date_base;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;


public class DateBase {
     private Map<Object,Map<Object,Object>> mapOfMaps;
     
     public DateBase(){
         mapOfMaps = new LinkedHashMap<Object,Map<Object,Object>>();
     }
     
     public DateBase(File file){
         mapOfMaps = new LinkedHashMap<Object,Map<Object,Object>>();
         //TODO
     }
     
     public boolean create_table(String table_name){
         // if key doesn't exist create table and return True else return False 
         // TODO check if class table_name exist to create table
         Object table_key = new String(table_name);
         Object res = mapOfMaps.putIfAbsent(table_key, new LinkedHashMap<Object,Object>());
         if (res == null){
             System.out.println("Table " + table_name + " created successfully on db");
             return true;
         }
         return false;
     }
     
     public Map<Object,Object> search_table(String table_name){
         // Return value of mapOfMaps with key if exist, else return null;
         Object table_key = new String(table_name);
         Map<Object,Object> value = mapOfMaps.get(table_key);
         return value;
     }
     
     public Object search_by_id(String table_name, Long id){
         // Return null if table or object with id doesn't exist else return mapped Object value
         Map<Object,Object> table = search_table(table_name);
         if(table != null){
             Object value = table.get(id);
             if(value==null){
                 System.err.printf("Table %s doesn't contains object with %s as key!\n", table_name, id);
             }
             return value;
         }else{
             System.err.printf("Table %s doesn't exist!\n", table_name);
         }
         return null;
     }
     
     public Long new_id_for_table(String table_name){
         // if table exist return max id + 1 or 1 if void or null if table  
         //doesn't exist
         Map<Object,Object> table = search_table(table_name);
         if (table != null){
             Long max = table.keySet().stream().max(Long::compareTo).orElse(0);
             return max+1;
         }
         return null;
     }
     
     public boolean check_related_fields(String table_name, Object o){
         //check mant2one fields
         boolean contains_id = search_by_id(table_name, Long.class.cast(Generic.class.cast(o).get_att("_id"))) != null;
         System.out.print(!contains_id);
         if (table_name.equals("Producte")){
             Producte p = Producte.class.cast(o);
             Long fam_id = p._familia_id;
             if(fam_id != null){
                 Object fp = search_by_id("FamiliaProducte", fam_id);
                 return fp != null;
             }
             System.err.println("El producte ha de tenir una familia assignada");
             return false;
         }else if(table_name.equals("Client")){
             Client cl = Client.class.cast(o);
             Long tar_id = cl._tarifa_id;
             if (tar_id != null){
                 Object oc = search_by_id("Tarifa", tar_id);
                 return oc != null;
             }
             //el client no  te perque tenir una tarifa assignada
             return true;
         }else if(table_name.equals("Factura")){
             Factura f = Factura.class.cast(o);
             boolean facturable = true;
             Long emp_id =  f._empleat_id;
             Long cl_id = f._client_id;
             if(emp_id != null){
                Object oe = search_by_id("Empleat", emp_id);
                facturable = (oe != null);
             }else{
                 System.err.println("Les factures han de tenir l'id de l'empleat");
                 facturable = false;
             }
             if(facturable){
                 if (cl_id != null){
                     return search_by_id("Client", cl_id) != null;
                 }
             }
             return facturable;
         }
         return true;
     }
     
     public String add_obj(String table_name, Object o){
         String res = "";
         System.out.println(o.getClass().toString());
         
         if (mapOfMaps.containsKey(table_name)){
            boolean constraint_pass = check_related_fields(table_name, o);
            if (constraint_pass){
                mapOfMaps.get(table_name).put(o._id, o);
                return "Afegit correctament";
            }else{
                res = "ERROR: No s'ha pogut afegir l'objecte " + table_name;
            }
         }else{
             res = "ERROR: La taula " + table_name + " no existeix";
         }
         return res;
     }
     
     /* TODO
     
     public Map<Object,Object> search_in_table_by_value(String table_name, String atribute_name, Object atribute_value){
         Map<Object,Object> table = search_table(table_name);
         if(table != null){
             Map<Object,Object> value = new LinkedHashMap<Object,Object>();
             for(Map.Entry<Key, YourObj> entry: this.media.entrySet()){
                System.out.println(entry.getValue().getAttribute());
             }
             Object value = table.get(id);
             if(value==null){
                 System.err.printf("Table %s doesn't contains object with %s as key!", table_name, id);
             }
             return value;
         }else{
             System.err.printf("Table %s doesn't exist!", table_name);
         }
         return null;
     }
     */
     
}
