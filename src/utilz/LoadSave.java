package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

public class LoadSave {
    public static final String PLAYER_ATLAS = "res/entities/player.png";
    public static final String PLAYER_RED_HOOD = "res/entities/red_hood_player.png";
    public static final String LEVEL_ATLAS = "res/tiles_set/tiles.png";
    public static final String LEVEL_ONE_DATA = "res/levels/level_one.png";
    public static BufferedImage GetSpriteAtlas(String filePath) {
        BufferedImage img = null;
        File file = new File(filePath);

        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
    public static int[][] GetLevelData(){
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);

        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int value = color.getRed();
                if(value>=255){
                    value = 0;
                }
                lvlData[i][j] = value;
            }
        }
        return lvlData;
    }
}
