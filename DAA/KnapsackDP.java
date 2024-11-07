import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KnapsackDP {
    static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        // Initialize the dynamic programming table
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Find the items included in the knapsack
        List<Integer> itemsIncluded = new ArrayList<>();
        int w = capacity;
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                itemsIncluded.add(i - 1); // Store the index of the item included
                w -= weights[i - 1];
            }
        }

        // Print the items included in the knapsack
        System.out.println("Items included in the knapsack:");
        for (int i = itemsIncluded.size() - 1; i >= 0; i--) {
            int itemIndex = itemsIncluded.get(i);
            System.out.println("Item " + (itemIndex + 1) + " (Weight: " + weights[itemIndex] + ", Value: " + values[itemIndex] + ")");
        }

        return dp[n][capacity];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();
        int[] weights = new int[n];
        int[] values = new int[n];

        System.out.println("Enter the weights of items:");
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }

        System.out.println("Enter the values of items:");
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }

        System.out.print("Enter the knapsack capacity: ");
        int capacity = scanner.nextInt();

        int maxValue = knapsack(weights, values, capacity);
        System.out.println("Maximum value that can be obtained: " + maxValue);

        scanner.close();
    }
}
