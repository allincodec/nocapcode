package org.allincodec.java.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a directed graph data structure where nodes are connected by directed edges.
 * A directed graph is a set of nodes where each edge has an orientation, meaning it
 * goes from a source node to a target node.
 *
 * This class provides methods for adding nodes and edges, checking connectivity,
 * and retrieving information about the graph structure.
 *
 * It is designed to be used in applications such as dependency resolution,
 * network routing, and graph-based analysis.
 *
 */
public class DirectedGraph {
    int vertices;
    List<DoublyLinkedList<Integer>> adjacencyList;

    public int vertices() {
        return vertices;
    }

    public List<DoublyLinkedList<Integer>> adjacencyList() {
        return adjacencyList;
    }

    public DirectedGraph(int vertices) {
        this.vertices = vertices;
        this.adjacencyList = new ArrayList<>(vertices);

        for(int i = 0; i < vertices; i++) {
            adjacencyList.add(new DoublyLinkedList<>());
        }
    }

    public void addEdge(int src, int dest) {
        if(src < vertices && dest < vertices ) {
            adjacencyList.get(src).insertAtTail(dest);
        }
    }

    public void printGraph() {
        for(int i = 0; i < vertices; i++) {
            DoublyLinkedList<Integer> list = adjacencyList.get(i);
            if(!list.isEmpty()) {
                System.out.print("| " + i + " | =>");
                DoublyLinkedList<Integer>.Node node = list.head();
                while (node != null) {
                    System.out.print(" [" + node.data() + "] -> ");
                    node = node.nextNode();
                }
                System.out.println("null");
            } else {
                System.out.println("| " + i + " | => null");
            }
        }
    }

}
