package Sand;

import java.awt.event.*;

public class Input extends KeyAdapter implements MouseListener, MouseMotionListener {
    static int mouseX;
    static int mouseY;
    static boolean mouseDown = false;

    public Input(GamePanel gamePanel) {
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_1){
            Brush.changeBrushType(PixelType.SAND);
        }
        if(e.getKeyCode() == KeyEvent.VK_2){
            Brush.changeBrushType(PixelType.WATER);
        }
        if(e.getKeyCode() == KeyEvent.VK_3){
            Brush.changeBrushType(PixelType.WOOD);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseDown = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       mouseDown = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        mouseX = e.getX();
        mouseY = e.getY();
        //System.out.println(Grid.grid[(int)Grid.nearestCell.getX()][(int)Grid.nearestCell.getY()]);
    }
}
