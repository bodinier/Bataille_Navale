package ensta.ships;
import ensta.ColorUtil;
import ensta.ColorUtil.*;

public class ShipState {
    private AbstractShip ship;
    private boolean struck;

    /**
     * @param tship ship which shipState belongs to
     * beware not to initialize @param struck 
     */
    public ShipState(AbstractShip tship){
        this.ship = tship;
    }

    public void addStrike(){
        this.struck = true;
        ship.addStrike();
    }

    public boolean isStruck(){
        return struck;
    }

    @Override
    public String toString(){
        if (this.struck){
            return ColorUtil.colorize(ship.getLabel(), Color.RED);
        }
        return ColorUtil.colorize(ship.getLabel(), Color.BLUE);
    }

    public boolean isSunk(){
        return ship.isSunk();
        }

    public AbstractShip getShip(){
        return this.ship;        
        } 
        
    public void setShip(AbstractShip ship){
        this.ship = ship;
    }
    
    }