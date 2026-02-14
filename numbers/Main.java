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
        System.out.println("Bem-vindo ao Números Incríveis!\n");
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

    private static void printResult(List<String> properties, long i) {
        String result = String.join(", ", properties);
        System.out.printf("%14d é %s%n", i, result);
    }

    private static long getDivisor(long num) {
        long primeiroDigito = Character.getNumericValue(String.valueOf(num).charAt(0)) * 10L;
        long ultimoDigito = num % 10;
        return primeiroDigito + ultimoDigito;
    }

    private static void properties(long start, long end){
        for (long i = start; i < start + end; i++){
            List<String> properties = new ArrayList<>();
            if (isBuzz(i)) properties.add("buzz");
            if (isDuck(i)) properties.add("pato");
            if (isPalindromic(i)) properties.add("palíndromo");
            if (isGapful(i)) properties.add("gapful");
            if (isSpy(i)) properties.add("espião");
            if (isSunny(i)) properties.add("ensolarado");
            if (isSquare(i)) properties.add("quadrado");
            if (isEven(i)) properties.add("par");
            else properties.add("ímpar");

            printResult(properties, i);
        }
        System.out.println();
    }

    private static void properties(long start, long end, String[] propertyParams){
        List<String> includeProps = new ArrayList<>();
        List<String> excludeProps = new ArrayList<>();
        List<String> invalidProps = new ArrayList<>();
        List<String[]> mutuallyExclusive = new ArrayList<>();

        // Processa os parâmetros de propriedades
        for (String param : propertyParams) {
            boolean exclude = param.startsWith("-");
            String cleanValue = exclude ? param.substring(1).toUpperCase() : param.toUpperCase();

            NumberProperty property = null;
            for (NumberProperty prop : NumberProperty.values()) {
                if (prop.name().equalsIgnoreCase(cleanValue)) {
                    property = prop;
                    break;
                }
            }

            if (property != null) {
                if (exclude) {
                    excludeProps.add(cleanValue);
                } else {
                    includeProps.add(cleanValue);
                }
            } else {
                invalidProps.add(param);
            }
        }

        // Verifica propriedades inválidas
        if (!invalidProps.isEmpty()) {
            if (invalidProps.size() == 1) {
                System.out.printf("A propriedade [%s] está errada.%n", invalidProps.get(0).toUpperCase());
            } else {
                String props = String.join(", ", invalidProps).toUpperCase();
                System.out.printf("As propriedades [%s] estão erradas.%n", props);
            }
            System.out.println("Propriedades disponíveis: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SUNNY, SQUARE, HAPPY, SAD, EVEN, ODD]");
            System.out.println();
            return;
        }

        // Verifica propriedades mutuamente exclusivas
        if (includeProps.contains("EVEN") && includeProps.contains("ODD")) {
            mutuallyExclusive.add(new String[]{"EVEN", "ODD"});
        }
        if (includeProps.contains("DUCK") && includeProps.contains("SPY")) {
            mutuallyExclusive.add(new String[]{"DUCK", "SPY"});
        }
        if (includeProps.contains("SUNNY") && includeProps.contains("SQUARE")) {
            mutuallyExclusive.add(new String[]{"SUNNY", "SQUARE"});
        }
        if (includeProps.contains("HAPPY") && includeProps.contains("SAD")) {
            mutuallyExclusive.add(new String[]{"HAPPY", "SAD"});
        }
        if (excludeProps.contains("EVEN") && excludeProps.contains("ODD")) {
            mutuallyExclusive.add(new String[]{"-EVEN", "-ODD"});
        }
        if (excludeProps.contains("DUCK") && excludeProps.contains("SPY")) {
            mutuallyExclusive.add(new String[]{"-DUCK", "-SPY"});
        }
        if (excludeProps.contains("SUNNY") && excludeProps.contains("SQUARE")) {
            mutuallyExclusive.add(new String[]{"-SUNNY", "-SQUARE"});
        }
        if (excludeProps.contains("HAPPY") && excludeProps.contains("SAD")) {
            mutuallyExclusive.add(new String[]{"-HAPPY", "-SAD"});
        }

        // Verifica conflitos entre inclusão e exclusão
        for (String prop : includeProps) {
            if (excludeProps.contains(prop)) {
                mutuallyExclusive.add(new String[]{prop, "-" + prop});
            }
        }

        if (!mutuallyExclusive.isEmpty()) {
            if (mutuallyExclusive.size() == 1) {
                System.out.printf("A solicitação contém propriedades mutuamente exclusivas: [%s, %s]%n",
                        mutuallyExclusive.get(0)[0], mutuallyExclusive.get(0)[1]);
            } else {
                System.out.print("A solicitação contém propriedades mutuamente exclusivas: ");
                for (int i = 0; i < mutuallyExclusive.size(); i++) {
                    System.out.printf("[%s, %s]", mutuallyExclusive.get(i)[0], mutuallyExclusive.get(i)[1]);
                    if (i < mutuallyExclusive.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
            System.out.println("Não existem números com essas propriedades.");
            System.out.println();
            return;
        }

        // Busca números com as propriedades especificadas
        long i = start;
        int count = 0;
        while (count < end) {
            List<String> properties = new ArrayList<>();
            if (isBuzz(i)) properties.add("buzz");
            if (isDuck(i)) properties.add("pato");
            if (isPalindromic(i)) properties.add("palíndromo");
            if (isGapful(i)) properties.add("gapful");
            if (isSpy(i)) properties.add("espião");
            if (isSunny(i)) properties.add("ensolarado");
            if (isSquare(i)) properties.add("quadrado");
            if (isHappy(i)) properties.add("feliz");
            if (!isHappy(i)) properties.add("triste");
            if (isEven(i)) properties.add("par");
            else properties.add("ímpar");

            boolean matchesInclude = true;
            for (String prop : includeProps) {
                if (!properties.contains(prop.toLowerCase())) {
                    matchesInclude = false;
                    break;
                }
            }

            boolean matchesExclude = true;
            for (String prop : excludeProps) {
                if (properties.contains(prop.toLowerCase())) {
                    matchesExclude = false;
                    break;
                }
            }

            if (matchesInclude && matchesExclude) {
                printResult(properties, i);
                count++;
            }
            i++;
        }
        System.out.println();
    }

    private static void properties(long num) {
        System.out.printf("Propriedades de %d%n", num);
        System.out.printf("%12s: %b%n", "par", isEven(num));
        System.out.printf("%12s: %b%n", "ímpar", !isEven(num));
        System.out.printf("%12s: %b%n", "buzz", isBuzz(num));
        System.out.printf("%12s: %b%n", "pato", isDuck(num));
        System.out.printf("%12s: %b%n", "palíndromo", isPalindromic(num));
        System.out.printf("%12s: %b%n", "gapful", isGapful(num));
        System.out.printf("%12s: %b%n", "espião", isSpy(num));
        System.out.printf("%12s: %b%n", "ensolarado", isSunny(num));
        System.out.printf("%12s: %b%n", "quadrado", isSquare(num));
        System.out.printf("%12s: %b%n", "feliz", isHappy(num));
        System.out.printf("%12s: %b%n", "triste", !isHappy(num));

        System.out.println();
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
            long tempValue = num % 10;
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

    private static boolean isSpy(long number){
        if (number < 10) return false;
        long soma = 0;
        long produto = 1;
        while (number > 0) {
            long temp = number % 10;
            number /= 10;
            soma += temp;
            produto *= temp;
        }
        return soma == produto;
    }

    private static boolean isSunny(long number) {
        if (number < 100) return false;
        return isSquare(number + 1);
    }

    private static boolean isSquare(long number) {
        if (number < 10) return false;
        double sqrt = Math.sqrt(number);
        return sqrt == Math.floor(sqrt);
    }

    private static boolean isHappy(long number) {
        if (number < 10) return false;

        Set<Long> seen = new HashSet<>();
        long original = number;

        while (number != 1 && !seen.contains(number)) {
            seen.add(number);
            long result = 0;

            while (number > 0) {
                long digit = number % 10;
                result += digit * digit;
                number /= 10;
            }
            number = result;
        }

        return number == 1;
    }

    private static void instructions(){
        String menu = """
                Solicitações suportadas:
                - digite um número natural para conhecer suas propriedades;
                - digite dois números naturais para obter as propriedades da lista:
                  * o primeiro parâmetro representa o número inicial;
                  * o segundo parâmetro mostra quantos números consecutivos serão processados;
                - dois números naturais e propriedades para buscar;
                - uma propriedade precedida por menos não deve estar presente nos números;
                - separe os parâmetros com um espaço;
                - digite 0 para sair.
                """;
        System.out.println(menu);
    }

    private static String[] request(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite uma solicitação: ");
        String input = sc.nextLine();
        System.out.println();

        return input.split(" ");
    }
}
