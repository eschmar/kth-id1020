package io.eschmann;

/**
 * Created by eschmar on 05/12/16.
 */
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
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

        // 3.1 - Connected Graph
        StdOut.println("");
        StdOut.println("This graph has " + g.numberOfVertices() + " vertices and " + g.numberOfEdges() + " edges.");
        StdOut.print("Calculating... ");

        DepthFirstSearch search = new DepthFirstSearch(g);
        int count = search.getComponentCount();

        if (count == 1) {
            StdOut.println("graph is fully connected.");
        }else if (count > 1){
            StdOut.println(count + " components found.");
        }else {
            StdOut.println("Whoops - something went wrong. (" + count + ") is not a valid result.");
        }

        // 3.2 - Shortest Path
        StdOut.println("\n");
        Vertex from = findVertexByLabel(g, "Renyn");
        Vertex to = findVertexByLabel(g, "Parses");

        Dijkstra regular = new Dijkstra(g);
        Dijkstra weighted = new Dijkstra(g, true);

        runDijkstra(regular, from, to);
        runDijkstra(weighted, from, to);
    }

    public static void runDijkstra(Dijkstra dij, Vertex from, Vertex to) {
        StdOut.println("Shortest path " + (dij.isWeighted ? "WITH" : "WITHOUT") + " WEIGHTS from " + from + " to " + to + ":");
        Stack<Edge> path = dij.findPath(from, to);
        dij.printPath(path);
        StdOut.println("\nWeight: " + dij.getWeight(to.id) + "\n");
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


