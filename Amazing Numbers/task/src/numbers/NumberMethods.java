package numbers;

import java.util.*;

public class NumberMethods {

    private boolean isOdd;
    private boolean isEven;
    private boolean isGapful;
    private boolean isPalindromic;
    private boolean isDuck;
    private boolean isBuzzFull;
    private boolean isSunny;
    private boolean isSquare;
    private boolean isSpy;
    private boolean isJumping;
    private boolean isSad;
    private boolean isHappy;

    public NumberMethods(long number) {
        this.isOdd = !isEvenOdd(number);
        this.isEven = isEvenOdd(number);
        this.isGapful = isGapful(number);
        this.isPalindromic = isPalindromic(number);
        this.isDuck = isDuck(number);
        this.isBuzzFull = isBuzzFull(number);
        this.isSpy = isSpy(number);
        this.isSquare = isSquare(number);
        this.isSunny = isSunny(number);
        this.isJumping = isJumping(number);
        this.isSad = !isHappy(number);
        this.isHappy = isHappy(number);
    }

    boolean isHappy(long number) {
        Set<Long> seenNumbers = new HashSet<>();

        while (number != 1 && !seenNumbers.contains(number)) {
            seenNumbers.add(number);
            long sumOfSquares = 0;
            while (number > 0) {
                long digit = number % 10;
                sumOfSquares += digit * digit;
                number /= 10;
            }
            number = sumOfSquares;
        }


        return number == 1;
    }

    boolean isJumping(long number) {

        String numStr = Long.toString(number);

        if (numStr.length() == 1) {
            return true;
        }

        for (int i = 1; i < numStr.length(); i++) {
            int digit1 = Character.getNumericValue(numStr.charAt(i - 1));
            int digit2 = Character.getNumericValue(numStr.charAt(i));


            int diff = Math.abs(digit1 - digit2);


            if (diff != 1) {
                return false;
            }
        }

        return true;

    }

    public boolean isSpy(long number) {

        long digit = 0;
        long sum = 0;
        long product = 1;

        while (number > 0) {
            digit = number % 10;

            sum += digit;

            product *= digit;
            number = number / 10;
        }

        return sum == product;

    }

    public boolean isGapful(long number) {
        if (number < 100) {
            return false;
        }
        String numStr = Long.toString(number);
        int firstDigit = Character.getNumericValue(numStr.charAt(0));
        int lastDigit = Character.getNumericValue(numStr.charAt(numStr.length() - 1));

        int concatenated = Integer.parseInt(firstDigit + "" + lastDigit);

        return number % concatenated == 0;
    }

    public boolean isPalindromic(long number) {
        String str1 = "" + number;
        StringBuilder str2 = new StringBuilder(str1);

        return str1.contentEquals(str2.reverse());
    }

    public boolean isDuck(long number) {
        return ("" + number).contains("0");
    }

    public boolean isBuzzFull(long number) {
        return isBuzzEnd(number) || isBuzzDivisibleSeven(number) || isBuzzDivisibleAndEnd(number);
    }

    public boolean isEvenOdd(long number) {
        return number % 2 == 0;
    }

    private boolean isBuzzEnd(long number) {
        return number % 10 == 7;
    }

    private boolean isBuzzDivisibleSeven(long number) {
        return number % 7 == 0;
    }

    private boolean isBuzzDivisibleAndEnd(long number) {
        return number % 10 == 7 && number % 7 == 0;
    }

    public boolean isSunny(long number) {
        return isSquare(number + 1);
    }

    public boolean isSquare(long number) {
        double sqrt = Math.sqrt(number);
        return sqrt == Math.floor(sqrt);
    }

    @Override
    public String toString() {

        StringJoiner stringJoiner = new StringJoiner(", ");

        if (isEven) stringJoiner.add(Constants.EVEN);
        if (isOdd) stringJoiner.add(Constants.ODD);
        if (isDuck) stringJoiner.add(Constants.DUCK);
        if (isGapful) stringJoiner.add(Constants.GAPFUL);
        if (isPalindromic) stringJoiner.add(Constants.PALINDROMIC);
        if (isBuzzFull) stringJoiner.add(Constants.BUZZ);
        if (isSquare) stringJoiner.add(Constants.SQUARE);
        if (isSunny) stringJoiner.add(Constants.SUNNY);
        if (isSpy) stringJoiner.add(Constants.SPY);
        if (isJumping) stringJoiner.add(Constants.JUMPING);
        if (isSad) stringJoiner.add(Constants.SAD);
        if (isHappy) stringJoiner.add(Constants.HAPPY);

        return stringJoiner.toString();
    }

}
