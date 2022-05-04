import java.awt.event.*;

import javax.swing.*;

public class Main extends JFrame implements KeyListener{

    private static final String TITLE = "Chicken Invaders";
    public static final int WIND_HEIGHT = 800;
    public static final int WIND_WIDTH = 1000;
    public static final char SHOOT = 'g';
    private Player player;
    private Enemy[] enemies;
    private GamePanel gPanel;
    public Main() {
        this.setTitle(TITLE);
        this.setResizable(false);
        this.setSize(WIND_WIDTH, WIND_HEIGHT);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);


        this.player = new Player(10, Player.startX_Pos, Player.startY_Pos);

        this.gPanel = new GamePanel(player);

        this.add(gPanel);

        while (true) {
            this.enemies = gPanel.getEnemies();
            this.gPanel.revalidate();
            this.gPanel.repaint();

        }

    }


    public static void main(String[] args) {
        new Main();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch(keyCode) {
            case KeyEvent.VK_RIGHT:
                if(checkOutOfBoundsForX(-1, 1)) {
                    this.player.addxPos(1);
                    for(Enemy enemy : enemies) {
                        if(enemy != null){
                            enemy.addxPos(-1);
                            enemy.addyPos(1);
                        }
                    }
                }

                break;
            case  KeyEvent.VK_LEFT:
                if(checkOutOfBoundsForX(1 , -1)){
                    this.player.addxPos(-1);
                    for(Enemy enemy : enemies) {
                        if(enemy != null){
                            enemy.addxPos(1);
                            enemy.addyPos(1);
                        }
                    }
                }
                break;
            case KeyEvent.VK_UP:
                if(checkOutOfBoundsForY(1, -1)){
                    this.player.addyPos(-1);
                    for(Enemy enemy : enemies) {
                        if(enemy != null){
                            enemy.addyPos(1);
                        }
                    }
                }
                break;
            case KeyEvent.VK_DOWN:
                if(checkOutOfBoundsForY(-1, 1)){
                    this.player.addyPos(1);
                    for(Enemy enemy : enemies) {
                        if(enemy != null){
                            enemy.addyPos(-1);
                        }
                    }
                }
                break;

        }

    }


    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyChar()) {
            case SHOOT :
                Egg egg = new Egg(this.player.getxPos() + Player.WIDTH / 2, this.player.getyPos());
                this.player.shoot(egg);
                this.gPanel.addToList(egg);
                break;

        }

    }

    private boolean checkOutOfBoundsForX(int directionEnemy, int directionPlayer) {
        if(player.getxPos() + Player.SPEED * directionPlayer > WIND_WIDTH || player.getxPos() + Player.SPEED * directionPlayer < 0) {
            return false;
        }
        for (Enemy enemy : enemies) {
            if(enemy != null){
                if(enemy.getxPos() + Enemy.SPEED * directionEnemy > WIND_WIDTH || enemy.getxPos() + Enemy.SPEED * directionEnemy < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkOutOfBoundsForY(int directionEnemy, int directionPlayer) {
        if(player.getyPos() + Player.SPEED * directionPlayer > WIND_HEIGHT || player.getyPos() + Player.SPEED * directionPlayer < 0) {
            return false;
        }
        for (Enemy enemy : enemies) {
            if(enemy != null){
                if(enemy.getyPos() + Enemy.SPEED * directionEnemy > WIND_HEIGHT || enemy.getyPos() + Enemy.SPEED * directionEnemy < 0) {
                    return false;
                }
            }
        }
        return true;
    }
}