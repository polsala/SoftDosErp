/*
 * This class validate and load files with date base input.
 */
package validator;

import java.io.File;


public class FileValidator {
    private File db;
    
    public FileValidator(File f){
        db = f;
    }
    
    public boolean validate_and_load(){
        if (db.exists()){
            
        }
        
        return false;
    }
    
}
