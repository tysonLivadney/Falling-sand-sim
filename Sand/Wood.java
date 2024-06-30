package Sand;

import java.awt.Color;
import java.util.Vector;

public class Wood extends Sand{
    public Wood(Color color, int x, int y){
        super(y, color, x, y, new Vector2D(0, 0));
        }

        @Override
        public void update(){
        }
}
