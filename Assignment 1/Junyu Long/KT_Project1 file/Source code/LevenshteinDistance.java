
public class LevenshteinDistance {
	// define weights for the three operations and a perfect match
	private final int m = 0; // match
	private final int i = 1; // insertion
	private final int d = 1; // deletion
	private final int r = 1; // replacement

	public int getDistance(String origin, String target) {
		int edit[][] = new int[origin.length() + 1][target.length() + 1];
		for (int x = 0; x <= origin.length(); x++) {
			edit[x][0] = d * x;
		}
		for (int y = 0; y <= target.length(); y++) {
			edit[0][y] = i * y;
		}
		for (int x = 1; x <= origin.length(); x++) {
			for (int y = 1; y <= target.length(); y++) {
				// To decide whether the last characters match
				int u = (origin.charAt(x - 1) == target.charAt(y - 1) ? m : r);
				edit[x][y] = Math.min(edit[x - 1][y] + d, Math.min(edit[x][y - 1] + i, edit[x - 1][y - 1] + u));
			}
		}
		return edit[origin.length()][target.length()];
	}

}
