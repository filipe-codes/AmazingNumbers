package numbers;

import java.util.Scanner;

public class Main {
    static boolean isEven, isOdd, isBuzz, isDuck;

    public static void main(String[] args) {
        printResult(getNaturalNumber());

    }

    public static void printResult(int input){
        if (!isNaturalNumber(input)) {
            System.out.println("This number is not natural!");
        } else {
            processNumber(input);
            System.out.printf("Properties of %d%n", input);
            System.out.printf("%12s: %b%n","even", isEven);
            System.out.printf("%12s: %b%n","odd", isOdd);
            System.out.printf("%12s: %b%n","buzz", isBuzz);
            System.out.printf("%12s: %b%n","duck", isDuck);
        }

    }

    private static void processNumber(int number){
        setIsDuck(number);
        setBuzzNumber(number);
        setEvenOdd(number);
    }

    private static void setIsDuck(int number){
        while (number > 0){
            int tempValue = number%10;
            number /= 10;
            if (tempValue == 0) {isDuck = true; break;}

        }
    }

    private static void setEvenOdd(int number){
        if ( (number & 1) == 0) isEven = true;
        else isOdd = true;
    }

    private static void setBuzzNumber(int number){
        if (number%10 == 7 || number%7 == 0) isBuzz = true;
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
