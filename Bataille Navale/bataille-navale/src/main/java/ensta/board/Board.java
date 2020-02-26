package ensta.board;
//import org.omg.CORBA.portable.ValueFactory;
import ensta.tools.*;
import ensta.tools.ColorUtil.Color;
import ensta.Hit;
import ensta.ships.*;

public class Board implements IBoard {

    private ShipState[][] navires;
    private Boolean[][] frappes;
    private String name;

    public Board(String bname, int bsize){
        this.name = bname;
        ShipState[][] tab = new ShipState[bsize][bsize];
        Boolean[][] frap = new Boolean[bsize][bsize];
        this.navires = tab;
        this.frappes = frap;
    }

    public Board(String bname){
        this.name = bname;
        ShipState[][] tab = new ShipState[10][10];
        Boolean[][] frap = new Boolean[10][10];
        this.navires = tab;
        this.frappes = frap;
    }

    @Override
    public void print(){
        int size = navires.length;

        System.out.println("");
        //On affiche les noms des tableaux
        System.out.print(ColorUtil.colorize("Navires", Color.GREEN));
        for (int i =0; i<4*size+1;i++ )
        {
            System.out.print(ColorUtil.colorize("-", Color.CYAN));
        }
        System.out.print(ColorUtil.colorize(" Frappes", Color.YELLOW));
        for (int i =0; i<4*(size-1);i++ )
        {
            System.out.print(ColorUtil.colorize("-", Color.CYAN));
        }
        System.out.println("");
        for (int i=0; i<size+1; i++){
            if (i==0)
                System.out.print("    ");
            else
                System.out.print(ColorUtil.colorize(" "+(char)(64+i) + "  ", Color.GREEN));
        }
        System.out.print("       ");
        for (int i=0; i<size+1; i++){
            if (i==0)
                System.out.print("   ");
            else
                System.out.print(ColorUtil.colorize(" "+(char)(64+i) + "  ", Color.YELLOW));
        }

        // Affichage des tableaux :
        System.out.println("");
        for (int i =0; i<size; i++)
        {
            if (i<9)
                System.out.print(ColorUtil.colorize(i+1 + "   ", Color.GREEN));
            else if (i>=9)
            System.out.print(ColorUtil.colorize(i+1 + "  ", Color.GREEN));
            // Navires :
            for (int j=0; j<size; j++)
            {
                if (navires[i][j] != null)
                    System.out.print(" " + navires[i][j].toString() + "  ");
                else 
                    System.out.print(ColorUtil.colorize(" ." + "  ", Color.GREEN));
            }
            System.out.print(ColorUtil.colorize("  |  ", Color.CYAN));
            if (i<9)
            System.out.print(ColorUtil.colorize(i+1 + "   ", Color.YELLOW));
            else if (i>=9)
            System.out.print(ColorUtil.colorize(i+1 + "  ", Color.YELLOW));
            
            //Frappes : 
            for (int j=0; j<size; j++)
            {
                if (frappes[i][j] != null){
                    if (frappes[i][j]){
                        System.out.print("  " + ColorUtil.colorize("X", Color.RED) + " ");
                    }
                    else 
                        System.out.print("  " + ColorUtil.colorize("X", Color.WHITE) + " ");
                }
                else {
                    System.out.print(ColorUtil.colorize("  ." + " ", Color.YELLOW));
                }

            }
            System.out.print(ColorUtil.colorize("  |  ", Color.CYAN));
            System.out.println("");
        }
        for (int i =0; i<4*size+1;i++ )
        {
            System.out.print(ColorUtil.colorize("-", Color.CYAN));
        }
        for (int i =0; i<5*size+1;i++ )
        {
            System.out.print(ColorUtil.colorize("-", Color.CYAN));
        }
        System.out.println("");
    }

    @Override
    public int getSize(){
        return navires[0].length;
    }
    
