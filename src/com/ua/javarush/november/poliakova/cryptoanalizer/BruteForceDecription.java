package com.ua.javarush.november.poliakova.cryptoanalizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class BruteForceDecription {

    public static void decryptTextbyBruteForce(Path path) {
        File file2 = new File(String.valueOf(path.getParent()), "DecryptedByBrunoForce.txt");
        System.out.println("Path of encrypted file is: " + String.valueOf(file2));
        Path pathOfEncryptedFile = Path.of(String.valueOf(file2));
        try {
            Path file1 = Files.createFile(pathOfEncryptedFile);
        } catch (IOException e) {
            System.out.println("Some problems with file creating");;
        }

        try (Scanner scannerFromFile = new Scanner(path);
             FileWriter writer = new FileWriter(String.valueOf(file2))) {
            Scanner scanner = new Scanner(System.in);
            String line = scannerFromFile.nextLine();
            int result = 2;
            int shift = 1;
            while (result != 1) {
                System.out.println(CaesarDecryption.decryptTextFromString(line,shift));
                System.out.println("Do you understand what is written?\n" +
                        "yes - enter 1\n" +
                        "no - enter 2");
                result = scanner.nextInt();
                if (result == 1) {writer.write(CaesarDecryption.decryptTextFromString(line, shift));}
                shift++;
            }

             { while (scannerFromFile.hasNextLine()) {
                 line = scannerFromFile.nextLine();
                writer.write(CaesarDecryption.decryptTextFromString(line, shift));
                writer.write("\n");
            }
            }

        } catch (IOException e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
    }


}
