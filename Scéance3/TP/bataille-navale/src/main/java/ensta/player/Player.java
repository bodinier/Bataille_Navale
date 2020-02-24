package ensta.player;

import java.io.Serializable;
import java.util.List;

import ensta.Hit;
import ensta.board.*;
import ensta.ships.*;
import ensta.tools.*;


public class Player {
    /* **
     * Attributs
     */
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    /* **
     * Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /* **
     * Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coodrinates.
     */
    public void putShips(Board board) {
        boolean done = false;
        int i = 0;

        do {
            AbstractShip s = ships[i];
            String msg = String.format("placer %d : %s(%d)", i + 1, s.getName(), s.getSize());
            System.out.println(msg);
            InputHelper.ShipInput res = InputHelper.readShipInput(board, s);
            switch (res.orientation){
                case "n" : 
                    s.setDirection(Direction.NORTH);
                    break;
                case "s" : 
                    s.setDirection(Direction.SOUTH);
                    break;
                case "e" : 
                    s.setDirection(Direction.EAST);
                    break;
                case "w" : 
                    s.setDirection(Direction.WEST);
                    break;
            }
            board.putShip(s, res.x, res.y);

            ++i;
            done = i == 5;

            board.print();
        } while (!done);
    }

    public Hit sendHit(int[] coords) {
        boolean done = false;
        Hit hit = null;

        do {
            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            // TODO call sendHit on this.opponentBoard
            hit = this.opponentBoard.sendHit(hitInput.x, hitInput.y);

            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            // return hit is obvious. But how to return coords at the same time ?
            coords[0] = hitInput.x;
            coords[1] = hitInput.x;
            if (hit == Hit.STIKE)
                done =true; 
        } while (!done);

        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}
