import java.util.Scanner;

public class ClockAngles {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ClockAngles test = new ClockAngles();

		int n = sc.nextInt();

		System.out.println(n);

		for (int i = 0; i < n; i++) {
			String[] time = sc.next().split(":");
			double hrs = Double.parseDouble(time[0]);
			double min = Double.parseDouble(time[1]);
			double sec = Double.parseDouble(time[2]);

			double hrsMin = Math.abs(test.hrAngle(hrs, min, sec) - test.minAngle(min, sec));
			if (hrsMin > 180) {
				hrsMin = 360 - hrsMin;
			}

			double hrsSec = Math.abs(test.hrAngle(hrs, min, sec) - test.secAngle(sec));
			if (hrsSec > 180) {
				hrsSec = 360 - hrsSec;
			}

			double minSec = Math.abs(test.minAngle(min, sec) - test.secAngle(sec));

			System.out.printf("%.2f ", hrsMin);
			System.out.printf("%.2f ", hrsSec);
			System.out.printf("%.2f\n", minSec);
		}
	}

	public double hrAngle(double hrs, double min, double sec) {
		return ((hrs % 12) + (min + (sec / 60)) / 60) * 30;
	}

	public double minAngle(double min, double sec) {
		return (min + (sec / 60)) * 6;
	}
	
	public double secAngle(double sec) {
		return sec * 6;
	}

}