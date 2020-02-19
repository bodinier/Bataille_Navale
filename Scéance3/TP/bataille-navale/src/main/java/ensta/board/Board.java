package ensta.board;
import ensta.ships.*;
import ensta.InputHelper;

public class Board implements IBoard {
    private char[][] navires;
    private boolean[][] frappes;
    String name;

    public Board(String bname, int bsize){
        this.name = bname;
        char[][] tab = new char[bsize][bsize];
        boolean[][] frap = new boolean[bsize][bsize];
        this.navires = tab;
        this.frappes = frap;
    }

    public Board(String bname){
        this.name = bname;
        char[][] tab = new char[10][10];
        boolean[][] frap = new boolean[10][10];
        this.navires = tab;
        this.frappes = frap;
    }

    public void print(){
        int size = navires.length;
        System.out.println(this.name + "\n");

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
                if (navires[i][j] != '\0')
                    System.out.print(" "+navires[i][j] + "  ");
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
                if (frappes[i][j])
                    System.out.print(" X" + " ");
                else 
                    System.out.print("  ." + " ");

            }
            System.out.println("");
        }
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
        char label = ship.getLabel();
        x--;
        y--;
        switch (dir){
            case NORTH :
                for (int i=0; i<size; i++)
                {
                    if ((x-i >= 0) && (navires[x-i][y] == '\0'))
                        navires[x-i][y] = label;
                    else 
                        System.out.print("erreur : il y a déjà un navire ici ou en dehors du cadre: (" + (x-i) +";"+ y +")");
                }
                break;
            case SOUTH :
                for (int i=0; i<size; i++)
                {
                    if ( (x+i <= this.getSize() ) && (navires[x+i][y] == '\0'))
                        navires[x+i][y] = label;
                    else 
                        System.out.print("erreur : il y a déjà un navire ici ou en dehors du cadre: (" + (x+i) +";"+ y +")");
                }
                break;
            case EAST :
                for (int i=0; i<size; i++)
                {
                    if ( (y+i <= this.getSize() ) && (navires[x][y+i] == '\0'))
                        navires[x][y+i] = label;
                    else 
                        System.out.print("erreur : il y a déjà un navire ici ou en dehors du cadre: (" + x +";"+ (y+i) +")");
                }
                break;
            case WEST :
                for (int i=0; i<size; i++)
                {
                    if ((y-i >= 0)&&(navires[x][y-i] == '\0'))
                        navires[x][y-i] = label;
                    else 
                        System.out.print("erreur : il y a déjà un navire ici ou en dehors du cadre: (" + x + ";" + (y-i) +")");
                }
                break;
        }
    }

    @Override
    public boolean hasShip(int x, int y){
        return (navires[x][y] != '\0') ? true : false;
    }

    @Override
    public void setHit(boolean hit, int x, int y){
        x--;
        y--;
        frappes[x][y] = hit;
    }

    @Override
    public boolean getHit(int x, int y){
        return (frappes[x][y]);
        
    }

    public boolean moveIsValid(int x,int y, AbstractShip ship, String dir ){
        int length = ship.getSize();
        if (0<= x && x <= (this.getSize()+1) && 0 <= y && y <= (this.getSize()+1) && !this.hasShip(x-1, y-1))
            {
                switch (dir){
                    case "n" :
                        if (x-length >= 0)
                            return true;
                        break;
                    case "s" :
                        if (x+length <= this.getSize()+1)
                            return true;
                        break;
                    case "e" :
                        if (y+length <= this.getSize()+1)
                            return true;
                        break;
                    case "w" :
                        if (y-length >= 0)
                            return true;
                     break;
                }
            }
        return false;
    }
}