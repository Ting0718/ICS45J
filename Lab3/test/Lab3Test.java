import java.io.BufferedReader;
import java.io.FileReader;

public class Lab3Test extends junit.framework.TestCase
{
    
    public void test1()
    {
        TicTacToe test = new TicTacToe( 3 );
        assertEquals(1, test.turn()); 
        assertEquals( false, test.gameOver() );
        assertEquals( true, test.makeMove( 1, 1 ) );
        assertEquals(2, test.turn());
        assertEquals(0, test.checkPosition( 0, 0 ) );
        assertEquals(1, test.checkPosition( 1, 1 ) );
        assertEquals( "000\n010\n000\n", test.toString() );
    }

    public void test2()
    {
        TicTacToe test = new TicTacToe( 3 );
        test.makeMove( 1, 1 );
        test.makeMove( 0, 0 );
        test.makeMove( 0, 1 );
        test.makeMove( 2, 0 );
        //test.printBoard();
        assertEquals( 0, test.gameStatus() );
        test.makeMove( 2, 1 );
        assertEquals( 1, test.gameStatus() );
        assertEquals( "210\n010\n210\n", test.toString() ); 
    }
    
    
    public void test3()
    {
        ConnectFour test = new ConnectFour();
        test.makeMove( 0 );
        assertEquals( 2, test.turn() );
        test.makeMove( 0 );
        test.makeMove( 0 );
        test.makeMove( 0 );
        test.makeMove( 0 );
        test.makeMove( 0 );
        assertEquals( false, test.gameOver() );
        assertEquals( 0, test.gameStatus() );
        assertEquals( "200000\n100000\n200000\n100000\n200000\n100000\n", test.toString() );
    }
    
    
    public void test4()
    {
        ConnectFour test = new ConnectFour();
        test.makeMove( 0 );
        test.makeMove( 0 );
        test.makeMove( 0 );
        test.makeMove( 0 );
        test.makeMove( 0 );
        test.makeMove( 0 );
        assertEquals( false, test.makeMove(0) );
        assertEquals( "200000\n100000\n200000\n100000\n200000\n100000\n", test.toString() );
    }

    
    public void test5()
    {
        ConnectFour test = new ConnectFour();
        test.makeMove( 0 );
        test.makeMove( 0 );
        test.makeMove( 1 );
        test.makeMove( 1 );
        test.makeMove( 2 );
        test.makeMove( 2 );
        test.makeMove( 3 );
        assertEquals( true, test.gameOver() );
        assertEquals( 1, test.gameStatus() );
        test.saveBoard("LAB3_OUTPUT.txt");
        try
        {
            FileReader fileReader = new FileReader( "LAB3_OUTPUT.txt" );
            BufferedReader read = new BufferedReader( fileReader );
            String output = "";
            String x = read.readLine();
            while( x != null )
            {
                output += x + "\n";
                x = read.readLine();
            }
            System.out.println( output );
            assertEquals( "000000\n000000\n000000\n000000\n222000\n111100\n", output );
        }
        catch( Exception e )
        {
            System.out.println( e );
        }
    }
    
   
    public void test6()
    {
        ConnectFour test = new ConnectFour();
        test.loadBoard( "LAB3_INPUT.txt" );
        assertEquals( 1, test.gameStatus() );
        assertEquals( true, test.gameOver() );
        assertEquals( "000001\n210012\n120222\n211211\n221112\n212121\n", test.toString());
    }



}

