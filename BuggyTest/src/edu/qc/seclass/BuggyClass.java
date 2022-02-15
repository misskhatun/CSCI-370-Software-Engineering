package edu.qc.seclass;

public class BuggyClass {
	
	 public int buggyMethod1(int a){
		 int x=10;
		 int y=2;
		 
		 if(y>=2) {
			 y=0;
			 
		 }
		 x=x/a;
		 
		 if(x==10) {
			 y=5;
		 }
		 else {
			 x=x/y;
		 }
		 return x;
		 
	 }
	 
	 public int buggyMethod2(int a){
		 int x=10;
		 int y=2;
		 
		 if(y>=2) {
			 y=0;
			 
		 }
		 x=x/a;
		 
		 if(x==10) {
			 y=5;
		 }
		 else {
			 x=x/y;
		 }
		 return x;
		 
	 }



    public void buggyMethod3(int a, int b){
        /*this is impossible to make. While I can create a method that satisfies 100% coverage of branches without failure which is divisible by 0
        I cannot in the case go ahead and and get 100% statement coverage even with less than 100% branch coverage by putting it one of the branches
        */
    }

    public void buggyMethod4(int a, int b){
        /* this is impossible as I stated and explained above that it is not feasible to have 100% statement coverage while being able to trigger
        the divisible by 0 error as the error 
         */
    }

    public void buggyMethod5(){
        /*This is also not possible. 
         */
    }
}