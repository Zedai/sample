import java.util.Scanner;
import java.util.LinkedList;
public class ReplacementGrammar {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\\z");
		LinkedList<String[]> rules = new LinkedList<String[]>();

		String whole = sc.next();

		while (sc.hasNext()) {
			whole += sc.next();
		}

		String[] delimited = whole.split("\r\n");

		int count = 0; // to count number of rules

		for (String st : delimited) {
			if (st.contains("|")) {
				count++;
			}
			rules.add(st.split("\\|"));
		}

		int size = rules.size();

		String[] blah = rules.get(size - 1);

		String replaced = blah[0];

		for (int i = 0; i < count; i++) {
			String[] rule = rules.get(i);
			if (rule.length > 1) {
				replaced = replaced.replace(rule[0], rule[1]);
			} else {
				replaced = replaced.replace(rule[0], "");
			}
		}
		System.out.println(replaced);
	}
}