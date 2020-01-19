
public class Lab1 {
    
    public double circleArea ( int radius )
    {
        return Math.PI * Math.pow(radius,2);
    }
    
    public double findSurfaceArea( int radius, int height)
    {
        return 2 * circleArea(radius) + 2 * Math.PI * height * radius;
    }
    
    public boolean isPerfectCube( int num )
    {   
        double i = Math.cbrt(num);
        if ( i == (int) i) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean isPerfectNumber( int num )
    {
        int sum = 0;
        for (int i = 1; i < num; i++)
        {
            if ((num % i) == 0) {
                sum += i;
            }
        }
        return sum == num;
    }
    
    public int greatestCommonFactor( int x, int y )
    {
        int smaller;
        int smaller_c;
        int bigger;
        if (x < y) {
            smaller = x;
            smaller_c = x;
            bigger = y;
        }
        else {
            smaller = y;
            smaller_c = y;  
            bigger = y;
        }
        
        while (smaller_c != 0) 
        {
	    if (smaller % smaller_c == 0 && bigger % smaller_c == 0)
	    {
               return smaller_c;
	    } 
            smaller_c --;        
        }
	
        return 1;    
    }
    
    public boolean isEquals( double x, double y, int places ) 
    {
        String x_string = Double.toString(x);
        String y_string = Double.toString(y);
        
        String x_c = x_string.substring(0, places+2);
        String y_c = y_string.substring(0, places+2);
        
        return x_c.equals(y_c);
    }
     
    public double findDegrees( int fahrenheit )
    {
        return ( (double)((fahrenheit-32) * 5) / 9);
    }
    
    public boolean isSecure( String password )
    {
        if (password.length() >= 8) {
            int upper_c, lower_c, digit_c, symbol_c;
            upper_c = lower_c = digit_c = symbol_c = 0;
            for (int i = 0; i < password.length(); i++) {
                if (Character.isWhitespace(password.charAt(i))) {
                    return false;
                }
                
                else if (Character.isUpperCase(password.charAt(i))) {
                    upper_c += 1;
                }
                
                else if (Character.isLowerCase((password.charAt(i)))) {
                    lower_c += 1;
                }
                
                else if (Character.isDigit((password.charAt(i)))) {
                    digit_c += 1;
                }
                
                else {
                    symbol_c += 1;
                }
              
                if (upper_c >= 1 && lower_c >= 1 && digit_c >= 1 && symbol_c >= 1) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isPalindrome( String word )
    {   
        String s = "";
        for (int i = word.length()-1; i >= 0; i--) {  
            s += word.charAt(i);
        }
        return s.equalsIgnoreCase(word);
    }
    
    public int find( String word, String sub ) {
        String word_sub = word;
        int length = word.length();
        int counter = 0;
        while (length != 0 && length >= sub.length())
         {  
            if (word_sub.contains(sub) == true)
            {
                counter++;
                word_sub = word_sub.substring(word_sub.indexOf(sub) + sub.length());
                length = word_sub.length();
            }
         }  
        return counter;
     }
    
    public int value(char c)
    {
        if (c == 'I') { return 1;}
        else if (c == 'V') { return 5;}
        else if (c == 'X') { return 10;}
        else if (c == 'L') { return 50;}
        else if (c == 'C') { return 100;}
        else if (c == 'D') { return 500;}
        else { return 900; }
    }
    
    public int valueof(char c, char c1)
    {
        if (c == 'I')
        {
            if (c1 == 'V') { return 4; }
            else if (c1 == 'X') { return 9;}
            else { return 1;}
        }
         
        else if (c == 'V') {
            return 5; }
        
        else if (c == 'X')
        {
            if (c1 == 'L') { return 40; }
            else if (c1 == 'C') { return 90;}
            { return 10; }   
        }
        
        else if (c == 'L')
        {   return 50; }
        
        else if (c == 'C')
        {
            if (c1 == 'D') { return 400; }
            else if (c1 == 'M') { return 900;}
            { return 100; }   
        }
        
        else if (c == 'D')
        { return 500; }
        
        else
        { return 1000;}
        
    }
    
    public int convertToDecimal( String roman) 
    {
        int sum = 0;
        for (int i = 0; i+1 <= roman.length(); i++)
        {   
            if (i+1 == roman.length())
            {
                if ( value(roman.charAt(i-1)) < value(roman.charAt(i))) {
                    break;
                }
                else {
                    sum += value(roman.charAt(i));
                }       
            }
            
            else if (i+1 < roman.length()) 
            {
                if ( i - 1 >=0 && value(roman.charAt(i-1)) < value(roman.charAt(i))) 
                {
                    continue;
                }
                else 
                {
                    sum += (valueof(roman.charAt(i),roman.charAt(i+1))); 
                }
            }
        }
        return sum;
    }
    
    public String convertToBinary( int num )
    {
       int mod = num % 2;
       int div = num / 2;
       String b_r = Integer.toString(mod); 
       while (div > 0)
       {
           mod = div % 2;
           div = div / 2;
           b_r += mod;
       }
  
       String b = "";
       for (int i = b_r.length()-1; i >= 0; i--)
       {
           b += b_r.charAt(i);
       }
       return b;
    }
}
    
