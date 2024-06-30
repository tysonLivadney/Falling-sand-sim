package Sand;

public class Vector2D{
    int x, y;

    public Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void set(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
    }
    public void subtract(Vector2D other) {
        this.x -= other.x;
        this.y -= other.y;
    }
    public void multiply(float scalar){
        this.x*=scalar;
        this.y*=scalar;
    }

}
