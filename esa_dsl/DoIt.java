import MakePizza;

public class DoIt {
    public static void main(String[] args) {
        MakePizza mp = (new MakePizza("Magaritta")).ingredients("Mehl,Wasser,Hefe,Tomaten,Morzarella,Basilikum").duration(20);
        System.out.println(mp);
    }
}