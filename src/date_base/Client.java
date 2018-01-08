/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package date_base;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author exor_p
 */
public class Client {
    public long _id;
    private String _name;
    private String _dni;
    private String _direccio;
    private Long _telefon;
    private Long _tarifa_id;
    int _punts;
    
    public Client(Long id, String name, String dni, String direccio, Long tel, Long tarifaid) throws Exception{
        
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
