package utilz;

import main.Game;

public class HelpMethods {
    public static boolean CanMoveHere(float x, float y, float w, float h, int[][] lvlData) {
        if (!IsSolid(x, y, lvlData))
            if (!IsSolid(x + w, y + h, lvlData))
                if (!IsSolid(x + w, y, lvlData))
                    if (!IsSolid(x, y + h, lvlData)) {
                        return true;
                    }
        return false;
    }

    private static boolean IsSolid(float x, float y, int[][] lvlData) {
        if (x < 0 || x >= Game.GAME_WIDTH) {
            return true;
        }
        if (y < 0 || y >= Game.GAME_HEIGHT) {
            return true;
        }
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];
        if (value >= 64 || value < 0 || value != 18) {
            return true;
        }
        return false;
    }
}
