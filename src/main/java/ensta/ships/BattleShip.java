package ensta.ships;

public class BattleShip extends AbstractShip {

    public BattleShip(String cname, char clabel, Direction cdir){
        super(cname, clabel, 4, cdir);
    }

    /**
     * @return default constructor that initializes size at 2 and dir at EAST
     */
    public BattleShip(){
        super("",'b',4,Direction.EAST);
    }
    /**
     * @return default constructor that initializes size at 2 and dir at EAST
     */
    public BattleShip(String name){
        super(name,'b',4,Direction.EAST);
    }

    public String toString(){
        return this.name + " " + this.label + " " + this.size + " " + this.dir;
    }
}