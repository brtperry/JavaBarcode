/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labratory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
/**
 *
 * @author pcbpy
 */
public class Barcode {
    
    private int defaultBarcodeWidth;
    
    private Map map = new Map();

    /**
     * Constructor.
     */
    private Barcode() {

        BarcodeData bc = new BarcodeData();
        
        for (int i = 0; i < bc.getLength(); i++) {
            map.Add(bc.characters[i], bc.binaries[i]);
        }
    }
    /**
     * Singleton instance
     */
    private static Barcode instance = new Barcode();
   
    /**
     * Retrieve a single instance of the class.
     * @return singleton instance
     */
    public static Barcode getInstance() {
        return instance;
    } 

    private String formatBarcode(String s) {
        return "*" + s.replaceAll("\\*", "").toUpperCase() + "*";
    }
    
    /**
     * This function is no longer required as using an algorithm 
     * to determine the correct width of the barcode.
     * Return an image cropped to a specified width.
     * @param src
     * @param width
     * @return 
     */
    private BufferedImage cropBufferedImage(BufferedImage src, int width) {
      return src.getSubimage(0, 0, width, 30);
    }
    
    private int setDefaultBarcodeWidth(int len)
    {
        //First character is 50
        // Second and thereafter += 16
        // There 'ZA12' = (50 + 16 + 16 + 16 = 98)
        return 50 + (len * 16);
    }
    
    public boolean generate(String letters) throws Exception{
        
        if (letters.isEmpty()) {
            throw new Exception("wtf");
        }
        
        int cx = 2;
        
        String result = formatBarcode(letters);
        
        defaultBarcodeWidth = setDefaultBarcodeWidth(letters.length() - 1);
        
        BufferedImage barcode = new BufferedImage(defaultBarcodeWidth, 30, BufferedImage.TYPE_INT_RGB);
        
        /**
         * Before I worked on the BufferedImage 
         * but I'm now using the graphics object, painting white
         * and then drawing the barcode onto the graphics object.
         */
        
        Graphics2D graphics = barcode.createGraphics();
        
        graphics.setPaint(new Color(255, 255, 255));
        graphics.fillRect( 0, 0, barcode.getWidth(), barcode.getHeight() );
        
        for (char ch: result.toCharArray()) {
            
            String bin = map.Find(ch);
            
            if (bin == null) {
                throw new Exception("No binary data attached to value. %c" + ch);
            }
            
            //System.out.println(bin);
            
            for(char c: bin.toCharArray()) {

                if ("1".equals(Character.toString(c))) {
                    
                    graphics.setColor(Color.BLACK);
                    graphics.drawLine(cx, 2, cx, 26);
                
                    //barcode.getGraphics().setColor(Color.BLACK);
                    //barcode.getGraphics().drawLine(cx, 2, cx, 26);
                    
                    
                }
                
                cx++;
            }
        }
        
        System.out.println("Final cropping width: " + cx);
        
        /**
         * Resize the image.  No longer necessary.
         */
        //barcode = cropBufferedImage(barcode, cx + 1);
        
        File output = new File("barcode.bmp");

        return ImageIO.write(barcode, "png", output);
    }
}




