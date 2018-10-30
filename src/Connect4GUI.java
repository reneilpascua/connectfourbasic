import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Connect4GUI extends Application {

    boolean gameover = false;
    GameState gs = new GameState(6,7);
    GridPane board = new GridPane();
    Scene scene;
    Scene winscene;
    final private int cellSz = 50;
    final int buffer = 5;
    int turncounter = 0;
    List<Circle> slots = new ArrayList<Circle>();
    
    
    
    public void start(Stage stg) throws Exception {
        
        scene = new Scene(board, cellSz*7 + buffer*8, cellSz*6 + buffer*7);
        
        for (int r=0; r<6; r++) {
            for (int c=0; c<7; c++) {
                Circle circ = new Circle(cellSz/2, Color.WHITE);
                slots.add(circ);
                board.add(circ,c,r,1,1);
            }
        }
        
        board.setAlignment(Pos.CENTER);
        board.setHgap(buffer);
        board.setVgap(buffer);
        scene.setFill(Color.BLUE);
        scene.setOnMouseClicked(this::dropPiece);
        stg.setScene(scene);
        stg.show();
    }
    
    public void dropPiece(MouseEvent m) {
        if (!gameover) {
            int x = (int) m.getX();
            /*
             * double y = m.getY();
             * System.out.printf("x: %.2f\ty:%.2f\n",x,y);
             */
            int selColumn = x/(cellSz+buffer);
            turncounter++;
            int player = (turncounter%2 !=0)?1:2;
            // record move on the abstract board
            int dropPlace = gs.dropPiece(selColumn, player);
            
            slots.get(7*dropPlace + selColumn).setFill((player==1)?Color.RED:Color.YELLOW);
            
            //check if win
            if (gs.checkWin(player)) {
                Label winner = new Label("Player "+player+" has won!");
                board.add(winner,2,3,3,1);
                winner.setAlignment(Pos.CENTER);
                gameover = true;
            }
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
}
