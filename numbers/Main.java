package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        write your code here
        printResult(getNaturalNumber());

    }

    public static void printResult(int input){
        if (!isNaturalNumber(input)) {
            System.out.println("This number is not natural!");
        } else {
            printIsEven(input);
            printIsBuzzNumber(isBuzzNumber(input), input);
        }

    }

    private static void printIsEven (int num){
        if ((num & 1) == 0) System.out.println("This number is Even.");
        else System.out.println("This number is Odd.");
    }

    private static void printIsBuzzNumber(int isBuzzResult, int input){
        if (isBuzzResult == 0 || isBuzzResult == 1 || isBuzzResult == 2){
            System.out.println("It is a Buzz number.");
            System.out.println("Explanation:");
            if (isBuzzResult == 0){
                System.out.printf("%d ends with 7.%n", input);
            } else if (isBuzzResult == 1){
                System.out.printf("%d is divisible by 7.%n", input);
            } else {
                System.out.println("7 is divisible by 7 and ends with 7.");
            }
        } else {
            System.out.println("It is not a Buzz number.");
            System.out.println("Explanation:");
            System.out.printf("%d is neither divisible by 7 nor does it end with 7.%n", input);
        }
    }

    private static int isBuzzNumber(int number){
        if (number%10 == 7 && number%7 == 0) return 2;
        if (number%10 == 7) return 0; // contains 7 in last position
        if (number%7 == 0) return 1; // divible by 7
        return -1;
    }

    private static boolean isNaturalNumber(int number){
        return (number > 0 && number == Math.floor(number));
    }

    private static int getNaturalNumber(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        return sc.nextInt();
    }
}
