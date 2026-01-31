package numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        start();
    }

    public static void start(){
        System.out.println("Welcome to Amazing Numbers!\n");
        instructions();

        while (true) {
            String[] values = request();
            if (values.length == 1) {
                long number;
                if (values[0].isEmpty()) instructions();
                else {
                    try{
                        number = Long.parseLong(values[0]);
                        if (!isNaturalNumber(number)) throw new NumberFormatException();
                        if (number == 0) break;
                        else properties(number);

                    } catch (NumberFormatException e) {
                        System.out.println("The first parameter should be a natural number or zero.");
                        System.out.println();
                    }
                }
            } else if (values.length == 2) {
                long start;
                long end; // exclusive

                try {
                    start = Long.parseLong(values[0]);
                    if (!isNaturalNumber(start)) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    System.out.println();
                    continue;
                }
                try {
                    end = Long.parseLong(values[1]);
                    if (!isNaturalNumber(end)) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    System.out.println("second parameter should be a natural number");
                    System.out.println();
                    continue;
                }

                properties(start, end);
            }
        }
        System.out.println("Goodbye!");
    }

    private static long getDivisor(long num) {
        long primeiroDigito = Character.getNumericValue(String.valueOf(num).charAt(0)) * 10L;
        long segundDigito = num %10;
        return primeiroDigito + segundDigito;
    }

    private static void properties(long start, long end){
        for (long i = start; i < start+end; i++){
            List<String> properties = new ArrayList<>();
            if (isBuzz(i)) properties.add("buzz");
            if (isDuck(i)) properties.add("duck");
            if (isPalindromic(i)) properties.add("palindromic");
            if (isGapful(i)) properties.add("gapful");
            if (isEven(i)) properties.add("even");
            else properties.add("odd");

            System.out.println("             "+i + " is "+String.join(", ", properties));
        }
        System.out.println();
    }

    private static void properties(long num) {
        System.out.printf("Properties of %d%n", num);
        System.out.printf("%12s: %b%n", "even", isEven(num));
        System.out.printf("%12s: %b%n", "odd", !isEven(num));
        System.out.printf("%12s: %b%n", "buzz", isBuzz(num));
        System.out.printf("%12s: %b%n", "duck", isDuck(num));
        System.out.printf("%12s: %b%n", "palindromic", isPalindromic(num));
        System.out.printf("%12s: %b%n%n", "gapful", isGapful(num));
    }

    private static boolean isGapful(long num) {
        return num >= 100 && num % getDivisor(num) == 0;
    }
    private static boolean isEven(long num){
        return (num & 1) == 0;
    }
    private static boolean isBuzz(long num){
        return num % 10 == 7 || num % 7 == 0;
    }
    private static boolean isDuck(long num){
        while (num > 0){
            long tempValue = num%10;
            num /= 10;
            if (tempValue == 0) return true;
        }
        return false;
    }
    private static boolean isPalindromic(long num){
        String value = String.valueOf(num);

        int start = 0;
        int end = value.length() - 1;

        while (start < end) {
            if (!Objects.equals(value.charAt(start), value.charAt(end))) return false;
            start++;
            end--;
        }
        return true;
    }
    private static boolean isNaturalNumber(long number){
        return (number >= 0 && number == Math.floor(number));
    }

    private static void instructions(){
        String menu = """
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be processed;
                - separate the parameters with one space;
                - enter 0 to exit.
                """;
        System.out.println(menu);
    }

    private static String[] request(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a request: ");
        String input = sc.nextLine();
        System.out.println();

        return input.split(" ");
    }
}
