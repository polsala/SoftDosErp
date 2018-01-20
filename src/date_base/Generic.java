package date_base;

import java.lang.reflect.Field;


public class Generic {
    public Object get_att(String att_name) throws NoSuchFieldException{
        try{
            Field f = this.getClass().getField(att_name);
            //f.setAccessible(true);
            return f.get(this);
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    public Boolean write_att(String att_name, Object value) throws NoSuchFieldException{
        try{
            Field f = this.getClass().getField(att_name);
            f.set(this, value);
            //f.setAccessible(true);
            System.out.println("Valor sobrescrit");
            return true;
        }catch(Exception e){
            System.err.println("Error no s'ha pogut sobreescriure");
            System.out.println(e);
        }
        return false;
    }
    
}
