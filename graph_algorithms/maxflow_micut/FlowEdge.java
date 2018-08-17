/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph_algorithms.maxflow_micut;

/**
 * Flow Edge data type represents basic Directed Network Graph Edge which can
 * contain Flow and capacity in it, member functions provide a way to modify the
 * flow through the Edge.
 *
 * @author manishjoshi394
 */
public class FlowEdge {

    private final int v;                // one vertex of the edge
    private final int w;                // the other vertex of the edge
    private final double capacity;      // the capacity of the flow edge
    private double flow;                // flow

    /**
     * Initializes an edge from vertex {@code v} to vertex {@code w} with given
     * {@code capacity}.
     *
     * @param v one vertex of the edge
     * @param w the other vertex of the edge
     * @param capacity the capacity of the flow edge
     */
    public FlowEdge(int v, int w, double capacity) {
        if (v < 0) {
            throw new IllegalArgumentException("Vertex index must be a Non-negative Integer");
        }
        if (w < 0) {
            throw new IllegalArgumentException("Vertex index must be a Non-negative Integer");
        }
        if (!(capacity >= 0.0)) {
            throw new IllegalArgumentException("Capacity should be a Non-negative Integer");
        }
        this.v = v;
        this.w = w;
        this.capacity = capacity;
        this.flow = 0.0;
    }

    /**
     * Initializes an edge from vertex {@code v} to vertex {@code w} with given
     * {@code capacity} AND given {@code flow}.
     *
     * @param v the tail vertex
     * @param w the head vertex
     * @param capacity the capacity of the edge
     * @param flow the Flow in the forward edge
     */
    public FlowEdge(int v, int w, double capacity, double flow) {
        this(v, w, capacity);       // initialize other fields
        if (!(flow >= 0.0)) {
            throw new IllegalArgumentException("Flow must be Non-Negative");
        }
        this.flow = flow;
    }

    /**
     * Returns the vertex this edge points from.
     *
     * @return the vertex this edge points from
     */
    public int from() {
        return v;
    }

    /**
     * Returns the vertex this edge points to,
     *
     * @return the vertex this edge points to
     */
    public int to() {
        return w;
    }

    /**
     * Returns the vertex other than given vertex.
     *
     * @param vertex the vertex provided
     * @return the vertex other than given vertex
     */
    public int other(int vertex) {
        if (vertex == this.v) {
            return w;
        } else if (vertex == this.w) {
            return v;
        } else {
            throw new IllegalArgumentException("Inconsistent Vertex");
        }
    }

    /**
     * Returns the capacity of the FLow Edge.
     *
     * @return the capacity of the Flow Edge
     */
    public double capacity() {
        return capacity;
    }

    /**
     * Returns the flow in the Flow Edge.
     *
     * @return the flow in the Flow Edge
     */
    public double flow() {
        return flow;
    }

    /**
     * Returns residual Capacity towards the vertex.
     *
     * @param vertex the vertex
     * @return residual Capacity towards the vertex
     */
    public double residualCapacityTo(int vertex) {
        if (vertex == v) {
            return flow;
        } else if (vertex == w) {
            return capacity - flow;
        } else {
            throw new java.lang.IllegalArgumentException("Inconsistent Edge");
        }
    }

    /**
     * Adds Flow to the Edge in direction of given Vertex.
     *
     * @param vertex one End-point of the Edge
     * @param delta amount by which to increase the flow
     * @throws IllegalArgumentException if vertex is Inconsistent
     */
    public void addResidualFlowTo(int vertex, double delta) {
        if (!(delta >= 0.0)) {
            throw new IllegalArgumentException("Delta must be non negative");
        }

        if (vertex == v) {
            flow -= delta;
        } else if (vertex == w) {
            flow += delta;
        } else {
            throw new IllegalArgumentException("Inconsistent edge");
        }
    }

    /**
     * Returns the string representation of the Edge.
     *
     * @return the string representation of the Edge
     */
    @Override
    public String toString() {
        return String.format("%d->%d %.2f/%.2f", v, w, flow, capacity);
    }

    // for unit testing of the class
    public static void main(String[] args) {
        FlowEdge e = new FlowEdge(0, 1, 10);
        System.out.println(e.other(0));
    }
}
