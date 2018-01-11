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
public class LiniaFactura {
    public Integer _num;
    public Producte _producte;
    public Integer _unitats;
    public Float _preu_linea;
    
    public LiniaFactura(Integer num, Producte p, Integer unitats, Float p_unitari) throws Exception{
        // si preu unitari es null calculem sobre el preu base.
        if (num == null || p == null || unitats == null){
            throw new Exception("Error on create linea factura capmps requerits:"
                    + " num, producte, unitats");
        }else{
            _num = num;
            _producte = p;
            _unitats = unitats;
            if (p_unitari != null){
                _preu_linea = p_unitari * unitats;
            }else{
                _preu_linea = p._preu_base * unitats;
            }
        }
    }
    
}
