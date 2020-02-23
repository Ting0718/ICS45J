public class ConnectFour extends TicTacToe {
    
    private int[] row_index = {5,5,5,5,5,5};
    
    public ConnectFour()
    {
        super(6);
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
    
    public int checkPosition( int y)
    {
        return super.checkPosition(row_index[y],y);
    }
}
