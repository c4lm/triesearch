package com.c4lm;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    @SneakyThrows
    private static String loadFile(String path) {
        return Files.readString(Paths.get(path));
    }


    public static void main(String[] args) {
        var words = loadFile("src/com/c4lm/words_alpha.txt").replace("\r", "").split("\\n");
        var root = new TrieNode();
        for (var word : words) {
            root.insertWord(word);
        }


        var scanInput = new Scanner(System.in);
        do {
            System.out.println("Input prefix, type DONE to end: ");
            String input = scanInput.nextLine();
            if (input.equals("DONE")) {
                break;
            }
            System.out.println(String.format("Looking for words by prefix %s, found: %s", input, root.searchWord(input)));
        } while (true);


    }

}
