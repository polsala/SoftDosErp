import date_base.*;
import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ProcessarFitxer {
    static String[] _tags = {"Empleat", "FamiliaProducte", "Producte", "Client"};
    static String[] _empleats_tags = {"DNI", "COMPTE", "DIRECCIO", "TELEFON", "CONTRACTE"};
    static String[] _familia_tags = {"INFO", "IMPOST"};
    static String[] _producte_tags = {"INFO", "FAMILIA", "PREU"};
    static String[] _client_tags = {"DNI", "DIRECCIO", "TELEFON", "TARIFA"};
    
    private static boolean check_class_exist(String className){
        // check if a class with className exist in a date_base package
        try  {
            Class.forName("date_base."+ className);
            return true;
        }  catch (ClassNotFoundException e) {
            System.err.println("Class " + className + " is not in package date_base");
            return false;
        }
    }
    
    public static boolean read_and_set_inventorys(DateBase cursor, String f_name){
        try{
            Scanner scanner = new Scanner(new File(f_name));
            scanner.useDelimiter(",");
            //Inventari magatzem = cursor.search_by_id(f_name, Long.MIN_VALUE)
            Inventari magatze = Inventari.class.cast(cursor.search_in_table_by_value("Inventari", "_name", "Magatzem").elementAt(0));
            Inventari botig = Inventari.class.cast(cursor.search_in_table_by_value("Inventari", "_name", "Botiga").elementAt(0));
            while(scanner.hasNext()){
                String fc = scanner.next().replaceAll("\\s+","");
                if(!fc.equals("")){
                    Long id = Long.parseLong(fc);
                    Integer magatzem = Integer.parseInt(scanner.next());
                    Integer botiga = Integer.parseInt(scanner.next());
                    System.out.println(id +" "+ magatzem+" "+botiga);

                    Producte p = Producte.class.cast(cursor.search_by_id("Producte", id)); 
                    magatze.afegir_modificar_Producte(p, magatzem);
                    botig.afegir_modificar_Producte(p, botiga);
                }
            }
            scanner.close();
            System.out.println("Finalitzada la importació d'inventaris!");
        }catch (Exception e){
            System.err.println("No s'ha pogut llegir el fitxer" + f_name+e);
        }
        return false;
    }
    
    private static boolean parse_and_create_object(DateBase cursor, Element eElement){
        String className = eElement.getNodeName();
        if (!className.equals("") && check_class_exist(className)){
            boolean table_created = cursor.create_table(className);
            Long id;
            String name;
            try{
                // name and id are communs
                id = Long.parseLong(eElement.getElementsByTagName("ID").item(0).getTextContent());
                name = eElement.getElementsByTagName("NAME").item(0).getTextContent();
                System.out.println(id);
                System.out.println(name);
                Vector<String> aux_tags = new Vector<>();
                String aux = "";
                if (className.equals("Empleat")){
                    for (int i = 0;i< _empleats_tags.length;i++){
                       try{
                           aux = eElement.getElementsByTagName(_empleats_tags[i]).item(0).getTextContent();
                           aux_tags.add(aux);
                           //Check if exist first TODO
                           //Empleat p = new Empleat(id, name);
                       }catch (Exception e){
                           aux_tags.add("");
                           System.err.println("Tag " + _empleats_tags[i] + " NOT FOUND: initialized to null");
                       }
                        
                    }
                    
                   
                    String dni = aux_tags.elementAt(0);
                    String compte = aux_tags.elementAt(1);
                    String direccio = aux_tags.elementAt(2);
                    Long tel = null;
                    if(!aux_tags.elementAt(3).equals("")){
                        tel = Long.parseLong(aux_tags.elementAt(3));
                    }
                    Long contracte = null;
                    if(!aux_tags.elementAt(4).equals("")){
                        contracte = Long.parseLong(aux_tags.elementAt(4));
                    }
                    
                    try{
                        // TODO check existing things in date base, tarifa...
                        Empleat emp = new Empleat(id, name, dni, compte, direccio, tel, contracte);
                        String mm = cursor.add_obj(className, emp);
                        if(mm.startsWith("ERROR")){
                            System.err.println(mm); 
                            return false;
                        }else{
                            System.out.println(mm);
                            return true;
                        }
                        
                    }catch (Exception e){
                        System.err.println(e);
                    }
                    
                }else if (className.equals("FamiliaProducte")){
                    for (int i = 0;i< _familia_tags.length;i++){
                       try{
                           aux = eElement.getElementsByTagName(_familia_tags[i]).item(0).getTextContent();
                           aux_tags.add(aux);
                       }catch (Exception e){
                           aux_tags.add("");
                           System.err.println("Tag " + _familia_tags[i] + " NOT FOUND: initialized to null");
                       }
                        
                    }
                    String info = aux_tags.elementAt(0);
                    Float impost = null;
                    if(!aux_tags.elementAt(1).equals("")){
                        impost = Float.parseFloat(aux_tags.elementAt(1));
                    }
                    
                    try{
                        // TODO check existing things in date base, tarifa...
                        FamiliaProducte fp = new FamiliaProducte(id, name, info, impost);
                        String mm = cursor.add_obj(className, fp);
                        if(mm.startsWith("ERROR")){
                            System.err.println(mm); 
                            return false;
                        }else{
                            System.out.println(mm);
                            return true;
                        }
                        
                    }catch (Exception e){
                        System.err.println(e);
                    }
                }else if (className.equals("Producte")){
                    for (int i = 0;i< _producte_tags.length;i++){
                       try{
                           aux = eElement.getElementsByTagName(_producte_tags[i]).item(0).getTextContent();
                           aux_tags.add(aux);
                       }catch (Exception e){
                           aux_tags.add("");
                           System.err.println("Tag " + _producte_tags[i] + " NOT FOUND: initialized to null");
                       }
                        
                    }
                    String info = aux_tags.elementAt(0); 
                    Long fam = null;
                    if(!aux_tags.elementAt(1).equals("")){
                        fam = Long.parseLong(aux_tags.elementAt(1));
                    } 
                    Float preu = null;
                    if(!aux_tags.elementAt(2).equals("")){
                        preu = Float.parseFloat(aux_tags.elementAt(2));
                    }   
                    try{
                        // TODO check existing things in date base, tarifa...
                        Producte prod = new Producte(id, name, info, fam, preu);
                        String mm = cursor.add_obj(className, prod);
                        if(mm.startsWith("ERROR")){
                            System.err.println(mm); 
                            return false;
                        }else{
                            System.out.println(mm);
                            return true;
                        }
                        
                    }catch (Exception e){
                        System.err.println(e);
                    }
                    
                    
                }else if (className.equals("Client")){
                    for (int i = 0;i< _client_tags.length;i++){
                       try{
                           aux = eElement.getElementsByTagName(_client_tags[i]).item(0).getTextContent();
                           aux_tags.add(aux); 
                       }catch (Exception e){
                           aux_tags.add("");
                           System.err.println("Tag " + _client_tags[i] + " NOT FOUND: initialized to null");
                       }
                        
                    }
                    String dni = aux_tags.elementAt(0);
                    String street = aux_tags.elementAt(1);
                    Long tel = null;
                    if(!aux_tags.elementAt(2).equals("")){
                        tel = Long.parseLong(aux_tags.elementAt(2));
                    }
                    Long tarifa = null;
                    if(!aux_tags.elementAt(3).equals("")){
                        tarifa = Long.parseLong(aux_tags.elementAt(3));
                    }
                    
                    try{
                        // TODO check existing things in date base, tarifa...
                        Client cli = new Client(id, name, dni, street, tel, tarifa);
                        String mm = cursor.add_obj(className, cli);
                        if(mm.startsWith("ERROR")){
                            System.err.println(mm); 
                            return false;
                        }else{
                            System.out.println(mm);
                        }
                        
                    }catch (Exception e){
                        System.err.println(e);
                        return false;
                    }
                    
                }
                System.out.println(aux_tags.toString());
                return true;
            } catch (Exception e){
                System.err.println("Error on parsing <ID> or <NAME> of " + className + " : Not found!");
                return false;
            }          
        }
        return false;
    }
    
    static public Boolean procesar_fitxer(DateBase cursor, String ff_n){
        try {
            File fXmlFile = new File(ff_n);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            for (String tag : _tags){
                NodeList nList = doc.getElementsByTagName(tag);
                int size_l = nList.getLength();
                if(size_l > 0){
                    int percentage = 100 / size_l;
                    System.out.println("Loading " + size_l + " " + tag + "s...\nCompleted 0%");
                    for(int i = 0; i < size_l; i++){
                        Node nNode = nList.item(i);
                        System.out.println("\nLoading " + nNode.getNodeName() + " " + (i+1) + "...\n");
                        
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            boolean res = parse_and_create_object(cursor, eElement);
                            if(res){
                                System.out.println("\n" + nNode.getNodeName() + " " + (i+1) + " loaded on date base ");
                            }else{
                                System.err.println("ERROR on loading " + nNode.getNodeName() + " " + (i+1));
                            }
                            //System.out.println("ID : " + eElement.getAttribute("id"));
                            
                        }else{
                            System.err.println("Error al carregar el Node!!!!");
                        }
                        
                        System.out.println("Completed " + (percentage * (i+1)) + "%");
                    }
                    if((percentage * (size_l)) < 100){
                        System.out.println("Completed 100%\n");
                    }
                    
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
