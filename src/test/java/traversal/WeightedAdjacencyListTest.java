package traversal;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class WeightedAdjacencyListTest {


    WeightedAdjacencyList<GeographicPoint> weightedList;

    @Before
    public void setUp(){
        weightedList = new WeightedAdjacencyList<>();
    }


    @Test
    public void addWeightedEdge() throws Exception {
        GeographicPoint point1 = new GeographicPoint(1.00, 1.00);
        GeographicPoint point2 = new GeographicPoint(2.00, 2.00);

        weightedList.addNode(point1);
        weightedList.addNode(point2);
        weightedList.addEdge(point1, point2, 1.00);

        assertThat(weightedList.getEdges().size()).isEqualTo(1);
    }
}
