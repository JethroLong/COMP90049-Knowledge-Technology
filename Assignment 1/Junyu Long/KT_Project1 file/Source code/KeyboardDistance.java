import java.util.HashMap;
import java.util.Map;

public class KeyboardDistance {

	// define weights for the three operations and a perfect match
	private final int m = 1; // match
	private final int i = -1; // insertion
	private final int d = -1; // deletion
	private int r; // replacement(keyboard-sensitive)
	
	 private static final Map<Character, String> charSiblings;
	    static {
	        charSiblings = new HashMap<>();
	        charSiblings.put('q', "aw");
	        charSiblings.put('w', "qase");
	        charSiblings.put('e', "wsdr");
	        charSiblings.put('r', "edft");
	        charSiblings.put('t', "rfgy");
	        charSiblings.put('y', "tghu");
	        charSiblings.put('u', "yhji");
	        charSiblings.put('i', "ujko");
	        charSiblings.put('o', "iklp");
	        charSiblings.put('p', "ol");
	        charSiblings.put('a', "zswq");
	        charSiblings.put('s', "azxdew");
	        charSiblings.put('d', "sxcfre");
	        charSiblings.put('f', "dcvgtr");
	        charSiblings.put('g', "fvbhyt");
	        charSiblings.put('h', "gbnjuy");
	        charSiblings.put('j', "hnmkiu");
	        charSiblings.put('k', "jmloi");
	        charSiblings.put('l', "kpo");
	        charSiblings.put('z', "asx");
	        charSiblings.put('x', "zsdc");
	        charSiblings.put('c', "xdfv");
	        charSiblings.put('v', "cfgb");
	        charSiblings.put('b', "vghn");
	        charSiblings.put('n', "bhjm");
	        charSiblings.put('m', "njk");
	    }
	    
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
					int use;
					//decide the value of "r"
					if (origin.charAt(x - 1) != target.charAt(y - 1)) {
						r = r_weight(origin.charAt(x - 1),target.charAt(y - 1));
						use = r;
					}else {
						use = m;
					}
					
					edit[x][y] = Math.max(edit[x - 1][y] + d, Math.max(edit[x][y - 1] + i, edit[x - 1][y - 1] + use));
				}
			}
			return edit[origin.length()][target.length()];
		}
	    
	    private int r_weight(char a,char b) {
	    	String str = charSiblings.get(a);
	    	if(str!= null && str.indexOf(b)>-1 ) {
	    		return 1; //keyboard mis-hit
	    	}else {
	    		return -1;  //ordinary replacement
	    	}
	    }
}
