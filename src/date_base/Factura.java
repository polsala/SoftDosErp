package date_base;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;


public class Factura extends Generic{
    public Long _id;
    public String _name;
    public Float _total_import;
    public Integer _num_caixa;
    public Long _empleat_id;
    public Long _client_id;
    public LocalDateTime _data_facturacio;
    public Map<Long, LiniaFactura> _linies;
    
    public Factura(Long id, String name, Integer num, Long e_id, Long cli) throws Exception{
        if (id == null || name.equals("") || num == null || e_id == null){
            throw new Exception("Error on create Factura id, import total, "
                    + "numero de caixa and employer id are requiered");
        }else{
            _id = id;
            _name = name;
            _num_caixa = num;
            _empleat_id = e_id;
            _client_id = cli;
            _data_facturacio = LocalDateTime.now();
            _linies = new LinkedHashMap<Long,LiniaFactura>(); //Long is product id
            System.out.println(_data_facturacio);
        }
    }
}
