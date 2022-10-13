package com.ua.javarush.november.poliakova.cryptoanalizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class CaesarEncryptor {
    public static HashMap<Character, Integer> alphabet = initialization();
    public static HashMap<Integer, Character> reversedAlphabetMap = reversedAlphabetMap();
    //public int shift;

    public static HashMap<Character, Integer> initialization() {
        String alphabetString = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЫЬЭЮЯ.,\":-!? ";
        char[] alphabetсhars = alphabetString.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 1; i <= alphabetсhars.length; i++) {
            map.put(alphabetсhars[i - 1], i);
        }
        return map;
    }

    public static HashMap<Integer, Character> reversedAlphabetMap() {
        reversedAlphabetMap = new HashMap<>();

        for (char chars : alphabet.keySet()) {
            reversedAlphabetMap.put(alphabet.get(chars), chars);
        }
        return reversedAlphabetMap;
    }

    public static String encryptTextFromString(String text, int shift) {
        text = text.toUpperCase();
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (alphabet.get(chars[i]) != null) {
                int valueOFInitialChar = alphabet.get(chars[i]);
                int valuePlusShift = valueOFInitialChar + shift;
                if (valuePlusShift > reversedAlphabetMap().size()) {
                    valuePlusShift = (valueOFInitialChar + shift) % reversedAlphabetMap().size();
                }
                chars[i] = reversedAlphabetMap.get(valuePlusShift);
            }
        }
        String encryptedText = new String(chars).toLowerCase();
        return encryptedText;
    }

    public static void encryptTextFromFileToFile(Path path, int shift) {
        File file = new File(creatingPathForEncryptedFile(path));
        Path pathOfEncryptedFile = Path.of(String.valueOf(file));
        if (file.exists()) {
            System.out.println("Encrypted File for " + String.valueOf(path.getFileName()) + " already exist");

        } else {
            System.out.println("Path of encrypted file is: " + String.valueOf(file));
            try (Scanner scanner = new Scanner(path);
                 FileWriter writer = new FileWriter(String.valueOf(file))) {
                {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        writer.write(encryptTextFromString(line, shift));
                        writer.write("\n");
                    }
                }

            } catch (IOException e) {
                System.out.println("Exception");
                e.printStackTrace();
            }
        }
    }

    public static String creatingPathForEncryptedFile(Path path) {
        String allPath = String.valueOf(path);
        String[] words = allPath.split("\\.");
        String newPath = words[0] + "EncryptedText.txt";
        return newPath;
    }
}




