public class Lab1Test extends junit.framework.TestCase
{
	public void test1()
	{
            Lab1 test = new Lab1();
            assertEquals( test.circleArea( 5 ), 78.53981633974483 );
	}

	public void test2()
	{
            Lab1 test = new Lab1();
            assertEquals( test.findSurfaceArea( 3, 4 ), 131.94689145077132 );
	}

	public void test3()
	{
            Lab1 test = new Lab1();
            assertEquals( test.isPerfectCube( 64 ), true );
            assertEquals( test.isPerfectCube( 16 ), false );
	}

	public void test4()
	{
            Lab1 test = new Lab1();
            assertEquals( test.isPerfectNumber( 6 ), true );
            assertEquals( test.isPerfectNumber( 8 ), false );
            assertEquals( test.isPerfectNumber( 28 ), true );
            assertEquals( test.isPerfectNumber( 496 ), true );
	}

	public void test5()
	{
            Lab1 test = new Lab1();
            assertEquals( test.greatestCommonFactor( 12, 18 ), 6 );
            assertEquals( test.greatestCommonFactor( 100, 400 ), 100);
            assertEquals( test.greatestCommonFactor( 13, 18 ), 1);

	}
        
        public void test6()
        {
            Lab1 test = new Lab1();
            assertEquals( test.isEquals( 1.23458, 1.23479, 3 ), true );
            assertEquals( test.isEquals( 1.23458, 1.23479, 4 ), false );
            assertEquals( test.isEquals( 1.2345892, 1.2345891, 6 ), true );
        }
        
 	public void test7()
	{
            Lab1 test = new Lab1();
            // You may or may not experience roundoff error. Check to make sure they are essentially 
            // the same.
            assertEquals( Math.abs( test.findDegrees( 84 ) - 28.88888888888889  ) < 0.0000000000001 , true );
	}

	public void test8()
	{
            Lab1 test = new Lab1();
            assertEquals( test.isSecure( "Hell0!W0r1d"), true );
            assertEquals( test.isSecure( "Hello world1"), false );
            assertEquals( test.isSecure("!@#$%sfdfsAWD009"), true);
	}

	public void test9()
	{
            Lab1 test = new Lab1();
            assertEquals( test.isPalindrome( "Racecar"), true );
            assertEquals( test.isPalindrome( "wowmom" ), false );
            assertEquals( test.isPalindrome( "civiC" ), true );  
	}
        
        public void test10()
        {
            Lab1 test = new Lab1();
            assertEquals( test.find( "falalala", "lala" ), 1 );
        }
        
        public void test11()
        {
            Lab1 test = new Lab1();
            assertEquals( test.convertToDecimal( "MMXIX" ), 2019 );
            assertEquals( test.convertToDecimal( "LVI" ), 56 );
            assertEquals( test.convertToDecimal( "XCIV" ), 94 );
            assertEquals( test.convertToDecimal( "LXXXIV" ), 84 );
        }

        public void test12()
        {
            Lab1 test = new Lab1();
            assertEquals( test.convertToBinary( 300 ), "100101100" );
            assertEquals( test.convertToBinary( 1467 ), "10110111011" );
        }

}
        
       