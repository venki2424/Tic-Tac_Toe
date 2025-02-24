package TicToe;
import java.util.Random;
import java.util.Scanner;

class TicTac1 {
    static char[][] board;

    public TicTac1() {
        board = new char[3][3]; // Initialize a 3x3 board
        initBoard();
    }

    void initBoard() {
        for (int i = 0; i < board.length; i++) { // Loop through rows
            for (int j = 0; j < board[i].length; j++) { // Loop through columns
                board[i][j] = ' '; // Initialize each cell with a space
            }
        }
    }

    static void display() {
        System.out.println("-------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| "); // Start each row
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | "); // Print each cell
            }
            System.out.println(); // End the row
            System.out.println("-------------"); // Row separator
        }
    }
    
    static void placeMark(int row, int col, char mark) {
    	if(row>=0 && row<=2 && col>=0 && col<=2) {
    		board[row][col]=mark;
    		
    	}
    	else {
    		System.out.println("invalid position");
    	}
    }
   static boolean checkColWin() {
    	for(int j=0; j<=2; j++) {
    		if(board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j]==board[2][j]) {
    			return true;
    		}
    	}
    	return false;
    }
    static boolean checkRowWin() {
    	for(int i=0; i<=2; i++) {
    		if(board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1]==board[i][2]) {
    			return true;
    		}
    	}
    	  return false;
    }
  

    static boolean checkDiagonal() {
        // Check top-left to bottom-right diagonal
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        // Check top-right to bottom-left diagonal
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }
    
}

//--------------------------------------------------------------

abstract class player{
	String name;
	char mark;
	abstract void makeMove();
	boolean isValidMove(int row, int col) {
		if(row>=0 && row<=2 && col>=0 && col<=2) {
			if(TicTac1.board[row][col]==' ') {
				return true;
			}
		}
		return false;
	}
}

//----------------------------------------------------------
class HumanPlayer2 extends player{
	String name;
	char mark;
	HumanPlayer2(String name,char mark){
		this.name=name;
		this.mark=mark;
	}
	void makeMove() {
		Scanner scan = new Scanner(System.in);	
		int row;
		int col;
		do {
			System.out.println("Enter the row and column");
			row=scan.nextInt();
			col=scan.nextInt();
		}while(!isValidMove(row, col)); 
			TicTac1.placeMark(row, col, mark);
		
		}
}
//-------------------------------------------------------------

class AIPlayer extends player{

	AIPlayer(String name,char mark){
		this.name=name;
		this.mark=mark;
	}
	void makeMove() {
		Scanner scan = new Scanner(System.in);	
		int row;
		int col;
		do {
			Random r= new Random();
			row=r.nextInt(3);// generate random 0,1,2
			col=r.nextInt(3);
		}while(!isValidMove(row, col)); 
			TicTac1.placeMark(row, col, mark);
		
		}
	
}

//---------------------------------------------------------------

public class LaunchGame2 {
	    public static void main(String[] args) {
	        TicTac1 t = new TicTac1();
	        //t.display();
	        System.out.println("Enter the row and column matrix");
	        HumanPlayer2 p1= new HumanPlayer2("null", 'x');
	        AIPlayer p2=new AIPlayer("AI", 'o');
	        player cp = p1;//current player
	        
	   while(true) {
		     System.out.println(cp.name + " turn");
		        cp.makeMove();
		        TicTac1.display();
		        if(TicTac1.checkColWin() || TicTac1.checkRowWin() || TicTac1.checkDiagonal() ) {
	            	System.out.println(cp.name+ " has won");
	            	break;
	            }
		        else {
	            	if(cp==p1) {
	            		cp=p2;
	            	}
	            	else {
	            		cp=p1;
	            	}
		        }
	            
	   }
	        
	    }
}
