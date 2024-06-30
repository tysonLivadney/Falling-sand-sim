package Sand;

import java.awt.Point;
import java.util.List;

public class Main {
    private boolean running;
    public int keyPressed;
    List<Sand> pixels;
    GameFrame frame;
    GamePanel panel;
    public Main(){
        running = true;
        frame = new GameFrame();
        panel = frame.gamePanel;
    }
    public void start(){
        while(running){
            update();
        }
    }
    public void stop(){
        running = false;
    }
    public void update(){
        if(!running) return;
        panel.updateGame();
        panel.repaint();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Grid.nearestCell = new Point(Input.mouseX / Grid.cellSize, Input.mouseY / Grid.cellSize);
        Sand.frameCount++;
        if(Input.mouseDown)
            Brush.createPixels((int)Grid.nearestCell.getX(), (int)Grid.nearestCell.getY());
        Brush.update();
        }
        
    public static void main(String[] args){
        Main game = new Main();
        game.start();
    }    
}
