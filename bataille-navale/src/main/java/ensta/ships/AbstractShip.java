package ensta.ships;

abstract public class AbstractShip{
    protected char label;
    protected String name;
    protected int size;
    protected Direction dir;

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

}