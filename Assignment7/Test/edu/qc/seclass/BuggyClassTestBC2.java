package edu.qc.seclass;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuggyClassTestBC2 {

	 BuggyClass myBuggyClass;

	    @Before
	    public void setUp() {
	        myBuggyClass= new BuggyClass();
	    }

	    @After            //after each test
	    public void tearDown() {
	        myBuggyClass = null;
	    }

	    //This test does 50%+ branch coverage and can only show the fault
	    @Test
	    public void buggyMethod2() {
	        assertEquals(0, myBuggyClass.buggyMethod2(2));
	    }

}
