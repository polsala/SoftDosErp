package date_base;


public class Producte extends Generic{
    public Long _id;
    public String _name;
    public String _info;
    public Long _familia_id;
    public Float _preu_base;
    
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
        return (tant/100F) * _preu_base;
    }
}
