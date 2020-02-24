package ensta.player;

import java.io.Serializable;
import java.util.*;

import ensta.Hit;
import ensta.board.*;
import ensta.ships.*;
import ensta.tools.*;

public class BattleShipsAI implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int size;
    private final IBoard board;
    private final IBoard opponent;

    
    //Coords of last known strike. Would be a good idea to target next hits around
    //this point.
    private int lastStrike[];
    //If last known strike lead me to think the underlying ship has vertical
    //placement.
    private Boolean lastVertical;

    /**
     *
     * @param myBoard       board where ships will be put.
     * @param opponentBoard Opponent's board, where hits will be sent.
     */
    public BattleShipsAI(IBoard myBoard, IBoard opponentBoard) {
        this.board = myBoard;
        this.opponent = opponentBoard;
        this.size = board.getSize();
        try {
            assert(board.getSize() == opponentBoard.getSize());
        }
        catch (Exception e){
            System.out.println("my board and my opponent's board aren't the same size");
        }
    }

    /**
     * Put the ships on owned board
     * @param ships the ships to put
     */
    public void putShips(AbstractShip ships[]) {
        int boardSize = this.size;
        int x = 1, y = 1;
        Direction o;
        Random rnd = new Random();
        Direction[] orientations = Direction.values();
        boolean valid = false;
        for (AbstractShip s : ships) {
            do {
                valid = false;
                x = rnd.nextInt(boardSize-1)+1;
                y = rnd.nextInt(boardSize-1)+1;
                o = orientations[rnd.nextInt(4)];
                s.setDirection(o);
                System.out.println("x = " + x + " y = " + y);
                if (this.board.canPutShip(x, y, s)){
                    System.out.println(this.board.canPutShip(x, y, s));
                    valid = true;
                }
            } while (!valid);
            s.toString();
            board.putShip(s, x, y);
            board.print();
        }
    }

    /**
     *
     * @param coords array must be of size 2. Will hold the coord of the send hit.
     * @return the status of the hit.
     */
    public Hit sendHit(int[] coords) {
        int res[] = null;
        if (coords == null || coords.length < 2) {
            throw new IllegalArgumentException("must provide an initialized array of size 2");
        }

        // already found strike & orientation?
        if (lastVertical != null) {
            if (lastVertical) {
                res = pickVCoord();
            } else {
                res = pickHCoord();
            }

            if (res == null) {
                // no suitable coord found... forget last strike.
                lastStrike = null;
                lastVertical = null;
            }
        } else if (lastStrike != null) {
            // if already found a strike, without orientation
            // try to guess orientation
            res = pickVCoord();
            if (res == null) {
                res = pickHCoord();
            }
            if (res == null) {
                // no suitable coord found... forget last strike.
                lastStrike = null;
            }
        }

        if (lastStrike == null) {
            res = pickRandomCoord();
        }

        Hit hit = opponent.sendHit(res[0]+1, res[1]+1);
        board.setHit(hit != Hit.MISS, res[0]+1, res[1]+1);

        if (hit != Hit.MISS) {
            if (lastStrike != null) {
                lastVertical = guessOrientation(lastStrike, res);
            }
            lastStrike = res;
        }

        coords[0] = res[0];
        coords[1] = res[1];
        return hit;
    }

    /*
     * *** Méthodes privées
     */


    private boolean guessOrientation(int[] c1, int[] c2) {
        return c1[0] == c2[0];
    }

    private boolean isUndiscovered(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size && board.getHit(x, y) == null;
    }

    private int[] pickRandomCoord() {
        Random rnd = new Random();
        int x;
        int y;

        do {
            x = rnd.nextInt(size);
            y = rnd.nextInt(size);
        } while (!isUndiscovered(x, y));

        return new int[] { x, y };
    }

    /**
     * pick a coord verically around last known strike
     * 
     * @return suitable coord, or null if none is suitable
     */
    private int[] pickVCoord() {
        int x = lastStrike[0];
        int y = lastStrike[1];

        for (int iy : new int[] { y - 1, y + 1 }) {
            if (isUndiscovered(x, iy)) {
                return new int[] { x, iy };
            }
        }
        return null;
    }

    /**
     * pick a coord horizontally around last known strike
     * 
     * @return suitable coord, or null if none is suitable
     */
    private int[] pickHCoord() {
        int x = lastStrike[0];
        int y = lastStrike[1];

        for (int ix : new int[] { x - 1, x + 1 }) {
            if (isUndiscovered(ix, y)) {
                return new int[] { ix, y };
            }
        }
        return null;
    }
}