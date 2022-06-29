import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Problem2 {

    // TC : O(Exponential)
    // SC : O(n)
    List<String> result;

    public String[] expand(String s) {

        if (s == null || s.length() == 0) return new String[]{};

        int n = s.length();

        List<List<Character>> blocks = new ArrayList<>();
        int i = 0;
        while (i != s.length()) {
            List<Character> block = new ArrayList<>();
            if (s.charAt(i) == '{') {
                i++;
                while (s.charAt(i) != '}') {
                    if (s.charAt(i) != ',') {
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            } else {
                block.add(s.charAt(i));
            }
            Collections.sort(block);
            blocks.add(block);
            i++;
        }
        //System.out.println("blocks size " + blocks.size());
        result = new ArrayList<>();

        backTracking(blocks, new StringBuilder(), 0);
        String[] answer = new String[result.size()];

        for (int j = 0; j < result.size(); j++) {
            answer[j] = result.get(j);
        }
        return answer;
    }

    private void backTracking(List<List<Character>> blocks, StringBuilder sb, int idx) {
        //base case
        if (idx == blocks.size()) {
            result.add(sb.toString());
            return;
        }

        //logic
        List<Character> temp = blocks.get(idx);
        for (char c : temp) {
            sb.append(c);
            //recurse
            backTracking(blocks, sb, idx + 1);
            //remove
            sb.setLength(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        Problem2 problem2 = new Problem2();
        System.out.println("Final output " + Arrays.toString(problem2.expand("{a,b}c{d,e}f")));
    }
}
