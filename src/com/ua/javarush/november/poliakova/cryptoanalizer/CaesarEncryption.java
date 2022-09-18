package com.ua.javarush.november.poliakova.cryptoanalizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class CaesarEncryption {
    public static HashMap<Character, Integer> alphabet = initialization();
    public static HashMap<Integer, Character> reversedAlphabetMap = reversedAlphabetMap();
    //public int shift;

    public static HashMap<Character, Integer> initialization() {
        HashMap<Character, Integer>map = new HashMap<>();
        map.put('А', 1);
        map.put('Б', 2);
        map.put('В', 3);
        map.put('Г', 4);
        map.put('Д', 5);
        map.put('Е', 6);
        map.put('Ё', 7);
        map.put('Ж', 8);
        map.put('З', 9);
        map.put('И', 10);
        map.put('Й', 11);
        map.put('К', 12);
        map.put('Л', 13);
        map.put('М', 14);
        map.put('Н', 15);
        map.put('О', 16);
        map.put('П', 17);
        map.put('Р', 18);
        map.put('С', 19);
        map.put('Т', 20);
        map.put('У', 21);
        map.put('Ф', 22);
        map.put('Х', 23);
        map.put('Ц', 24);
        map.put('Ч', 25);
        map.put('Ш', 26);
        map.put('Щ', 27);
        map.put('Ы', 28);
        map.put('Ь', 29);
        map.put('Э', 30);
        map.put('Ю', 31);
        map.put('Я', 32);
        map.put('(', 33);
        map.put('.', 34);
        map.put(',', 35);
        map.put('"', 36);
        map.put(':', 37);
        map.put('-', 38);
        map.put('!', 39);
        map.put('?', 40);
        map.put(' ', 41);
        map.put(')', 42);
        return map;
    }
    public static HashMap<Integer, Character> reversedAlphabetMap() {
        reversedAlphabetMap = new HashMap<>();

        for  (char chars: alphabet.keySet())
        {
          reversedAlphabetMap.put(alphabet.get(chars), chars);
        }
        return reversedAlphabetMap;
    }

    public static String encryptTextFromString(String text, int shift) {
        text = text.toUpperCase();
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (alphabet.get(chars[i]) != null)
            {int valueOFInitialChar = alphabet.get(chars[i]);
            int valuePlusShift = valueOFInitialChar + shift;
            if (valuePlusShift > reversedAlphabetMap().size())
            {
                valuePlusShift = (valueOFInitialChar + shift) % reversedAlphabetMap().size();
            }
            chars[i] = reversedAlphabetMap.get(valuePlusShift);}
        }
        String encryptedText = new String(chars);

        return encryptedText;
    }

    public static void encryptTextFromFileToFile(Path path, int shift) {
        File file = new File(creatingPathForEncryptedFile(path));
        Path pathOfEncryptedFile = Path.of(String.valueOf(file));
                if (file.exists()) {
            System.out.println("Encrypted File for " + String.valueOf(path.getFileName()) + " already exist");

        } else {
            try {
                Path file1 = Files.createFile(pathOfEncryptedFile);
            } catch (IOException e) {
                System.out.println("Some problems with file creating");
                ;
            }
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
    public static String creatingPathForEncryptedFile(Path path){
        String allPath = String.valueOf(path);
        String[] words = allPath.split("\\.");
        String newPath = words[0] + "EncryptedText.txt";
        return newPath;
    }
        }




