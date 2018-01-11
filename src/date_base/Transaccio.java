package date_base;

public abstract class Transaccio {
    public Long _id;
    public Float _import;
    
    
    public Transaccio(Long id, Float imp){
        _id = id;
        _import = imp;
    }
    
}
