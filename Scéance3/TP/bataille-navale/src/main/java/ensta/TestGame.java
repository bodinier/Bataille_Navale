package ensta;

import java.util.ArrayList;
import java.util.Arrays;

import ensta.board.*;
import ensta.player.BattleShipsAI;
import ensta.ships.*;

public class TestGame {

    private static void sleep(int ms) { 
        try {
            Thread.sleep(ms);
            } 
        catch (InterruptedException e) {
            e.printStackTrace(); 
            }
        }

    public static void main(){
        Board myBoard = new Board("TestGame", 8);
        myBoard.print();
        Destroyer destroyer = new Destroyer("destroyer", 'd', Direction.EAST);
        BattleShip battleShip = new BattleShip("battleship", 'b', Direction.WEST);
        Submarine submarine1 = new Submarine("submarine 1", 's', Direction.SOUTH);
        Submarine submarine2 = new Submarine("submarine 2", 's', Direction.NORTH);
        Carrier carrier = new Carrier("carrier", 'c', Direction.EAST);
        ArrayList<AbstractShip> shipList = new ArrayList<AbstractShip>();
        shipList.add(destroyer);
        shipList.add(submarine1);
        shipList.add(submarine2);
        shipList.add(battleShip);
        shipList.add(carrier);
        myBoard.putShip(destroyer, 1, 1);
        myBoard.putShip(battleShip, 2, 5);
        myBoard.putShip(submarine1, 5, 1);
        myBoard.putShip(submarine2, 8, 6);
        myBoard.putShip(carrier, 7, 1);
        myBoard.print();

        BattleShipsAI ai = new BattleShipsAI(myBoard, myBoard);
        //ai.putShips(shipList);
        int sunkCounter = 0;
        int[] shipSunkList = {2, 3, 4, 5};
        do {
            int[] coords = {0, 0};
            Hit hit = ai.sendHit(coords);
            System.out.println("hit at : (" + coords[0] + "," + coords[1]+")");
            System.out.println(hit.toString());
            if (Arrays.asList(shipSunkList).contains(hit)){
                sunkCounter ++;
            }
            myBoard.print();
            sleep(1000);
        } while (sunkCounter <5);
    }
}