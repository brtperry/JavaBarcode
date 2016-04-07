/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labratory;

import java.util.HashMap;

/**
 *
 * @author pcbpy
 */
public class Map implements Operations {
    
    private HashMap<Character, String> data = new HashMap<>();
    
    /**
     * Constructor
     */
    public Map() {}
    
    @Override
    public void Add(char c, String bin) {
        data.put(c, bin);
    }
    
    @Override
    public String Find(char c) {
        return data.get(c);
    }
}
