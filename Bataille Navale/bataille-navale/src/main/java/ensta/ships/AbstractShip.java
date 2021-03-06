package ensta.ships;

abstract public class AbstractShip{
    protected char label;
    protected String name;
    protected int size;
    protected Direction dir;
    protected int strikeCount;

    public AbstractShip(String cname, char clabel, int csize, Direction cdir){
        this.name = cname;
        this.label = clabel;
        this.size = csize;
        this.dir = cdir;
    }

    public char getLabel(){
        return this.label;
    }

    public String getName(){
        return this.name;
    }

    public int getSize(){
        return this.size;
    }

    public Direction getDirection(){
        return this.dir;
    }

    public void setSize(int newSize){
        this.size = newSize;
    }

    public void setLabel(char newLabel){
        this.label = newLabel;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void setDirection(Direction newDir){
        this.dir = newDir;
    }

    public void setDirection(String newDir){
        switch (newDir){
            case "n" :
                this.dir = Direction.NORTH;
                break;
            case "s" :
                this.dir = Direction.SOUTH;
                break;
            case "e" :
                this.dir = Direction.EAST;
                break;
            case "w" :
                this.dir = Direction.WEST;
                break;
        }
    }

    public void addStrike(){
        this.strikeCount ++;
    }

    public boolean isSunk(){
        if (strikeCount >= this.getSize()){
            return true;
        }
        else 
            return false;
    }
}