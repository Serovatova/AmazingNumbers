package numbers;

import java.util.*;

public class Main {

    static final Scanner scanner = new Scanner(System.in);
    static List<String> wrongProperties = new ArrayList<>();
    static List<String> mutuallyExclusiveProperties = new ArrayList<>();

    static String inputString;
    static Long firstNumber;

    static List<String> paramsList = Arrays.stream(new String[]{
            Constants.BUZZ,
            Constants.DUCK,
            Constants.PALINDROMIC,
            Constants.GAPFUL,
            Constants.SPY,
            Constants.SQUARE,
            Constants.SUNNY,
            Constants.EVEN,
            Constants.ODD,
            Constants.JUMPING,
            Constants.SAD,
            Constants.HAPPY,
            Constants.EXCLUDE_BUZZ,
            Constants.EXCLUDE_DUCK,
            Constants.EXCLUDE_PALINDROMIC,
            Constants.EXCLUDE_GAPFUL,
            Constants.EXCLUDE_SPY,
            Constants.EXCLUDE_SQUARE,
            Constants.EXCLUDE_SUNNY,
            Constants.EXCLUDE_EVEN,
            Constants.EXCLUDE_ODD,
            Constants.EXCLUDE_JUMPING,
            Constants.EXCLUDE_SAD,
            Constants.EXCLUDE_HAPPY
    }).toList();

    static String[][] exclusiveParams = new String[][]{
            {Constants.EVEN, Constants.ODD},
            {Constants.SPY, Constants.DUCK},
            {Constants.SUNNY, Constants.SQUARE},
            {Constants.SAD, Constants.HAPPY},
            {Constants.EXCLUDE_EVEN, Constants.EXCLUDE_ODD},
            {Constants.EXCLUDE_HAPPY, Constants.EXCLUDE_SAD},

//            {Constants.EXCLUDE_EVEN, Constants.EVEN},
//            {Constants.EXCLUDE_ODD, Constants.ODD},
//            {Constants.EXCLUDE_GAPFUL, Constants.GAPFUL},
//            {Constants.EXCLUDE_PALINDROMIC, Constants.PALINDROMIC},
//            {Constants.EXCLUDE_DUCK, Constants.DUCK},
//            {Constants.EXCLUDE_BUZZ, Constants.BUZZ},
//            {Constants.EXCLUDE_SUNNY, Constants.SUNNY},
//            {Constants.EXCLUDE_SQUARE, Constants.SQUARE},
//            {Constants.EXCLUDE_SPY, Constants.SPY},
//            {Constants.EXCLUDE_JUMPING, Constants.JUMPING},
//            {Constants.EXCLUDE_HAPPY, Constants.HAPPY},
    };


