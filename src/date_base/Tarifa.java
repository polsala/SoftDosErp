package date_base;

import java.util.LinkedHashMap;
import java.util.Map;

public class Tarifa {
    private Map<Long,Float> _products;
    private Map<Long,Float> _families;
    
    public Tarifa(){
        _products = new LinkedHashMap<>();
        _families = new LinkedHashMap<>();
    }
    
    public Boolean afegir_descompte_familia(Long id, Float desc){
        Object res = _families.put(id, desc);
        
        if (res == null){
            System.out.println("Descompte afegit");
        }else{
            System.out.println("Descompte actualitzat");
        }
        return true;
        
    }
    
    public Boolean afegir_descompte_producte(Long id, Float desc){
        Object res = _products.put(id, desc);
        if (res == null){
            System.out.println("Descompte afegit");
        }else{
            System.out.println("Descompte actualitzat");
        }
        return true;
    }
    
    
    public Boolean eliminar_descpmpte_producte(Long id){
        Object res = _products.remove(id);
        if (res == null){
            System.err.println("No existeix el producte amb codi " + id.toString());
            return false;
        }else{
            System.out.println("Producte " + id.toString() + " eliminat");
            return true;
        }
    }
    
    public Boolean eliminar_descpmpte_familia(Long id){
        Object res = _families.remove(id);
        if (res == null){
            System.err.println("No existeix la familia amb codi " + id.toString());
            return false;
        }else{
            System.out.println("Familia " + id.toString() + " eliminat");
            return true;
        }
    }
    
    public Float get_descompte_familia(Long id){
        return _families.getOrDefault(id, 0F);
    }
    public Float get_descompte_producte(Long id){
        return _products.getOrDefault(id, 0F);
    }
    
    
}
