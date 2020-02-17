package ensta.ships;

public class Destroyer extends AbstractShip {

    public Destroyer(String cname, char clabel, Direction cdir){
        super(cname, clabel, 2, cdir);
    }

    /**
     * @return default constructor that initializes size at 2 and dir at EAST
     */
    public Destroyer(){
        super("",'d',2,Direction.EAST);
    }
    public Destroyer(String name){
        super(name,'d',2,Direction.EAST);
    }
    public String toString(){
        return this.name + " " + this.label + " " + this.size + " " + this.dir;
    }
}