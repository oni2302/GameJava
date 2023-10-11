package main;

import javax.swing.JPanel;

import inputs.KeyboardInputs;

import java.awt.Dimension;
import java.awt.Graphics;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private KeyboardInputs keyboardInputs;
    private Game game;
    public GamePanel(Game game) {
        this.game = game;
        mouseInputs = new MouseInputs(this);
        keyboardInputs = new KeyboardInputs(this);
        setPanelSize();
        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    //Hàm cập nhật game (xử lí tính toán)
    public void updateGame(){
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }
    public Game getGame(){
        return game;
    }
    public void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

}
