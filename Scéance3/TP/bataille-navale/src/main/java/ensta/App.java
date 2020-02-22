package ensta;

import ensta.board.*;
import ensta.ships.*;
import ensta.player.*;
import java.util.ArrayList;

public class App 
{
    public static void main( String[] args )
    {
        boolean test = true;
        
        // Init :
        if (!test){
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
        player.putShips(myBoard);
    }

        if (test){
            /*Board myBoard = new Board("myBoard", 8);
            Carrier carrier = new Carrier("black pearl", 'd', Direction.EAST);
            myBoard.putShip(carrier, 2, 3);
            myBoard.print();
            myBoard.sendHit(2, 3);
            myBoard.sendHit(1, 8);
            myBoard.sendHit(2, 8);
            myBoard.sendHit(2, 6);
            myBoard.sendHit(2, 4);
            System.out.println(myBoard.sendHit(2, 7));
            myBoard.print();*/
            TestGame.main();
        }
    }
}
