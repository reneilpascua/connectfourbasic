
public class GameState {
    
    public int[][] boardArr;
    public int numcols;
    public int numrows;
    public int[] bottoms;
    
    public GameState(int rows, int columns) {
        numcols = columns;
        numrows = rows;
        boardArr = new int[rows][columns];
        
        // initialize bottoms
        bottoms = new int[columns];
        for (int i=0; i<columns; i++) {
            bottoms[i] = rows-1;
        }
    }
    
    public void displayState() {
        for (int i=0; i<numrows; i++) {
            for (int j=0; j<numcols; j++) {
                System.out.printf("%3d",boardArr[i][j]);
            }
            System.out.printf("\n");
        }
    }
    
    public void dropPiece(int col, int playerNum) {
        boardArr[bottoms[col]][col] = playerNum;
        bottoms[col]--;
    }
    
    public boolean checkWin(int player) {
        return (checkCols(player) | checkRows(player));
    }
    
    public boolean checkCols(int player) {
        int consec = 0;
        for (int c = 0; c<numcols; c++) {
            for (int r = 0; r<numrows; r++) {
                if(consec>=4) {
                    return true;
                } else if (boardArr[r][c] == player) {
                    consec++;
                } else {
                    consec = 0;
                }
            }
        }
        return false;
    }
    
    public boolean checkRows(int player) {
        int consec = 0;
        for (int r=0; r<numrows; r++) {
            for (int c=0; c<numcols; c++) {
                if (consec>=4) {
                    return true;
                } else if (boardArr[r][c] == player) {
                    consec++;
                } else {
                    consec = 0;
                }
            }
        }
        return false;
    }
    
}
