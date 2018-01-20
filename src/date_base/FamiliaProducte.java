package date_base;

public class FamiliaProducte extends Generic{
    public Long _id;
    public String _name;
    public String _info;
    public Float _impost_afegit;
    
    public FamiliaProducte(Long id, String name, String info, Float impost) throws Exception{
        if (id == null || name.equals("")){
            throw new Exception("ERROR on Creating Familia id, name are requiered\n");
        }else{
            _id = id;
            _name = name;
            _info = info;
            _impost_afegit = impost;
        }
    }
}
