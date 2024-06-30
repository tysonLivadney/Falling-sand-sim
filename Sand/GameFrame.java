package Sand;

import javax.swing.*;
import java.awt.*;
class GameFrame extends JFrame{
    GamePanel gamePanel;

    public GameFrame(){
         // Create and add the game panel
         
         this.setVisible(true);
         this.setTitle("Game");
         setMinimumSize(new Dimension(1920, 1080));
         this.setSize(1920, 1080);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setLocationRelativeTo(null);
         setResizable(false);
         this.add(gamePanel = new GamePanel());
         this.pack();
    }
}