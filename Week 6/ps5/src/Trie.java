import java.util.ArrayList;

public class Trie {

    // Wildcards
    final char WILDCARD = '.';

    private class TrieNode {
        TrieNode[] presentChars = new TrieNode[62];
        boolean isEndOfWord;
    }

    TrieNode root;

    public Trie() {
        // Initialize root curr
        root = new TrieNode();
    }

    /**
     * Inserts string s into the Trie.
     *
     * @param s string to insert into the Trie
     */
    void insert(String s) {
        TrieNode curr = root;
        for (int i = 0; i < s.length(); i++) {
            int index = getIndex(s.charAt(i));
            if (curr.presentChars[index] == null) {
                curr.presentChars[index] = new TrieNode();
            }
            curr = curr.presentChars[index];
        }
        curr.isEndOfWord = true;
    }

    /**
     * Checks whether string s exists inside the Trie or not.
     *
     * @param s string to check for
     * @return whether string s is inside the Trie
     */
    boolean contains(String s) {
        TrieNode curr = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = getIndex(c);
            if (curr.presentChars[index] == null) {
                return false;
            }
            curr = curr.presentChars[index];
        }
        return curr != null && curr.isEndOfWord;
    }

    private int getIndex(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a';
        } else if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 26;
        } else if (c >= '0' && c <= '9') {
            return c - '0' + 52;
        } else {
            return 62;
        }
    }

    private char getChar(int index) {
        if (index < 26) {
            return (char) ('a' + index);
        } else if (index < 52) {
            return (char) ('A' + (index - 26));
        } else {
            return (char) ('0' + (index - 52));
        }
    }

    /**
     * Searches for strings with prefix matching the specified pattern sorted by lexicographical order. This inserts the
     * results into the specified ArrayList. Only returns at most the first limit results.
     *
     * @param s       pattern to match prefixes with
     * @param results array to add the results into
     * @param limit   max number of strings to add into results
     */
    void prefixSearch(String s, ArrayList<String> results, int limit) {
        TrieNode node = root;

        int sLength = s.length();

        for (int i = 0; i < sLength; i++) {
            char currCharacter = s.charAt(i);

            //if the character is .
            if (currCharacter == WILDCARD) {
                String remainder = s.substring(i + 1);
                for (int j = 0; j < node.presentChars.length; j++) {
                    if (node.presentChars[j] != null) {
                        StringBuilder currString = new StringBuilder(s.substring(0, i));
                        currString.append(getChar(j));
                        prefixSearchHelper(results, limit, node.presentChars[j], remainder, currString);
                    }
                }
                return;
            } else {
                // character is not .
                int index = getIndex(currCharacter);
                if (node.presentChars[index] == null) {
                    return;
                }
                node = node.presentChars[index];
            }
        }

        prefixSearchHelper(results, limit, node, "", new StringBuilder(s));
    }

    void prefixSearchHelper(ArrayList<String> results, int limit, TrieNode node, String remainder, StringBuilder targetString) {
        if (node != null && node.isEndOfWord && results.size() < limit && remainder.length() == 0) {
            results.add(targetString.toString());
        }

        if (results.size() == limit) {
            return;
        }

        if (remainder.length() != 0) {
            char currCharacter = remainder.charAt(0);

            if (currCharacter == WILDCARD) {
                for (int i = 0; i < node.presentChars.length; i ++) {
                    if (node.presentChars[i] != null) {
                        char d = getChar(i);
                        targetString.append(d);
                        prefixSearchHelper(results, limit, node.presentChars[i], remainder.substring(1), targetString);
                        targetString.deleteCharAt(targetString.length() - 1);
                    }
                }
            } else {
                int index = getIndex(currCharacter);
                if (node.presentChars[index] != null) {
                    targetString.append(currCharacter);
                    prefixSearchHelper(results, limit, node.presentChars[index], remainder.substring(1), targetString);
                }
            }
        } else {
            // case of empty remainder
            for (int i = 0; i < node.presentChars.length; i++) {
                if (node.presentChars[i] != null) {
                    char c = getChar(i);
                    targetString.append(c);
                    prefixSearchHelper(results, limit, node.presentChars[i], "", targetString);
                    targetString.deleteCharAt(targetString.length() - 1);
                }
            }
        }
    }


    // Simplifies function call by initializing an empty array to store the results.
    // PLEASE DO NOT CHANGE the implementation for this function as it will be used
    // to run the test cases.
    String[] prefixSearch(String s, int limit) {
        ArrayList<String> results = new ArrayList<String>();
        prefixSearch(s, results, limit);
        return results.toArray(new String[0]);
    }


    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("peter");
        t.insert("piper");
        t.insert("picked");
        t.insert("a");
        t.insert("peck");
        t.insert("of");
        t.insert("pickled");
        t.insert("peppers");
        t.insert("pepppito");
        t.insert("pepi");
        t.insert("pik");

        String[] result1 = t.prefixSearch("pe", 10);
        String[] result2 = t.prefixSearch("pe.", 10);
        // result1 should be:
        // ["peck", "pepi", "peppers", "pepppito", "peter"]
        // result2 should contain the same elements with result1 but may be ordered arbitrarily
    }
}
