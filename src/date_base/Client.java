
package date_base;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client extends Generic{
    public Long _id;
    public String _name;
    public String _dni;
    public String _direccio;
    public Long _telefon;
    public Long _tarifa_id;
    public int _punts;
    
    public Client(Long id, String name, String dni, String direccio, Long tel, 
            Long tarifaid) throws Exception{
        
        if(id == null || id < 1 || name.equals("") || dni.equals("")  ){
            throw new Exception("ERROR on Creating Client id, name and dni "
                    + "requiered\n");
        }
        else{
            _id = id;
            _name = name;
            _dni = dni;
            _direccio = direccio;
            _telefon = tel;
            _tarifa_id = tarifaid;
            _punts = 0;
        }
    }
    
    
    
}
