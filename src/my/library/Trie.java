package my.library;

import java.util.Map;
import java.util.TreeMap;

public class Trie {
    private class TrieNode {
        int count;
        Map<Character, TrieNode> children;
        public TrieNode() {
            children = new TreeMap<>();
            count = 0;
        }
    }

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    /** add word into trie */
    public void add(String word) {
        TrieNode currentNode = this.root;
        for(char ch: word.toCharArray()) {
            TrieNode node = currentNode.children.get(ch);
            if(node == null) {
                node = new TrieNode();
                currentNode.children.put(ch, node);
            }
            currentNode = node;
        }
        currentNode.count += 1;
    }

    /** check a words contains in the trie or not */
    public boolean contains(String word) {
        TrieNode currentNode = this.root;
        for(char ch: word.toCharArray()) {
            TrieNode node = currentNode.children.get(ch);
            if(node == null) {
                return false;
            }
            currentNode = node;
        }
        return currentNode.count > 0;
    }

    /** returns number of times a word pushed into the trie */
    public int getCount(String word) {
        TrieNode currentNode = this.root;
        for(char ch: word.toCharArray()) {
            TrieNode node = currentNode.children.get(ch);
            if(node == null) {
                return 0;
            }
            currentNode = node;
        }
        return currentNode.count;
    }

    /** remove a word from the trie(recursive) */
    private boolean remove(TrieNode currentNode, String word, int index) {
        if(index == word.length()) {
            if(currentNode.count > 0) {
                currentNode.count--;
            }
            return currentNode.children.size() == 0 && currentNode.count <= 0;
        }

        char ch = word.charAt(index);
        TrieNode node = currentNode.children.get(ch);
        if(node == null) {
            return false;
        }

        // recursion
        if(remove(node, word, index+1)) {
            currentNode.children.remove(ch);
            return (currentNode.children.size() == 0) && currentNode.count <= 0;
        }

        return false;
    }

    /** remove a word from the trie */
    public void remove(String word) {
        remove(this.root, word, 0);
    }
}
