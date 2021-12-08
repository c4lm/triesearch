package com.c4lm;

import lombok.SneakyThrows;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TrieNodeTest {

    @Test
    public void trieReturnsEmptyListWhenWordIsNotFound() {
        var words = List.of("aaa", "bbb");
        TrieNode root = new TrieNode();
        for (var word : words) {
            root.insertWord(word);
        }
        assertThat(root.searchWord("ccc").size(), is(0));
    }

    @Test
    public void trieReturnsWordsWhenTheyAreFound() {
        var words = List.of("foo", "bar", "foobar");
        TrieNode root = new TrieNode();
        for (var word : words) {
            root.insertWord(word);
        }
        assertThat(root.searchWord("foo"), hasItem("foo"));
        assertThat(root.searchWord("foo"), hasItem("foobar"));
    }


    @Test
    public void trieReturnsEmptyListWhenTrieIsEmpty() {
        TrieNode root = new TrieNode();
        assertThat(root.searchWord("ccc").size(), is(0));
    }

    @Test
    public void trieWorksForAllWordsSupplied() {
        TrieNode root = new TrieNode();
        var words = loadFile("src/com/c4lm/words_alpha.txt").replace("\r", "").split("\\n");
        for (var word : words) {
            root.insertWord(word);
        }
        for (var word : words) {
            assertThat(root.searchWord(word), hasItem(word));
        }
    }

    @SneakyThrows
    private static String loadFile(String path) {
        return Files.readString(Paths.get(path));
    }

}