
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ConnectFour extends TicTacToe {
    
    protected int[] row_index = {5,5,5,5,5,5};
    private int size;
    
    
    public ConnectFour()
    {
        super(6);
        this.size = 6;
    }
    
    // two methods
    public boolean makeMove(int y)
    {

        if (row_index[y] < 0) { 
            return false; 
        }
        if (super.checkPosition(row_index[y],y) == -1) { 
            return false; 
        }
        else if (isEmpty(row_index[y],y) == false) { 
            return false; }
        else {
            super.makeMove(row_index[y], y);
            row_index[y]--;
            return true;
        }
    }
    
    @Override
    protected boolean checkIfHorizontalEqual(int[] a)
    {
        int count = 0;
        for (int i = 1; i < a.length; i++)
        {
            if (Integer.compare(a[i-1],a[i]) == 0 && a[i] != EMPTY)
            {
                count += 1;
            }
            else { 
                count = 0;}
            if (count >= 3) {
                return true;}
        }
        return false;
    }
    
    // need to 
    @Override
    protected boolean ifVerticalWin(int y)
    {
        int count = 1;
        for (int col = 1; col < this.size; col++)
        {
            if (Integer.compare(this.board[col-1][y], this.board[col][y]) == 0 && this.board[col][y] != EMPTY)
            {
                count += 1;
                if (count >= 4) { return true; }
            }
            else {
                count = 1;
            }
        }
        return count >= 4;
    }
    
    protected boolean mainDiagonal()
    {
        int mainCount = 0;
        int mainCount2 = 0;
        
        for (int row = 0; row < this.size-1; row++)
        {
            if (Integer.compare(this.board[row][row],this.board[row+1][row+1]) == 0 && this.board[row+1][row+1] != EMPTY)
            {
                mainCount += 1;
                DIAGONAL_WINNER = this.board[row+1][row+1];
            }
            else { 
                mainCount = 0;
                if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                    DIAGONAL_WINNER = EMPTY;
                }
            }
            if (mainCount >= 3) {
                return true;}
            
        }
        mainCount = 0;
        
        for (int row = 1; row < this.size - 1; row++)
        {
            if (Integer.compare(this.board[row][row-1], this.board[row+1][row]) == 0 && this.board[row+1][row] != EMPTY) 
            {
                mainCount += 1;
                if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                    DIAGONAL_WINNER = this.board[row+1][row]; }
            }
            
            else { 
                mainCount = 0; 
                if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                    DIAGONAL_WINNER = EMPTY; }
            }
            
            if (Integer.compare(this.board[row-1][row], this.board[row][row+1]) == 0 && this.board[row][row+1] != EMPTY)
            {
                mainCount2 += 1;
                if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                    DIAGONAL_WINNER = this.board[row][row+1]; }
            }
            
            else { 
                mainCount2 = 0; 
                if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                    DIAGONAL_WINNER = EMPTY; }
            }
            
            if (mainCount >= 3 || mainCount2 >= 3) { return true;}
        }
        mainCount = 0; 
        mainCount2 = 0;
        
        for (int row = 2; row < this.size-1; row++)
        {
            if (Integer.compare(this.board[row][row-2], this.board[row+1][row-1]) == 0 && this.board[row+1][row-1] != EMPTY)
            {
                mainCount += 1;
                if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                    DIAGONAL_WINNER = this.board[row+1][row-1]; }
            } 
            
            else {
                if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                    DIAGONAL_WINNER = EMPTY; }
                mainCount = 0; 
            }
            
            if (Integer.compare(this.board[row-2][row], this.board[row-1][row+1]) == 0 && this.board[row-1][row+1] != EMPTY)
            {
                mainCount2 += 1;
                if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                    DIAGONAL_WINNER = this.board[row-1][row+1]; }
            }
            
            else { 
                mainCount2 = 0; 
                if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                    DIAGONAL_WINNER = EMPTY; }
            }
            
            if (mainCount >= 3 || mainCount2 >= 3) { return true;}
        }
        return false;
    }
    
    protected boolean minorDiagonal()
    {
        int mainCount = 0;
        int mainCount2 = 0;
        
        for (int row = 0, col = 5; row < this.size-1; row++, col--)
        {
            if (Integer.compare(this.board[row][col],this.board[row+1][col-1]) == 0 && this.board[row+1][col-1] != EMPTY)
            {
                mainCount += 1;
                if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                    DIAGONAL_WINNER = this.board[row+1][col-1]; }
            }
            else { 
                mainCount = 0; 
                if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                    DIAGONAL_WINNER = EMPTY; }
            }
            if (mainCount >= 3) { return true;}
            
        }
        
        mainCount = 0;
        
       for (int row = 3, col = 0; row >= 0; row--, col++)
       {
        if (Integer.compare(this.board[row+1][col],this.board[row][col+1]) == 0 && this.board[row][col+1] != EMPTY)
        {
            mainCount += 1;
            if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                DIAGONAL_WINNER = this.board[row][col+1]; }
        }
        
        else { 
            mainCount = 0;
            if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                DIAGONAL_WINNER = EMPTY; }
        }
        
        if (Integer.compare(this.board[row+2][col+1],this.board[row+1][col+2]) == 0 && this.board[row+1][col+2] != EMPTY)
        {
            mainCount2 += 1;
            if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                DIAGONAL_WINNER = this.board[row+1][col+2]; }
        }
        
        else { 
            mainCount2 = 0; 
            if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                DIAGONAL_WINNER = EMPTY; }
        }
            
        if (mainCount >= 3 || mainCount2 >= 3) { return true;}
       }
       mainCount = 0; 
       mainCount2 = 0;
       
       for (int row = 3, col = 0; row >= 1; row--, col++)
       {
        if (Integer.compare(this.board[row][col],this.board[row-1][col+1]) == 0 && this.board[row-1][col+1] != EMPTY)
        {
            mainCount += 1;
            if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                DIAGONAL_WINNER = this.board[row-1][col+1]; }
            
        }
        
        else { 
            mainCount = 0; 
            if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                DIAGONAL_WINNER = EMPTY; }
        }
        
        if (Integer.compare(this.board[row+2][col+2],this.board[row+1][col+3]) == 0 && this.board[row+1][col+3] != EMPTY)
        {
            mainCount2 += 1;
            if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                DIAGONAL_WINNER = this.board[row+1][col+3]; }
        }
        
        else { 
            mainCount2 = 0; 
            if (Integer.compare(DIAGONAL_WINNER, EMPTY) == 0) {
                DIAGONAL_WINNER = EMPTY; }
        }
            
        if (mainCount >= 3 || mainCount2 >= 3) { return true;}
       }
      
       return false; 
    }
    
    @Override
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
            
            for (int i = 0; i < this.size; i++)
            {
                changeRowIndex(i);
            }   
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
    protected void changeRowIndex(int col)
    {
        if (this.board[5][col] == 0) 
        {
            row_index[col] = 5;
             return;
        }
        for (int i = 0; i < this.size; i++)
        {
            if (this.board[i][col] != 0) {
                row_index[col] = this.size - i + 1;
                break;
            }
        }
    }
    
    @Override
    protected boolean ifDiagonalWin() // check if diagonal has the same elements
    {   
        return (mainDiagonal() || minorDiagonal());
    }
    
    public int checkPosition(int y)
    {
        return super.checkPosition(row_index[y]+1,y);
    }

    public int rowIndex(int col) {
        return row_index[col]+1;
    }
    
    protected void UndoAnIndex(int col) {
        this.board[rowIndex(col)][col] = EMPTY;
        row_index[col]++;
        if (turn() == PLAYER1 ) { this.turn = PLAYER2; }
        else {
            this.turn = PLAYER1;
        }
    }
}
