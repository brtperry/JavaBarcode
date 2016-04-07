/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labratory;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pcbpy
 */
public class Labratory {
    
    public static Barcode getSingletonBarcode()
    {
        return Barcode.getInstance();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        if (args.length != 1) {return;}
        
        System.out.println("Arguments: " + args[0]);
        
        try {
            
            String m = args[0]; //"A0123456789B0123456789C01";//args[0];
            
            Barcode bc = getSingletonBarcode();
            
            boolean result = bc.generate(m);
            
            System.out.println("Result of barcode formatting is: " + result);
            
        } 
        catch (Exception ex) {
            Logger.getLogger(Labratory.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        System.out.println("Fin.");
    }
}
