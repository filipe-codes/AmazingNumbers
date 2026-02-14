package numbers;

import java.util.*;

public class Main {

    public enum NumberProperty {
        BUZZ,
        DUCK,
        PALINDROMIC,
        GAPFUL,
        SPY,
        EVEN,
        ODD,
        SUNNY,
        SQUARE,
        HAPPY,
        SAD
    }

    public static void main(String[] args) {
        start();
    }

    public static void start(){
        System.out.println("Bem-vindo ao Amazing Numbers!\n");
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
                        System.out.println("O primeiro parâmetro deve ser um número natural ou zero.");
                        System.out.println();
                    }
                }
            } else if (values.length == 2) {
                long start;
                long end;

                try {
                    start = Long.parseLong(values[0]);
                    if (!isNaturalNumber(start)) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    System.out.println("O primeiro parâmetro deve ser um número natural ou zero.");
                    System.out.println();
                    continue;
                }
                try {
                    end = Long.parseLong(values[1]);
                    if (!isNaturalNumber(end)) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    System.out.println("O segundo parâmetro deve ser um número natural.");
                    System.out.println();
                    continue;
                }

                properties(start, end);
            }
            else if (values.length >= 3) {
                long start;
                long end;

                try {
                    start = Long.parseLong(values[0]);
                    if (!isNaturalNumber(start)) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    System.out.println("O primeiro parâmetro deve ser um número natural ou zero.");
                    System.out.println();
                    continue;
                }
                try {
                    end = Long.parseLong(values[1]);
                    if (!isNaturalNumber(end)) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    System.out.println("O segundo parâmetro deve ser um número natural.");
                    System.out.println();
                    continue;
                }

                String[] propertyParams = Arrays.copyOfRange(values, 2, values.length);
                properties(start, end, propertyParams);
            }
        }
        System.out.println("Até logo!");
    }

    private static void properties(long num) {
        System.out.printf("Propriedades do número %d%n", num);
        System.out.printf("%12s: %b%n", "par", isEven(num));
        System.out.printf("%12s: %b%n", "ímpar", !isEven(num));
        System.out.printf("%12s: %b%n", "buzz", isBuzz(num));
        System.out.printf("%12s: %b%n", "duck", isDuck(num));
        System.out.printf("%12s: %b%n", "palindrômico", isPalindromic(num));
        System.out.printf("%12s: %b%n", "gapful", isGapful(num));
        System.out.printf("%12s: %b%n", "spy", isSpy(num));
        System.out.printf("%12s: %b%n", "sunny", isSunny(num));
        System.out.printf("%12s: %b%n", "quadrado perfeito", isSquare(num));
        System.out.printf("%12s: %b%n", "feliz", isHappy(num));
        System.out.printf("%12s: %b%n", "triste", !isHappy(num));

        System.out.println();
    }

    private static void instructions(){
        String menu = """
                Requisições suportadas:
                - digite um número natural para conhecer suas propriedades;
                - digite dois números naturais para obter a lista de propriedades:
                  * o primeiro parâmetro representa o número inicial;
                  * o segundo parâmetro indica quantos números consecutivos serão processados;
                - dois números naturais e propriedades para buscar;
                - uma propriedade precedida por menos (-) não deve estar presente nos números;
                - separe os parâmetros com um espaço;
                - digite 0 para sair.
                """;
        System.out.println(menu);
    }

    private static String[] request(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite uma requisição: ");
        String input = sc.nextLine();
        System.out.println();

        return input.split(" ");
    }
}
