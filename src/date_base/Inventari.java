package date_base;

import java.util.LinkedHashMap;
import java.util.Map;
import javafx.util.Pair;


public class Inventari extends Generic{
    private Map<Long,Pair<Producte, Integer>> _products;
    public Long _id;
    public String _name;
    
    public Inventari(){
        _products = new LinkedHashMap<>();
    }
    public Inventari(Long id, String name){
        _id = id;
        _name = name;
        _products = new LinkedHashMap<>();
    }
    
    
    public void afegir_modificar_Producte(Producte p, Integer unitats){
        // TODO evitar stock negatiu?
        Pair<Producte,Integer> value = _products.putIfAbsent(p._id, new Pair<>(p,unitats));
        if ( value!= null){
            Integer current_stock = value.getValue() + unitats;
            value = new Pair<>(p, current_stock);
            _products.put(p._id, value);
            System.out.println("Stock actualitzat");
        }else{
            System.out.println("Producte afegit");
        }
    }
    
    public void modificar_producte_per_id(Long p, Integer unitats){
        // TODO evitar stock negatiu?
        Pair<Producte,Integer> value = _products.putIfAbsent(p, new Pair<>(p,unitats));
        if ( value!= null){
            Integer current_stock = value.getValue() + unitats;
            value = new Pair<>(value.getKey(), current_stock);
            _products.put(p, value);
            System.out.println("Stock actualitzat");
        }else{
            System.out.println("Producte afegit");
        }
    }
    
    public void eliminar_Producte(Producte p){
        _products.remove(p._id);
    }
    
    public Integer consultar_unitats(Producte p){
        Pair<Producte, Integer> par = _products.get(p._id);
        if(par != null){
            return par.getValue();
        }
        return null;
    }
    
    public void eliminar_Producte(Long p){
        _products.remove(p);
    }
    
    public Integer consultar_unitats(Long p){
        Pair<Producte, Integer> par = _products.get(p);
        if(par != null){
            return par.getValue();
        }
        return null;
    }
    
    public void llistar(){
        _products.entrySet().forEach((entry) -> {
            Integer stock = entry.getValue().getValue();
            Producte p = entry.getValue().getKey();
            System.out.println(
                    "ID : " + p._id + " | Nom : " 
                            + p._name + " | Unitats: " + stock
            );
        });
        
    }
    
    
}
