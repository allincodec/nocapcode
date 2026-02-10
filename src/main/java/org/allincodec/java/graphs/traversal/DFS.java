package org.allincodec.java.graphs.traversal;

import org.allincodec.java.graphs.DirectedGraph;
import org.allincodec.java.graphs.DoublyLinkedList;

import java.util.LinkedList;
import java.util.Queue;

public class DFS {

    public void depthFirstSearch(DirectedGraph dg, int source, boolean[] visited) {
        System.out.print("| " + source + " | => ");
        visited[source] = true;
        DoublyLinkedList<Integer>.Node node = dg.adjacencyList().get(source).head();
        while(node!=null) {
            if(!visited[node.data()]) {
                depthFirstSearch(dg, node.data(), visited);
            }
            node = node.nextNode();
        }
    }
}
