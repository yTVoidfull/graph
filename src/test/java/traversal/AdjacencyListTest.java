package traversal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AdjacencyListTest {

    private AdjacencyList<GeographicPoint> list;

    @Before
    public void createAdjacencyList(){
        list = new AdjacencyList<>();
    }

    @Test
    public void edgesCanBeAdded() throws Exception {
        GeographicPoint point1 = new GeographicPoint(0.00, 1.00);
        GeographicPoint point2 = new GeographicPoint(1.00, 1.00);
        list.addNode(point1);
        list.addNode(point2);
        list.addEdge(point1, point2);

        Assert.assertEquals(list.getOutNeighbors(point1).size(), 1);
    }

    @Test
    public void theSameNodeCannotBeAddedTwice() throws Exception {
        list.addNode(new GeographicPoint(0.00, 1.00));
        Assert.assertEquals(list.addNode(new GeographicPoint(0.00, 1.00)), false);
    }

    @Test
    public void nodesAndEdgesAreProperlyCounted() throws Exception {
        GeographicPoint point1 = new GeographicPoint(0.00, 1.00);
        GeographicPoint point2 = new GeographicPoint(1.00, 1.00);
        GeographicPoint point3 = new GeographicPoint(0.00, 1.00);

        list.addNode(point1);
        list.addNode(point2);
        list.addNode(point3);
        list.addEdge(point1, point2);
        list.addEdge(new GeographicPoint(1.00, 1.00), point2);
        list.addEdge(point2, new GeographicPoint(1.00, 1.00));

        Assert.assertEquals(list.getNumberOfNodes(), 2);
        Assert.assertEquals(list.getNumberOfEdges(), 2);
    }

    @Test
    public void removingANodeWithSeveralEdges() throws Exception {
        GeographicPoint point1 = new GeographicPoint(0.00, 1.00);
        GeographicPoint point2 = new GeographicPoint(1.00, 1.00);

        list.addNode(point1);
        list.addNode(point2);
        list.addEdge(point1, point2);
        list.addEdge(point1, point1);

        list.removeNode(point1);
        Assert.assertEquals(list.getNumberOfEdges(), 0);
    }

    @Test
    public void sameEdgeCannotBeAddedTwice() throws Exception {
        GeographicPoint point1 = new GeographicPoint(0.00, 1.00);
        GeographicPoint point2 = new GeographicPoint(1.00, 1.00);

        list.addNode(point1);
        list.addNode(point2);
        list.addEdge(point1, point2);
        list.addEdge(point1, point2);

        Assert.assertEquals(list.getNumberOfEdges(), 1);
    }

    @Test
    public void canDeleteAnEdgeInTheGraph() throws Exception {
        GeographicPoint point1 = new GeographicPoint(0.00, 1.00);
        GeographicPoint point2 = new GeographicPoint(1.00, 1.00);

        list.addNode(point1);
        list.addNode(point2);
        list.addEdge(point1, point2);
        list.addEdge(point1, point1);
        list.removeEdge(point1, point1);


        Assert.assertEquals(list.getNumberOfEdges(), 1);
    }

    @Test
    public void checkOutAndInNeighbors() throws Exception {
        GeographicPoint point1 = new GeographicPoint(0.00, 1.00);
        GeographicPoint point2 = new GeographicPoint(1.00, 1.00);

        list.addNode(point1);
        list.addNode(point2);
        list.addEdge(point1, point2);
        list.addEdge(point1, point1);

        Assert.assertEquals(1, list.getInNeighbors(point2).size());
        Assert.assertEquals(1, list.getInNeighbors(point1).size());

        Assert.assertEquals(2, list.getOutNeighbors(point1).size());
        Assert.assertEquals(0, list.getOutNeighbors(point2).size());
    }

}
