import java.util.Scanner;

public class fibonacciRecursive {

    // Recursive method to calculate Fibonacci number
    public static int fibonacciRecursive(int n, int[] stepCount) {
        stepCount[0]++; // Increment step count
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1, stepCount) + fibonacciRecursive(n - 2, stepCount);
    }

    // Iterative method to calculate Fibonacci number
    public static int fibonacciIterative(int n, int[] stepCount) {
        if (n <= 1) {
            stepCount[0]++; // Single step for small cases
            return n;
        }

        int a = 0, b = 1, result = 0;
        for (int i = 2; i <= n; i++) {
            stepCount[0]++; // Increment step count
            result = a + b;
            a = b;
            b = result;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Taking input for n
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();

        // Recursive calculation
        int[] recursiveStepCount = {0};
        int fibonacciRecursive = fibonacciRecursive(n, recursiveStepCount);
        System.out.println("Recursive Fibonacci of " + n + " = " + fibonacciRecursive);
        System.out.println("Recursive Steps Count: " + recursiveStepCount[0]);

        // Iterative calculation
        int[] iterativeStepCount = {0};
        int fibonacciIterative = fibonacciIterative(n, iterativeStepCount);
        System.out.println("Iterative Fibonacci of " + n + " = " + fibonacciIterative);
        System.out.println("Iterative Steps Count: " + iterativeStepCount[0]);

        scanner.close();
    }
}
