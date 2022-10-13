package com.ua.javarush.november.poliakova.cryptoanalizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class CaesarDecryptor {
    public static String decryptTextFromString(String text, int shift) {
        text = text.toUpperCase();
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (CaesarEncryptor.alphabet.get(chars[i]) != null) {
                int valueOFInitialChar = CaesarEncryptor.alphabet.get(chars[i]);
                int valueMinusShift = valueOFInitialChar - shift;
                if (valueMinusShift == 0) {
                    valueMinusShift = CaesarEncryptor.reversedAlphabetMap().size();
                } else if (valueMinusShift < 1) {
                    valueMinusShift = CaesarEncryptor.reversedAlphabetMap().size() - shift;
                }
                chars[i] = CaesarEncryptor.reversedAlphabetMap.get(valueMinusShift);
            }
        }
        String decryptedText = new String(chars).toLowerCase();

        return decryptedText;
    }

    public static void decryptTextFromFileToFile(Path path, int shift) {
        File file = new File(creatingPathForDecryptedFile(path));
        Path pathOfEncryptedFile = Path.of(String.valueOf(file));
        if (file.exists()) {
            System.out.println("Decrypted File for " + String.valueOf(path.getFileName()) + " already exist");

        } else {
            try (Scanner scanner = new Scanner(path);
                 FileWriter writer = new FileWriter(String.valueOf(file))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    writer.write(decryptTextFromString(line, shift));
                    writer.write("\n");
                }
            } catch (IOException e) {
                System.out.println("Exception");
                e.printStackTrace();
            }
        }
    }

    public static String creatingPathForDecryptedFile(Path path) {
        String allPath = String.valueOf(path);
        String[] words = allPath.split("\\.");
        String newPath = words[0] + "DecryptedText.txt";
        System.out.println("Path to decrypted file is " + String.valueOf(newPath));
        return newPath;
    }
}
