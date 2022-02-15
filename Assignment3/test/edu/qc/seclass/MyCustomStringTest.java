package edu.qc.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MyCustomStringTest {

    private MyCustomStringInterface mycustomstring;

    @Before
    public void setUp() {
        mycustomstring = new MyCustomString();
    }

    @After
    public void tearDown() {
        mycustomstring = null;
    }

    @Test
    public void testCountNumbers1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals(7, mycustomstring.countNumbers());
    }
    // tests the empty string
    @Test
    public void testCountNumbers2() {
    	  mycustomstring.setString("");
          assertEquals(0, mycustomstring.countNumbers()); 
    }
    
  //tests that the program throws NullPointerException
    @Test (expected = NullPointerException.class) 
    public void testCountNumbers3() {
    	 mycustomstring.setString(null);
         mycustomstring.countNumbers();
    }
    //tests when there is no numbers in the string
    @Test
    public void testCountNumbers4() {
    	mycustomstring.setString("There is no numbers in this specific test.");
        assertEquals(0, mycustomstring.countNumbers());
    }
    // tests multiple digits number
    @Test
    public void testCountNumbers5() {
    	mycustomstring.setString("check 1 33554 89 968 9 1");
        assertEquals(6, mycustomstring.countNumbers());
    }
    //tests 1 digits number in two words
    @Test
    public void testCountNumbers6() {
    	mycustomstring.setString("hell0 the6e");
        assertEquals(2, mycustomstring.countNumbers());
    }
    // tests every 3rd character in the string when startFromEnd if false
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("d33p md1  i51,it", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));
    }
    
    // tests spaced strings
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd2() {
        mycustomstring.setString("   b   u   s");
        assertEquals("   s", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));
    }
    // tests every 5th character in the string when startFromEnd if true
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd3() {
    	 mycustomstring.setString("Today is a nice weather");
         assertEquals("a ca", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(5, true));
    }
  //tests that the program throws NullPointerException
    @Test (expected= NullPointerException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd4() {
    	mycustomstring.setString(null);
		mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4,true);
    }
    // tests when n is greater than the length of the string

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd5() {
    	mycustomstring.setString("Small");
		mycustomstring.getEveryNthCharacterFromBeginningOrEnd(9,false);
    }
  //tests that the program throws IllegalArgumentException

    @Test (expected= IllegalArgumentException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd6() {
  
    	mycustomstring.setString("Hello");
		mycustomstring.getEveryNthCharacterFromBeginningOrEnd(0,true);
    }
    // tests a string whose length is less than n
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd7() {
    	mycustomstring.setString("nice");
        assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(5, true));
    }
    // tests every 6th character in the string when startFromEnd if false
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd8() {
    	mycustomstring.setString("in person classes are better");
        assertEquals("rl e", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(6, false));
    }
    // tests printing only spaces
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd9() {
    	mycustomstring.setString("   ");
        assertEquals("   ", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, true));
    }
    //tests empty string
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd10() {
    	mycustomstring.setString("");
        assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));
    }
    //tests every 2nd character from the end
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd11() {
    	mycustomstring.setString("CS370 ");
        assertEquals("C30", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, true));
    }
    //tests only numbers
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd12() {
    	mycustomstring.setString("8345076");
        assertEquals("357", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, false));
    }
    //tests every 4th character from the end in a 5 digits number
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd13() {
    	mycustomstring.setString("Hello");
        assertEquals("e", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4, true));
    }
    //tests every 1st character including spaces
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd14() {
    	mycustomstring.setString("CS370  class");
        assertEquals("CS370  class", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, true));
    }

    @Test
    public void testConvertDigitsToNamesInSubstring1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("I'd b3tt3r put sZerome dOneSix1ts in this 5tr1n6, right?", mycustomstring.getString());
    }
    //tests from position 18th to 23 
    @Test
    public void testConvertDigitsToNamesInSubstring2() {
    	 mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
         mycustomstring.convertDigitsToNamesInSubstring(18, 23);
         assertEquals("I'd b3tt3r put s0me dOneSix1ts in this 5tr1n6, right?", mycustomstring.getString());
    }
    
    //tests only numbers
    @Test
    public void testConvertDigitsToNamesInSubstring3() {
    	mycustomstring.setString("00101100");
    	mycustomstring.convertDigitsToNamesInSubstring(1, 8);
    	assertEquals("ZeroZeroTenOneTenZero", mycustomstring.getString());
    }
    //tests NullPointerException

    @Test (expected = NullPointerException.class)
    public void testConvertDigitsToNamesInSubstring4() {
    	mycustomstring.setString(null);
    	mycustomstring.convertDigitsToNamesInSubstring(1, 8); 
    }
    
    //tests IllegalArgumentException
    @Test (expected = IllegalArgumentException.class)
    public void testConvertDigitsToNamesInSubstring5() {
    	mycustomstring.setString("Small String");
        mycustomstring.convertDigitsToNamesInSubstring(23, 17);
    }
    
    //tests MyIndexOutOfBoundsException

    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToNamesInSubstring6() { 
    	mycustomstring.setString("Small String");
        mycustomstring.convertDigitsToNamesInSubstring(17, 23); 
        }
    //tests when string length is equal to the length from start position to end position

    @Test
    public void testConvertDigitsToNamesInSubstring7() {
    	mycustomstring.setString("Ar9ument5 fr0m fir57 t10 las7 posi7ion");
        mycustomstring.convertDigitsToNamesInSubstring(1, 38);
        assertEquals("ArNineumentFive frZerom firFiveSeven tTen lasSeven posiSevenion", mycustomstring.getString());
    }

  //tests when string length is equal to the length from start position to end position with special characters

    @Test
    public void testConvertDigitsToNamesInSubstring8() {
    	mycustomstring.setString("f#ed-om69 36 be4st ");
        mycustomstring.convertDigitsToNamesInSubstring(1, 16);
        assertEquals("f#ed-omSixNine ThreeSix beFourst ", mycustomstring.getString());
    }

    
    
    
}
