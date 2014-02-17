// Draws a recursive Pythagorean tree
// Takes 3 arguments - the number of recursive steps to take
//                   - the angle of the tree (in degrees)
//                   - (optional) "instant", which will display the tree in one go
//         - e.g. java PythagoreanTree 12 30
// Requires StdDraw from Princeton University's stdlib library from the CompSci dept.

public class PythagoreanTree {

    // Reads from command line the depth of recursion and angle of tree.
    public static void main(String[] args) {    
        int N = Integer.parseInt(args[0]);
        double angle = Double.parseDouble(args[1])/180*Math.PI;
        if (args.length > 2) {
            if (args[2].equals("instant")) {
                // Starts animation, which stops rendering until the next call
                // of StdDraw.show(0);
                StdDraw.show(0);
            }
        }
        // Starts the recursion with a tilt of 0 and angle as supplied by user.
        tree(N, 0.45, 0.5, 0.1, 0, angle);

        // Renders the tree image from the buffer
        StdDraw.show(0);
    }
    
    public static void tree(int n, double x, double y, double s, double theta, double angle) {
        if (n == 0) return;
        
        // Draws a single square
        drawBox(x, y, s, theta, n);

        // Calculates the bottom-left hand corner, side length and angle of each new square.
		double xChange1 = x + s * Math.sin(theta);   
		double yChange1 = y + s * Math.cos(theta);
		double sChange1 = s * Math.cos(angle);
		double thetaChange1 = theta - angle;
		double xChange2 = x + s * Math.sin(Math.PI / 2.0 - theta) + s * Math.sin(Math.PI - theta) + s*Math.sin(angle)*Math.sin(theta - angle);
		double yChange2 = y + s * Math.sin(-theta) + s * Math.sin(Math.PI / 2.0 - theta) + s*Math.sin(angle)*Math.cos(theta - angle);
		double sChange2 = s * Math.sin(angle);
		double thetaChange2 = theta + Math.PI/2 - angle;
		
        // Recursive call to draw two new squares
        tree(n-1, xChange1, yChange1, sChange1, thetaChange1, angle);
        tree(n-1, xChange2, yChange2, sChange2, thetaChange2, angle);   
    }
    

    // Draws each square based on the coordinates of the bottom left corner of each square,
    // the side length and the angle. (As it tilts, the bottom left corner can really be
    // anywhere, but imagine tracking the bottom left corner of the original square as it tilts)
    public static void drawBox (double x, double y, double s, double theta, int n) {
    		double[] xCoord = new double[4];
            xCoord[0] = x;
            xCoord[1] = x + s * Math.sin(Math.PI / 2.0 - theta);
            xCoord[3] = x + s * Math.sin(Math.PI - theta);
            xCoord[2] = xCoord[1] - xCoord[0] + xCoord[3];
            
            double[] yCoord = new double[4];
            yCoord[0] = y;
            yCoord[1] = y + s * Math.sin(-theta);
            yCoord[3] = y + s * Math.sin(Math.PI / 2.0 - theta);
            yCoord[2] = yCoord[1] - yCoord[0] + yCoord[3];
            
            double[] xCoord1 = new double[4];
            double[] yCoord1 = new double[4];
            
            // Inverts the whole tree so it grows both up and down.
            for(int i = 0; i < 4; i++) {
            	xCoord1[i] = 1 - xCoord[i];
            	yCoord1[i] = 1 - yCoord[i];
            }

            // Random colouring I decided to add to the three.
            // Based on the depth of recursion, so limits the depth of
            // recursion achievable. I should probably find a better way of
            // doing this at some point.
            int red = 15 * n;
            if (red > 255) {
                for (int i = 1; red > 255; i++) 
                    red = 15 * (n - 17 * i);
            }
            int green = 180 + 5 * n;
            if (green > 255) {
                for (int i = 1; green > 255; i++)
                    green = 180 + 5 * (n - 51 * i);
            }
            int blue = 200 - 10 * n;         
            if (blue < 0) {
                for (int i = 1; blue < 0; i++)
                    blue = 200 - 10 * (n - 25 * i);
            }   
            StdDraw.setPenColor(red, green, blue);
            StdDraw.filledPolygon(xCoord, yCoord);
            StdDraw.filledPolygon(xCoord1, yCoord1);
    }
}
