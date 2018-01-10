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
    
}
