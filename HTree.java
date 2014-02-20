/* Prints a fractal HTree,t aking arguments N (depth of recursion) and theta (angle of branches)
 
    - Uses the Princeton University StdDraw class, available athttp://introcs.cs.princeton.edu/java/stdlib/stdlib.jar
 */

public class HTree {

	public static void tree(int n, double x, double y, double size, double theta, double deltaTheta) {
		if (n == 0) return;


		double x0 = x + size/Math.sqrt(2)*Math.cos(theta);
		double y0 = y + size/Math.sqrt(2)*Math.sin(theta);

		drawTree(x, y, x0, y0, n);

		tree(n-1, x0, y0, size/Math.sqrt(2), theta - deltaTheta, deltaTheta);
		tree(n-1, x0, y0, size/Math.sqrt(2), theta + deltaTheta, deltaTheta);

	}

	public static void drawTree(double x0, double y0, double x1, double y1, int n) {
		StdDraw.setPenRadius(Math.pow(n,1.3)/1200);
		StdDraw.setPenColor(15*n, 150+5*n, 200-10*n);
		StdDraw.line(x0, y0, x1, y1);
	}


	public static void main(String[] arguments) {
		int N = Integer.parseInt(arguments[0]);
		double deltaTheta = Double.parseDouble(arguments[1]) / 180 * Math.PI;
		tree (N, 0.5, 0.0, 0.4, Math.PI/2, deltaTheta);
	}
}