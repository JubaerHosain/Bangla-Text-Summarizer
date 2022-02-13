package my.library;

import java.util.Map;
import java.util.TreeMap;

public class Trie {
     private class TrieNode {
        boolean endOfWord;
        Map<Character, TrieNode> children;
        public TrieNode() {
            children = new TreeMap<>();
            endOfWord = false;
        }
    }

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

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
        currentNode.endOfWord = true;
    }

    public boolean contains(String word) {
        TrieNode currentNode = this.root;
        for(char ch: word.toCharArray()) {
            TrieNode node = currentNode.children.get(ch);
            if(node == null) {
                return false;
            }
            currentNode = node;
        }
        return currentNode.endOfWord;
    }

    private boolean remove(TrieNode currentNode, String word, int index) {
        if(index == word.length()) {
            if(!currentNode.endOfWord) {
                return false;
            }
            currentNode.endOfWord = false;
            return currentNode.children.size() == 0;
        }

        char ch = word.charAt(index);
        TrieNode node = currentNode.children.get(ch);
        if(node == null) {
            return false;
        }

        // recursion
        if(remove(node, word, index+1)) {
            currentNode.children.remove(ch);
            return (currentNode.children.size() == 0) && !currentNode.endOfWord;
        }

        return false;
    }

    public void remove(String word) {
        remove(this.root, word, 0);
    }
}
