package Sand;

import java.awt.Color;

public class Water extends Sand{
    int cellsSideways;
    public Water(int gravity, Color color, int x, int y, Vector2D velocity) {
        super(gravity, color, x, y, velocity);
        //this.cellsSideways = cellsSideways;
    }
    @Override
    public void update() {
        if (prevFrame == frameCount) return; // Ensure it updates only once per frame
        prevFrame = frameCount;

        // Apply gravity
        if (position.y + velocity.y + gravity < gridHeight && Grid.grid[position.x][position.y + velocity.y + gravity] == null) {
            velocity.setY(velocity.getY() + gravity); // Accelerate downwards
        } 
        else {
            velocity.setY(0); // Stop vertical movement
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
}
