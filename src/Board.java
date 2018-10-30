import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Board extends Application {

    GridPane gameboard = new GridPane();
    private int[][] arr;
    List<Circle> circles = new ArrayList<Circle>();
    Scene scene;
    final private int slotW = 50;
    final private int numRows = 6;
    final private int numCols = 7;
    final private int buffer = 5;
    
    
    public void start(Stage stg) throws Exception {
        arr = new int[numRows][numCols];
        
        scene = new Scene(gameboard, numCols*slotW + (numCols+1)*buffer, 
                numRows*slotW + (numRows+1)*buffer);
        
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Circle slot = new Circle(slotW/2.0, Color.WHITE);
                // the index of this specific circle is i*6 + j
                circles.add(slot);
                gameboard.add(slot, col,row,1,1);
            }
        }
        
        scene.setOnMouseClicked(this::click);
        
        gameboard.setAlignment(Pos.CENTER);
        gameboard.setHgap(buffer);
        gameboard.setVgap(buffer);
        scene.setFill(Color.BLUE);
        stg.setScene(scene);
        stg.show();
    }
    
    public void click(MouseEvent m) {
        circles.get(5).setFill(Color.RED);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
