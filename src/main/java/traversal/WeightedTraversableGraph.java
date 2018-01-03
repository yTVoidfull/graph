package traversal;

public interface WeightedTraversableGraph<N> extends TraversableGraph<N> {

    Double getEdgeWeight(N from, N to);

    Double getNodeWeight(N node);

    void setNodeWeight(N node, Double weight);

}
