package com.ua.javarush.november.poliakova.cryptoanalizer;

import java.io.File;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainClass {

    static int choose = -1;
    static boolean moreActions = true;

    public static void main(String[] args) throws InputMismatchException {


        while (moreActions) {
            System.out.println("Hello!\n" +
                    "Please choose what do you want to do:\n" +
                    "Encrypt text by Caesar method - enter 1\n" +
                    "Decrypt encrypted by Caesar method text - enter 2\n" +
                    "Decrypt text by Brute Force method - enter 3\n" +
                    "Exit - enter 4");

            switch (chooseInScanner(4)) {
                case 1:
                    System.out.println("You have chosen encryption text by Caesar method\n" +
                            "Do you want encrypt text from: \n" +
                            "file (enter 1)\n" +
                            "console (enter 2)");
                    if (chooseInScanner(2) == 2) {
                        String tfc = textFromConsole();
                        int shift = chooseShiftInAlphabet();
                        System.out.println("was entered: \n" +
                                tfc + "\n" +
                                "encrypted text: \n" +
                                CaesarEncryptor.encryptTextFromString(tfc, shift));
                    } else {
                        Path pathToFile = readPathToFileFromConsole();
                        int shift = chooseShiftInAlphabet();
                        CaesarEncryptor.encryptTextFromFileToFile(pathToFile, shift); //need to print the path to new file
                    }
                    moreActions = doMoreActions();
                    break;
                case 2:
                    System.out.println("You have chosen decryption text by Caesar method");
                    System.out.println("Do you want decrypt text from: \n" +
                            "file (enter 1)\n" +
                            "console (enter 2)");
                    if (chooseInScanner(2) == 2) {
                        String tfc = textFromConsole();
                        int shift = chooseShiftInAlphabet();

                        System.out.println("was entered: \n" + tfc+ "\n"
                                           + "encrypted text: \n" +
                                          CaesarDecryptor.decryptTextFromString(tfc, shift));
                    } else {
                        Path pathToFile = readPathToFileFromConsole();
                        int shift = chooseShiftInAlphabet();
                        CaesarDecryptor.decryptTextFromFileToFile(pathToFile, shift);
                        //need to print the path to new file
                    }
                    moreActions = doMoreActions();
                    break;
                case 3:
                    System.out.println("You have chosen decryption text by Brute Force method");
                    Path pathToFile = readPathToFileFromConsole();
                    BruteForceDecryptor.decryptTextByBruteForce(pathToFile);
                    moreActions = doMoreActions();
                    break;
                case 4:
                    System.out.println("Exit");
                    moreActions = false;
                    break;
            }

        }
    }

    public static int chooseInScanner(int options) {
        Scanner scan = new Scanner(System.in);
        try {
            choose = scan.nextInt();
            while (choose > options || choose < 1) {
                System.out.println("You have entered incorrect number, please enter from 1 up " + options);
                choose = scan.nextInt();
            }
        } catch (InputMismatchException i) {
            System.out.println("You have entered not number");
        }
        return choose;
    }

    public static String textFromConsole() {
        Scanner scan = new Scanner(System.in);
        String text = null;
        System.out.println("Please enter your text");
        boolean textExist = false;
        while (!textExist) {
            text = scan.nextLine();
            if (text.isEmpty()) {
                System.out.println("You've entered nothing\n" +
                        "Please enter your text");
            } else {
                textExist = true;
            }
        }
        return text;
    }

    public static int chooseShiftInAlphabet() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter shift in alphabet");
        int shift = -1;
        while (shift < 0) {
            try {
                shift = scan.nextInt();
            } catch (InputMismatchException i) {
                System.out.println("You've entered incorrect shift");
            }
            if (shift > CaesarEncryptor.alphabet.size()) {
                return shift % CaesarEncryptor.alphabet.size();
            }
            else if (shift < 0) {
                System.out.println("You've entered incorrect shift, please enter shift one more time");
            }
        }
        return shift;

    }

    public static Path readPathToFileFromConsole() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter file path in format D:\\...\\fileName.txt");
        Path path = Path.of(scan.nextLine());
        File file = new File(String.valueOf(path));
        while (file.length() == 0) {
            System.out.println("Given file is empty\n" +
                    "Do you want to give another file?\n" +
                    "Yes - enter 1\n" +
                    "No - enter 2");
            int answer = chooseInScanner(2);
            if (answer == 2) {
                break;
            } else if (answer == 1) {
                System.out.println("Please enter file path:");
                Path path2 = Path.of(scan.nextLine());
                return path2;
            }

        }
        return path;
    }

    public static boolean doMoreActions() {
        System.out.println("Do you want to perform one more action?\n" +
                "Yes - enter 1\n" +
                "No - enter 2");
        int answer = chooseInScanner(2);
        if (answer == 2) {
            System.out.println("End");
            return false;
        } else return true;
    }
}




