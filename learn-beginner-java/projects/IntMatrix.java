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
