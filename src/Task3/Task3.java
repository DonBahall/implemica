package Task3;

import java.math.BigInteger;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        BigInteger factorial = BigInteger.valueOf(1);
        factorial = getFactorial(factorial);
        sumOfNumber(factorial);
    }

    /**
     *  Method calculates the factorial of the entered number
     *
     * @param factorial Variable where will be recorded factorial
     * @return factorial value
     */
    public static BigInteger getFactorial(BigInteger factorial) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input factorial ");
        int size = scanner.nextInt();
        for (int i = 1; i < size + 1; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }

    /**
     * Method splits the factorial result into numbers and sums them
     *
     * @param f factorial of number
     */
    private static void sumOfNumber(BigInteger f) {
        int result = 0;
        String sum = String.valueOf(f);
        String[] summ = sum.split("");
        for (String s : summ) {
            result += Integer.parseInt(s);
        }
        System.out.println(result);
    }
}
