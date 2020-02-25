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
            Hit hit = myBoard.sendHit(5, 1);
            myBoard.setHit(true, 5, 1);
            System.out.println(hit);
            myBoard.print();
            myBoard.sendHit(5, 2);
            myBoard.setHit(true, 5, 2);
            System.out.println(hit);
            myBoard.print();
            myBoard.sendHit(5, 3);
            myBoard.setHit(true, 5, 3);
            System.out.println(hit);
            myBoard.print();
            myBoard.sendHit(5, 4);
            myBoard.setHit(true, 5, 4);
            System.out.println(hit);
            myBoard.print();
            hit = myBoard.sendHit(5, 5);
            myBoard.setHit(true, 5, 5);
            System.out.println(hit);
            myBoard.print();*/
            TestGame.main();
            /*Board myBoard = new Board("myBoard", 8);
            Carrier carrier = new Carrier("black pearl", 'c', Direction.WEST);
            myBoard.print();
            System.out.println(myBoard.canPutShip(carrier, 1, 5));*/
        }
    }
}
