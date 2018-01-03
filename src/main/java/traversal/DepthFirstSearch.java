package traversal;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DepthFirstSearch<N> {
    private N start;
    private N end;
    private TraversableGraph<N> graph;
    private Stack<N> nodesToExplore;
    private Stack<N> path;
    private Set<N> visited;

    public DepthFirstSearch(N start, N end, TraversableGraph<N> graph) {
        this.start = start;
        this.end = end;
        this.graph = graph;
        this.nodesToExplore = new Stack<>();
        this.path = new Stack<>();
        this.visited = new HashSet<>();
    }

    public List<N> invoke() {
        visitAndExplore(start);

        while (!nodesToExplore.empty()) {
            N current = nodesToExplore.pop();
            List<N> neighbors = graph.getOutNeighbors(current);

            updatePath(current, neighbors);

            if (current.equals(end)) {
                return path;
            }

            checkNodesToBeVisited(neighbors);
        }
        return null;
    }

    private void updatePath(N node, List<N> neighbors) {
        if(neighbors.size() > 0
            || node.equals(end)){
            path.add(node);
        }
    }

    private void visitAndExplore(N node){
        nodesToExplore.add(node);
        visited.add(node);
    }

    private void checkNodesToBeVisited(List<N> neighbors){
        for (N n : neighbors) {
            if (!visited.contains(n)) {
                visitAndExplore(n);
            }
        }
    }
}


