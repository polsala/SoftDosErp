
import date_base.Client;
import date_base.DateBase;
import date_base.Empleat;
import date_base.Factura;
import date_base.Inventari;
import date_base.LiniaFactura;
import date_base.Producte;
import date_base.Tarifa;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.LinkedHashMap;

public class PasarPerCaixa {
    private static Integer point_eqivalence = 3; // 1 Punt = 3 euros
    
    public static void modificar_inventari_botiga(DateBase cursor, Producte p, Integer unitats){
        Inventari botiga = Inventari.class.cast(cursor.search_in_table_by_value("Inventari", "_name", "Botiga").elementAt(0));
        botiga.afegir_modificar_Producte(p, -unitats);
        System.out.println("Inventari botiga modificat -" + unitats + "de " + p._name);
    }
    
    public static int calcular_punts(Float import_total){
        int punts = 0;
        if(import_total != null){
            if (import_total > 0){
                punts = import_total.intValue() / point_eqivalence;
            }
        }
        return punts;
    }
    
    
    public static void afegir_linies_factura(DateBase cursor, Factura fact, Client cli) throws IOException{
        Map<Long, LiniaFactura> v_linies = new LinkedHashMap<Long,LiniaFactura>();
        Integer n_line = 1;
        Float totale = 0F;
        BufferedReader reader = 
                new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Entra el codi de producte o # per acabar:");
        String codi_p = reader.readLine();
        while(!codi_p.equals("#")){
            Long id = null;
            try{
                id = Long.parseLong(codi_p);
            }catch (Exception e){
                System.err.println("L'id ha de ser un número");
                id = null;
            }
            if(id != null){
                Object prod = cursor.search_by_id("Producte", id);
                if (prod != null){
                    System.out.println("Entra la quantitat:");
                    String quantitat = reader.readLine();
                    boolean is_int = false;
                    Integer qnt = null;
                    while(!is_int){
                        try{
                            qnt = Integer.parseInt(quantitat);
                            is_int = true;
                        }catch (Exception e){
                            System.err.println("ERROR: la quantitat a de ser un numero");
                        }
                        if(!is_int){
                            System.out.println("Entra la quantitat:");
                            quantitat = reader.readLine();
                        }
                    }
                    //ja tenim producte i quantitat
                    
                    LiniaFactura lf = v_linies.get(id);
                    Producte producte = Producte.class.cast(prod);
                    if(lf != null){
                        Float preu_u = lf._preu_linea/lf._unitats;
                        lf._unitats += qnt;
                        lf._preu_linea = lf._unitats * preu_u;
                        totale += qnt*preu_u;
                        //recalcular preu linia!!
                        modificar_inventari_botiga(cursor, producte, qnt);
                    }else{
                        Float preu_l = 0F;
                        try{
                            if (cli != null && cli._tarifa_id != null){
                                Object tarifa = cursor.search_by_id("Tarifa", cli._tarifa_id);
                                if (tarifa != null){
                                    preu_l = (producte._preu_base - producte._preu_base * Tarifa.class.cast(tarifa).get_descompte_producte(producte._id)) * qnt;
                                }else{
                                    preu_l = producte._preu_base * qnt;
                                }
                            }else{
                                preu_l = producte._preu_base * qnt;
                            }
                            lf = new LiniaFactura(n_line, producte, qnt, preu_l);
                            v_linies.put(id, lf);
                            System.out.println("Afegits " + qnt + producte._name);
                            totale +=preu_l;
                            n_line += 1;
                            modificar_inventari_botiga(cursor, producte, qnt);
                        }catch (Exception e){
                            System.err.println("No s'ha pogut crear la linia");
                        }       
                    }                 
                }else{
                    System.err.println("No s'ha trobat el producte" + codi_p);
                }
            }
            System.out.println("Entra el codi de producte o # per acabar:");
            codi_p = reader.readLine();
            
        }
        if(!v_linies.isEmpty()){
            fact._linies = v_linies;
            fact._total_import = totale;
            //calcular punts
            if (fact._client_id != null && cli!= null){
                int sum_punts = calcular_punts(totale);
                cli.sumar_punts(sum_punts);
            }
        }
        //LiniaFactura(Integer num, Producte p, Integer unitats, Float p_unitari)
        
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
                        afegir_linies_factura(cursor, factura, Client.class.cast(cli));
                        String msg = cursor.add_obj("Factura", factura);
                        
                        
                        if (!msg.startsWith("ERROR")){
                            factura.imprimir_factura();
                        }
                        System.out.println(msg);
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
