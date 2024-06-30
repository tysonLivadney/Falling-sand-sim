package Sand;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Brush {
    static int radius = 30; // pixels radius
    static float density = .01f;
    static int pixelsToMake = (int) (Math.PI * radius * radius * density);
    Sand toPaint;
    static PixelType currentBrush = PixelType.SAND;
    static Vector2D pos = new Vector2D(Input.mouseX, Input.mouseY);
    static Vector2D prevPos = new Vector2D(Input.mouseX, Input.mouseY);
    static Vector2D vel = new Vector2D(0, 0);

    public static void update() {
        pos.set(Input.mouseX, Input.mouseY);
        vel.set(pos.x - prevPos.x, pos.y - prevPos.y);
        if (Input.mouseDown) {
            createPixels((int) Grid.nearestCell.getX(), (int) Grid.nearestCell.getY());
        }
        prevPos.set(Input.mouseX, Input.mouseY);
    }

    public static void createPixels(int x, int y) {
        Random random = new Random();
        if (currentBrush == PixelType.WOOD) {
            for (int i = x - radius; i < x + radius; i++) {
                for (int j = y - radius; j < y + radius; j++) {
                    if (!isWithinBounds(i, j)) continue;
                    if ((i - x) * (i - x) + (j - y) * (j - y) <= radius * radius && Grid.grid[i][j] == null)
                        Grid.add(new Wood(Color.GRAY, i, j), i, j);
                }
            }
        } else {
            for (int i = 0; i < pixelsToMake; i++) {
                // Calculate random offsets within the circular area
                int dx = random.nextInt(radius * 2 + 1) - radius;
                int dy = random.nextInt(radius * 2 + 1) - radius;

                // Check if the generated position is within the circle's radius
                if (dx * dx + dy * dy <= radius * radius) {
                    int newX = x + dx;
                    int newY = y + dy;

                    if (!isWithinBounds(newX, newY))
                        continue;
                    // Ensure the generated pixel is within bounds and not occupied
                    if (Grid.grid[newX][newY] == null) {
                        switch (currentBrush) {
                            case SAND:
                                Grid.add(new Sand(8, Color.yellow, newX, newY, vel), newX, newY);
                                break;
                            case WATER:
                                Grid.add(new Water(8, Color.BLUE, newX, newY, vel), newX, newY);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }

    public static void changeBrushType(PixelType newType) {
        currentBrush = newType;
    }

    private static boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < Grid.grid.length && y >= 0 && y < Grid.grid[0].length;
    }

    public static void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
        g2d.setColor(Color.BLACK);
        g2d.fillOval(Input.mouseX - radius * Grid.cellSize, Input.mouseY - radius * Grid.cellSize, radius * Grid.cellSize * 2, radius * Grid.cellSize * 2);
    }
}
