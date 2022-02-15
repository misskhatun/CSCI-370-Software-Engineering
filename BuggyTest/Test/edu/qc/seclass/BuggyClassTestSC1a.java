package edu.qc.seclass;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuggyClassTestSC1a {

	BuggyClass myBuggyClass;

    @Before
    public void setUp() {
        myBuggyClass= new BuggyClass();
    }

    @After            //after each test
    public void tearDown() {
        myBuggyClass = null;
    }

    //This test does 100% statement coverage but not 100% branch coverage and does not show the fault
    @Test
    public void buggyMethod1() {
        assertEquals(10, myBuggyClass.buggyMethod1(1));
    }

}
