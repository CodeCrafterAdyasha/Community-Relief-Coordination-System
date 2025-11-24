package com.communityrelief.io;

import java.util.Scanner;

/**
 * Wraps scanner operations with validation helpers.
 */
public class InputHelper {
    private final Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readNonEmpty(String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine().trim();
        while (value.isEmpty()) {
            System.out.print("Please enter a value: ");
            value = scanner.nextLine().trim();
        }
        return value;
    }

    public int readInt(String prompt, int min, int max) {
        System.out.print(prompt);
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value < min || value > max) {
                    throw new NumberFormatException();
                }
                return value;
            } catch (NumberFormatException ex) {
                System.out.printf("Enter a number between %d and %d: ", min, max);
            }
        }
    }
}

