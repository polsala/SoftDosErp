
import date_base.Client;
import date_base.DateBase;
import date_base.Empleat;
import date_base.Factura;
import date_base.LiniaFactura;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.LinkedHashMap;

public class PasarPerCaixa {
    
    public static void afegir_linies_factura(DateBase cursor){
        Map<Long, LiniaFactura> v_linies = new LinkedHashMap<Long,LiniaFactura>();
        
        
    }
    
    public static void pasar_per_caixa(DateBase cursor) throws IOException{
        BufferedReader reader = 
                new BufferedReader(new InputStreamReader(System.in));
        
        
         
        //Factura(Long id, String name, Integer num, Long e_id, Long cli);
        System.out.println("    GESTIÓ DE VENTA    ");
        System.out.println("-----------------------");
         
        
        // Reading data using readLine
        boolean is_number = false;
        while(!is_number){
            Long iid = null;
            try{
                //cerca empleat required
               System.out.println("Entra l'id d'empleat:");
               String id = reader.readLine();
               iid = Long.parseLong(id);
               is_number = true;
               Object emp = cursor.search_by_id("Empleat", iid);
               if(emp != null){
                   System.out.println("Empleat " + Empleat.class.cast(emp)._name + " seleccionat");
                   System.out.println("És correcte? y/n");
                   String op = reader.readLine();
                   while(!op.equals("y") && !op.equals("n")){
                       System.out.println("És correcte? y/n");
                       op = reader.readLine();
                   }
                   if(op.equals("y")){
                        // numero de caica
                        Integer nuu = null;
                        boolean numc = false;
                        while(!numc){
                            System.out.println("Entra el número de caixa:");
                            String nu = reader.readLine();
                            try{
                                nuu = Integer.parseInt(nu);
                                numc = true;
                            }catch(Exception e){
                                System.err.println("Error: el numero de caixa a de ser un número");
                            }
                        }
                        // És client?
                        System.out.println("És client de la botiga?:y/n");
                        op = reader.readLine();
                        while(!op.equals("y") && !op.equals("n")){
                            System.out.println("És client de la botiga?:y/n");
                            op = reader.readLine();
                        }
                        // buscar client
                        Object cli = null;
                        Long idde = null;
                        if (op.equals("y")){
                            boolean is_lon = false;
                            while(!is_lon){
                                idde = null;
                                try{
                                    System.out.println("Entra l'id d'client: / or skip to set null");
                                    String ide = reader.readLine();
                                    if(ide.equals("skip")){
                                        is_lon = true;
                                        System.out.println("La factura no tindra client assignat");
                                    }else{
                                        idde = Long.parseLong(ide);
                                        is_lon = true;
                                        cli = cursor.search_by_id("Client", idde);
                                        if(cli != null){
                                            System.out.println("Client " + Client.class.cast(cli)._name + " seleccionat");
                                            System.out.println("És correcte? y/n");
                                            op = reader.readLine();
                                            while(!op.equals("y") && !op.equals("n")){
                                                System.out.println("És correcte? y/n");
                                                op = reader.readLine();
                                            }
                                            if(op.equals("y")){
                                                System.out.println("Client seleccionat");
                                            }else{
                                                is_lon = false;
                                            }
                                        }else{
                                            System.err.println("Error: no existeix el client " + ide);
                                            is_lon = false;
                                        }
                                    }
                                }catch (Exception e){
                                    System.err.println("Error: l'id del client a de ser un número");
                                    is_lon = false;
                                }
                            }      
                        }else{
                            System.out.println("La factura no tindra client assignat");
                        }
                        
                        //Crear factura
                        
                        cursor.create_table("Factura");
                        Long id_f = cursor.new_id_for_table("Factura");

                        Long cliid = null;
                        if(cli != null){
                            cliid = idde;
                        }
                        
                        Factura factura = new Factura(id_f, id_f.toString(), nuu, iid, cliid);
                        
                        cursor.add_obj("Factura", factura);
                        
                        //bloc de productes
                        //sadsads/
                        
                        
                        
                        
                   }else{
                       is_number = false;
                   }
                    
               }else{
                   System.err.println("No existeix l'empleat amb id " + id);
                   is_number = false;
               }
            
            }catch (Exception e){
                System.err.println("Error: l'id ha de ser un numero");
            }
            
        }
        
        
     }
}
