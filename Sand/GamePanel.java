package Sand;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GamePanel extends JPanel{
    int width;
    int height;
    Grid grid;

    public GamePanel() {
        Input input = new Input(this);
        this.addKeyListener(input);
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                System.out.println("GamePanel gained focus");
            }

            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("GamePanel lost focus");
            }
        });
    }
    @Override
    public void addNotify() {
        super.addNotify();
        SwingUtilities.invokeLater(() -> {
            grid = new Grid(this);
        });
        this.requestFocusInWindow();
    }
    public void updateGame(){
        //update pixels if needed
        //pixels.removeIf(pixel -> pixel.isOffScreen());
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        List<Sand> list = new ArrayList<Sand>(1000);
        if(Grid.grid!=null){
            for (int j = Grid.grid[0].length - 1; j > 0; j--) {
                for (int i = 0; i < Grid.grid.length; i++) {
                    if(Grid.grid[i][j] != null){
                        list.add(Grid.grid[i][j]);
                    }
                }
            }
            Collections.shuffle(list);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).update();
                list.get(i).draw(g);
            }
        }
        Brush.draw(g);
    }
}

