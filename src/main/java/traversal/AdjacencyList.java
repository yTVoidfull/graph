package traversal;

import java.util.*;
import java.util.function.BiFunction;

public class AdjacencyList<N> implements TraversableGraph<N>{

    private Map<N, List<N>> adjacencyList;
    private Set<N> nodes;
    private Set<Edge<N>> edges;

    public AdjacencyList() {
        this.adjacencyList = new HashMap<>();
        this.nodes = new HashSet<>();
        this.edges = new HashSet<>();
    }

    public boolean addEdge(N from, N to) {
        Edge<N> newEdge = new Edge<>(from, to);

        if(!edges.contains(newEdge)){
            getOutNeighbors(from).add(to);
            edges.add(newEdge);
            return true;
        }
        return false;
    }

    public List<N> getOutNeighbors(N node) {
        return adjacencyList.get(node);
    }

    public List<N> getInNeighbors(N node){
        List<N> output = new LinkedList<>();
        for(N n : nodes){
            if(getOutNeighbors(n).contains(node)){
                output.add(n);
            }
        }
        return output;
    }

    public boolean addNode(N node) {
        if(!nodes.contains(node)){
            adjacencyList.put(node, new LinkedList<>());
            nodes.add(node);
            return true;
        }

        return false;
    }

    public int getNumberOfNodes() {
        return nodes.size();
    }

    public int getNumberOfEdges() {
        return edges.size();
    }

    public boolean removeNode(N node) {
        if(nodes.contains(node)){
            removeEdgesInAndOutOfTheNode(node);
            removeNodeFromNeighborsList(node);

            adjacencyList.remove(node);
            nodes.remove(node);
            return true;
        }
        return false;
    }

    private void removeNodeFromNeighborsList(N node) {
        for (N n : nodes){
            getOutNeighbors(n).remove(node);
        }
    }

    private void removeEdgesInAndOutOfTheNode(N node) {
        edges.removeIf(e -> e.getFrom().equals(node)
                || e.getTo().equals(node));
    }

    public List<N> getDepthFirstSearchPath(N start, N end){
        return new DepthFirstSearch<>(start, end, this).invoke();
    }

    public boolean removeEdge(N from, N to) {
        Edge<N> newEdge = new Edge<>(from, to);
        if(edges.contains(newEdge)){
            getOutNeighbors(from).remove(to);
            edges.remove(newEdge);
            return true;
        }
        return false;
    }

    public List<N> getBreadthFirstSearch(N start, N end) {
        return new BreadthFirstSearch<>(start, end, this).invoke();
    }

    public Set<N> getNodes(){
        return nodes;
    }

    public Set<Edge<N>> getEdges(){return edges;}

}
