package ensta.ships;

public enum Direction {
    NORTH(0),
    SOUTH(1),
    EAST(2),
    WEST(3);

    private int value;

    Direction(int val){
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