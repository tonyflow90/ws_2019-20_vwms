package de.vwms2019.esa4;


public class Hello {
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        String sWorld = "World";
        System.out.println(computeMessageString(sWorld));
    }

    
    /** 
     * @param message
     * @return String
     */
    public static String computeMessageString(String message) {
        return "Hello " + message + "!";
    }
}