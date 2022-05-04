public class Egg {
    private int xPos;
    private int yPos;
    public static final int HEIGHT = 10;
    public static final int WIDTH = 10;

    public Egg(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return this.xPos;
    }

    public int getyPos() {
        return this.yPos;
    }

    public void takeFromY() {
        --this.yPos;
    }

    public void addTOy() {
        ++this.yPos;
    }
}