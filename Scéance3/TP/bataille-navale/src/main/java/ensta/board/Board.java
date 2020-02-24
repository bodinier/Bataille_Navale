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
        System.out.print("Navires");
        for (int i =0; i<4*size+1;i++ )
        {
            System.out.print(" ");
        }
        System.out.println(" Frappes");
        for (int i=0; i<size+1; i++){
            if (i==0)
                System.out.print("    ");
            else
                System.out.print(" "+(char)(64+i) + "  ");
        }
        System.out.print("       ");
        for (int i=0; i<size+1; i++){
            if (i==0)
                System.out.print("   ");
            else
                System.out.print(" "+(char)(64+i) + "  ");
        }

        // Affichage des tableaux :
        System.out.println("");
        for (int i =0; i<size; i++)
        {
            if (i<9)
                System.out.print(i+1 + "   ");
            else if (i>=9)
            System.out.print(i+1 + "  ");
            // Navires :
            for (int j=0; j<size; j++)
            {
                if (navires[i][j] != null)
                    System.out.print(" " + navires[i][j].toString() + "  ");
                else 
                    System.out.print(" ." + "  ");
            }
            System.out.print("     ");
            if (i<9)
                System.out.print(i+1 + "   ");
            else if (i>=9)
            System.out.print(i+1 + "  ");
            
            //Frappes : 
            for (int j=0; j<size; j++)
            {
                if (frappes[i][j] != null){
                    if (frappes[i][j].booleanValue() && this.hasShip(i, j)){
                        System.out.print("  " + ColorUtil.colorize("X", Color.RED) + " ");
                    }
                    else 
                        System.out.print("  " + ColorUtil.colorize("X", Color.WHITE) + " ");
                }
                else {
                    System.out.print("  ." + " ");
                }

            }
            System.out.println("");
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
    public void putShip(AbstractShip ship, int x, int y){
        int size = ship.getSize();
        Direction dir = ship.getDirection();
        if (x < 0 || y < 0 || x > this.getSize() || y > this.getSize()){
            System.out.println("erreur : données invalides, je quitte le programme");
            System.exit(0);
        }
        switch (dir){
            case NORTH :
                for (int i=0; i<size; i++)
                {
                    navires[x-i-1][y-1]= new ShipState(ship) ;
                }
                break;
            case SOUTH :
                for (int i=0; i<size; i++)
                {
                    navires[x+i-1][y-1]= new ShipState(ship);
                }
                break;
            case EAST :
                for (int i=0; i<size; i++)
                {
                    navires[x-1][y+i-1] = new ShipState(ship);
                }
                break;
            case WEST :
                for (int i=0; i<size; i++)
                {
                    navires[x-1][y-i-1] = new ShipState(ship);
                }
                break;
        }
    }

    @Override
    public boolean hasShip(int x, int y){
        return (this.navires[x][y] != null) ? true : false;
    }

    @Override
    public void setHit(boolean hit, int x, int y){
        x--;
        y--;
        if (hit)   
            this.frappes[x][y] = true;
        else 
            this.frappes[x][y] = false;
        if (this.hasShip(x, y)){
            this.navires[x][y].addStrike();
        }
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
    public boolean canPutShip(int x,int y, AbstractShip ship){
        System.out.println("("+x+","+y+") and dir = " + ship.getDirection() + " shipSize = " + ship.getSize());
        int shipLength = ship.getSize();
        Direction shipDir = ship.getDirection();
        int boardSize = this.getSize();
        if (x-1 < 0 || x-1 > boardSize || y-1 < 0 || y-1 > boardSize)
            return false;
        switch (shipDir){
            case NORTH :
            for (int i =0; i<shipLength; i++){
                if ((x-1-i)< 0)
                    return false;
                else if (this.hasShip(x-1-i, y-1))
                    return false;
            }
            break;
            case SOUTH : 
            for (int i =0; i<shipLength; i++){
                if ((x-1+i)> boardSize-1)
                    return false;
                if (this.hasShip(x-1+i, y-1))
                    return false;
            }
            break;
            case EAST : 
            for (int i =0; i<shipLength; i++){
                if ((y-1+i)> boardSize-1)
                    return false;
                else if (this.hasShip(x-1, y-1+i))
                    return false;
            }
            break;
            case WEST : 
            for (int i =0; i<shipLength; i++){
                if ((y-1-i) < 0 )
                    return false;
                else if (this.hasShip(x-1, y-1-i))
                    return false;
            }
            break;
        }
        return true;
    }

    @Override
    public Hit sendHit(int x, int y){
        if (this.hasShip(x-1, y-1)){
            AbstractShip shipStruck = this.navires[x-1][y-1].getShip();
            if (!shipStruck.isSunk()){
                //TODO remove setHit
                this.setHit(true, x, y);
                return Hit.STIKE;
            }
            else {
                //TODO remove setHit
                this.setHit(true, x, y);
                int type = shipStruck.getSize();
                return Hit.fromInt(type);
            }
        }
        else {
            this.setHit(true, x, y);
            return Hit.MISS;
        }
    }
}