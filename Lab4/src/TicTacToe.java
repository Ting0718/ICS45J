
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TicTacToe {
    
    enum State
    {
        NOONE, PLAYER1WIN, PLAYER2WIN;
    }
    
    
    protected int[][] board;
    protected static final int EMPTY = 0;
    protected static final int PLAYER1 = 1;
    protected static final int PLAYER2 = 2;
    protected int turn = PLAYER1;

    
    // the current status of game -> no winner
    protected int Status = 0;
    protected int DIAGONAL_WINNER = EMPTY;
    

    public TicTacToe(int n)
    {
        this.board = new int[n][n];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = EMPTY;
            }
        }
    }
   
    public boolean makeMove( int x, int y )
    {
        if ((checkPosition(x,y) == -1)) { return false; }        
        // if occupied
        else if (isEmpty(x,y) == false) {
            System.out.println("this spot is occupied");
            return false;
        }
        else {
            this.board[x][y] = turn();
            // change the turn after making a move
            if (ifHorizontalWin(x)) { 
                Status = turn(); }
            if (ifVerticalWin(y)) {
                Status = turn();}
            if (isFull() && Status != PLAYER1 && Status != PLAYER2) { Status = -1; } // tie
            if (ifDiagonalWin()) {
                Status = turn();}
            else {
                changeTurn();
            }
            return true;
        }
    }
     
    private boolean isFull()
    {
        for (int row = 0; row < board.length; row++) 
        {
            for (int col = 0; col < board[row].length; col++) 
            {
                if (Integer.compare(board[col][row],EMPTY) == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    protected boolean isEmpty(int x, int y)
    {
        return Integer.compare(this.board[x][y], EMPTY) == 0;
    }
    

    
    protected boolean checkIfHorizontalEqual(int[] a)
    {
        for (int i = 1; i < a.length; i++)
        {
            if (Integer.compare(a[0],a[i]) != 0)
            {
                return false;
            }
        }
        return true;
    }
    
    private State whichPlayerWin(int[] a)
    {
        for (int i = 0; i < a.length; i++)
        {
            if (a[i] != EMPTY)
            {
               if (Integer.compare(a[i], PLAYER1) == 0) { return State.PLAYER1WIN; }
               else
               { return State.PLAYER2WIN; }
            }
        }
        return State.NOONE;
    }
    
    // check if the row has the same element given the row index
    public boolean ifHorizontalWin(int x) 
    {
        if (checkIfHorizontalEqual(this.board[x]))
        {
            return true;
        }
        return false;
    }
     
    
    // check if the column has the same element given the column index
    protected boolean ifVerticalWin(int y)
    {
        for (int col = 1; col < board.length; col++)
        {
            if (Integer.compare(this.board[0][y], this.board[col][y]) != 0)
            {
                return false;
            }
        }
        return true;
    }
    
    
    
    protected boolean ifDiagonalWin() // check if diagonal has the same elements
    {  
        int winnter = EMPTY;
        boolean mainDiagonal = true;
        boolean minorDiagonal = true;
        
        if (Integer.compare(this.board[0][0],EMPTY) == 0) { mainDiagonal = false;}
        if (Integer.compare(this.board[0][board.length-1],EMPTY) == 0 ) { minorDiagonal = false; }
        
        if (mainDiagonal != false || minorDiagonal != false)
        {
            for (int row = 1; row < board.length; row++)
            {
                if (Integer.compare(this.board[0][0],this.board[row][row]) != 0)
                { 
                    mainDiagonal = false; 
                    break;
                }
                else { DIAGONAL_WINNER = this.board[0][0]; }
            }

            for (int row = 0, col = board.length-1; row < board.length; row++)
            {
                if (Integer.compare(this.board[0][board.length-1], this.board[row][col]) != 0)
                { 
                    minorDiagonal = false;
                    break;
                } 
                else { DIAGONAL_WINNER = this.board[0][0]; }
            }
        }
        
        return mainDiagonal == true || minorDiagonal == true;
    }
    
    protected void changeTurn()
    {
        if (Integer.compare(turn, PLAYER1) == 0) { 
            turn = PLAYER2;
        }
        else {
            turn = PLAYER1;     
        }
    }

    public int turn()
    {
        return turn;
    }
    
    public int gameStatus()
    {
        return Status;
    }
    
    public boolean gameOver()
    {
        return gameStatus() != 0;
    }
    
    @Override
    public String toString()
    {
        String s = "";
        for (int row = 0; row < board.length; row++) 
        {
            for (int col = 0; col < board[row].length; col++) 
            {
                s += board[row][col];
            }
            s += "\n";
        }
        return s;
    }
    protected static int[] IntStringToList(String s)
    {
        int[] a = new int[s.length()];
        for (int i = 0; i < s.length();i++)
        {
            a[i] = Character.getNumericValue(s.charAt(i));
        }
        return a;
    }
    
    // give the player in a column or a row
    protected int whichPlayerIsIn(int line)
    {
        for (int i = 0; i < this.board.length; i++)
        {
            if (Integer.compare(this.board[line][i], PLAYER1) == 0) {return PLAYER1; }
            else if (Integer.compare(this.board[line][i], PLAYER2) == 0) {return PLAYER2; }
        }
        return EMPTY;
    }
    
    
    public void loadBoard(String fileName)
    {  
        try
        {
            FileReader reader = new FileReader(fileName);
            Scanner input = new Scanner(reader);
            
            int r = 0;
            while (input.hasNext())
            {
                String line = input.nextLine();
                int[] a = TicTacToe.IntStringToList(line);
                
                this.board[r] = a;
                r++;
            }
            input.close();
            
        }
        catch (FileNotFoundException e)
        {
           System.out.println(e);
        }
        
        if (ifDiagonalWin()) {Status = DIAGONAL_WINNER; }
        else{
            for (int i = 0; i < this.board.length; i++) {
                if (ifHorizontalWin(i)) { 
                    Status = whichPlayerIsIn(i); 
                    break; 
                }
                else if (ifVerticalWin(i)) { 
                    Status = whichPlayerIsIn(i); 
                    break;
                }
            }
        }
    }
    
    public void saveBoard(String fileName)
    {
        try
        {
            PrintWriter output = new PrintWriter(fileName);
            output.print(this.toString());
            output.close();
        }
  
        catch (FileNotFoundException e)
        {
           System.out.println(e);
        }
    }
    
    public int checkPosition( int x, int y )
    {
        if (x < board.length && y < board.length) { return this.board[x][y]; }
        else { return -1;}
    }
    
   
    protected void printBoard()
    {
        for (int row = 0; row < board.length; row++) 
        {
            for (int col = 0; col < board[row].length; col++) 
            {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}
