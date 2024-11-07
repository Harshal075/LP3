import java.util.PriorityQueue;
import java.util.Scanner;

class HuffmanNode implements Comparable<HuffmanNode> {
    char data;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
    }

    public int compareTo(HuffmanNode node) {
        return this.frequency - node.frequency;
    }
}

public class HuffmanEncoding {
    public static void printCodes(HuffmanNode root, String code) {
        if (root == null) {
            return;
        }
        if (root.data != '\0') {
            System.out.println(root.data + ": " + code);
        }
        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of characters: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter character: ");
            char c = scanner.nextLine().charAt(0);
            System.out.print("Enter frequency of " + c + ": ");
            int frequency = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            
            priorityQueue.add(new HuffmanNode(c, frequency));
        }
        
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode merged = new HuffmanNode('\0', left.frequency + right.frequency);
            merged.left = left;
            merged.right = right;
            priorityQueue.add(merged);
        }

        HuffmanNode root = priorityQueue.peek();

        System.out.println("Huffman Codes:");
        printCodes(root, "");

        scanner.close();
    }
}
