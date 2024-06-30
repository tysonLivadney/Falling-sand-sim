//grid not displayed, display works based on cell positions


package Sand;

import java.awt.Point;

public class Grid {
    Thread thread = new Thread();
    static Sand[][] grid;
    int width;
    int height;
    static int count;
    static Point nearestCell = new Point(0, 0);
    static int cellSize = 3; //pixels
    public Grid(GamePanel panel){
        width = panel.getWidth();
        height = panel.getHeight();
        grid = new Sand[width/cellSize][height/cellSize];
    }
    public static void add(Sand p, int x, int y){
        p.position.setX(x);
        p.position.setY(y);
        grid[x][y] = p;
    }

}
