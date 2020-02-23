public class Driver 
{
    public static void main (String [] args)
    {
        /*
        TicTacToe tic = new TicTacToe(3);
        //tic.printBoard();
        System.out.println();
        tic.makeMove(0,0);
        tic.makeMove(0,1);
        tic.makeMove(1,1);
        tic.makeMove(1,0);
        tic.makeMove(0,2);
        tic.makeMove(2,2);
        tic.makeMove(2,0);
        */
        
        //TicTacToe test = new TicTacToe( 6 );
        
        /*
        test.makeMove( 1, 1 );
        test.makeMove( 0, 0 );
        test.makeMove( 0, 1 );
        test.makeMove( 2, 0 );
        */

        //tic.makeMove(3,0);
        
        //test.printBoard();
        //test.loadBoard("src/LAB3_INPUT.txt");
        //test.saveBoard("src/output.txt");
        //test.printBoard();
       
        ConnectFour test = new ConnectFour();
        test.makeMove( 0 );
        test.makeMove( 0 );
        test.makeMove( 1 );
        test.makeMove( 1 );
        test.makeMove( 2 );
        test.makeMove( 2 );
        test.makeMove( 3 );
        
        //System.out.println(test.turn());
        //int[] a = {1,1,1,1,0,0};
        //test.ifHorizontalWin(1);
 
        
        //test.printBoard();
        System.out.println(test.gameOver());
        System.out.println(test.gameStatus());
        
    }
}