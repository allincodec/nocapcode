package org.allincodec.java.greedy;

import java.util.*;

/**
 * Grocery Selling Problem - Flipkart Inventory Management
 *
 * <p>In the recent expansion into grocery delivery, Flipkart faces a crucial challenge in effective
 * inventory management. Each grocery item on the platform carries its own expiration date and profit
 * margin, represented by two arrays, A and B of size N. A[i] denotes the time left before expiration
 * date for the ith item, and B[i] denotes profit margin for the ith item. To mitigate potential losses
 * due to expiring items, Flipkart is seeking a strategic solution.</p>
 *
 * <p>The objective is to identify a method to strategically buy certain items, ensuring they are sold
 * before their expiration date, thereby maximizing overall profit. Can you assist Flipkart in developing
 * an innovative approach to optimize their grocery inventory and enhance profitability?</p>
 *
 * <p>Your task is to find the maximum profit one can earn by buying groceries considering that you can
 * only buy one grocery item at a time.</p>
 *
 * <h3>NOTE:</h3>
 * <ul>
 * <li>You can assume that it takes 1 minute to buy a grocery item, so you can only buy the ith grocery
 * item when the current time <= A[i] - 1.</li>
 * <li>You can start buying from day = 0.</li>
 * <li>Return your answer modulo 10^9 + 7.</li>
 * </ul>
 *
 * <h3>Problem Constraints:</h3>
 * <ul>
 * <li>1 <= N <= 10^5</li>
 * <li>1 <= A[i] <= 10^9</li>
 * <li>0 <= B[i] <= 10^9</li>
 * </ul>
 *
 * <h3>Input Format:</h3>
 * <ul>
 * <li>The first argument is an integer array A represents the deadline for buying the grocery items.</li>
 * <li>The second argument is an integer array B represents the profit obtained after buying the grocery items.</li>
 * </ul>
 *
 * <h3>Output Format:</h3>
 * <p>Return an integer denoting the maximum profit you can earn.</p>
 *
 * <h3>Example Input:</h3>
 * <pre>
 * Input 1:
 *  A = [1, 3, 2, 3, 3]
 *  B = [5, 6, 1, 3, 9]
 *
 * Input 2:
 *  A = [3, 8, 7, 5]
 *  B = [3, 1, 7, 19]
 * </pre>
 *
 * <h3>Example Output:</h3>
 * <pre>
 * Output 1:
 *  20
 *
 * Output 2:
 *  30
 * </pre>
 *
 * <h3>Example Explanation:</h3>
 * <pre>
 * Explanation 1:
 *  At time 0, buy item with profit 5.
 *  At time 1, buy item with profit 6.
 *  At time 2, buy item with profit 9.
 *  At time = 3 or after, you can't buy any item, as there is no item with deadline >= 4.
 *  So, total profit that one can earn is 20.
 * </pre>
 */
public class GrocerySellingProblem {

    static class GroceryItem implements Comparable<GroceryItem> {
        int expirationDate;
        int value;

        GroceryItem(int expirationDate, int value) {
            this.expirationDate = expirationDate;
            this.value = value;
        }

        public int getExpirationDate() {
            return expirationDate;
        }

        public int getValue() {
            return value;
        }

        @Override
        public int compareTo(GroceryItem o) {
            return Integer.compare(expirationDate, o.getExpirationDate());
        }
    }

    public static int maxGroceryProfits(List<Integer> A, List<Integer> B) {
        // min heap we will consider to store the profits of grocery items

        List<GroceryItem> groceryItems = new ArrayList<>();
        // sort the items based on their expiration date
        for(int i=0; i < A.size(); i++) {
            groceryItems.add(new GroceryItem(A.get(i), B.get(i)));
        }
        Collections.sort(groceryItems);
        Queue<GroceryItem> giQueue = new PriorityQueue<>(Comparator.comparingInt(GroceryItem::getValue));

        for(GroceryItem groceryItem: groceryItems) {
            // Check if we have capacity to add this item before its deadline
            if(giQueue.size() < groceryItem.getExpirationDate()) {
                giQueue.add(groceryItem);
            } else if(!giQueue.isEmpty() && giQueue.peek().getValue() < groceryItem.getValue()) {
                // Replace the minimum profit item with current higher profit item
                giQueue.poll();
                giQueue.add(groceryItem);
            }
        }
        long sum = giQueue.stream().mapToLong(GroceryItem::getValue).sum();
        return (int)(sum % 1000000007);

    }

    public static void main(String[] args) {
        int maxProfit = maxGroceryProfits(List.of(1, 3, 2, 3, 3), List.of(5, 6, 1, 3, 9));
//        int maxProfit = maxGroceryProfits(List.of(3, 8, 7, 5), List.of(3, 1, 7, 19));
//        int maxProfit = maxGroceryProfits(List.of(1,7,6,2,8,4,4,6,8,2), List.of(8,11,7,7,10,8,7,5,4,9));
        System.out.println("Maximum Profit: " + maxProfit);
    }
}
