package com.ua.javarush.november.poliakova.cryptoanalizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class CaesarDecryption {
    public static String decryptTextFromString(String text, int shift) {
        text = text.toUpperCase();
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (CaesarEncryption.alphabet.get(chars[i]) != null) {
                int valueOFInitialChar = CaesarEncryption.alphabet.get(chars[i]);
                //System.out.println("valueOFInitialChar " + valueOFInitialChar);
                int valueMinusShift = valueOFInitialChar - shift;
                //System.out.println("valueMinusShift" + valueMinusShift);
                if (valueMinusShift == 0) {
                    valueMinusShift = CaesarEncryption.reversedAlphabetMap().size();
                }
                else if (valueMinusShift < 1) {
                    valueMinusShift = CaesarEncryption.reversedAlphabetMap().size() - shift;
                   //  System.out.println("CaesarEncryption.reversedAlphabetMap().size() + shift" + valueMinusShift);
                }
                chars[i] = CaesarEncryption.reversedAlphabetMap.get(valueMinusShift);
            }
        }
            String decryptedText = new String(chars).toLowerCase();

        return decryptedText;
    }
    public static void decryptTextFromFileToFile(Path path, int shift) {
        File file1 = new File(String.valueOf(path.getParent()), "DecryptedText.txt");
        System.out.println("Path of decrypted file is: " + String.valueOf(file1));
        Path pathOfEncryptedFile = Path.of(String.valueOf(file1));
        try {
            Path file2 = Files.createFile(pathOfEncryptedFile);
        } catch (IOException e) {
            System.out.println("Some problems with file creating");;
        }

        try (Scanner scanner = new Scanner(path);
             FileWriter writer = new FileWriter(String.valueOf(file1))) {
            { while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                writer.write(decryptTextFromString(line,shift));
                writer.write("\n");
            }
            }

        } catch (IOException e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
    }
}
