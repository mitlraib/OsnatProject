import java.awt.*;

import java.util.ArrayList;

import javax.swing.*;


public class GamePanel extends JPanel {

    private Player player;
    private Enemy[] enemies;
    private ArrayList<Egg> eggs;
    private ImageIcon spaceship;
    private ImageIcon chicken;
    private ImageIcon space;
    private JLabel text;
    private boolean flag;
    private final static int ENEMY_AMOUNT = 5;
    private final static int ENEMY_HRT = 3;
    private final static int ENEMY_SPACING = 20;
    public static final int MAX_OF_LEFT = 0;
    public static final int MAX_OF_RIGHT = 1000;
    public static final int WIND_HEIGHT = 800;
    public static final int WIND_WIDTH = 1000;
    public GamePanel(Player player) {

        this.player = player;
        this.initializeEnemies();
        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(Color.white);
        gamePanel.setBounds(0, 0, 300, 420);
        this.eggs = new ArrayList<>();

        this.spaceship = new ImageIcon("spaceship.jpeg");
        this.chicken = new ImageIcon("R.jpeg");
        moveEnemy();
    }

    public void moveEnemy() {
        new Thread(() -> {
            while (true) {
                if (enemies != null) {
                    move();
                }

                repaint();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public int checkCollision(Enemy[] enemies, Egg egg) {
        int enemyNumber = -1;
        for (int i = 0; i < enemies.length; i++) {
            if ((enemies[i] != null) && (egg.getxPos() > enemies[i].getxPos() && egg.getxPos() < enemies[i].getxPos() + Enemy.WIDTH) && (egg.getyPos() > enemies[i].getyPos() && egg.getyPos() < enemies[i].getyPos() + Enemy.WIDTH)) {
                enemyNumber = i;
                System.out.println("collision!");
                break;
            }
        }
        return enemyNumber;
    }


    private void checkPlayerCollision() {
        for (Enemy enemy : enemies) {
            if (enemy != null) {
                boolean checkX = (player.getxPos() >= enemy.getxPos() && player.getxPos() <= enemy.getxPos() + Enemy.WIDTH) || (player.getxPos() + Player.WIDTH >= enemy.getxPos() && player.getxPos() + Player.WIDTH <= enemy.getxPos() + Enemy.WIDTH);
                boolean checkY = (player.getyPos() >= enemy.getyPos() && player.getyPos() <= enemy.getyPos() + Enemy.WIDTH) || (player.getyPos() + Player.WIDTH >= enemy.getyPos() && player.getyPos() + Player.WIDTH <= enemy.getyPos() + Enemy.WIDTH);
                if (checkX && checkY) {
                    this.gameOver();

                }
            }
        }
    }

    private void checkWin() {
        for (Enemy enemy : enemies) {
            if (enemy != null) {
                return;
            }
        }
        this.gameOver();
    }


    private void gameOver() {

        for (int i = 0; i < this.enemies.length; i++) {
            this.enemies[i] = null;
        }
        initializeEnemies();
        this.player.setxPos(Player.startX_Pos);
        this.player.setyPos(Player.startY_Pos);

        if(this.player.getPoints() > Player.maxPoints) {
            Player.maxPoints = this.player.getPoints();
        }
    }
    public void over(){
        JLabel title = new JLabel("You Win!");
        title.setBounds(150, 50, WIND_WIDTH/3, WIND_HEIGHT/2);
        Font titleButtonFont = new Font("Arial", Font.ITALIC, 50);
        title.setFont(titleButtonFont);
        this.add(title);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(300, 150, WIND_WIDTH/3, WIND_HEIGHT/2);
        exitButton.addActionListener((event) ->
        {
            System.exit(0);
        });
        this.add(exitButton);
        JButton nextLevelButton = new JButton("Next");
        nextLevelButton.setBounds(50,150,WIND_WIDTH/3, WIND_HEIGHT/2);
        nextLevelButton.addActionListener((event) ->
        {
            this.remove(title);
            nextLevelButton.setVisible(false);
            exitButton.setVisible(false);
        });
        this.add(nextLevelButton);
    }



    public void addToList(Egg egg) {
        eggs.add(egg);
    }

    private void initializeEnemies() {
        Enemy[] enemies = new Enemy[ENEMY_AMOUNT];
        int startX_Pos = 0;
        int startY_Pos = 0;
        for (int i = 0; i < enemies.length; i++) {
            if (startX_Pos + Enemy.WIDTH > Main.WIND_WIDTH) {
                startY_Pos += Enemy.HEIGHT + ENEMY_SPACING;
                startX_Pos = 0;
            }
            enemies[i] = new Enemy(ENEMY_HRT, startX_Pos, startY_Pos);
            startX_Pos += 80;
            this.enemies = enemies;
        }
    }
    public void move(){
        if (!flag ){
            for (Enemy enemy : enemies){
                enemy.moveRigth();
            }
        }
        if (enemies[enemies.length -1].getxPos() == MAX_OF_RIGHT){
            flag = true;
        }
        if (flag){
            for (Enemy enemy : enemies){
                enemy.moveLeft();
            }

        }
        if (enemies[0].getxPos() == MAX_OF_LEFT){
            flag = false;
        }
    }

    protected void paintComponent(Graphics g) {
        checkPlayerCollision();
        checkWin();
        super.paintComponent(g);
        this.space = new ImageIcon("M.jpeg");
        this.space.paintIcon(this , g , 0 , -50);

        this.spaceship.paintIcon(this, g , player.getxPos() , player.getyPos());
        for (Enemy enemy : enemies) {
            if(enemy != null){
                this.chicken.paintIcon(this, g , enemy.getxPos() , enemy.getyPos());
            }
        }
        for (int i = 0; i < this.eggs.size(); i++) {
            if(this.eggs.get(i) != null) {
                g.setColor(Color.black);
                g.fillRect(this.eggs.get(i).getxPos(), this.eggs.get(i).getyPos(), Egg.WIDTH, Egg.HEIGHT);
                int enemyIndex = this.checkCollision(this.enemies, this.eggs.get(i));
                if(enemyIndex != -1) {
                    this.enemies[enemyIndex].takeDamage();
                    this.eggs.set(i, null);
                    if(this.enemies[enemyIndex].getHealth() <= 0) {
                        this.enemies[enemyIndex] = null;
                        this.player.addPoints();
                    }
                }
            }

        }



    }

    public JLabel getText() {
        return text;
    }

    public void setText(JLabel text) {
        this.text = text;
    }

}