import java.util.Arrays;
import java.util.Scanner;

public class MagicSquares {

	public static void main(String[] args) {
		Scanner security = new Scanner(System.in);
		System.out.print("Enter an odd integer: ");
		int n = security.nextInt();
		int[][] Lucas = new int[n][n];
		security.close();
		int x = n - 1;
		int y = n/2;
		for (int i = 1; i <= Math.pow(n, 2); i ++) {
			if (i == 1) {
				Lucas[x][y] = i;
			}
			else if (x == n && y == n && Lucas[0][0] != 0) {
				x = n - 2;
				y = n - 1;
				Lucas[x][y] = i;
			}
			else if (x > n - 1 && y > n - 1) {
				x = 0;
				y = 0;
				Lucas[x][y] = i;
			}
			else if (x > n - 1) {
				x = 0;
				Lucas[x][y] = i;
			}
			else if (y > n - 1) {
				y = 0;
				Lucas[x][y] = i;
			}
			else if (Lucas[x][y] != 0) {
				x -= 2;
				y --;
				Lucas[x][y] = i;
			}
			else if (Lucas[x][y] == 0) {
				Lucas[x][y] = i;
			}
			x ++;
			y ++;
		}
		for (int i = 0; i < n; i ++) {
			System.out.println(Arrays.toString(Lucas[i]));
		}
	}

}
