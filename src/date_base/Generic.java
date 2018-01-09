/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package date_base;

import java.lang.reflect.Field;

/**
 *
 * @author exor_p
 */
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
