package io.eschmann;

/**
 * Created by eschmar on 05/12/16.
 */
import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.DataSource;
import se.kth.id1020.Vertex;

import java.util.ArrayList;

public class Paths {
    public static void main(String[] args) {
        Graph g = DataSource.load();
        // work on g

        // 3.1 - Connected Graph
        println("");
        println("This graph has " + g.numberOfVertices() + " vertices and " + g.numberOfEdges() + " edges.");
        print("Calculating... ");
        int degreeSum = getSumOfDegrees(g);
        print("done. \n\n");

        int numberOfEdges = (int) (degreeSum / 2);
        println("The sum of degrees of all vertices connected to " + g.vertex(0) + " is " + degreeSum + ".");
        println(degreeSum + " / 2 = " + numberOfEdges);

        if (g.numberOfEdges() > (degreeSum / 2)) {
            println(g.numberOfEdges() + " > " + numberOfEdges + " --> graph is NOT connected.");
        }else if (g.numberOfEdges() == (degreeSum / 2)) {
            println(g.numberOfEdges() + " == " + numberOfEdges + " --> graph IS connected.");
        }else {
            println("Oops, something went wrong!");
        }

        // 3.2 - Shortest Path
        // todo
    }

    public static void print(String s) {
        System.out.print(s);
    }

    public static void println(String s) {
        System.out.println(s);
    }

    public static int getSumOfDegrees(Graph g) {
        Queue<Integer> vertexQueue = new Queue<Integer>();
        BinarySearch<Integer> bs = new BinarySearch<Integer>();

        ArrayList<Integer> visited = new ArrayList<Integer>();
        vertexQueue.enqueue(g.vertex(0).id);
        int degreeCount = 0;

        while (vertexQueue.size() > 0) {
            int id = vertexQueue.dequeue();

            int pos = bs.search(id, visited);
            if (pos >= 0) {
                continue;
            }

            visited.add(-pos-1, id);

            for (Edge e : g.adj(id)) {
                degreeCount++;
                vertexQueue.enqueue(e.to);
            }
        }

        return degreeCount;
    }
}
