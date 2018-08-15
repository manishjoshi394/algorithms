/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph_algorithms.maxflow_micut;

import edu.princeton.cs.algs4.Bag;

/**
 * Typical Flow Network API.
 *
 * @author manishjoshi394
 */
public class FlowNetwork {

    private final int V;            // the number of the vertices
    private int E;                  // the number of the Edges
    private Bag<FlowEdge>[] adj;    // the adjacency list

    /**
     * Empty V-vertex flow vertex.
     *
     * @param V the number of vertices
     */
    public FlowNetwork(int V) {
        this.E = 0;
        this.V = V;

        // intitalize adjacency list
        adj = new Bag[V];
        for (int v = 0; v < V; ++v) {
            adj[v] = new Bag<>();
        }
    }

    // throws an IllegalArgumentException unless 0 <= v < V
    private void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= V) {
            throw new IllegalArgumentException("Vertex " + vertex + " is NOT between 0 and " + V);
        }
    }

    /**
     * Returns the number of vertices.
     *
     * @return the number of vertices
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of Edges.
     *
     * @return the number of Edges
     */
    public int E() {
        return E;
    }

    /**
     * Adds an Edge to the FlowNetwork.
     *
     * @param e the FlowEdge to be added
     */
    public void addEdge(FlowEdge e) {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    /**
     * Returns the edges pointing from v.
     *
     * @param v the tail vertex
     * @return the edges pointing from v
     */
    Iterable<FlowEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }
}
