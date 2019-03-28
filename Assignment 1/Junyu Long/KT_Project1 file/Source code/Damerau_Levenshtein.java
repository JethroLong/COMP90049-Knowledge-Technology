/***
 * this class contains a method to calculate the distance between to strings.
 * Besides basic operations in general edit distance algorithm, this time I
 * added the a new operation, namely transposition, to the algorithm, with
 * parameters (m,t,i,d,r)=(+1,+1,-1,-1,-1)
 * 
 * @author Junyu Long
 *
 */
public class Damerau_Levenshtein {

	// define the weights for basic operations
	private final int m = 1; // match
	private final int t = 1; // transposition
	private final int i = -1; // insertion
	private final int d = -1; // deletion
	private final int r = -1; // replacement

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
				int u = (origin.charAt(x-1) == target.charAt(y-1) ? m : r);
				int tempDistance = Math.max(edit[x - 1][y] + d, Math.max(edit[x][y - 1] + i, edit[x - 1][y - 1] + u));
			if(origin.charAt(x-1) != target.charAt(y-1)) {
				// adjacent character checking (transposition)
				if (x > 1 && y > 1 && (origin.charAt(x - 1) == target.charAt(y - 2))
						&& (origin.charAt(x - 2) == target.charAt(y - 1))) {
					tempDistance = Math.max(tempDistance, edit[x - 2][y - 2] + t);
				}
				}
				edit[x][y] = tempDistance;
			}
		}
		return edit[origin.length()][target.length()];
	}

}
