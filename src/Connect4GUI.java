import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
    
    Group root;
    Group info;
    Text whosturn;
    
    public void start(Stage stg) throws Exception {
        
        root = new Group(board);
        scene = new Scene(root, cellSz*7 + buffer*8, cellSz*7 + buffer*7);
        
        // populate gridpane
        for (int r=0; r<6; r++) {
            for (int c=0; c<7; c++) {
                Circle circ = new Circle(cellSz/2, Color.WHITE);
                slots.add(circ);
                board.add(circ,c,r,1,1);
            }
        }
        
        //infobox
        Rectangle infobox = new Rectangle(cellSz*5,cellSz,Color.WHITE);
        board.add(infobox,1,6,5,1);
        board.setHalignment(infobox, HPos.CENTER);
        
        whosturn = new Text("Player 1 (Red)'s turn.");
        board.add(whosturn, 2, 6, 3, 1);
        board.setHalignment(whosturn, HPos.CENTER);
        
        board.setHgap(buffer);
        board.setVgap(buffer);
        scene.setFill(Color.BLUE);
        scene.setOnMouseClicked(this::dropPiece);
        stg.setScene(scene);
        stg.show();
    }
    
    public void dropPiece(MouseEvent m) {
        if (gameover) {
            newgame();
            return;
        }
        
        if (!gameover) {
            
            
            int x = (int) m.getX();
            /*
             * double y = m.getY();
             * System.out.printf("x: %.2f\ty:%.2f\n",x,y);
             */
            int selColumn = (x+buffer)/(cellSz+buffer);
            
            turncounter++;
            String whos = (turncounter%2==0)?"1 (Red)":"2 (Yellow)";
            whosturn.setText("Player "+whos +"'s turn");
            
            int player = (turncounter%2 !=0)?1:2;
            // record move on the abstract board
            int dropPlace = gs.dropPiece(selColumn, player);
            
            slots.get(7*dropPlace + selColumn).setFill((player==1)?Color.RED:Color.YELLOW);
            
            //check if win
            if (gs.checkWin(player)) {
                winmsg(player);
            }
        }
      
    }
    
    public void newgame() {
        //remove winmsg
        root.getChildren().remove(1);
        
        for (int i=0; i<42; i++) {
            slots.get(i).setFill(Color.WHITE);
        }
        gs = new GameState(6,7);
        gameover=false;
        turncounter=0;
        whosturn.setText("Player 1 (Red)'s turn");
    }
    
    public void winmsg(int player) {
        
        Rectangle boxout = new Rectangle(scene.getWidth(),45,Color.BLACK);
        boxout.setY(cellSz*2 -5);
        
        Rectangle boxin = new Rectangle(scene.getWidth(),40,Color.WHITE);
        boxin.setY(cellSz*2 + 2.5 - 5);
        
        Text who = new Text("Player "+player+" has won");
        who.setX(cellSz*2 + 20);
        who.setY(cellSz*2 + 15);
        Text playagain = new Text("Click anywhere to play again.");
        playagain.setX(cellSz*2 + 20);
        playagain.setY(cellSz*2 +30);
        
        Group winning = new Group(boxout, boxin);
        
        winning.getChildren().add(who);
        winning.getChildren().add(playagain);
        root.getChildren().add(winning);
        
        gameover = true;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
}
