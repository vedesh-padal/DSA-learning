// MEDIUM - HARD
// string, depth-first-search, trie, design

/*
    Design a data structure that supports adding new words and finding if a
    string matches any previously added string.

    Implement the WordDictionary class:

    WordDictionary() Initializes the object.
    void addWord(word) Adds word to the data structure, it can be matched later.
    bool search(word) Returns true if there is any string in the data structure
    that matches word or false otherwise. word may contain dots '.' where dots
    can be matched with any letter.

    Example:
    Input:
    ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
    [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
    Output:
    [null,null,null,null,false,true,true,true]
    Explanation
    WordDictionary wordDictionary = new WordDictionary();
    wordDictionary.addWord("bad");
    wordDictionary.addWord("dad");
    wordDictionary.addWord("mad");
    wordDictionary.search("pad"); // return False
    wordDictionary.search("bad"); // return True
    wordDictionary.search(".ad"); // return True
    wordDictionary.search("b.."); // return True

    Constraints:
    1 <= word.length <= 25
    word in addWord consists of lowercase English letters.
    word in search consist of '.' or lowercase English letters.
    There will be at most 2 dots in word for search queries.
    At most 10^4 calls will be made to addWord and search.
*/

class WordDictionary {

    class TrieNode {
        TrieNode[] links;
        boolean isEnd;

        TrieNode() {
            this.links = new TrieNode[26];
            this.isEnd = false;
        }
    }

    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();    
    }
    
    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.links[ch - 'a'] == null) {
                node.links[ch - 'a'] = new TrieNode();
            }
            node = node.links[ch - 'a'];
        }
        node.isEnd = true;
    }
    
    public boolean search(String word) {
        // this will be a recursive function
        // word, index of the char to search, TrieNode
        return searchHelper(word, 0, this.root);
    }

    private boolean searchHelper(String word, int pos, TrieNode node) {
        if (pos == word.length()) {
            return node.isEnd;
        }

        char ch = word.charAt(pos);
        if (ch != '.') {
            int ind = ch - 'a';
            if (node.links[ind] != null && searchHelper(word, pos + 1, node.links[ind])) {
                return true;
            }
        }
        else {  // that means this char is a '.'
            // check all 26 chars [ links ]
            for (TrieNode link: node.links) {
                if (link != null && searchHelper(word, pos + 1, link)) {
                    return true;
                }
            }
        }

        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */