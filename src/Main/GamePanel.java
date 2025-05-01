package Main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private int width=1280, height=720;
    private float xDelta=100,yDelta=100;
    private float xDir=1f,yDir=1f;
    private Color color;
    private Random random;

    public GamePanel() {
        random = new Random();
        mouseInputs = new MouseInputs(this);
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void changeXDelta(int value) {
        this.xDelta += value;

    }
    public void changeYDelta(int value) {
        this.yDelta += value;

    }
    public void setRectPosition(int x, int y) {
        this.xDelta=x;
        this.yDelta=y;

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateRectangle();
        g.setColor(color);
        g.fillRect((int)xDelta, (int)yDelta, 200, 50);

    }
    private void updateRectangle() {
        xDelta+=xDir;
        if(xDelta>getWidth()||xDelta<0){
            xDir*=-1;
            color=getRandomColor();
        }
        yDelta+=yDir;
        if(yDelta>getHeight()||yDelta<0){
            yDir*=-1;
            color=getRandomColor();
        }
    }

    private Color getRandomColor() {
        int r=random.nextInt(255);
        int g=random.nextInt(255);
        int b=random.nextInt(255);
        return new Color(r,g,b);
    }
}
