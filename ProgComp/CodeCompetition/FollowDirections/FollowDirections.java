import java.util.Scanner;

public class FollowDirections {
	final static int NORTH = 0;
	final static int EAST = 1;
	final static int SOUTH = 2;
	final static int WEST = 3;

	public static void main(String[] args) {
		int direction = NORTH;
		int x = 0;
		int y = 0;

		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			String instruc = sc.nextLine();
			
			if (instruc.charAt(0) == 'M') {
				int move = Integer.parseInt(instruc.substring(5));
				
				switch (direction) {
					case NORTH: y += move; break;
					case EAST: x += move; break;
					case SOUTH: y -= move; break;
					case WEST: x -= move; break;
				}
			} else if (instruc.charAt(0) == 'T') {
				if (instruc.charAt(5) == 'r') {
					direction += 1;
					if (direction == 4) {
						direction = 0;
					}
				} else if (instruc.charAt(5) == 'l') {
					direction -= 1;
					if (direction == -1) {
						direction = 3;
					}
				} else {
					System.out.println("Input incorrect - turn");
				}
			} else {
				System.out.println("Input incorrect - command");
			}
		}
		System.out.println("Final position: (" + x + ", " + y + ")");
	}
}