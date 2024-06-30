package Sand;

import java.awt.Color;

public class Water extends Sand {
    int cellsSideways = 5;

    public Water(int gravity, Color color, int x, int y, Vector2D velocity) {
        super(gravity, color, x, y, velocity);
    }

    @Override
    public void update() {
        if (prevFrame == frameCount) return; // Ensure it updates only once per frame
        prevFrame = frameCount;

        // Try to fall down
        if (position.y + gravity < gridHeight && Grid.grid[position.x][position.y + gravity] == null) {
            movePixel(position.x, position.y, position.x, position.y + gravity);
        } else {
            // Try to move diagonally
            boolean moveRightFirst = Math.random() > 0.5;
            if (moveRightFirst) {
                if (position.x + 1 < gridWidth && position.y + 1 < gridHeight && Grid.grid[position.x + 1][position.y + 1] == null) {
                    movePixel(position.x, position.y, position.x + 1, position.y + 1);
                } else if (position.x - 1 >= 0 && position.y + 1 < gridHeight && Grid.grid[position.x - 1][position.y + 1] == null) {
                    movePixel(position.x, position.y, position.x - 1, position.y + 1);
                } else {
                    moveSideways();
                }
            } else {
                if (position.x - 1 >= 0 && position.y + 1 < gridHeight && Grid.grid[position.x - 1][position.y + 1] == null) {
                    movePixel(position.x, position.y, position.x - 1, position.y + 1);
                } else if (position.x + 1 < gridWidth && position.y + 1 < gridHeight && Grid.grid[position.x + 1][position.y + 1] == null) {
                    movePixel(position.x, position.y, position.x + 1, position.y + 1);
                } else {
                    moveSideways();
                }
            }
        }
    }

    private void moveSideways() {
        for (int i = 1; i <= cellsSideways; i++) {
            // Try to move right
            if (position.x + i < gridWidth && Grid.grid[position.x + i][position.y] == null) {
                movePixel(position.x, position.y, position.x + i, position.y);
                return;
            }
            // Try to move left
            if (position.x - i >= 0 && Grid.grid[position.x - i][position.y] == null) {
                movePixel(position.x, position.y, position.x - i, position.y);
                return;
            }
        }
    }

    @Override
    public void movePixel(int oldX, int oldY, int newX, int newY) {
        Grid.grid[oldX][oldY] = null;
        Grid.grid[newX][newY] = this;
        this.position.setX(newX);
        this.position.setY(newY);
    }
}
