public class Magic {
	public static void main(String[] args) {

    int myNumber =  2;
    // refer to original number as myNumber 

    int stepOne = myNumber * myNumber;
    int stepTwo = stepOne + myNumber;
    int stepThree = stepTwo / myNumber;
    int stepFour = stepThree + 17;
    int stepFive = stepFour - myNumber;
    int stepSix = stepFive/ 6;
    System.out.println(stepSix);

		/*Recreate this project using only two variables: myNumber and magicNumber. Use your understanding of compound assignment operators to recreate the above program by only manipulating magicNumber.
    */

    int myNumber =  2;

    int magicNumber = myNumber * myNumber;
    magicNumber += myNumber;
    magicNumber /= myNumber;
    magicNumber += 17;
    magicNumber -= myNumber;
    magicNumber /= 6;
     System.out.println(magicNumber);
	}
}
