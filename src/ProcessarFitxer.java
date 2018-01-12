import date_base.*;
import java.io.File;
import java.util.Map;
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
    static String[] _empleats_tags = {"NAME", "DNI", "COMPTE", "DIRECCIO", "TELEFON", "CONTRACTE"};
    static String[] _familia_tags = {"NAME", "INFO", "IMPOST"};
    static String[] _producte_tags = {"NAME", "INFO", "FAMILIA", "PREU"};
    static String[] _client_tags = {"NAME", "DNI", "COMPTE", "DIRECCIO", "TELEFON", "CONTRACTE"};
    
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
    
    private static boolean parse_and_create_object(DateBase cursor, Element eElement){
        String className = eElement.getNodeName();
        if (!className.equals("") && check_class_exist(className)){
            boolean table_created = cursor.create_table(className);
            
            System.out.println("Name : " + eElement.getElementsByTagName("NAME").item(0).getTextContent());
        }
        return false;
    }
    
    static public Boolean procesar_fitxer(DateBase cursor, File fXmlFile){
        try {
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
                        System.out.println("\nLoading " + nNode.getNodeName() + " " + (i+1) + "...");
                        
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
                        System.out.println("Completed 100%");
                    }
                    
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
