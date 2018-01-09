/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package date_base;

import java.time.LocalDateTime;

/**
 *
 * @author exor_p
 */
public class Factura extends Generic{
    public Long _id;
    public String _name;
    public Float _total_import;
    public Integer _num_caixa;
    public Long _empleat_id;
    public LocalDateTime _data_facturacio;
    
    public Factura(Long id, String name, Integer num, Long e_id) throws Exception{
        if (id == null || name.equals("") || num == null || e_id == null){
            throw new Exception("Error on create Factura id, import total, "
                    + "numero de caixa and employer id are requiered");
        }else{
            _id = id;
            _name = name;
            _num_caixa = num;
            _empleat_id = e_id;
            _data_facturacio = LocalDateTime.now();
            System.out.println(_data_facturacio);
        }
    }
}
