// Design a data structure that is initialized with a list of different words. Provided a string, you should determine if you can change exactly one character in this string to match any word in the data structure.

// Implement the MagicDictionary class:

// MagicDictionary() Initializes the object.
// - `void buildDict(String[] dictionary)` Sets the data structure with an array of distinct strings dictionary.
// - `bool search(String searchWord)` Returns true if you can change exactly one
//      character in searchWord to match any string in the data structure, otherwise returns false.

// Example 1:
// Input
// ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
// [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]

// Output
// [null, null, false, true, false, false]

// Explanation
// MagicDictionary magicDictionary = new MagicDictionary();
// magicDictionary.buildDict(["hello", "leetcode"]);
// magicDictionary.search("hello"); // return False
// magicDictionary.search("hhllo"); // We can change the second 'h' to 'e' to match "hello" so we return True
// magicDictionary.search("hell"); // return False
// magicDictionary.search("leetcoded"); // return False
 
// Constraints:
// 1 <= dictionary.length <= 100
// 1 <= dictionary[i].length <= 100
// dictionary[i] consists of only lower-case English letters.
// All the strings in dictionary are distinct.
// 1 <= searchWord.length <= 100
// searchWord consists of only lower-case English letters.
// buildDict will be called only once before search.
// At most 100 calls will be made to search.

class MagicDictionary {

    class TrieNode {
        TrieNode[] links;
        boolean isEnd;

        TrieNode() {
            this.links = new TrieNode[26];
            this.isEnd = false;
        }
    }    

    TrieNode root;
    public MagicDictionary() {
        root = new TrieNode();
    }
    
    private void addWord(String word) {
        TrieNode node = this.root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.links[ch - 'a'] == null) {
                node.links[ch - 'a'] = new TrieNode();
            }
            node = node.links[ch - 'a'];
        }
        node.isEnd = true;
    }

    public void buildDict(String[] dictionary) {
        for (String word: dictionary) {
            addWord(word);
        }
    }
    
    private boolean searchHelper(String word, int pos, boolean changed, TrieNode node) {
        if (pos == word.length()) {
            return changed && node.isEnd;   // if changed, and we have found the whole word
        }
        
        char ch = word.charAt(pos);
        // this char of the search word is present in the Trie
        if (node.links[ch - 'a'] != null) {
            if (searchHelper(word, pos + 1, changed, node.links[ch - 'a'])) {
                return true;
            }
        }
        
        // this specific char of the searchWord is not present in the dictionary
        if (!changed) {
            // then we change the char, and check if from other possible links if we
            // are able to find the search word, we do this recursively
            for (int i = 0; i < 26; i++) {
                if (node.links[i] != null && (i != ch - 'a')) {
                    if (searchHelper(word, pos + 1, true, node.links[i])) {
                        return true;
                    }
                }
            }
        }
        // if already changed it would directly return false
        return false;
    }

    public boolean search(String searchWord) {
        return searchHelper(searchWord, 0, false, this.root);
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */