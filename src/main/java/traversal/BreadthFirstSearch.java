package traversal;

import java.util.*;

public class BreadthFirstSearch<N> {

    private N start;
    private N end;
    private TraversableGraph<N> graph;
    private Queue<N> nodesToExplore;
    private Map<N, N> previousNodes;
    private Set<N> visited;


    public BreadthFirstSearch(N start, N end, TraversableGraph<N> graph) {
        this.start = start;
        this.end = end;
        this.graph = graph;
        this.nodesToExplore = new LinkedList<>();
        this.previousNodes = new HashMap<>();
        this.visited = new HashSet<>();
    }

    public List<N> invoke() {
        visitAndExplore(start);

        while (!nodesToExplore.isEmpty()) {
            N current = nodesToExplore.poll();
            List<N> neighbors = graph.getOutNeighbors(current);

            if(current.equals(end)){
                return generatePath();
            }

            checkNodesToBeVisited(current, neighbors);
        }
        return null;
    }

    private List<N> generatePath(){
        List<N> output = new LinkedList<>();
        N current = end;
        output.add(current);

        while (!current.equals(start)){
            current = previousNodes.get(current);
            output.add(current);
        }

        Collections.reverse(output);
        return output;
    }

    private void visitAndExplore(N node){
        nodesToExplore.add(node);
        visited.add(node);
    }

    private void checkNodesToBeVisited(N node, List<N> neighbors){
        for (N n : neighbors) {
            if (!visited.contains(n)) {
                visitAndExplore(n);
                previousNodes.put(n, node);
            }
        }
    }
}
