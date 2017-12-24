/*
 * This class represents a Date bBase of shop
 */
package date_base;

import java.util.LinkedHashMap;
import java.util.Map;


public class DateBase {
     private Map<Object,Map<Object,Object>> mapOfMaps;
     
     public DateBase(){
         mapOfMaps = new LinkedHashMap<Object,Map<Object,Object>>();
     }
     
     public boolean create_table(String table_name){
         // if key doesn't exist create table and return True else return False 
         Object table_key = new String(table_name);
         Object res = mapOfMaps.putIfAbsent(table_key, new LinkedHashMap<Object,Object>());
         return res == null;
     }
     
     public Map<Object,Object> search_table(String table_name){
         // Return value of mapOfMaps with key if exist, else return null;
         Object table_key = new String(table_name);
         Map<Object,Object> value = mapOfMaps.get(table_key);
         return value;
     }
     
     
}
