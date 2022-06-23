package Task1;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the positive number ");
        int numOfBrackets;
        do {
            numOfBrackets = scanner.nextInt();
        } while (numOfBrackets <= 0);
        System.out.println(factorial(2 * numOfBrackets) / (factorial(numOfBrackets) * factorial(numOfBrackets + 1)));
    }

    /**
     * Method counts the number of possible parentheses
     *
     * @param n number of pairs of brackets
     * @return factorial of n
     */
    private static long factorial(int n) {
        if (n == 1) return 1;
        return factorial(n - 1) * n;
    }
}