public class Player {
    private int xPos;
    private int yPos;
    private int cooldown;
    public static final int WIDTH = 100;
    public static final int SPEED = 15;
    public static final int startX_Pos = 400;
    public static final int startY_Pos = 600;
    public static int maxPoints = 0;
    private int points;

    public Player(int health, int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.points = 0;
    }


    public int getCooldown() {
        return this.cooldown;
    }

    public void shoot(Egg egg) {
        (new Thread(() -> {
            while(egg != null) {
                try {
                    Thread.sleep(5L);
                    egg.takeFromY();
                } catch (InterruptedException var2) {
                    var2.printStackTrace();
                }
            }

        })).start();
    }

    public int getxPos() {
        return this.xPos;
    }

    public int getyPos() {
        return this.yPos;
    }

    public int getHEIGHT() {
        return 100;
    }

    public int getWIDTH() {
        return 100;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void addxPos(int direction) {
        this.xPos += 15 * direction;
    }

    public void addyPos(int direction) {
        this.yPos += 15 * direction;
    }

    public void addPoints() {
        ++this.points;
    }

    public int getPoints() {
        return this.points;
    }
}