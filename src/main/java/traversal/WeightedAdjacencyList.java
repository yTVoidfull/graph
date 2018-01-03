package traversal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class WeightedAdjacencyList<N> extends AdjacencyList<N> implements WeightedTraversableGraph<N> {

    private Map<Edge<N>, Double> edgeWeights;
    private Map<N, Double> nodeWeights;

    public WeightedAdjacencyList() {
        super();
        this.edgeWeights = new HashMap<>();
        this.nodeWeights = new HashMap<>();
    }

    public boolean addEdge(N from, N to, Double weight){
        Edge<N> newEdge = new Edge<>(from, to);

        if(!getEdges().contains(newEdge)){
            getOutNeighbors(from).add(to);
            getEdges().add(newEdge);
            edgeWeights.put(newEdge, weight);
            return true;
        }

        return false;
    }

    public Double getEdgeWeight(N from, N to){
        return edgeWeights.get(new Edge<>(from, to));
    }
    public List<N> getDijkstraSearchPath(N start, N end){
        setNodeWeightsToInfinity();
        return new DijkstraSearch<>(start, end, this).invoke();
    }

    public Double getNodeWeight(N node){
        return nodeWeights.get(node);
    }

    private void setNodeWeightsToInfinity(){
        for(N node : getNodes()){
            nodeWeights.put(node, Double.POSITIVE_INFINITY);
        }
    }

    public void setNodeWeight(N node, Double weight){
        nodeWeights.put(node, weight);
    }

    public List<N> getAStarSearchPath(N start, N end, BiFunction<N, N, Double> distanceApproximator){
        setNodeWeightsToInfinity();
        return new AStarSearch<>(start, end, this, distanceApproximator).invoke();
    }

}
