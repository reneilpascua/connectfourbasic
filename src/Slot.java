import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Slot extends javafx.scene.shape.Circle {
    
    int content = 0;
    
    public Slot(double r, Color c) {
        super(r, c);
        setOnMouseClicked(this::click);
    }
    
    public void click(MouseEvent m) {
        
    }
}
