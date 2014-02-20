public class Song implements Runnable {

	static String twinkle = "nn//  /0..,,mmn0//..,,m0//..,,m0nn//  /0..,,mmn";
	private static char character;

	public void run() {
		for (int i = 0; i < twinkle.length(); i++) {
			character = twinkle.charAt(i);
			try {
				Thread.currentThread().sleep(500 + (int) Math.pow(-1, i) * 50);
			} catch (InterruptedException ie) {
				System.out.println("interrupted");
			}
		}
	}

	public static synchronized char getChar() {
		char temp = character;
		character = '0';
		return temp;
	}
}