/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilz;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author ONI
 */
public class FontHelper {
    // Method to load the custom font
    public static Font PixelFont(float size) {
        String fontFilePath = "src/res/font/dotty.ttf";
        Font customFont = null;
        try {
            // Load the font file
            File fontFile = new File(fontFilePath);
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont); // Register the font with the graphics environment
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // Handle font loading exception
        }
        return customFont;
    }
}
