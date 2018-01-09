/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package date_base;

/**
 *
 * @author exor_p
 */
public class Producte {
    public Long _id;
    private String _name;
    private String _info;
    private Long _familia_id;
    private Float _preu_base;
    
    public Producte(Long id, String name, String info, Long fam_id, Float preu) 
            throws Exception{
        if (id == null || name.equals("") || fam_id == null || preu == null){
            throw new Exception("ERROR on Creating Producte id, name family "
                    + "and preu are requiered\n");
        }else{
            _id = id;
            _name = name;
            _info = info;
            _familia_id = fam_id;
            _preu_base = preu;
        }
    }
    
    public Float calcula_tant_per_cent(Float tant){
        return (tant/100) * _preu_base;
    }
}
