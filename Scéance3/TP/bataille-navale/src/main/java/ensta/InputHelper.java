package ensta;
import java.util.Arrays;
import java.util.Scanner;
import ensta.board.*;
import ensta.ships.*;

public final class InputHelper {

    /* **
     * Constructeur
     */
    private InputHelper() {}

    /* **
     * Classe ShipInput, interne à InputHelper
     */
    public static class ShipInput {
        public String orientation;
        public int x;
        public int y;

        public void print(){
            System.out.println("direction = " + this.orientation + " -> ("+ x +","+ y + ")");
        }
    }

    /* **
     * Classe CoordInput, interne à InputHelper
     */
    public static class CoordInput {
        public int x;
        public int y;

        public void print(){
            System.out.println("("+ x +","+ y + ")");
        }
    }

    /**
     * @param board where to place the ship
     * @param ship to be placed
     */
    public static ShipInput readShipInput(Board board, AbstractShip ship) {
        @SuppressWarnings("resource")
        Scanner sin = new Scanner(System.in);
        ShipInput res = new ShipInput();
        String[] validOrientations = {"n", "s", "e", "w"}; // North, South, East, West
        boolean done = false;

        do {
            try {
                String[] in = sin.nextLine().toLowerCase().split(" ");
                if (in.length == 2) {
                    String coord = in[0];
                    if (Arrays.asList(validOrientations).contains(in[1])) {
                        res.orientation = in[1];
                        res.y = (int)(coord.charAt(0) - 'a'+1);
                        res.x = Integer.parseInt(coord.substring(1, coord.length()));
                        if (board.moveIsValid(res.x, res.y, ship, res.orientation))
                            done = true;
                    }
                }
            } catch (Exception e) {
                // nop
            }

            if (!done) {
                System.err.println("Format incorrect! Entrez la position sous forme 'A0 n'");
            }
        } while (!done && sin.hasNextLine());

        return res;
    }

    public static CoordInput readCoordInput() {
        @SuppressWarnings("resource")
        Scanner sin = new Scanner(System.in);
        CoordInput res = new CoordInput();
        boolean done = false;

        do {
            try {
                String coord = sin.nextLine().toLowerCase();
                res.x = coord.charAt(0) - 'a';
                res.y = Integer.parseInt(coord.substring(1, coord.length())) - 1;
                done = true;
            } catch (Exception e) {
                // nop
            }

            if (!done) {
                System.err.println("Format incorrect! Entrez la position sous forme 'A0'");
            }
        } while (!done && sin.hasNextLine());

        return res;
    }
}
