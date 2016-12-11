package io.eschmann;

import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;

/**
 * Created by eschmar on 10/12/16.
 */
public class DepthFirstSearch {
    Graph g;
    protected boolean[] visited;
    public int componentCount;

    public DepthFirstSearch(Graph g) {
        this.g = g;
        this.componentCount = 0;

        this.visited = new boolean[g.numberOfVertices()];
        for (Vertex v : g.vertices()) {
            if (this.visited[v.id]) {
                continue;
            }

            this.traverseAndMark(v);
            this.componentCount++;
        }
    }

    protected void traverseAndMark(Vertex vertex) {
        this.visited[vertex.id] = true;
        for (Edge e : g.adj(vertex.id)) {
            if (this.visited[e.to]) {
                continue;
            }

            this.traverseAndMark(g.vertex(e.to));
        }
    }

    public int getComponentCount() {
        return this.componentCount;
    }
}
