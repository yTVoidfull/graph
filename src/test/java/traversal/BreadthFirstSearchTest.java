package traversal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class BreadthFirstSearchTest {


    private AdjacencyList<GeographicPoint> list;

    @Before
    public void createAdjacencyList(){
        list = new AdjacencyList<>();
    }

    @Test
    public void bfsWillResultInAPath() throws Exception {
        GeographicPoint point1 = new GeographicPoint(0.00, 1.00);
        GeographicPoint point2 = new GeographicPoint(1.00, 1.00);
        GeographicPoint point3 = new GeographicPoint(1.00, 2.00);


        list.addNode(point1);
        list.addNode(point2);
        list.addNode(point3);

        list.addEdge(point1, point2);
        list.addEdge(point1, point1);

        list.addEdge(point2, point1);
        list.addEdge(point2, point2);
        list.addEdge(point2, point3);

        List<GeographicPoint> path = list.getBreadthFirstSearch(point1, point3);
        Assert.assertEquals(point1, path.get(0));
        Assert.assertEquals(point2, path.get(1));
        Assert.assertEquals(point3, path.get(2));
    }

    @Test
    public void bfsWillFindEndInAForkedPath() throws Exception {
        GeographicPoint point1 = new GeographicPoint(0.00, 1.00);
        GeographicPoint point2 = new GeographicPoint(1.00, 1.00);
        GeographicPoint point3 = new GeographicPoint(1.00, 2.00);
        GeographicPoint point4 = new GeographicPoint(2.00, 2.00);

        list.addNode(point1);
        list.addNode(point2);
        list.addNode(point3);
        list.addNode(point4);

        list.addEdge(point1, point2);
        list.addEdge(point1, point1);

        list.addEdge(point2, point2);
        list.addEdge(point2, point3);
        list.addEdge(point2, point4);

        list.addEdge(point4, point3);

        List<GeographicPoint> path = list.getBreadthFirstSearch(point1, point3);
        Assert.assertEquals(point1, path.get(0));
        Assert.assertEquals(point2, path.get(1));
        Assert.assertEquals(point3, path.get(2));
    }

    @Test
    public void bfsWillFindEndInAComplexGraph() throws Exception {
        GeographicPoint point1 = new GeographicPoint(0.00, 1.00);
        GeographicPoint point2 = new GeographicPoint(1.00, 1.00);
        GeographicPoint point3 = new GeographicPoint(1.00, 2.00);
        GeographicPoint point4 = new GeographicPoint(2.00, 2.00);
        GeographicPoint point5 = new GeographicPoint(2.00, 3.00);

        list.addNode(point1);
        list.addNode(point2);
        list.addNode(point3);
        list.addNode(point4);
        list.addNode(point5);

        list.addEdge(point1, point2);
        list.addEdge(point1, point5);

        list.addEdge(point2, point5);

        list.addEdge(point4, point3);

        list.addEdge(point5, point4);

        List<GeographicPoint> path = list.getBreadthFirstSearch(point1, point3);
        Assert.assertEquals(4, path.size());
        Assert.assertEquals(point1, path.get(0));
        Assert.assertEquals(point5, path.get(1));
        Assert.assertEquals(point4, path.get(2));
        Assert.assertEquals(point3, path.get(3));
    }

}
