package traversal;

import java.util.*;
import java.util.function.BiFunction;

public class AStarSearch<N> {

    private N start;
    private N end;
    private WeightedTraversableGraph<N> graph;
    private PriorityQueue<N> nodesToExplore;
    private Map<N, N> previousNodes;
    private Set<N> visited;
    private BiFunction<N, N, Double> distanceApproximator;

    private Comparator<N> comparator = (one, other) -> {
        if (graph.getNodeWeight(one) > graph.getNodeWeight(other)) {
            return 1;
        } else if (graph.getNodeWeight(one).equals(graph.getNodeWeight(other))) {
            return 0;
        }
        return -1;
    };

    public AStarSearch(N start, N end, WeightedTraversableGraph<N> graph, BiFunction<N, N, Double> distanceApproximator) {
        this.start = start;
        this.end = end;
        this.graph = graph;
        nodesToExplore = new PriorityQueue<>(comparator);
        previousNodes = new HashMap<>();
        visited = new HashSet<>();
        this.distanceApproximator = distanceApproximator;
    }

    public List<N> invoke() {

        nodesToExplore.add(start);
        visited.add(start);
        graph.setNodeWeight(start, 0.0);

        while (!nodesToExplore.isEmpty()) {
            N currentNode = nodesToExplore.poll();
            visited.add(currentNode);
            Double currentWeight = graph.getNodeWeight(currentNode);

            List<N> neighbors = graph.getOutNeighbors(currentNode);

            if (currentNode.equals(end)) {
                return generatePath();
            }

            checkNodesToBeVisited(currentNode, neighbors, currentWeight);
        }

        return null;
    }

    private List<N> generatePath() {
        List<N> output = new LinkedList<>();
        N current = end;
        output.add(current);

        while (!current.equals(start)) {
            current = previousNodes.get(current);
            output.add(current);
        }

        Collections.reverse(output);
        return output;
    }

    private void checkNodesToBeVisited(N node, List<N> neighbors, double currentWeight) {
        for (N n : neighbors) {
            if (!visited.contains(n)) {
                Double newWeight = currentWeight + graph.getEdgeWeight(node, n) + distanceApproximator.apply(n, end);
                if(graph.getNodeWeight(n) > newWeight){
                    previousNodes.put(n, node);
                    graph.setNodeWeight(n, newWeight);
                    nodesToExplore.add(n);
                }
            }
        }
    }


}
