/*
 * This class represents a Date Base of shop
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
     
     public Object search_by_id(String table_name, long id){
         Map<Object,Object> table = search_table(table_name);
         if(table != null){
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
     
     public Map<Object,Object> search_in_table_by_value(String table_name, String atribute_name, Object atribute_value){
         
     }
     
}
