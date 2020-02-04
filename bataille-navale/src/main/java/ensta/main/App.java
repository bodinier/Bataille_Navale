package ensta.main;
import ensta.ships.*;

public class App 
{
    static public void show(Object o){
        System.out.println(o.toString());
    }
    public static void main( String[] args )
    {
        System.out.println( "\nHello World!\n" );
        Carrier carr = new Carrier();
        Destroyer dest = new Destroyer("Black Pearl", 'a', Direction.WEST);
        show(carr);
        show(dest);
        dest.setSize(4);
        show(dest);
        System.out.println( "\n" );
    }
}
