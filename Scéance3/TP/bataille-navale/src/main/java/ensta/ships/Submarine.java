package ensta.ships;

public class Submarine extends AbstractShip {

    public Submarine(String cname, char clabel, Direction cdir){
        super(cname, clabel, 3, cdir);
    }

    /**
     * @return default constructor that initializes size at 2 and dir at EAST
     */
    public Submarine(){
        super("",'c',3,Direction.EAST);
    }
    public String toString(){
        return this.name + " " + this.label + " " + this.size + " " + this.dir;
    }
}