import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// Class to represent an item in the knapsack
class Item {
    double value;
    double weight;

    public Item(double value, double weight) {
        this.value = value;
        this.weight = weight;
    }

    // Method to calculate the value-to-weight ratio
    public double valuePerWeight() {
        return value / weight;
    }
}

public class FractionalKnapsack {

    // Function to solve the Fractional Knapsack Problem
    public static double fractionalKnapsack(List<Item> items, double capacity) {
        // Sort items by value-to-weight ratio in descending order
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                return Double.compare(b.valuePerWeight(), a.valuePerWeight());
            }
        });

        double totalValue = 0.0;  // Total value accumulated in the knapsack
        double totalWeight = 0.0; // Total weight accumulated in the knapsack

        // Loop through the sorted items
        for (Item item : items) {
            if (totalWeight + item.weight <= capacity) {
                // If adding the whole item doesn't exceed capacity, take the whole item
                totalWeight += item.weight;
                totalValue += item.value;
            } else {
                // Otherwise, take a fraction of the item to fill the knapsack
                double remainingCapacity = capacity - totalWeight;
                double fraction = remainingCapacity / item.weight;
                totalValue += item.value * fraction;
                totalWeight += item.weight * fraction;
                break; // Knapsack is now full
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking input from the user
        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter value of item " + (i + 1) + ": ");
            double value = scanner.nextDouble();
            System.out.print("Enter weight of item " + (i + 1) + ": ");
            double weight = scanner.nextDouble();
            items.add(new Item(value, weight));
        }

        // Input: Capacity of the knapsack
        System.out.print("Enter the capacity of the knapsack: ");
        double capacity = scanner.nextDouble();

        // Calculate the maximum value of the knapsack
        double maxValue = fractionalKnapsack(items, capacity);

        System.out.printf("\nMaximum value in Knapsack: %.2f\n", maxValue);
        
        scanner.close();
    }
}
