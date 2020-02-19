package ensta;

import ensta.board.*;
import ensta.ships.*;

import java.util.ArrayList;

import ensta.InputHelper.ShipInput;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        boolean test = false;
        // Init :
        if (!test){
        Board myBoard = new Board("myBoard", 10);
        Board opponentBoard = new Board("opponentBoard", 10);
        Destroyer destroyer = new Destroyer("destroyer");
        BattleShip battleShip = new BattleShip("battleship");
        Submarine submarine1 = new Submarine("submarine 1");
        Submarine submarine2 = new Submarine("submarine 2");
        Carrier carrier = new Carrier("carrier");
        ArrayList<AbstractShip> shipList = new ArrayList<AbstractShip>();
        shipList.add(destroyer);
        shipList.add(submarine1);
        shipList.add(submarine2);
        shipList.add(battleShip);
        shipList.add(carrier);
        
        Player player = new Player(myBoard, opponentBoard, shipList);
        player.putShips(myBoard);}
        if (test){
            Board myBoard = new Board("myBoard", 8);
            myBoard.print();
            /*System.out.println(myBoard.getSize());
            Carrier carrier1 = new Carrier("carrier", 'c', Direction.NORTH);
            System.out.println(myBoard.moveIsValid(5, 1, carrier1, "n"));
            Carrier carrier2 = new Carrier("carrier", 'c', Direction.SOUTH);
            System.out.println(myBoard.moveIsValid(6, 5, carrier2, "s"));
            Carrier carrier3 = new Carrier("carrier", 'c', Direction.EAST);
            System.out.println(myBoard.moveIsValid(1, 7, carrier3, "e"));
            Carrier carrier4 = new Carrier("carrier", 'c', Direction.WEST);
            System.out.println(myBoard.moveIsValid(2, 5, carrier4, "w"));*/
        }
    }
}
