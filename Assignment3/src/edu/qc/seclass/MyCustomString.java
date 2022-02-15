package edu.qc.seclass;

public class MyCustomString implements MyCustomStringInterface {
	
	private String stringInput;
	

	@Override
	public String getString() {
		 if(this.stringInput.isEmpty()){
	            return null;
	        }

	        else{
	            return this.stringInput;
	        }	
	}

	@Override
	public void setString(String string) {
		this.stringInput=string;
		
	}

	@Override
	public int countNumbers() {
		   int countNum = 0;
	        boolean previousNum = false;
//	        int ifMoreThanTwoSetisPreviousIntTFalse = 0;

	        if(this.stringInput.isEmpty()){
	            return 0;
	        }

	        else if(this.stringInput == null){
	            throw new NullPointerException();
	        }
	  
	     
	        else{
	            for(int i = 0; i < this.stringInput.length(); i++){
	                if(Character.isDigit(this.stringInput.charAt(i))){
	                    if(previousNum == false){
	                    	countNum++;
	                        previousNum = true;
	                    }

	                    else if(previousNum == true && Character.isDigit(this.stringInput.charAt(i+1))) {
	                    	previousNum = true;}

	                    else{
	                    	previousNum = true;
	                    }
	                }
	                else{
	                	previousNum = false;
	                }
	            }
	            return countNum;
	        }
		 
	}

	@Override
	public String getEveryNthCharacterFromBeginningOrEnd(int n, boolean startFromEnd) {
		// TODO Auto-generated method stub
		// string
        

        // method
      

		  StringBuffer stringNew = new StringBuffer();
		 if(this.stringInput == null && n > 0){
			            throw new NullPointerException();
			        }
	        else if(n <= 0){
	            throw new IllegalArgumentException();
	        }

	       

	        else {
	            if (n > this.stringInput.length()) {
	                return "";
	            } else {
	                if (startFromEnd == true) {
	                    for (int i = this.stringInput.length() - n; i >= 0; i -= n) {
	                    	stringNew.insert(0, this.stringInput.charAt(i));
	                    }
	                    return stringNew.toString();
	                } else {
	                    for (int i = n - 1; i < this.stringInput.length(); i += n) {
	                    	stringNew.append(this.stringInput.charAt(i));
	                    }
	                    return stringNew.toString();
	                }
	            }
	        }
        
	}

	@Override
	public void convertDigitsToNamesInSubstring(int startPosition, int endPosition) {
		// TODO Auto-generated method stub
//		throws MyIndexOutOfBoundsException, IllegalArgumentException, NullPointerException {
			
			if (startPosition > endPosition){
				throw new IllegalArgumentException();
			}
			if (startPosition <1 || endPosition > this.stringInput.length()) {
				throw new MyIndexOutOfBoundsException();
			}




			String myString = stringInput.substring(startPosition-1, endPosition);
			
			myString = myString.replaceAll("10", "Ten");
			
			myString = myString.replaceAll("0", "Zero");
			
			myString = myString.replaceAll("1", "One");
			
			myString = myString.replaceAll("2", "Two");
			
			myString = myString.replaceAll("3", "Three");
			
			myString = myString.replaceAll("4", "Four");
			
			myString = myString.replaceAll("5", "Five");
			
			myString = myString.replaceAll("6", "Six");
			
			myString = myString.replaceAll("7", "Seven");
			
			myString = myString.replaceAll("8", "Eight");
			
			myString = myString.replaceAll("9", "Nine");
			

			this.stringInput = stringInput.substring(0, startPosition-1) + myString 
					+ stringInput.substring(endPosition, stringInput.length());
		
	}

}
