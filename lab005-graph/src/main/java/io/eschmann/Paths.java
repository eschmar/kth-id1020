package io.eschmann;

/**
 * Created by eschmar on 05/12/16.
 */
import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.DataSource;
import se.kth.id1020.Vertex;

import java.util.ArrayList;
import java.util.Stack;

import edu.princeton.cs.algs4.Queue;

public class Paths {
    public static void main(String[] args) {
        Graph g = DataSource.load();
        // work on g

        // 3.1 - Connected Graph
        println("");
        println("This graph has " + g.numberOfVertices() + " vertices and " + g.numberOfEdges() + " edges.");
        print("Calculating... ");

        int count = getSubgraphCount(g);

        if (count == 1) {
            println("graph is fully connected.");
        }else {
            println(count + " components found.");
        }

        // 3.2 - Shortest Path
        println("\n");
        Vertex from = findVertexByLabel(g, "Renyn");
        Vertex to = findVertexByLabel(g, "Parses");

        Dijkstra regular = new Dijkstra(g);
        Dijkstra weighted = new Dijkstra(g, true);

        runDijkstra(regular, from, to);
        runDijkstra(weighted, from, to);
    }

    public static void runDijkstra(Dijkstra dij, Vertex from, Vertex to) {
        println("Shortest path " + (dij.isWeighted ? "WITH" : "WITHOUT") + " WEIGHTS from " + from + " to " + to + ":");
        Stack<Edge> path = dij.findPath(from, to);
        dij.printPath(path);
        println("\nWeight: " + dij.getWeight(to.id) + "\n");
    }

    public static void print(String s) {
        System.out.print(s);
    }

    public static void println(String s) {
        System.out.println(s);
    }

    public static int getSubgraphCount(Graph g) {
        Queue<Integer> vertexQueue = new Queue<Integer>();
        BinarySearch<Integer> bs = new BinarySearch<Integer>();
        ArrayList<Integer> visited = new ArrayList<Integer>();

        int pos;
        int result = 0;

        for (Vertex v : g.vertices()) {
            pos = bs.search(v.id, visited);
            if (pos >= 0) {
                continue;
            }

            vertexQueue.enqueue(v.id);
            while (vertexQueue.size() > 0) {
                int id = vertexQueue.dequeue();

                pos = bs.search(id, visited);
                if (pos >= 0) {
                    continue;
                }

                visited.add(-pos-1, id);

                for (Edge e : g.adj(id)) {
                    vertexQueue.enqueue(e.to);
                }
            }

            result++;
        }

        return result;
    }

    public static Vertex findVertexByLabel(Graph g, String label) {
        Vertex result = null;
        for (Vertex v : g.vertices()) {
            if (v.label.equals(label)) {
                result = v;
                break;
            }
        }

        if (result == null) {
            throw new IllegalArgumentException("Could not find a vertex with label \"" + label + "\" within the graph.");
        }

        return result;
    }
}


