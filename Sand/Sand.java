package Sand;

import java.awt.Color;
import java.awt.Graphics;

public class Sand {
    Vector2D position;
    Vector2D velocity;
    int gravity;
    private int size;
    private Color color;
    static int frameCount = 0;
    int prevFrame;
    int gridHeight = Grid.grid[0].length;
    int gridWidth = Grid.grid.length;

    public Sand(int gravity, Color color, int x, int y, Vector2D vel) {
        this.velocity = vel;
        this.position = new Vector2D(x, y);
        this.gravity = gravity;
        this.size = Grid.cellSize;
        this.color = color;
        prevFrame = -1;
    }

    public void update() {
        if (prevFrame == frameCount) return; // Ensure it updates only once per frame
        prevFrame = frameCount;

        // Apply gravity
        if (position.y + velocity.y + gravity < gridHeight && Grid.grid[position.x][position.y + velocity.y + gravity] == null) {
            velocity.setY(velocity.getY() + gravity); // Accelerate downwards
        } 
        else {
            velocity.setY(0); // Stop vertical movement

            if (position.y + 1 < gridHeight && Grid.grid[position.x][position.y + 1] instanceof Water) {
                sink(position.x, position.y);
            }

            boolean moveRightFirst = Math.random() > 0.5;
            if (moveRightFirst) {
                if (position.x + 1 < gridWidth && position.y + 1 < gridHeight && Grid.grid[position.x + 1][position.y + 1] == null) {
                    velocity.set(1, 1);
                } else if (position.x - 1 >= 0 && position.y + 1 < gridHeight && Grid.grid[position.x - 1][position.y + 1] == null) {
                    velocity.set(-1, 1);
                } else {
                    velocity.setX(0);
                }
            } else {
                if (position.x - 1 >= 0 && position.y + 1 < gridHeight && Grid.grid[position.x - 1][position.y + 1] == null) {
                    velocity.set(-1, 1);
                } else if (position.x + 1 < gridWidth && position.y + 1 < gridHeight && Grid.grid[position.x + 1][position.y + 1] == null) {
                    velocity.set(1, 1);
                } else {
                    velocity.setX(0);
                }
            }
        }
        

        // Calculate new position based on velocity
        Vector2D newPos = toMove(velocity.getX(), velocity.getY());
        int newX = position.getX() + newPos.getX();
        int newY = position.getY() + newPos.getY();
        // Ensure new position is within bounds
        if (Grid.grid[newX][newY] == null) {      
            movePixel(position.x, position.y, newX, newY);
        } 
        else if (position.y + 1 < gridHeight && Grid.grid[position.x][position.y + 1] == null)
            movePixel(position.x, position.y, position.x, position.y + 1);
        
        
    }

    public void sink(int x, int y){
        Sand temp = Grid.grid[x][y];
        Grid.grid[x][y] = Grid.grid[x][y + 1];
        Grid.grid[x][y + 1] = temp;

        // Update the position of the sand particle
        this.position.set(x, y + 1);
        ((Water) Grid.grid[x][y]).position.set(x, y);
    }

    public void movePixel(int oldX, int oldY, int newX, int newY) {
        Grid.grid[oldX][oldY] = null;
        Grid.grid[newX][newY] = this;
        this.position.setX(newX);
        this.position.setY(newY);
    }

    public Vector2D toMove(int velX, int velY) {
        int movesX = 0;
        int movesY = 0;

        if (velX > 0) {
            for (int i = 1; i <= velX; i++) {
                if (position.getX() + i >= gridWidth || Grid.grid[position.getX() + i][position.getY()] != null) {
                    break;
                }
                movesX++;
            }
        } else {
            for (int i = -1; i >= velX; i--) {
                if (position.getX() + i < 0 || Grid.grid[position.getX() + i][position.getY()] != null) {
                    break;
                }
                movesX--;
            }
        }

        if (velY > 0) {
            for (int i = 1; i <= velY; i++) {
                if (position.getY() + i >= gridHeight || Grid.grid[position.getX()][position.getY() + i] != null) {
                    break;
                }
                movesY++;
            }
        } else {
            for (int i = -1; i >= velY; i--) {
                if (position.getY() + i < 0 || Grid.grid[position.getX()][position.getY() + i] != null) {
                    break;
                }
                movesY--;
            }
        }

        return new Vector2D(movesX, movesY);
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(this.position.getX() * Grid.cellSize, this.position.getY() * Grid.cellSize, size, size);
    }
}