    public static void main(String[] args) {

        try (scanner) {
            System.out.println("""
                    Welcome to Amazing Numbers!
                                       
                    Supported requests:
                    - enter a natural number to know its properties;
                    - enter two natural numbers to obtain the properties of the list:
                      * the first parameter represents a starting number;
                      * the second parameter shows how many consecutive numbers are to be printed;
                    - two natural numbers and properties to search for;
                    - a property preceded by minus must not be present in numbers;
                    - separate the parameters with one space;
                    - enter 0 to exit.""");
            System.out.println();

            while (true) {

                inputString = input();

                String[] tokens = inputString.split("\\s", 3);

                if (tokens.length == 1 && tokens[0].equals("0")) {
                    System.out.println(Constants.GOODBYE);
                    break;
                }

                try {
                    numberValidation(Long.parseLong(tokens[0]), Constants.FIRST);
                } catch (NumberFormatException | IllegalParameterException ex) {
                    System.out.printf(Constants.ERROR, Constants.FIRST);

                    continue;
                }

                if (tokens.length > 1) {
                    try {
                        numberValidation(Long.parseLong(tokens[1]), Constants.SECOND);
                    } catch (NumberFormatException | IllegalParameterException ex) {
                        System.out.printf(Constants.ERROR, Constants.SECOND);

                        continue;
                    }
                }

                if (tokens.length > 2) {
                    String[] subArray = tokens[2].split("\\s");

                    for (String str : subArray) {
                        if (!paramsList.contains(str)) {
                            wrongProperties.add(str);
                        }
                    }

                    for (String[] str : exclusiveParams) {

                        if (!str[0].startsWith("-") && tokens[2].contains(str[0])
                                && !str[1].startsWith("-") && tokens[2].contains(str[1])) {
                            mutuallyExclusiveProperties.add(str[0]);
                            mutuallyExclusiveProperties.add(str[1]);
                        }

                    }

                }

                if (!wrongProperties.isEmpty()) {

                    System.out.printf(
                            wrongProperties.size() > 1 ? Constants.WRONG_PROPERTIES : Constants.WRONG_PROPERTY,
                            wrongProperties,
                            paramsList
                    );

                    wrongProperties.clear();
                    continue;
                }

                if (!mutuallyExclusiveProperties.isEmpty()) {
                    System.out.printf(
                            Constants.MUTUALLY_PROPERTIES,
                            mutuallyExclusiveProperties
                    );

                    mutuallyExclusiveProperties.clear();
                    continue;
                }

                firstNumber = Long.parseLong(tokens[0]);

                NumberMethods numberMethods = new NumberMethods(Long.parseLong(tokens[0]));

                String propertiesFirstNumber = "Properties of %d\n\t" +
                        "even: " + numberMethods.isEvenOdd(firstNumber) + "\n\t" +
                        "odd:  " + !numberMethods.isEvenOdd(firstNumber) + "\n\t" +
                        "buzz: " + numberMethods.isBuzzFull(firstNumber) + "\n\t" +
                        "duck: " + numberMethods.isDuck(firstNumber) + "\n\t" +
                        "palindromic: " + numberMethods.isPalindromic(firstNumber) + "\n\t" +
                        "gapful: " + numberMethods.isGapful(firstNumber) + "\n\t" +
                        "spy: " + numberMethods.isSpy(firstNumber) + "\n\t" +
                        "sunny: " + numberMethods.isSunny(firstNumber) + "\n\t" +
                        "square: " + numberMethods.isSquare(firstNumber) + "\n\t" +
                        "jumping: " + numberMethods.isJumping(firstNumber) + "\n\t" +
                        "sad: " + !numberMethods.isHappy(firstNumber) + "\n\t" +
                        "happy: " + numberMethods.isHappy(firstNumber) + "\n\t";


                if (tokens.length == 1) {
                    System.out.printf(propertiesFirstNumber, firstNumber);
                    System.out.println();
                }

                if (tokens.length == 2) {
                    Long secondNumber = Long.parseLong(tokens[1]);

                    for (long i = firstNumber; i < firstNumber + secondNumber; i++) {
                        System.out.printf(Constants.NUMBER_PROPERTIES, i, new NumberMethods(i));
                    }

                }

                if (tokens.length > 2) {
                    long start = Long.parseLong(tokens[0]);
                    long end = Long.parseLong(tokens[1]);

                    String[] subArray = tokens[2].split("\\s");

                    for (long i = start; end > 0; i++) {

                        boolean flag = true;

                        String currentStr = new NumberMethods(i).toString();

                        for (String str : subArray) {
                            if (str.startsWith("-") && currentStr.contains(str.substring(1))
                                    || !str.startsWith("-") && !currentStr.contains(str)) {
                                flag = false;
                                break;
                            }

                        }


                        if (flag) {
                            System.out.printf(Constants.NUMBER_PROPERTIES, i, currentStr);
                            end--;
                        }
                    }
                }
            }
        }

    }

    static String input() {
        String result;
        Scanner scanner = new Scanner(System.in);
        System.out.println(Constants.REQUEST);

        result = scanner.nextLine().toLowerCase();

        return result;
    }

    static boolean numberValidation(long number, String msg) throws IllegalParameterException {
        if (number < 0)
            throw new IllegalParameterException(Constants.ERROR);

        return true;
    }

}