    /**
     * Place a ship 'ship' starting at (x,y). Warning, indices starts at 1
     * @param ship 
     * @param x coordinate (1 <= x <= size)
     * @param y coordinate (1 <= y <= size)
     */
    @Override
    public void putShip(AbstractShip ship, int x, int y) {
        if(x < 0 || x >= this.getSize()+1 || y < 0 || y >= this.getSize()+1) {
            throw new IllegalArgumentException("Out of map!"  + x + " " + y);
        }
        int size = ship.getSize();

        switch (ship.getDirection()) {

            case NORTH:
                if (x-1-size < 0 || x-1-size > this.getSize()+1)
                    throw(new IllegalArgumentException("Out of map!" + x + " " + y));
                for (int i =0; i < size; i++){
                    if (hasShip(x-1-i, y-1))
                        throw new IllegalArgumentException("Ship Collapse !" + x + " " + y);
                    }
                for (int i =0; i < size; i++){
                    this.navires[x-1-i][y-1] = new ShipState(ship);
                }
                break;

            case SOUTH:
                if (x-1+size >this.getSize()+1 || x-1+size < 0 )
                    throw(new IllegalArgumentException("Out of map!" + x + " " + y));

                for (int i =0; i < size; i++){
                    if (hasShip(x-1+i, y-1))
                        throw new IllegalArgumentException("Ship Collapse !" + x + " " + y);
                    }
                for (int i =0; i < size; i++){
                    this.navires[x-1+i][y-1] = new ShipState(ship);
                }
                break;

            case WEST:
                if (y-size < 0 || y-1-size > this.getSize()+1)
                {
                    throw(new IllegalArgumentException("Out of map!" + x + " " + y));
                }
                for (int i =0; i < size; i++){
                    if (hasShip(x-1, y-1-i))
                        throw new IllegalArgumentException("Ship Collapse !" + x + " " + y);
                    }
                for (int i =0; i < size; i++){
                    this.navires[x-1][y-1-i] = new ShipState(ship);
                }
                break;

            case EAST:
                if (y-1+size > this.getSize()+1 || y-1+size < 0){
                    throw(new IllegalArgumentException("Out of map!"));
                }
                    for (int i =0; i < size; i++){
                        if (hasShip(x-1, y-1+i))
                            throw new IllegalArgumentException("Ship Collapse !" + x + " " + y);
                        }
                    for (int i =0; i < size; i++){
                        this.navires[x-1][y-1+i] = new ShipState(ship);
                    }
                break;
        }
    }
    

    @Override
    public boolean hasShip(int x, int y){
        // if the ship have been sunk, we consider that it doesnt exist anymore
        if (this.navires[x][y] != null){
            if (this.navires[x][y].isSunk())
                return false;
            else 
                return true;
        }
        else 
            return false;

    }

    @Override
    public void setHit(boolean hit, int x, int y){
        x--;
        y--;
        this.frappes[x][y] = hit;
    }
    
    /**
     * @param x
     * @param y
     * @return true if there is a hit at (x,y)
     */
    @Override
    public Boolean getHit(int x, int y){
        return (frappes[x][y]);
        
    }

    /**
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @param ship
     * @param dir
     * @return true if this ship can be placed at (x,y) with this direction
     */
    public boolean canPutShip(AbstractShip ship, int x, int y) {
        System.out.println("( " + x + ", " + y + ") => dir = " + ship.getDirection());
        if(x-1 < 0 || x-1 > this.getSize()+2 || y-1 < 0 || y-1 > this.getSize()+2) {
            return false;
        }
        int size = ship.getSize();
        try {
        switch (ship.getDirection()) {

            case NORTH:
                if (x-1-size < 0 || x-1-size > this.getSize()+2)
                    return false;
                for (int i =0; i < size; i++){
                    if (hasShip(x-1-i, y-1))
                    return false;
                }
                break;

            case SOUTH:
                if (x-1+size >this.getSize()+2 || x-1+size < 0 )
                    return false;
                for (int i =0; i < size; i++){
                    if (hasShip(x-1+i, y-1))
                        return false;
                }
                break;

            case WEST:
                if (y-size < 0 || y-1-size > this.getSize()+2){
                    System.out.println("LA1");
                    return false;}
                for (int i =0; i < size; i++){
                    if (hasShip(x-1, y-1-i))
                        return false;
                }
                break;

            case EAST:
                if (y-1+size > this.getSize()+3 || y-1+size < 0){
                    System.out.println("LA2");
                    return false;
                }
                for (int i =0; i < size; i++){
                    if (hasShip(x-1, y-1+i))
                        return false;
                }
                break;
            default :
                return false;
        }}
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Hit sendHit(int x, int y) {
        //check if not already hit
        if (frappes[x-1][y-1] != null){
            if (frappes[x-1][y-1])
                return Hit.MISS;
        }
        if(x < 0 || x >= this.getSize()+1| y < 0 || y >= this.getSize()+1){
            throw new IllegalArgumentException("outside the map !");
        }

        if (this.navires[x-1][y-1] == null) {
            return Hit.MISS;
        } 

        else {
            if (!this.navires[x-1][y-1].isSunk()) {
                this.navires[x-1][y-1].addStrike();
                // we consider the case only one strike remained
                if (this.navires[x-1][y-1].isSunk()) {
                    Hit hit = Hit.fromInt(this.navires[x-1][y-1].getShip().getSize());
                    return hit;
                } 
                else 
                    return Hit.STIKE; // this ship isnt sunk
                }
            else {
                AbstractShip sunkShip = this.navires[x-1][y-1].getShip();
                Hit hit = Hit.fromInt(sunkShip.getSize());
                return hit;
            }
    }  
}
}