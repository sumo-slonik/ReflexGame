package Game.functionality;

import javafx.scene.paint.Color;

import java.util.Random;

public class RandomColorGiver {
    public static Color GiveColor(int color) {
        return switch (color) {
            case 0 -> Color.RED;
            case 1 -> Color.BLUE;
            case 2 -> Color.GREEN;
            case 3 -> Color.ORANGE;
            case 4 -> Color.INDIANRED;
            case 5 -> Color.BURLYWOOD;
            case 6 -> Color.ROSYBROWN;
            case 7 -> Color.PINK;
            case 8 -> Color.YELLOW;
            case 9 -> Color.BLACK;
            case 10 -> Color.GREY;
            default -> throw new IllegalStateException("Unexpected value: " + color);
        };
    }
}
