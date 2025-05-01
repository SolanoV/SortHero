package Main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Utils.Constants.PlayerConstants.*;
import static Utils.Constants.Directions.*;


public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float xDelta=100,yDelta=100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed=24;
    private int playerAction=IDLE;
    private int playerDirection=-1;
    private boolean moving=false;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);

        importImg();
        loadAnimations();

        setPanelSize();
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    private void setPanelSize() {
        Dimension size=new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    private void loadAnimations() {
        animations=new BufferedImage[4][6];
        for(int j=0; j<animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 64, j*40, 64, 40);
            }
        }
    }
    private void importImg() {
        InputStream is=getClass().getResourceAsStream("/hero1_sprite.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void setDirection(int direction){
        this.playerDirection=direction;
        moving=true;
    }
    public void setMoving(boolean moving){
        this.moving=moving;
    }

    private void updateAnimationTick() {
          animationTick++;
        if(animationTick>=animationSpeed){
            animationTick=0;
            animationIndex++;
            if(animationIndex>=GetSpriteAmount(playerAction)){
                animationIndex=0;
            }
        }
    }

    private void setAnimation() {
        if(moving){
            playerAction=RUNNING;
        }
        else{
            playerAction=IDLE;
        }
    }

    private void updatePosition() {
        if(moving){
            switch(playerDirection){
                case UP:
                    yDelta-=3;
                    break;
                case LEFT:
                    xDelta-=3;
                    break;
                case RIGHT:
                    xDelta+=3;
                    break;
                case DOWN:
                    yDelta+=3;
                    break;
            }
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateAnimationTick();
        setAnimation();
        updatePosition();
        g.drawImage(animations[playerAction][animationIndex], (int)xDelta, (int)yDelta, 192,120,null);
    }


}
