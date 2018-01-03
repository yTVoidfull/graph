package traversal;

public class Edge<N> {

    private N from;
    private N to;

    public Edge(N from, N to) {
        this.from = from;
        this.to = to;
    }

    public N getFrom() {
        return from;
    }

    public N getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge<?> edge = (Edge<?>) o;

        if (!from.equals(edge.from)) return false;
        return to.equals(edge.to);
    }

    @Override
    public int hashCode() {
        int result = from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }
}
