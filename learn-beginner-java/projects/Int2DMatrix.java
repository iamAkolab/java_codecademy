import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) {
		
	  // Using the provided 2D array
	  int[][] intMatrix = {
				{1, 1, 1, 1, 1},
				{2, 4, 6, 8, 0},
				{9, 8, 7, 6, 5}
		};   
    
		// Declare and initialize a 2D array called subMatrix
		int[][] subMatrix = new int[2][2];

    subMatrix[0][0] = intMatrix[0][0] * 5;
    subMatrix[0][1] = intMatrix[0][1] * 5;
    subMatrix[1][0] = intMatrix[1][0] * 5;
    subMatrix[1][1] = intMatrix[1][1] * 5;

    System.out.println(Arrays.deepToString(subMatrix));
    
    }
}

public class Main {
	public static void main(String[] args) {
		
		//Given the provided 2d array
		int[][] intMatrix = {
				{ 4,  6,  8, 10, 12, 14, 16},
				{18, 20, 22, 24, 26, 28, 30},
				{32, 34, 36, 38, 40, 42, 44},
				{46, 48, 50, 52, 54, 56, 58},
				{60, 62, 64, 66, 68, 70, 79}
		};
		int rows = intMatrix.length;
		int columns = intMatrix[0].length;
    
		int sum = 0;
		for(int i=0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
                // Add a line to calculate sum of all elements
				sum+=intMatrix[i][j];
			}
		}
		System.out.println(sum);
	}
}

public class Main {
	public static void main(String[] args) {
		int[][] binary = {
      {0, 1, 0, 1},
      {1, 1, 1, 0},
      {1, 0, 0, 1},
      {1, 0, 1, 0}
    };
		
		int onesCount = 0;

    for (int[] row : binary) {
      for (int i: row) {
        onesCount += i;
      }
    }
    

		System.out.println(onesCount);

    String[][] wordData = {{"study", "consider", "examine", "learn"}, {"ponder", "read", "think", "cogitate"}};	

    int i = 0, j = 0;
    while (i < wordData.length) {
      j = 0;
      while (j < wordData[i].length) {
        System.out.println(wordData[i][j] + ": [" + i + "]" + "[" + j + "]");
        j++;
      }
      i++;
    }
	}
}

public class Main {
	public static void main(String[] args) {
		// Given runner lap data
		double[][] times = {{64.791, 75.972, 68.950, 79.039, 73.006, 74.157}, {67.768, 69.334, 70.450, 67.667, 75.686, 76.298}, {72.653, 77.649, 74.245, 62.121, 63.379, 79.354}};
		
		double runnerTime = 0.0;
		for(int outer = 0; outer < times.length; outer++) {
			runnerTime = 0.0;
			for(int inner = 0; inner < times[outer].length; inner++) {
        System.out.println("Runner index: " + outer + ", Time index: " + inner);
				// Add a line to sum up the values in each row. Use the variable runnerTime
				runnerTime+=times[outer][inner];
			}
      double averageVal = 0;
      averageVal = runnerTime / times[outer].length;
			System.out.println("Sum of runner " + outer + " times: " + runnerTime);
			System.out.println("Average of runner " + outer + ": " + averageVal);
		}
	}
}


public class Main {
	public static void main(String[] args) {
    // Given runner lap data
		double[][] times = {{64.791, 75.972, 68.950, 79.039, 73.006, 74.157}, {67.768, 69.334, 70.450, 67.667, 75.686, 76.298}, {72.653, 77.649, 74.245, 62.121, 63.379, 79.354}};
		
		double lapTime = 0.0;
		for(int outer = 0; outer < times[0].length; outer++){
			lapTime = 0.0;
			for(int inner = 0; inner < times.length; inner++){
				System.out.println("Lap index: " + outer + ", Time index: " + inner);
				// Add a line to sum up the values
        lapTime+=times[inner][outer];
			}
			double averageVal = 0;
      averageVal = lapTime / times.length;
			System.out.println("Sum of lap " + outer + " times: " + lapTime);
			System.out.println("Average time for lap " + outer + ": " + averageVal);
		}
	}
}





import java.util.Arrays;
public class Main {
	public static void main(String[] args) {
		int[][] imageData={
      {100,90,255,80,70,255,60,50},
			{255,10,5,255,10,5,255,255},
			{255,255,255,0,255,255,255,75},
			{255,60,30,0,30,60,255,255}
    };
		// Declare and initialize the 2D array newImage
		int[][] newImage = new int[4][6];

		// Add a nested `for` loop and copy the data of `imagedata` to `newImage`
    for(int i=0; i<newImage.length; i++){
      for(int j=0; j<newImage[i].length; j++){
        newImage[i][j] = imageData[i][j];
      }
    }

		System.out.println(Arrays.deepToString(newImage));
			
		for(int i=0; i<newImage.length; i++){
      for(int j=0; j<newImage[i].length; j++){
        // Add the if-else statement here
        if(newImage[i][j]-50<0){
          newImage[i][j] = 0;
        }
        else{
          newImage[i][j]-=50;
        }
      }
    }
		System.out.println(Arrays.deepToString(newImage));
	}
}





import java.util.Arrays;
public class Main {
	public static void main(String[] args) {  

		//Declare and initialize a 4x3 2D array of doubles called `scores`
  	double[][] scores = {{80.4, -1, -1}, {96.2, -1, -1}, {100.0, -1, -1}, {78.9, -1, -1}};


  	System.out.println(Arrays.deepToString(scores));

		//Manually enter the scores for the second exam
    scores[0][1] = 89.7;
    scores[1][1] = 90.5;
    scores[2][1] = 93.6;
    scores[3][1] = 88.1;

  	System.out.println(Arrays.deepToString(scores));
		
  	//Declare and initialize an empty 4x2 2D array of double values called `newScores`
    double[][] newScores = new double[4][2];

  	//Use `for` loops to copy the scores
    for(int i = 0; i < newScores.length; i++){
      for(int j = 0; j < newScores[i].length; j++){
        newScores[i][j] = scores[i][j];
      }
    }

  	System.out.println(Arrays.deepToString(newScores));

  	//Iterate through the `newScores` 2D array and use `if` statement to add 2 additional points
    for(int i = 0; i < newScores.length; i++){
      for(int j = 0; j < newScores[i].length; j++){
        if(newScores[i][j]<90){
          newScores[i][j]+=2;
        }
      }
    }
    System.out.println(Arrays.deepToString(newScores));
	}
}
