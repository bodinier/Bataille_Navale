package ensta.ships;

public class Carrier extends AbstractShip {

    public Carrier(String cname, char clabel, Direction cdir){
        super(cname, clabel, 5, cdir);
    }

    /**
     * @return default constructor that initializes size at 2 and dir at EAST
     */
    public Carrier(){
        super("",'c',5,Direction.EAST);
    }
    /**
     * @return default constructor that initializes size at 2 and dir at EAST
     */
    public Carrier(String name){
        super(name,'c',5,Direction.EAST);
    }
    public String toString(){
        return this.name + " " + this.label + " " + this.size + " " + this.dir;
    }
}