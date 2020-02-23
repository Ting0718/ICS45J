
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
    
    
    private int[][] board;
    private static final int EMPTY = 0;
    private static final int PLAYER1 = 1;
    private static final int PLAYER2 = 2;
    private int turn = PLAYER1;
    
    // the current status of game -> no winner
    private int Status = 0;
    

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
            if (ifHorizontalWin(x).compareTo(State.PLAYER1WIN) == 0 || ifVerticalWin(y).compareTo(State.PLAYER1WIN) == 0) { Status = 1; } // 1 wins
            else if (ifHorizontalWin(x).compareTo(State.PLAYER2WIN) == 0 || ifVerticalWin(y).compareTo(State.PLAYER2WIN) == 0) { Status = 2;} // 2 wins
            else if (isFull() && Status != 1 && Status != 2) { Status = -1; } // tie
            else if (ifDiagonalWin()) {Status = turn();}
            
            if (Status == 0)
            {
                if (check4HorizontalEqual(this.board[x]))
                {
                if (Integer.compare(this.board[x][y],PLAYER1) == 0) { Status = 1;}
                else { Status = 2;}
                }
            }
          
            changeTurn();
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
    
    private boolean check4HorizontalEqual(int[] a)
    {
        int count = 1;
        for (int i = 1; i < a.length; i++)
        {
            if (Integer.compare(a[i-1],a[i]) == 0)
            {
                count += 1;
            }
            if (count == 4) {return true;}
        }
        return false;
    }
    
    private boolean checkIfHorizontalAllEqual(int[] a)
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
    
    // check if the row has the same element given the row index
    private State ifHorizontalWin(int x) 
    {
        if (isEmpty(x,this.board[x][0])) { return State.NOONE;}
        else {
           if (checkIfHorizontalAllEqual(this.board[x]))
           {
               if (Integer.compare(this.board[x][0],PLAYER1) == 0) { return State.PLAYER1WIN;}
               else { return State.PLAYER2WIN;}
           }
        }
        return State.NOONE;
    }
     
    
    // check if the column has the same element given the column index
    private State ifVerticalWin(int y)
    {
        for (int col = 1; col < board.length; col++)
        {
            if (isEmpty(this.board[0][y],y)) {
                return State.NOONE;}
            else if (Integer.compare(this.board[0][y], this.board[col][y]) != 0)
            {
                return State.NOONE;
            }
        }
        if (Integer.compare(this.board[0][y],PLAYER1) == 0) {return State.PLAYER1WIN;}
        else { return State.PLAYER2WIN;}
        
    }
    
    
    
    private boolean ifDiagonalWin() // check if diagonal has the same elements
    {   
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
            }

            for (int row = 0, col = board.length-1; row < board.length; row++)
            {
                if (Integer.compare(this.board[0][board.length-1], this.board[row][col]) != 0)
                { 
                    minorDiagonal = false;
                    break;
                }  
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
    public static int[] IntStringToList(String s)
    {
        int[] a = new int[s.length()];
        for (int i = 0; i < s.length();i++)
        {
            a[i] = Character.getNumericValue(s.charAt(i));
        }
        return a;
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
    
    
    // need to delte
    private void printBoard()
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
