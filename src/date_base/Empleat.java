package date_base;


public class Empleat extends Generic{
    public Long _id;
    public String _name;
    public String _dni;
    public String _compte_banc;
    public String _direccio;
    public Long _telefon;
    public Long _contract_type; 
    
    public Empleat(Long id, String name, String dni, String compte, String dir, Long tel, Long contract) throws Exception{
        if (id == null || name.equals("") || dni.equals("") || contract == null){
            throw new Exception("ERROR on Creating Empleat id, name, dni and"
                    + "contract are requiered\n");
        }else{
            _id = id;
            _name = name;
            _dni = dni;
            _compte_banc = compte;
            _direccio = dir;
            _telefon = tel;
            _contract_type = contract;
        }
    }
}
