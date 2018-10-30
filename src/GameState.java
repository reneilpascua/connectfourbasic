
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
                | checkUpDiag2(player));
    }
    
    public boolean checkCols(int player) {
        for (int c = 0; c<numcols; c++) {
            int consec = 0;
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
        for (int r=0; r<numrows; r++) {
            int consec = 0;
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
    
    /**
     * downward diagonal (\) from first column
     */
    public boolean checkDownDiag1(int player) {
        for (int d=0; d<numrows; d++) {
            int consec = 0;
            for (int e=0; e<numcols && (d+e)<numrows ; e++) {
                if (consec >= 4) {
                    return true;
                } else if (boardArr[d+e][e] == player) {
                    consec++;
                } else {
                    consec = 0;
                }
            }
        }
        
        return false;
    }
    public boolean checkDownDiag2(int player) {
        for (int d=1; d<numcols; d++) {
            int consec = 0;
            for (int e=0; e<numrows && (d+e)<numcols; e++) {
                if (consec >=4) {
                    return true;
                } else if (boardArr[e][d+e] == player) {
                    consec++;
                } else {
                    consec=0;
                }
                
            }
        }
        return false;
    }
    
    /**
     * up diagonals (/) from first column
     */
    public boolean checkUpDiag1(int player) {
        for (int d=numrows-1; d>=0; d--) {
            int consec =0;
            for (int e=0; e<numcols && (d-e)>=0; e++) {
                if (consec>=4) {
                    return true;
                } else if (boardArr[d-e][e] == player) {
                    consec++;
                } else {
                    consec=0;
                }
            }
        }
        return false;
    }
    public boolean checkUpDiag2(int player) {
        for (int d=1; d<numcols; d++) {
            int consec = 0;
            int iter=0;
            for (int e=numrows-1; e>=0 && d+iter < numcols ; e--,iter++) {
                if (consec >=4) {
                    return true;
                } else if (boardArr[e][d+iter] == player) {
                    consec++;
                } else {
                    consec=0;
                }
            }
        }
        return false;
    }
    
}
