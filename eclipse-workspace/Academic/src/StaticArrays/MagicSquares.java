package StaticArrays;
import java.util.Arrays;
import java.util.Scanner;
/**
 * This program asks the user to enter an odd integer and generates a magic square with that as the dimensions.
 * @author eric_li
 *
 */
public class MagicSquares {

	public static void main(String[] args) {
		Scanner user = new Scanner(System.in);
		System.out.print("Enter an odd integer: ");
		int n = user.nextInt();
		int[][] square = new int[n][n];
		user.close();
		int x = n - 1;
		int y = n/2;
		for (int i = 1; i <= Math.pow(n, 2); i ++) {
			if (i == 1) {
				square[x][y] = i;
			}
			else if (x == n && y == n && square[0][0] != 0) {
				x = n - 2;
				y = n - 1;
				square[x][y] = i;
			}
			else if (x > n - 1 && y > n - 1) {
				x = 0;
				y = 0;
				square[x][y] = i;
			}
			else if (x > n - 1) {
				x = 0;
				square[x][y] = i;
			}
			else if (y > n - 1) {
				y = 0;
				square[x][y] = i;
			}
			else if (square[x][y] != 0) {
				x -= 2;
				y --;
				square[x][y] = i;
			}
			else if (square[x][y] == 0) {
				square[x][y] = i;
			}
			x ++;
			y ++;
		}
		for (int i = 0; i < n; i ++) {
			System.out.println(Arrays.toString(square[i]));
		}
	}

}
