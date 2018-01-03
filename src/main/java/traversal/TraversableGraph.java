package traversal;

import java.util.List;

public interface TraversableGraph<N> {

    List<N> getOutNeighbors(N node);

    List<N> getInNeighbors(N node);

}
