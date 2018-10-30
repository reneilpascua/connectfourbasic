
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
        System.out.printf("\n");
    }
    
    public int dropPiece(int col, int playerNum) {
        boardArr[bottoms[col]][col] = playerNum;
        return bottoms[col]--;
    }
    
    public boolean checkWin(int player) {
        return (checkCols(player) 
                | checkRows(player)
                | checkDownDiag1(player)
                | checkDownDiag2(player)
                | checkUpDiag1(player)
                | checkUpDiag2(player)
                );
    }
    
    public boolean checkCols(int player) {
        // column by column
        for (int c=0; c<numcols; c++) {
            int consec = 0;
            for (int r=0; r<numrows; r++) {
                if (boardArr[r][c] == player) {
                    consec++;
                } else {
                    consec = 0;
                }
                if (consec>=4) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean checkRows(int player) {
        // row by row
        for (int r=0; r<numrows; r++) {
            int consec=0;
            for (int c=0; c<numcols; c++) {
                if (boardArr[r][c] == player) {
                    consec++;
                } else {
                    consec=0;
                }
                if(consec>=4) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean checkDownDiag1(int player) {
        // check downdiagonals (\) with tops at column 0
        for (int sr=0; sr<numrows; sr++) {
            int consec=0;
            for (int c=0; c<numcols && (sr+c)<numrows; c++) {
                if (boardArr[sr+c][c] ==player) {
                    consec++;
                } else {
                    consec=0;
                }
                if (consec>=4) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean checkDownDiag2(int player) {
        // check downdiagonals (\) with tops at row 0
        for (int sc=1; sc<numcols; sc++) {
            int consec=0;
            for(int r=0; r<numrows && (sc+r) < numcols; r++) {
                if (boardArr[r][sc+r]==player) {
                    consec++;
                } else {
                    consec=0;
                }
                if (consec>=4) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean checkUpDiag1(int player) {
        // check updiags (/) with bottoms at column 0
        for (int sr= numrows-1; sr>=0 ; sr--) {
            int consec=0;
            for (int c=0; c<numcols && (sr-c)>=0; c++) {
                if (boardArr[sr-c][c] == player) {
                    consec++;
                } else {
                    consec=0;
                }
                if (consec>=4) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean checkUpDiag2(int player) {
        //check updiags (/) with bottoms at last row
        for (int sc=1; sc<numcols; sc++) {
            int consec=0;
            int lastrow = numrows-1;
            for (int r=lastrow; r>=0 && (sc+lastrow-r)<numcols; r--) {
                if (boardArr[r][sc+lastrow-r] == player) {
                    consec++;
                } else {
                    consec=0;
                }
                if (consec>=4) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
        
}
