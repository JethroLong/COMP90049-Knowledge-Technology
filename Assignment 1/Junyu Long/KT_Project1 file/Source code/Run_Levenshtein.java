import java.io.*;
import java.util.*;

public class Run_Levenshtein {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println("program starts at: " + start);

		ArrayList<String> dict = new ArrayList<String>();
		ArrayList<String> misSpell = new ArrayList<String>();
		ArrayList<String> candidate = new ArrayList<String>();
		ArrayList<String> predictResult = new ArrayList<String>();

		LevenshteinDistance ed = new LevenshteinDistance();
		Scanner dictInput = null;
		PrintWriter predictionOutput = null;
		Scanner wiki_misspell_Input = null;
		try {
			dictInput = new Scanner(new FileInputStream("dict.txt"));
			wiki_misspell_Input = new Scanner(new FileInputStream("wiki_misspell.txt"));
			predictionOutput = new PrintWriter(new FileOutputStream("Levenshtein_EditDistance(0,+1,+1,+1).txt", true));
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		while (dictInput.hasNextLine()) {
			String line = dictInput.nextLine();
			dict.add(line.trim());
		}
		while (wiki_misspell_Input.hasNextLine()) {
			String line = wiki_misspell_Input.nextLine();
			misSpell.add(line.trim());
		}
		dictInput.close();
		wiki_misspell_Input.close();

		for (int i = 0; i < misSpell.size(); i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < dict.size(); j++) {
				int temp = ed.getDistance(misSpell.get(i), dict.get(j));
				if (temp < min) {
					min = temp;
					candidate.clear();
					candidate.add(dict.get(j));
				} else if (temp == min) {
					candidate.add(dict.get(j));
				}
			}
			// for tied edit distance, simply consider the first candidate as the best match
			String str = candidate.get(0) + " " + min;
			predictResult.add(str);
			predictionOutput.println(predictResult.get(i));
		}
		long end = System.currentTimeMillis();
		System.out.println("program ends at: " + end);
		long timediff = (end - start);
		System.out.println("time difference is: " + timediff);
		predictionOutput.close();
	}

}
