package dungeon;

/* @author marbi */
public class Player {

    private int x;
    private int y;

    public Player() {
        this.x = 0;
        this.y = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "@" + " " + this.x + " " + this.y;
    }
}
