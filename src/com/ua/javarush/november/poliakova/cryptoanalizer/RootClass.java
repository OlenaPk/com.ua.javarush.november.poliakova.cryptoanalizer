package com.ua.javarush.november.poliakova.cryptoanalizer;

import java.io.File;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RootClass {
    static int choose = -1;
    public static void main(String[] args) throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello!");
        System.out.println("Please choose what do you want to do:");
        System.out.println("Encrypt text by Caesar method - enter 1");
        System.out.println("Decrypt encrypted by Caesar method text - enter 2");
        System.out.println("Decrypt text by Brute Force method - enter 3");
        System.out.println("Exit - enter 4");

        switch (chooseInScanner(4)) {
            case 1:
                System.out.println("You have chosen encryption text by Caesar method");
                System.out.println("Do you want encrypt text from: \n" +
                        "file (enter 1)\n" +
                        "console (enter 2)");
                if (chooseInScanner(2) == 2)
                {
                    String tfc = textFromConsole();
                    int shift = chooseShiftInAlphabet();
                    if (tfc.isEmpty()) {
                        System.out.println("You've entered nothing");
                    }
                    else {
                    System.out.println("was entered: \n" + tfc);
                    System.out.println("encrypted text: ");
                    System.out.println(CaesarEncryption.encryptTextFromString(tfc, shift));}
                }
                else {
                    Path pathToFile = readPathToFileFromConsole();
                    int shift = chooseShiftInAlphabet();
                    CaesarEncryption.encryptTextFromFileToFile(pathToFile, shift); //need to print the path to new file
                }

                break;
            case 2:
                System.out.println("You have chosen decryption text by Caesar method");
                System.out.println("Do you want encrypt text from: \n" +
                        "file (enter 1)\n" +
                        "console (enter 2)");
                if (chooseInScanner(2) == 2)
                {
                    String tfc = textFromConsole();
                    int shift = chooseShiftInAlphabet();
                    if (tfc.isEmpty()) {
                        System.out.println("You've entered nothing");
                    }
                    else {
                        System.out.println("was entered: \n" + tfc);
                        System.out.println("encrypted text: ");
                        System.out.println(CaesarDecryption.decryptTextFromString(tfc, shift));}
                }
                else {
                    Path pathToFile = readPathToFileFromConsole();
                    int shift = chooseShiftInAlphabet();
                    CaesarDecryption.decryptTextFromFileToFile(pathToFile, shift);//need to print the path to new file
                }
                //
                break;
            case 3:
                System.out.println("You have chosen decryption text by Brute Force method");
                Path pathToFile = readPathToFileFromConsole();
                BruteForceDecription.decryptTextbyBruteForce(pathToFile);
                //
                break;
            case 4:
                System.out.println("Exit");
                break;


        }

    }

    public static int chooseInScanner(int options) {
        Scanner scanner = new Scanner(System.in);
                try {
            choose = scanner.nextInt();
            while (choose > options || choose < 1) {
                System.out.println("You have entered incorrect number, please enter from 1 up " + options);
                choose = scanner.nextInt();
            }
        } catch (InputMismatchException i) {
            System.out.println("You have entered not number");
        }
                return choose;
    }

    public static String textFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your text");
        String text = scanner.nextLine();
        return text;
    }


    public static int chooseShiftInAlphabet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter shift in alphabet");
        int shift = 0;
        try {
            shift = scanner.nextInt();
        } catch (InputMismatchException i) {
            System.out.println("You've entered incorrect shift");
        }
        if (shift > CaesarEncryption.alphabet.size()) {
            return shift % CaesarEncryption.alphabet.size();
        }
        return shift;
    }

    public static Path readPathToFileFromConsole() {
        Scanner scanner = new Scanner(System.in);
        /*System.out.println("Please enter path to file");
        Path path = Path.of(scanner.nextLine());*/
        System.out.println("Please enter file path:");
        Path path = Path.of(scanner.nextLine());
        File file = new File(String.valueOf(path));
        while (file.length() == 0) {
            System.out.println("Given file is empty\n" +
                    "Do you want to give another file?\n" +
                    "Yes - enter 1\n" +
                    "No - enter 2");
            int answer = scanner.nextInt();
            if (answer == 2) {
                break;
            }
            else {
                path = Path.of(scanner.nextLine());

            }

        }
        return path;
    }



}




