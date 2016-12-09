package io.eschmann;

import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by eschmar on 08/12/16.
 */
public class Dijkstra {
    Graph g;
    boolean isWeighted;
    public static final int LINE_BREAK_AFTER = 8;

    double[] distance;
    Edge[] previous;

    public Dijkstra(Graph g) {
        this.g = g;
        this.isWeighted = false;
    }

    public Dijkstra(Graph g, boolean isWeighted) {
        this.g = g;
        this.isWeighted = isWeighted;
    }

    public Stack<Edge> findPath(Vertex from, Vertex to) {
        distance = new double[this.g.numberOfVertices()];
        previous = new Edge[this.g.numberOfVertices()];

        for (int i = 0; i < distance.length; i++) {
            distance[i] = Double.POSITIVE_INFINITY;
        }

        distance[from.id] = 0;
        VertexByWeightComparator comparator = new VertexByWeightComparator(distance);
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(comparator);
        queue.add(from);

        Vertex current = null;
        while (queue.size() > 0) {
            current = queue.poll();
            if (current.id == to.id) {
                break;
            }

            for (Edge e : g.adj(current.id)) {
                if (distance[e.to] == -1) continue;
                double alt = distance[current.id] + (this.isWeighted ? e.weight : 1);
                if (alt < distance[e.to]) {
                    distance[e.to] = alt;
                    previous[e.to] = e;
                    queue.add(g.vertex(e.to));
                }
            }

            distance[current.id] = -1;
        }

        if (current == null) {
            throw new IllegalArgumentException("There is no path between " + from + " and " + to + ".");
        }

        // backtrace
        Stack<Edge> path = new Stack<Edge>();
        while (previous[current.id] != null) {
            path.push(previous[current.id]);
            current = g.vertex(previous[current.id].from);
        }

        return path;
    }

    public double getWeight(int id) {
        return this.distance[id];
    }

    public void printPath(Stack<Edge> path) {
        int i = 1;
        StringBuilder out = new StringBuilder();

        System.out.print("Path: ");

        for (Edge current : path) {
            out.insert(0, " -> " + g.vertex(current.to));
            i++;
            if (i % LINE_BREAK_AFTER == 0) out.insert(0, "\n");
        }

        out.insert(0, g.vertex(path.lastElement().from));
        System.out.println(out.toString());
    }

    protected class VertexByWeightComparator implements Comparator<Vertex> {
        double[] distance;
        public VertexByWeightComparator(double[] distance) {
            this.distance = distance;
        }

        public int compare(Vertex a, Vertex b) {
            if (distance[a.id] < distance[b.id]) return -1;
            if (distance[a.id] == distance[b.id]) return 0;
            else return 1;
        }
    }
}