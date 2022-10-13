package com.ua.javarush.november.poliakova.cryptoanalizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class BruteForceDecryptor {

    public static void decryptTextByBruteForce(Path path) {
        File file = new File(creatingPathForDecryptedByBrunoForceFile(path));
        if (file.exists()) {
            System.out.println("Decrypted File for " + String.valueOf(path.getFileName()) + " already exist");
        } else {try (Scanner scannerFromFile = new Scanner(path);
                FileWriter writer = new FileWriter(String.valueOf(file))) {
                String line = scannerFromFile.nextLine();
                int result = 2;
                int shift = 0;
                while (result != 1) {
                    System.out.println(CaesarDecryptor.decryptTextFromString(line, shift));
                    System.out.println("Do you understand what is written?\n" +
                            "yes - enter 1\n" +
                            "no - enter 2");
                    result = MainClass.chooseInScanner(2);

                    if (result == 1) {
                        writer.write(CaesarDecryptor.decryptTextFromString(line, shift));
                        writer.write("\n");
                        while (scannerFromFile.hasNextLine()) {
                            line = scannerFromFile.nextLine();
                            writer.write(CaesarDecryptor.decryptTextFromString(line, shift));
                            writer.write("\n");
                        }
                        System.out.println("Path to decrypted file is " + String.valueOf(file));
                    }
                    else {
                    shift++;}
                }

            } catch (IOException e) {
                System.out.println("Exception");
                e.printStackTrace();
            }
        }
    }

    public static String creatingPathForDecryptedByBrunoForceFile(Path path){
        String allPath = String.valueOf(path);
        String[] words = allPath.split("\\.");
       String newPath = words[0] + "DecryptedByBrunoForce.txt";
        return newPath;
    }
}
