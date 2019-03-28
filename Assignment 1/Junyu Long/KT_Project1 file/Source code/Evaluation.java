import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class Evaluation {
	public static void main(String[] args) {
		ArrayList<String> predict1 = new ArrayList<String>();
		ArrayList<String> predict2 = new ArrayList<String>();
		ArrayList<String> predict3 = new ArrayList<String>();
		ArrayList<String> predict4 = new ArrayList<String>();
		ArrayList<String> correct = new ArrayList<String>();

		Scanner predictInput1 = null;
		Scanner predictInput2 = null;
		Scanner predictInput3 = null;
		Scanner predictInput4 = null;

		Scanner correctInput = null;
		int count1 = 0, count2 = 0, count3 = 0, count4 = 0;
		try {
			predictInput1 = new Scanner(new FileInputStream("Modified_EditDistance(+1,-1,-1,-1).txt"));
			predictInput2 = new Scanner(new FileInputStream("Damerau_Levenshtein(+1, +1, -1, -1, -1).txt"));
			predictInput3 = new Scanner(new FileInputStream("KeyboardDistance(+1,-1,-1,-1||+1).txt"));
			predictInput4 = new Scanner(new FileInputStream("Levenshtein_EditDistance(0,+1,+1,+1).txt"));
			correctInput = new Scanner(new FileInputStream("wiki_correct.txt"));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		while (predictInput1.hasNextLine()) {
			String entry = predictInput1.nextLine().trim();
			entry = entry.split(" ")[0];
			predict1.add(entry);
		}
		while (predictInput2.hasNextLine()) {
			String entry = predictInput2.nextLine().trim();
			entry = entry.split(" ")[0];
			predict2.add(entry);
		}
		while (predictInput3.hasNextLine()) {
			String entry = predictInput3.nextLine().trim();
			entry = entry.split(" ")[0];
			predict3.add(entry);
		}
		while (predictInput4.hasNextLine()) {
			String entry = predictInput4.nextLine().trim();
			entry = entry.split(" ")[0];
			predict4.add(entry);
		}
		while (correctInput.hasNextLine()) {
			String entry = correctInput.nextLine().trim();
			correct.add(entry);
		}
		predictInput1.close();
		predictInput2.close();
		predictInput3.close();
		predictInput4.close();
		correctInput.close();

		System.out.println(
				"size are: " + predict1.size() + " " + predict2.size() + " " + predict3.size() + " " + predict4.size());

		for (int i = 0; i < correct.size(); i++) {
			if (correct.get(i).equals(predict1.get(i))) {
				count1++;
			}
		}
		for (int i = 0; i < correct.size(); i++) {
			if (correct.get(i).equals(predict2.get(i))) {
				count2++;
			}
		}
		for (int i = 0; i < correct.size(); i++) {
			if (correct.get(i).equals(predict3.get(i))) {
				count3++;
			}
		}
		for (int i = 0; i < correct.size(); i++) {
			if (correct.get(i).equals(predict4.get(i))) {
				count4++;
			}
		}

		DecimalFormat dFormat = new DecimalFormat("######0.00");
		String accuracy1 = dFormat.format((double) count1 * 100 / predict1.size()) + "%";
		String accuracy2 = dFormat.format((double) count2 * 100 / predict2.size()) + "%";
		String accuracy3 = dFormat.format((double) count3 * 100 / predict3.size()) + "%";
		String accuracy4 = dFormat.format((double) count4 * 100 / predict4.size()) + "%";

		System.out.println("Modified_EditDistance(+1,-1,-1,-1) accuracy : " + accuracy1 + " with " + count1
				+ " correct out of " + correct.size());
		System.out.println("Damerau_Levenshtein(+1, +1, -1, -1, -1) accuracy : " + accuracy2 + " with " + count2
				+ " correct out of " + correct.size());
		System.out.println("KeyboardDistance(+1,-1,-1,-1||+1) accuracy : " + accuracy3 + " with " + count3
				+ " correct out of " + correct.size());
		System.out.println("Levenshtein_EditDistance(0,+1,+1,+1) accuracy : " + accuracy4 + " with " + count4
				+ " correct out of " + correct.size());
	}
}