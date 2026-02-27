package org.allincodec.java.graphs;

import org.allincodec.java.graphs.traversal.BFS;
import org.allincodec.java.graphs.traversal.DFS;

public class DirectedGraphMain {

    public static void main(String[] args) {
        DirectedGraph dg = new DirectedGraph(6);
        dg.addEdge(0, 1);
        dg.addEdge(0, 4);
        dg.addEdge(4, 3);
        dg.addEdge(1, 2);
        dg.addEdge(1, 5);
        dg.addEdge(3, 2);

        BFS bfs = new BFS(dg);

        System.out.println(">> Adjacency List of the Graph <<");
        dg.printGraph();
        System.out.println();

        bfs.breadthFirstTraversal(0);

        DFS dfs = new DFS();
        System.out.print("DFS Traversal: ");
        dfs.depthFirstSearch(dg, 4, new boolean[dg.vertices()]);
    }
}
