package traversal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DijkstraSearchTest {

    private WeightedAdjacencyList<GeographicPoint> list;

    @Before
    public void createAdjacencyList(){
        list = new WeightedAdjacencyList<>();
    }

    @Test
    public void dijkstraWillResultInAPath() throws Exception {
        GeographicPoint point1 = new GeographicPoint(1.00, 1.00);
        GeographicPoint point2 = new GeographicPoint(2.00, 2.00);
        GeographicPoint point3 = new GeographicPoint(3.00, 3.00);


        list.addNode(point1);
        list.addNode(point2);
        list.addNode(point3);

        list.addEdge(point1, point2, 1.00);
        list.addEdge(point2, point3, 0.00);
        list.addEdge(point1, point3, 1.50);

        List<GeographicPoint> path = list.getDijkstraSearchPath(point1, point3);
        Assert.assertEquals(point1, path.get(0));
        Assert.assertEquals(point2, path.get(1));
        Assert.assertEquals(point3, path.get(2));
    }

    @Test
    public void dijkstraWillFindShortestPath() throws Exception {
        GeographicPoint point1 = new GeographicPoint(1.00, 1.00);
        GeographicPoint point2 = new GeographicPoint(2.00, 2.00);
        GeographicPoint point3 = new GeographicPoint(3.00, 3.00);


        list.addNode(point1);
        list.addNode(point2);
        list.addNode(point3);

        list.addEdge(point1, point2, 1.00);
        list.addEdge(point2, point3, 1.00);
        list.addEdge(point1, point3, 2.00);

        List<GeographicPoint> path = list.getDijkstraSearchPath(point1, point3);
        Assert.assertEquals(point1, path.get(0));
        Assert.assertEquals(point3, path.get(1));
    }

}
