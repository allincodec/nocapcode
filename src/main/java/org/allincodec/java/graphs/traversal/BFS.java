package org.allincodec.java.graphs.traversal;

import org.allincodec.java.graphs.DirectedGraph;
import org.allincodec.java.graphs.DoublyLinkedList;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    private final DirectedGraph dg;

    public BFS(DirectedGraph dg) {
        this.dg = dg;

    }
    public void breadthFirstTraversal(int source) {
        int vertices = dg.vertices();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertices];
        queue.offer(source);
        visited[source] = true;

        System.out.print("BFS Traversal: ");

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");
            DoublyLinkedList<Integer> adjacencyList = dg.adjacencyList().get(current);
            DoublyLinkedList<Integer>.Node node = adjacencyList.head();
            while (node != null) {
                if (!visited[node.data()]) {
                    queue.offer(node.data());
                    visited[node.data()] = true;
                }
                node = node.nextNode();
            }
        }
        System.out.println();
    }
}
