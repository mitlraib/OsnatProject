public class Enemy {
    private int health;
    private int xPos;
    private int yPos;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    public static final int SPEED = 30;


    public Enemy(int health, int xPos, int yPos) {
        this.health = health;
        this.xPos = xPos;
        this.yPos = yPos;


    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getHEIGHT() {
        return 50;
    }

    public int getWIDTH() {
        return 50;
    }

    public int getxPos() {
        return this.xPos;
    }

    public void addxPos(int direction) {
        this.xPos += 30 * direction;
    }

    public void addyPos(int direction) {
        this.yPos += 30 * direction;
    }

    public int getyPos() {
        return this.yPos;
    }

    public int getHealth() {
        return this.health;
    }
    public  void moveRigth(){
        this.xPos++;
    }
    public  void moveLeft(){
        this.xPos--;
    }
    public void takeDamage() {
        --this.health;
        System.out.println("took a hit");
    }
}