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
        // Init :
        Board myBoard = new Board("myBoard", 8);
        Board opponentBoard = new Board("opponentBoard", 8);
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
        player.putShips();
    }
}
