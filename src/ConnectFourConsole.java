import java.util.Scanner;

public class ConnectFourConsole {
    
    public static void prt(String s) {
        System.out.println(s);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        boolean quit = false;
        GameState gs = new GameState(6,7);
        int turncounter = 0;
        int drophere;
        
        prt("Hi let's play Connect Four\n");
        while(!quit) {
            turncounter++;
            prt("-----------\n\n");
            int pnum = (turncounter%2 == 0)?2:1;
            prt("Player "+pnum+"'s turn.\n");
            gs.displayState();
            prt("Drop a piece into this column:");
            drophere = sc.nextInt();
            gs.dropPiece(drophere,pnum);
            if (gs.checkWin(pnum)) {
                gs.displayState();
                prt("Congrats, Player "+pnum+"!!!\nYou win.");
                quit = true;
            }
        }
    }
}
