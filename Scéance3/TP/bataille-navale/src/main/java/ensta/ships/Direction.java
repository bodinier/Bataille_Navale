package ensta.ships;

public enum Direction {
    NORTH(0, "n"),
    SOUTH(1, "s"),
    EAST(2, "e"),
    WEST(3, "w");

    private int value;

    Direction(int val){
        this.value = val;
    }
    Direction(int val, String input){
        this.value = val;
    }

    public static Direction setDirection(int value){
        for (Direction dir : Direction.values()) {
            if (dir.value == value) {
                return dir;
            }
        }
        return Direction.SOUTH;
    }
}