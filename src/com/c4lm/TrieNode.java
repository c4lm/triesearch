package com.c4lm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieNode {
    public String value;
    public boolean isWord = false;
    public Map<Character, TrieNode> children;

    public TrieNode() {
        this.children = new HashMap<>();
    }

    List<String> searchWord(String prefix) {
        var root = this;
        for (var c : prefix.toCharArray()) {
            if (!root.children.containsKey(c)) {
                return List.of();
            } else {
                root = root.children.get(c);
            }
        }
        return root.traverseToEnd();

    }

    private List<String> traverseToEnd() {
        var words = new ArrayList<String>();
        if (this.children.isEmpty() && value != null) {
            return List.of(value);
        }

        for (var childrenPrefixKey : this.children.keySet()) {
            words.addAll(this.children.get(childrenPrefixKey).traverseToEnd());
        }
        if (this.isWord && value != null) {
            words.add(this.value);
        }
        return words;
    }

    void insertWord(String word) {
        var root = this;
        for (int i = 0; i < word.length(); ++i) {
            if (!root.children.containsKey(word.charAt(i))) {
                var tmp = new TrieNode();
                tmp.value = word.substring(0, i);
                root.children.putIfAbsent(word.charAt(i), tmp);
            }
            root = root.children.get(word.charAt(i));
        }
        root.value = word;
        root.isWord = true;
    }
}
