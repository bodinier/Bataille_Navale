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
        player.putShips();
    }

        if (test){
            /*Board myBoard = new Board("myBoard", 8);
            Carrier carrier = new Carrier("black pearl", 'd', Direction.EAST);
            BattleShip battleship = new BattleShip("ckikou", 'b', Direction.SOUTH);
            myBoard.putShip(carrier, 5, 1);
            myBoard.print();
            myBoard.putShip(battleship, 4, 1);
            System.out.println(myBoard.canPutShip(battleship, 4, 1));
            myBoard.print();*/
            TestGame.main();
        }
    }
}
